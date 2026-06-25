package estadoPedido;
import java.time.LocalDate;

import notificacionesPedido.NotificadorEmail;

public class Entregado extends EstadoPedido {

	public Entregado(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public void procesarEstado() {
		System.out.println("El pedido ha sido entregado el día " + LocalDate.now());
	}
	@Override
	public void siguienteEstado() {
		//Estado terminal.
	}

	@Override
	public void cancelarPedido() {
		//Estado terminal.
	}
	
	public void notificarPedidoEntregado(NotificadorEmail n) {
		n.notificarPedidoEntregado();
	}

}
