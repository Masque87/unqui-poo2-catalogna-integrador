package notificacionesPedido;
import estadoPedido.*;
import java.util.ArrayList;
import java.util.List;

public class GeneradorFactura implements Notificable{
	
	public List<String> facturas = new ArrayList<>();
	
	public void recibir(Estado anterior, Estado nuevo) {
		this.generarComprobanteFiscal(nuevo);
	}
	
	public void generarComprobanteFiscal(Estado estado) {
		//Genera un comprobante fiscal del pedido.
		this.addFactura("Comprobante fiscal del pedido");
	}
	
	public void addFactura(String factura) {
		facturas.add(factura);
	}

}
