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
		/*TODO: Agregar el procesamiento del pedido como pedido e items vendidos, esto permite que en el punto 2.8
		 * yo pueda verificar los pedidos que fueron vendidos efectivamente en un periodo y ordenar la lista de items
		 * segun el criterio pedido, accediendo a cada uno de los pedidos.
		 */
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
