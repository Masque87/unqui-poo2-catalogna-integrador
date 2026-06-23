package estadoPedido;
import java.time.LocalDate;

public class Enviado extends EstadoPedido {

	public Enviado(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public void procesarEstado() {
	System.out.println("El pedido ha sido enviado el día " + LocalDate.now());	
	}
	
	@Override
	public void siguienteEstado() {
		this.pedido.cambiarEstado(new Entregado(pedido));
	}

	@Override
	public void cancelarPedido() {
		this.pedido.reembolsoProductos();
		this.pedido.cambiarEstado(new Cancelado(pedido, this.mensajeAlCancelar()));

	}
	
	public String mensajeAlCancelar() {
		return ("Se reembolsará el costo de los productos.");
	}

}
