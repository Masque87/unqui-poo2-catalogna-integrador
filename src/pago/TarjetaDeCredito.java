package pago;


public class TarjetaDeCredito extends MedioDePago {
//Proposito: Modelar Tarjeta de credito como un medio de pago valido para unqShop
	private TarjetaDeCreditoAPI api;
    private String numeroTarjeta;
    private String cvv;
    private String vencimiento;
    private String cuponDePago;

    public TarjetaDeCredito(TarjetaDeCreditoAPI api, String numeroTarjeta, String cvv, String vencimiento, double monto){
    	super(monto);
        this.api = api;
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.vencimiento = vencimiento;
    }
    
	@Override
	protected void validarDatos() {
		//validar N.° de tarjeta, CVV y vencimiento (*)
		boolean valido = api.validarDatos(numeroTarjeta, cvv, vencimiento);
		validarResultado(valido, "Datos de tarjeta inválidos");
	}

	@Override
	protected void reservarFondos() {
		//realizar Pre-autorización al banco emisor (*)
		boolean reservado = api.reservarFondos(monto);
		validarResultado(reservado, "No se pudo realizar la pre-autorización");
	}

	@Override
	protected void ejecutarTransaccion() {
		//Realizar Transferencia inmediata (*)
		boolean ejecutado = api.ejecutarTransaccion();
		validarResultado(ejecutado, "La transacción no pudo completarse");
	}

	@Override
	protected void notificarResultado() {
		// realizar un Cupón de pago imprimible (Generarlo y registrarlo)
		 cuponDePago = "Cupón de pago - " + generarCodigoTransaccion();
	}
	
	public String getComprobante(){
		return cuponDePago;
	}
		
}