package pago;

public class TransferenciaBancaria extends MedioDePago {
	//Proposito: Modelar las Transferencias Bancarias como un medio de pago valido para unqShop
	TransferenciaBancariaAPI api;
	String cbu;
	String alias;
	TransferenciaBancaria(TransferenciaBancariaAPI api, String cbu, String alias, double monto){
		super(monto);
		this.api = api;
		this.cbu = cbu;
		this.alias = alias;
	}
	@Override
	protected void validarDatos() {
		//validarCBU/CVU y alias válido (*)
		api.validarCBU(cbu, alias);
	}

	@Override
	protected void reservarFondos() {
	//no aplica transferencia directa
	}

	@Override
	protected void ejecutarTransaccion() {
		// realizar Transferencia inmediata o programada (*)
		api.ejecutarTransferencia(monto);
	}
	@Override
	protected void notificarResultado() {
		//Realizar un Comprobante CBU con número de operación (Generarlo y registrarlo)
	    System.out.println("Comprobante CBU generado: " + generarCodigoTransaccion());
	}
}
