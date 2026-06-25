package pago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferenciaBancariaTest {

    private TransferenciaBancariaAPI apiMock;
    private TransferenciaBancaria transferencia;

    private final double MONTO = 8000.0;
    private final String CBU = "0000003100012345678901"; 
    private final String ALIAS = "MI.ALIAS.BANCO";

    @BeforeEach
    public void setUp() {
        apiMock = mock(TransferenciaBancariaAPI.class);
        transferencia = new TransferenciaBancaria(apiMock, CBU, ALIAS, MONTO);
    }

    // happy path

    @Test
    public void testProcesarPagoExitoso() {
        when(apiMock.validarCBU(CBU, ALIAS)).thenReturn(true);
        when(apiMock.ejecutarTransferencia(MONTO)).thenReturn(true);

        transferencia.procesarPago();

        verify(apiMock).validarCBU(CBU, ALIAS);
        verify(apiMock).ejecutarTransferencia(MONTO);
    }

    // sad path progresivo

    @Test
    public void testCBUInvalidoLanzaExcepcion() {
        when(apiMock.validarCBU(CBU, ALIAS)).thenReturn(false);

        assertThrows(PagoException.class, () -> transferencia.procesarPago());

        // verificar que no avance
        verify(apiMock, never()).ejecutarTransferencia(anyDouble());
    }

    @Test
    public void testTransferenciaFallaLanzaExcepcion() {
        when(apiMock.validarCBU(CBU, ALIAS)).thenReturn(true);
        when(apiMock.ejecutarTransferencia(MONTO)).thenReturn(false);

        assertThrows(PagoException.class, () -> transferencia.procesarPago());
    }

    // reservarFondos no aplica en transferencia

    @Test
    public void testReservarFondosNoInteracturaConApi() {
        when(apiMock.validarCBU(CBU, ALIAS)).thenReturn(true);
        when(apiMock.ejecutarTransferencia(MONTO)).thenReturn(true);

        transferencia.procesarPago();

        
        verify(apiMock, times(1)).validarCBU(CBU, ALIAS);
        verify(apiMock, times(1)).ejecutarTransferencia(MONTO);
        verifyNoMoreInteractions(apiMock);
    }

    // notificarResultado: comprobante CBU con código de transacción

    @Test
    public void testComprobanteGeneradoTrasTransaccionExitosa() {
        when(apiMock.validarCBU(CBU, ALIAS)).thenReturn(true);
        when(apiMock.ejecutarTransferencia(MONTO)).thenReturn(true);

        transferencia.procesarPago();

        String comprobante = transferencia.getComprobante();
        assertNotNull(comprobante);
        assertTrue(comprobante.contains("TXN-"));
    }

    @Test
    public void testComprobanteEsNuloSiPagoNoSeProceso() {
        // sin llamar a procesarPago(), el comprobante nunca se genera
        assertNull(transferencia.getComprobante());
    }

    @Test
    public void testComprobanteNoSeGeneraSiTransferenciaFalla() {
        when(apiMock.validarCBU(CBU, ALIAS)).thenReturn(true);
        when(apiMock.ejecutarTransferencia(MONTO)).thenReturn(false);

        assertThrows(PagoException.class, () -> transferencia.procesarPago());

        assertNull(transferencia.getComprobante());
    }

    // validaciones de parámetros correctos

    @Test
    public void testValidarDatosLlamaApiConCBUYAliasCorrectos() {
        when(apiMock.validarCBU(CBU, ALIAS)).thenReturn(true);
        when(apiMock.ejecutarTransferencia(MONTO)).thenReturn(true);

        transferencia.procesarPago();

        verify(apiMock).validarCBU(CBU, ALIAS);
    }

    @Test
    public void testEjecutarTransferenciaLlamaApiConMontoCorrector() {
        when(apiMock.validarCBU(CBU, ALIAS)).thenReturn(true);
        when(apiMock.ejecutarTransferencia(MONTO)).thenReturn(true);

        transferencia.procesarPago();

        verify(apiMock).ejecutarTransferencia(MONTO);
    }
}