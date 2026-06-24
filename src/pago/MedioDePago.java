package pago;

public abstract class MedioDePago {
//Proposito: Modelar procedimientos comunes entre distintos medios de pago
	protected double monto;
	
	MedioDePago(double monto) {
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
}
