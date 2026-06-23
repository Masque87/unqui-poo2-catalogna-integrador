package estadoPedido;

public class Borrador extends EstadoPedido{

	
	public Borrador(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public void procesarEstado() {
		//Estado inicial.
	}
	
	public void siguienteEstado() {
		pedido.cambiarEstado(new Confirmado(pedido));
	}
	
	public void cancelarPedido() {
		pedido.cambiarEstado(new Cancelado(pedido, this.mensajeAlCancelar()));
	}
	
	
	@Override
	public Boolean puedeAgregarItems() {
		return true;
	}
	
	@Override
	public Boolean puedeRemoverItems() {
		return true;
	}
}
