package metodoEnvio;

import estadoPedido.Pedido;

public class EnvioEstandar extends MetodoEnvio {
	CorreoArgentina gestorDeCorreo;
	Direccion direccionDelEnvio;

	public EnvioEstandar(Direccion direccion, CorreoArgentina gestor) {
		this.gestorDeCorreo = gestor;
		this.direccionDelEnvio = direccion;
	}
	
	@Override
	public float calcularCosto(Pedido pedido) {
		//Calcula el costo en funcion del peso total del pedido (kilogramos) y la direccion de entrega (kilometros)
		//Hacer la vinculacion con el pedido y reemplazar los argumentos por los del pedido.
		return gestorDeCorreo.estimarEnvio(pedido.getPeso(), getDireccionDeEnvio());
	}
	
	public String getTiempoDeEntrega(Pedido pedido) {
		return "Entrega de 5 a 7 dias habiles despues de la compra.";
	}

	public Direccion getDireccionDeEnvio() {
		return this.direccionDelEnvio;
	}
	
}
