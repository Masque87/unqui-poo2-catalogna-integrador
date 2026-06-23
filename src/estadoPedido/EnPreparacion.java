package estadoPedido;

public class EnPreparacion extends EstadoPedido {

	public EnPreparacion(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public void procesarEstado() {
		System.out.println("El pedido se encuentra en preparación");
	}
	
	@Override
	public void siguienteEstado() {
		this.pedido.cambiarEstado(new Enviado(pedido));
	}

	@Override
	public void cancelarPedido() {
		throw new IllegalStateException("No es posible cancelar el pedido en esta fase.");
	}

}
