package pago;

public interface TransferenciaBancariaAPI {
	//proposito: Implementar una api para las transferencias bancarias
	
	boolean validarCBU(String cbu, String alias); //validarDatos
	boolean ejecutarTransferencia(double monto);  //ejecutarTransaccion
}
