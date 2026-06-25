package pago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TarjetaDeCreditoTest {

    private TarjetaDeCreditoAPI apiMock;
    private TarjetaDeCredito tarjeta;

    private final double MONTO = 10000.0;
    private final String NUMERO = "4111111111111111";
    private final String CVV = "123";
    private final String VENCIMIENTO = "12/26";

    @BeforeEach
    public void setUp() {
        apiMock = mock(TarjetaDeCreditoAPI.class);
        tarjeta = new TarjetaDeCredito(apiMock, NUMERO, CVV, VENCIMIENTO, MONTO);
    }

    // happy path

    @Test
    public void testProcesarPagoExitoso() {
        when(apiMock.validarDatos(NUMERO, CVV, VENCIMIENTO)).thenReturn(true);
        when(apiMock.reservarFondos(MONTO)).thenReturn(true);
        when(apiMock.ejecutarTransaccion()).thenReturn(true);

        tarjeta.procesarPago();

        verify(apiMock).validarDatos(NUMERO, CVV, VENCIMIENTO);
        verify(apiMock).reservarFondos(MONTO);
        verify(apiMock).ejecutarTransaccion();
    }

    // sad path progresivo

    @Test
    public void testDatosInvalidosLanzaExcepcion() {
        when(apiMock.validarDatos(NUMERO, CVV, VENCIMIENTO)).thenReturn(false);

        assertThrows(PagoException.class, () -> tarjeta.procesarPago());

        // verificar que no avance
        verify(apiMock, never()).reservarFondos(anyDouble());
        verify(apiMock, never()).ejecutarTransaccion();
    }

    @Test
    public void testPreAutorizacionFallaLanzaExcepcion() {
        when(apiMock.validarDatos(NUMERO, CVV, VENCIMIENTO)).thenReturn(true);
        when(apiMock.reservarFondos(MONTO)).thenReturn(false);

        assertThrows(PagoException.class, () -> tarjeta.procesarPago());

        verify(apiMock, never()).ejecutarTransaccion();
    }

    @Test
    public void testTransaccionFallaLanzaExcepcion() {
        when(apiMock.validarDatos(NUMERO, CVV, VENCIMIENTO)).thenReturn(true);
        when(apiMock.reservarFondos(MONTO)).thenReturn(true);
        when(apiMock.ejecutarTransaccion()).thenReturn(false);

        assertThrows(PagoException.class, () -> tarjeta.procesarPago());
    }

    // notificarResultado: cupón generado con código de transacción

    @Test
    public void testCuponGeneradoTrasTransaccionExitosa() {
        when(apiMock.validarDatos(NUMERO, CVV, VENCIMIENTO)).thenReturn(true);
        when(apiMock.reservarFondos(MONTO)).thenReturn(true);
        when(apiMock.ejecutarTransaccion()).thenReturn(true);

        tarjeta.procesarPago();

        String cupon = tarjeta.getComprobante();
        assertNotNull(cupon);
        assertTrue(cupon.contains("TXN-"));
    }

    @Test
    public void testCuponEsNuloSiPagoNoSeProceso() {
        // sin llamar a procesarPago(), el cupón nunca se genera
        assertNull(tarjeta.getComprobante());
    }

    @Test
    public void testCuponNoSeGeneraSiTransaccionFalla() {
        when(apiMock.validarDatos(NUMERO, CVV, VENCIMIENTO)).thenReturn(true);
        when(apiMock.reservarFondos(MONTO)).thenReturn(true);
        when(apiMock.ejecutarTransaccion()).thenReturn(false);

        assertThrows(PagoException.class, () -> tarjeta.procesarPago());

        assertNull(tarjeta.getComprobante());
    }

    // validaciones de parámetros correctos

    @Test
    public void testValidarDatosLlamaApiConParametrosCorrectos() {
        when(apiMock.validarDatos(NUMERO, CVV, VENCIMIENTO)).thenReturn(true);
        when(apiMock.reservarFondos(MONTO)).thenReturn(true);
        when(apiMock.ejecutarTransaccion()).thenReturn(true);

        tarjeta.procesarPago();

        verify(apiMock).validarDatos(NUMERO, CVV, VENCIMIENTO);
    }

    @Test
    public void testReservarFondosLlamaApiConMontoCorrector() {
        when(apiMock.validarDatos(NUMERO, CVV, VENCIMIENTO)).thenReturn(true);
        when(apiMock.reservarFondos(MONTO)).thenReturn(true);
        when(apiMock.ejecutarTransaccion()).thenReturn(true);

        tarjeta.procesarPago();

        verify(apiMock).reservarFondos(MONTO);
    }
}