package estadoPedido;

import notificacionesPedido.*;

public abstract class EstadoPedido implements Estado{
	protected Pedido pedido;

	public String mensajeAlCancelar() {return ("");}; //Mensaje que informa la cancelacion del pedido y las acciones realizadas.
	public Boolean puedeAgregarItems() { return false; }
	public Boolean puedeRemoverItems() { return false; }
	public void enviarCorreoConCupon(Fidelizacion n) {}
	public void notificarAlCliente(NotificadorEmail notificador) {}
}
