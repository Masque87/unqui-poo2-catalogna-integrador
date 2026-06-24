package pago;
public class TarjetaDeCredito extends MedioDePago {
//Proposito: Modelar Tarjeta de credito como un medio de pago valido para unqShop
	private TarjetaDeCreditoAPI api;
    private String numeroTarjeta;
    private String cvv;
    private String vencimiento;

    TarjetaDeCredito(TarjetaDeCreditoAPI api, String numeroTarjeta, String cvv, String vencimiento, double monto){
    	super(monto);
        this.api = api;
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.vencimiento = vencimiento;
    }
    
	@Override
	protected void validarDatos() {
		//validar N.° de tarjeta, CVV y vencimiento (*)
		api.validarDatos(numeroTarjeta, cvv, vencimiento);
	}

	@Override
	protected void reservarFondos() {
		//realizar Pre-autorización al banco emisor (*)
		 api.reservarFondos(monto);

	}

	@Override
	protected void ejecutarTransaccion() {
		//Realizar Transferencia inmediata (*)
		 api.ejecutarTransaccion();
	}

	@Override
	protected void notificarResultado() {
		// realizar un Cupón de pago imprimible (Generarlo y registrarlo)
	    System.out.println("Cupón de pago generado y registrado");
	}
}
