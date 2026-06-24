package pago;

public interface TarjetaDeCreditoAPI {
	//proposito: Implementar una api para las tarjetas de credito
	
	boolean validarDatos(String numeroDeTarjeta, String cvv, String vencimiento);
	boolean reservarFondos(double monto);
	boolean ejecutarTransaccion(); 
}
