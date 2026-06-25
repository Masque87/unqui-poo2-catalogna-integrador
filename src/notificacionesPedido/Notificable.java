package notificacionesPedido;
import estadoPedido.*;

public interface Notificable {
	//Recibe el aviso de un cambio de estado y actúa en consecuencia.
	public void recibir(Estado estadoAnterior, Estado estadoNuevo);
}
