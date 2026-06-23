package pago;

public abstract class MedioDePago {
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
