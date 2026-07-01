package metodoEnvio;

import estadoPedido.Pedido;

public interface Sucursal {

	boolean tieneStock(Pedido pedido);
	//Indica si esta sucursal tiene stock de los productos del pedido
}
