package unqShop.estadoPedido;

import unqShop.Pedido;

public abstract class EstadoPedido {
	protected Pedido pedido;
	
	public abstract void siguienteEstado();
	public abstract void cancelarPedido();
	public abstract void procesarEstado();
	public String mensajeAlCancelar() {return ("");};
	
	public Boolean puedeAgregarItems() {
		return false;
	}
	
	public Boolean puedeRemoverItems() {
		return false;
	}
}
