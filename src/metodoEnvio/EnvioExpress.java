package metodoEnvio;

public class EnvioExpress extends MetodoEnvio {

	@Override
	public float getCosto() {
		//Calcula el costo en funcion del valor monetario total del pedido.
		return EnvioExpress.calcularCosto(float precio);
	}
	
	public String getTiempoDeEntrega() {
		return "Entrega un dia habil despues de la compra.";
	}

}
