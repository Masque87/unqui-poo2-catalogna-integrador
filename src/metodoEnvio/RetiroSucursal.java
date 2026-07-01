package metodoEnvio;

import estadoPedido.Pedido;

public class RetiroSucursal extends MetodoEnvio {
	
	protected Sucursal sucursal;
	protected String mensajeConStock = "Entrega inmediata";
	protected String mensajeSinStock = "Entrega hasta 3 dias despues de la compra.";
	
	@Override
	public float calcularCosto(Pedido pedido) {
		//El costo de envio es siempre cero para el retiro en sucursal.
		return 0;
	}

	public String getTiempoDeEntrega(Pedido pedido) {
		//Hacer la vinculacion con la sucursal a la que se realiza el pedido y reemplazar los argumentos por los del pedido y la sucursal.
		return (conEntregaInmediata(pedido) ? mensajeConStock : mensajeSinStock);
	}
	
	public Sucursal getSucursal() {
		return sucursal;
	}
	
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	public boolean conEntregaInmediata(Pedido pedido) {
		return (sucursal.tieneStock(pedido));
	}	
	
}
