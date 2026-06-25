package pago;

public class BilleteraVirtual extends MedioDePago {
	//Proposito: Modelar Billetera Virtual como un medio de pago valido para unqShop
	private BilleteraVirtualAPI api;

	public BilleteraVirtual(BilleteraVirtualAPI api, double monto){
		super(monto);
		this.api = api;
	}
	@Override
	protected void validarDatos() {
		// validar Saldo suficiente en cuenta (*)
		boolean valido = api.validarSaldo(monto);
		validarResultado(valido, "Saldo insuficiente");
	}

	@Override
	protected void reservarFondos() {
		//realizar Bloqueo del saldo hasta confirmar (*)
		boolean reservado = api.bloquearSaldo(monto);
		validarResultado(reservado, "No se pudo bloquear el saldo");
	}

	@Override
	protected void ejecutarTransaccion() {
		// realizar Acreditación en tiempo real al vendedor (*)
		boolean ejecutado = api.confirmarAcreditacion();
		validarResultado(ejecutado, "La acreditación no pudo completarse");
	}
	@Override
	protected void notificarResultado() {
		//realizar Notificación push. (*)
	    api.enviarNotificacionPush(); 
	}
}

 