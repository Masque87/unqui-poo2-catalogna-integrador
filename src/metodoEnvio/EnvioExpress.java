package metodoEnvio;

import estadoPedido.Pedido;

public class EnvioExpress extends MetodoEnvio {

<<<<<<< HEAD
	private double porcentajeRecargo;
	private double recargoFijo;

	public EnvioExpress(double porcentajeRecargo, double recargoFijo) {
		this.porcentajeRecargo = porcentajeRecargo; 
		this.recargoFijo = recargoFijo;
=======
	CorreoArgentina envioExpress;
	float porcentajeDeRecargo;
	
	@Override
	public float calcularCosto() {
		//Calcula el costo en funcion del valor monetario total del pedido.
		//Hacer la vinculacion con el pedido y reemplazar los argumentos por los del pedido.
		
		return (pedido.getPrecioFinal() * porcentajeDeRecargo);
>>>>>>> 8f1c6da (implementacion al 90% terminada del visitor, punto 2.8)
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
