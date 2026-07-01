package metodoEnvio;

import estadoPedido.Pedido;

public class EnvioEstandar extends MetodoEnvio {
	CorreoArgentina gestorDeCorreo;

	@Override
	public float getCosto() {
		//Calcula el costo en funcion del peso total del pedido (kilogramos) y la direccion de entrega (kilometros)
		//Hacer la vinculacion con el pedido y reemplazar los argumentos por los del pedido.
		return gestorDeCorreo.estimarEnvio(pedido.getPeso(), getDireccionDeEnvio());
	}
	
	public String getTiempoDeEntrega() {
		return "Entrega de 5 a 7 dias habiles despues de la compra.";
	}

	public EnvioEstandar(Pedido pedido, Sucursal sucursal) {
		super(pedido);
	}
}
