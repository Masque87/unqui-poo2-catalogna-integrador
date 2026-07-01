package estadoPedido;
import notificacionesPedido.*;

public class Cancelado extends EstadoPedido {

	protected String accionTomada;
	public Cancelado(Pedido pedido, String accion) {
		this.pedido = pedido;
		this.accionTomada = accion;
	}
	
	@Override
	public void procesarEstado() {
		//Informa la cancelación del pedido y las acciones tomadas en base a la fase del pedido de la que proviene.
		System.out.println("El pedido ha sido cancelado. " + accionTomada);
	}
	
	@Override
	public void siguienteEstado() {
		//Estado terminal.
		throw new IllegalStateException("Este pedido fue cancelado.");
	}

	@Override
	public void cancelarPedido() {
		//Estado terminal.
		throw new IllegalStateException("Este pedido ya ha sido cancelado");
	}
	
	@Override
	public void enviarCorreoConCupon(Fidelizacion f){
		f.enviarCuponAlCliente();
	}
	

}
