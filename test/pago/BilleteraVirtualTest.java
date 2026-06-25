package pago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BilleteraVirtualTest {

    private BilleteraVirtualAPI apiMock;
    private BilleteraVirtual billetera;
    private final double MONTO = 5000.0;

    @BeforeEach
    public void setUp() {
        apiMock = mock(BilleteraVirtualAPI.class);
        billetera = new BilleteraVirtual(apiMock, MONTO);
    }

    //happy path

    @Test
    public void testProcesarPagoExitoso() {
        when(apiMock.validarSaldo(MONTO)).thenReturn(true);
        when(apiMock.bloquearSaldo(MONTO)).thenReturn(true);
        when(apiMock.confirmarAcreditacion()).thenReturn(true);
        when(apiMock.enviarNotificacionPush()).thenReturn(true);

        billetera.procesarPago();

        verify(apiMock).validarSaldo(MONTO);
        verify(apiMock).bloquearSaldo(MONTO);
        verify(apiMock).confirmarAcreditacion();
        verify(apiMock).enviarNotificacionPush();
    }

    //sad path progresivo

    @Test
    public void testSaldoInsuficienteYLanzaExcepcion() {
        when(apiMock.validarSaldo(MONTO)).thenReturn(false);

        assertThrows(PagoException.class, () -> billetera.procesarPago());

        //verificar que no avance
        verify(apiMock, never()).bloquearSaldo(anyDouble());
        verify(apiMock, never()).confirmarAcreditacion();
    }

    @Test
    public void testNoSePudoBloquearSaldoYLanzaExcepcion() {
        when(apiMock.validarSaldo(MONTO)).thenReturn(true);
        when(apiMock.bloquearSaldo(MONTO)).thenReturn(false);

        assertThrows(PagoException.class, () -> billetera.procesarPago());

        verify(apiMock, never()).confirmarAcreditacion();
    }

    @Test
    public void testAcreditacionFallaYLanzaExcepcion() {
        when(apiMock.validarSaldo(MONTO)).thenReturn(true);
        when(apiMock.bloquearSaldo(MONTO)).thenReturn(true);
        when(apiMock.confirmarAcreditacion()).thenReturn(false);

        assertThrows(PagoException.class, () -> billetera.procesarPago());

        verify(apiMock, never()).enviarNotificacionPush();
    }

    //faltantes

    @Test
    public void testValidarDatosLlamaApiConMontoCorrecto() {
        when(apiMock.validarSaldo(MONTO)).thenReturn(true);
        when(apiMock.bloquearSaldo(MONTO)).thenReturn(true);
        when(apiMock.confirmarAcreditacion()).thenReturn(true);

        billetera.procesarPago();

        verify(apiMock).validarSaldo(MONTO);
    }

    @Test
    public void testReservarFondosLlamaApiConMontoCorrecto() {
        when(apiMock.validarSaldo(MONTO)).thenReturn(true);
        when(apiMock.bloquearSaldo(MONTO)).thenReturn(true);
        when(apiMock.confirmarAcreditacion()).thenReturn(true);

        billetera.procesarPago();

        verify(apiMock).bloquearSaldo(MONTO);
    }
}
