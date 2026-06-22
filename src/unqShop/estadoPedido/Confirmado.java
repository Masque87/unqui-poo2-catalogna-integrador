package unqShop.estadoPedido;

import unqShop.Pedido;

public class Confirmado extends EstadoPedido {

	public Confirmado(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public void procesarEstado() {
		pedido.decrementarStockDeLosPedidos();
	}
	
	public void siguienteEstado() {
		this.pedido.cambiarEstado(new EnPreparacion(pedido));
	}

	
	public void cancelarPedido() {
		this.pedido.reembolsoTotal();
		this.pedido.restablecerStockDeLosItems();
		this.pedido.cambiarEstado(new Cancelado(pedido, mensajeAlCancelar()));
		
	}
	@Override
	public String mensajeAlCancelar() {
		return ("Se reembolsará tanto el costo de los productos como del envío.");
	}
}
