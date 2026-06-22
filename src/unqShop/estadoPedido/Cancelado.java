package unqShop.estadoPedido;

import unqShop.Pedido;

public class Cancelado extends EstadoPedido {

	protected String accionTomada;
	public Cancelado(Pedido pedido, String accion) {
		this.pedido = pedido;
		this.accionTomada = accion;
	}
	
	@Override
	public void procesarEstado() {
		System.out.println("El pedido ha sido cancelado. " + accionTomada);
	}
	
	@Override
	public void siguienteEstado() {
		//Estado terminal.
	}

	@Override
	public void cancelarPedido() {
		//Estado terminal.
	}

}
