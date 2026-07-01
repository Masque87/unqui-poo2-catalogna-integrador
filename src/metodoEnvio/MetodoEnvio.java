package metodoEnvio;

import estadoPedido.Borrador;
import estadoPedido.Pedido;

public abstract class MetodoEnvio {
	/*	Propósito: comportamiento común a implementar por todos los metodos de envio.
		representa en el Strategy de metodos de envio: "Strategy"
		Se decide que sea una clase abstracta por contexto y necesidades del ejercicio.
	 */
	
	/*	El tiempo de entrega es un estimativo segun el enunciado, no es un requerimento que se cumple exactamente.
		Por lo tanto, no exige un control de ningun tipo, su unico proposito es informarle al usuario cuando llega su pedido, segun
		el metodo de envio elegido.
	*/
	Pedido pedido;
	CorreoArgentina sucursal;
	public abstract String getTiempoDeEntrega();
	public abstract float getCosto();
	public abstract String getDireccionDeEnvio();
	
	
	public MetodoEnvio(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
}
