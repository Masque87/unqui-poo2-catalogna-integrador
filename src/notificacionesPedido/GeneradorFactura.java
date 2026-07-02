package notificacionesPedido;
import estadoPedido.*;
import java.util.ArrayList;
import java.util.List;
import catalogo.*;

public class GeneradorFactura implements Notificable{
	
	public List<String> facturas = new ArrayList<>();
	
	public void recibir(Estado anterior, Estado nuevo) {
		nuevo.generarComprobanteFiscal(this);
	}
	
	public void generarComprobanteFiscal(List<ItemCatalogo> items, double precioTotal) {
		//Genera un comprobante fiscal del pedido.
		StringBuilder factura = new StringBuilder();
		for (ItemCatalogo i : items) {
			String infoDelItem = String.format("%-15s $%.2f\n", i.getNombre(), i.getPrecioFinal());
			factura.append(infoDelItem);
		}
		factura.append("-------------------------\n");
        factura.append(String.format("%-15s $%.2f", "TOTAL:", precioTotal));
		this.addFactura(factura.toString());
	}
	
	public void addFactura(String factura) {
		facturas.add(factura);
	}
	
	public List<String> getFacturas(){
		return this.facturas;
	}

}
