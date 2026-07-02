package estadoPedido;

import notificacionesPedido.NotificadorEmail;

public class Confirmado extends EstadoPedido {

	public Confirmado(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public void procesarEstado() {
		pedido.decrementarStockDeLosItems();
		//TODO: Computar items del pedido como vendidos, puedo computar el pedido en todo caso, lo agrega al reporte de items vendidos.
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
