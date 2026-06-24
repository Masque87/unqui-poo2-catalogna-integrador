package pago;

public class BilleteraVirtual extends MedioDePago {
	//Proposito: Modelar Tarjetas de credito como un medio de pago valido para unqShop
	BilleteraVirtualAPI api;

	BilleteraVirtual(BilleteraVirtualAPI api, double monto){
		super(monto);
		this.api = api;
	}
	@Override
	protected void validarDatos() {
		// validar Saldo suficiente en cuenta (*)
		api.validarSaldo(monto);
	}

	@Override
	protected void reservarFondos() {
		//realizar Bloqueo del saldo hasta confirmar (*)
		api.bloquearSaldo(monto);
	}

	@Override
	protected void ejecutarTransaccion() {
		// realizar Acreditación en tiempo real al vendedor (*)
		api.confirmarAcreditacion();
	}
	@Override
	protected void notificarResultado() {
		//realizar Notificación push. (*)
	    api.enviarNotificacionPush(); 
	}
}

 