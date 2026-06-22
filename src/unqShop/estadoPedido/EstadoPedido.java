package unqShop.estadoPedido;

import unqShop.Pedido;

public abstract class EstadoPedido {
	protected Pedido pedido;
	
	public abstract void siguienteEstado();//Se avanza al siguiente estado del pedido.
	public abstract void cancelarPedido();//Se cancela el pedido.
	public abstract void procesarEstado(); //Se realizan las acciones correspondientes a cada estado del pedido.
	public String mensajeAlCancelar() {return ("");}; //Mensaje que informa las acciones realizadas una vez cancelado el pedido
	
	public Boolean puedeAgregarItems() {
		return false;
	}
	
	public Boolean puedeRemoverItems() {
		return false;
	}
}
