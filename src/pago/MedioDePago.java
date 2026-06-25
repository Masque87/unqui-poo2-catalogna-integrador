package pago;

public abstract class MedioDePago {
//Proposito: Modelar procedimientos comunes entre distintos medios de pago
	protected double monto;
	
	protected MedioDePago(double monto) {
        this.monto = monto;
    }
	public final void procesarPago() {
        validarDatos();
        reservarFondos();
        ejecutarTransaccion();
        notificarResultado();
    }
	
	 protected abstract void validarDatos();
	 protected abstract void reservarFondos();
	 protected abstract void ejecutarTransaccion();
	 protected void notificarResultado() {
	        System.out.println("Transacción registrada con código: " + generarCodigoTransaccion());
	    }
	 protected String generarCodigoTransaccion() {
	        return "TXN-" + System.currentTimeMillis();
	    }
	 
	 protected void validarResultado(boolean resultado, String mensajeError) {
		 //patron para validar un resultado
		    if (!resultado) {
		        throw new PagoException(mensajeError);
		    }
	 }
}
