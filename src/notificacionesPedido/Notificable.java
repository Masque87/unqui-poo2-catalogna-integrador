package notificacionesPedido;
import estadoPedido.*;

public interface Notificable {
	public void recibir(Estado estadoAnterior, Estado estadoNuevo);
}
