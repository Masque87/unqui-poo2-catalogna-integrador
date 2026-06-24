package metodoEnvio;

public class EnvioEstandar extends MetodoEnvio {

	@Override
	public float getCosto() {
		//Calcula el costo en funcion del peso total del pedido (kilogramos) y la direccion de entrega (kilometros)
		//Hacer la vinculacion con el pedido y reemplazar los argumentos por los del pedido.
		return CorreoArgentina.estimarEnvio(float peso, Direccion direccionEnvio);
	}
	
	public String getTiempoDeEntrega() {
		return "Entrega de 5 a 7 dias habiles despues de la compra.";
	}

}
