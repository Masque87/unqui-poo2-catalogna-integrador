package metodoEnvio;

import estadoPedido.Pedido;

public class RetiroSucursal extends MetodoEnvio {
	
	protected Sucursal sucursal;
	protected String mensajeConStock = "Entrega inmediata";
	protected String mensajeSinStock = "Entrega hasta 3 dias despues de la compra.";
	
	@Override
	public float getCosto() {
		//El costo de envio es siempre cero para el retiro en sucursal.
		return 0;
	}

	public String getTiempoDeEntrega() {
		//Hacer la vinculacion con la sucursal a la que se realiza el pedido y reemplazar los argumentos por los del pedido y la sucursal.
		return (conEntregaInmediata() ? mensajeConStock : mensajeSinStock);
	}
	
	public Sucursal getSucursal() {
		return sucursal;
	}
	
	public boolean conEntregaInmediata() {
		return (sucursal.tieneStock(pedido));
	}
	
	public RetiroSucursal(Pedido pedido) {
		super(pedido);
	}
	
	
}
