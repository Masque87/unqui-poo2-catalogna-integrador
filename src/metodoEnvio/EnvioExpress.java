package metodoEnvio;

import estadoPedido.Pedido;

public class EnvioExpress extends MetodoEnvio {

	private double porcentajeRecargo;
	private double recargoFijo;

	public EnvioExpress(double porcentajeRecargo, double recargoFijo) {
		this.porcentajeRecargo = porcentajeRecargo;
		this.recargoFijo = recargoFijo;
	}
	
	@Override
	public float calcularCosto(Pedido pedido) {
		//Calcula el costo en funcion del valor monetario total del pedido.
		//Hacer la vinculacion con el pedido y reemplazar los argumentos por los del pedido.
		
		/*TODO
		Reemplazo "EnvioExpress" por "envioExpress" para evitar la confusion de nombres temporalmente.
		El nombre fue dado por el ejercicio y choca con lo que quiere ejemplificar (servicio externo).
		*/
		return (float) this.calcularCosto(pedido.costoTotalProductos().floatValue());
	}
	
	public double calcularCosto(double totalProductos) {
		return ((totalProductos * porcentajeRecargo) + recargoFijo);
	}
	
	public String getTiempoDeEntrega(Pedido pedido) {
		return "Entrega un dia habil despues de la compra.";
	}

}
