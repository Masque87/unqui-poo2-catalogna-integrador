package estadoPedido;

import notificacionesPedido.NotificadorEmail;

public abstract class EstadoPedido implements Estado{
	protected Pedido pedido;

	public String mensajeAlCancelar() {return ("");}; //Mensaje que informa la cancelacion del pedido y las acciones realizadas.
	public Boolean puedeAgregarItems() { return false; }
	public Boolean puedeRemoverItems() { return false; }
	public void notificarAlCliente(NotificadorEmail notificador) {}
}
