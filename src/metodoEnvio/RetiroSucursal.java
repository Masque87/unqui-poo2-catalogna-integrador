package metodoEnvio;

public class RetiroSucursal extends MetodoEnvio {

	@Override
	public float getCosto() {
		//El costo de envio es siempre cero para el retiro en sucursal.
		return 0;
	}

	public String getTiempoDeEntrega() {
		//Hacer la vinculacion con la sucursal a la que se realiza el pedido y reemplazar los argumentos por los del pedido y la sucursal.
		return (Sucursal.tieneStock(pedido)) ? "Envio inmediato." : "Entrega hasta 3 dias despues de la compra.";
	}
}
