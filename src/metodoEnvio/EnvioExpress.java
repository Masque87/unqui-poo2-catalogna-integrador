package metodoEnvio;

import estadoPedido.Pedido;

public class EnvioExpress extends MetodoEnvio {
	private double porcentajeRecargo;
	private double recargoFijo;
	public EnvioExpress(float porcentajeRecargo, float recargoFijo) {
		this.porcentajeRecargo = porcentajeRecargo; 
		this.recargoFijo = recargoFijo;
	}
	
	@Override
	public float calcularCosto(Pedido pedido) {
		//Calcula el costo en funcion del valor monetario total del pedido.
		return (float) this.calcularCostoDelEnvio(pedido.costoTotalProductos().floatValue());
	}
	
	public double calcularCostoDelEnvio(double totalProductos) {
		return ((totalProductos * porcentajeRecargo) + recargoFijo);
	}
	
	public String getTiempoDeEntrega(Pedido pedido) {
		return "Entrega un dia habil despues de la compra.";
	}

}
