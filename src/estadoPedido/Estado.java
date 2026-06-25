package estadoPedido;

public interface Estado{
	
	public abstract void siguienteEstado();//Se avanza al siguiente estado del pedido.
	public abstract void cancelarPedido();//Se cancela el pedido y se realizan las acciones correspondientes al estado previo a la cancelación.
	public abstract void procesarEstado(); //Se realizan las acciones correspondientes a cada estado del pedido.
	public String mensajeAlCancelar(); //Mensaje que informa las acciones realizadas una vez cancelado el pedido
	public Boolean puedeAgregarItems();//Indica si se pueden agregar items en el estado actual.
	public Boolean puedeRemoverItems();//Indica si se pueden remover items en el estado actual.
}
