package pago;


public class TransferenciaBancaria extends MedioDePago {
	//Proposito: Modelar las Transferencias Bancarias como un medio de pago valido para unqShop
	private TransferenciaBancariaAPI api;
	private String cbu;
	private String alias;
	private String comprobante;
	public TransferenciaBancaria(TransferenciaBancariaAPI api, String cbu, String alias, double monto){
		super(monto);
		this.api = api;
		this.cbu = cbu;
		this.alias = alias;
	}
	@Override
	protected void validarDatos() {
		//validarCBU/CVU y alias válido (*)
		boolean valido = api.validarCBU(cbu, alias);
		validarResultado(valido, "CBU o alias inválido");
	}

	@Override
	protected void reservarFondos() {
	//no aplica transferencia directa
	}

	@Override
	protected void ejecutarTransaccion() {
		// realizar Transferencia inmediata o programada (*)
		boolean ejecutado = api.ejecutarTransferencia(monto);
		validarResultado(ejecutado, "La transferencia no pudo completarse");
	}
	@Override
	protected void notificarResultado() {
		//Realizar un Comprobante CBU con número de operación (Generarlo y registrarlo)
		comprobante = "Comprobante CBU generado: " + generarCodigoTransaccion();
	}
	public String getComprobante() {
		//devuelve comprobantes actuales
		return comprobante;
	}
}	

