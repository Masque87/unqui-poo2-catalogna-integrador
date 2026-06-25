package estadoPedido;

import notificacionesPedido.NotificadorEmail;

public class Confirmado extends EstadoPedido {

	public Confirmado(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public void procesarEstado() {
		pedido.decrementarStockDeLosPedidos();
	}
	
	@Override
	public void siguienteEstado() {
		this.pedido.cambiarEstado(new EnPreparacion(pedido));
	}

	@Override
	public void cancelarPedido() {
		this.pedido.reembolsoTotal();
		this.pedido.restablecerStockDeLosItems();
		this.pedido.cambiarEstado(new Cancelado(pedido, mensajeAlCancelar()));
		
	}
	
	@Override
	public void notificarAlCliente(NotificadorEmail n) {
		n.notificarPedidoConfirmado();
	}
	
	
	@Override
	public String mensajeAlCancelar() {
		return ("Se reembolsará tanto el costo de los productos como del envío.");
	}
}
