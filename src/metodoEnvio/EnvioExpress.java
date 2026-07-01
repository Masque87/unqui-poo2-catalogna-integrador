package metodoEnvio;

import estadoPedido.Pedido;

public class EnvioExpress extends MetodoEnvio {

	@Override
	public float getCosto() {
		//Calcula el costo en funcion del valor monetario total del pedido.
		//Hacer la vinculacion con el pedido y reemplazar los argumentos por los del pedido.
		
		/*TODO
		Reemplazo "EnvioExpress" por "envioExpress" para evitar la confusion de nombres temporalmente.
		El nombre fue dado por el ejercicio y choca con lo que quiere ejemplificar (servicio externo).
		*/
		return envioExpress.calcularCosto(pedido.getPrecioFinal());
	}
	
	public String getTiempoDeEntrega() {
		return "Entrega un dia habil despues de la compra.";
	}

	public EnvioExpress(Pedido pedido) {
		super(pedido);
	}
}
