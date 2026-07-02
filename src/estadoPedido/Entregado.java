package estadoPedido;
import java.time.LocalDate;

import notificacionesPedido.GeneradorFactura;
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
		throw new IllegalStateException("Este pedido ya fue entregado.");
	}

	@Override
	public void cancelarPedido() {
		//Estado terminal.
		throw new IllegalStateException("No es posible cancelar un pedido entregado.");
	}
	
	@Override
	public void notificarAlCliente(NotificadorEmail n) {
		n.notificarPedidoEntregado();
	}
	
	@Override
	public void generarComprobanteFiscal(GeneradorFactura generadorFactura) {
		generadorFactura.generarComprobanteFiscal(pedido.getItems(), pedido.costoTotalProductos());
	}

}
