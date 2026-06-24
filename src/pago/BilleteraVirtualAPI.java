package pago;

public interface BilleteraVirtualAPI {
	//proposito: Implementar una api para las billeteras virtuales 
	
	 boolean validarSaldo(double monto); //validarDatos
	 boolean bloquearSaldo(double monto);//reservarFondos
	 boolean confirmarAcreditacion();//ejecutarTransaccion
	 boolean enviarNotificacionPush(); //notificarResultado
}
