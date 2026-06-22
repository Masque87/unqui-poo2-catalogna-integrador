package unqShop;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

	protected EstadoPedido estado;
	protected List<ItemCatalogo> items = new ArrayList<>();
	
	public Pedido () {
		this.estado = new Borrador(this);
	}
	

	public EstadoPedido getEstado() {
		return estado;
	}
	
	
	public void cambiarEstado(EstadoPedido estadoDelPedido) {
		this.estado = estadoDelPedido;
	}
	
	public void addItem(ItemCatalogo item){
		this.validarPuedeAgregarItems();
		this.validarItemTieneStock(item);
		this.items.add(item);
	}
	
	public void removeItem(ItemCatalogo item) {
		this.validarPuedeRemoverItems();
		this.items.remove(item);
	}
	
	public void siguienteEstado() {
		estado.siguienteEstado();
		estado.procesarEstado();
		
	}
	
	public void cancelarPedido() {
		estado.cancelarPedido();
		estado.procesarEstado();
	}
	
	public void decrementarStockDeLosPedidos() {
		for (ItemCatalogo i : items) {
			i.decrementarStock();
		}
	}
	
	public void reembolsoTotal() {
		//Rembolsa al cliente tanto el valor de los productos como el valor del envio.
	}
	
	public void reembolsoProductos () {
		//Reembolsa al cliente la suma de los productos en el pedido.
	}
	
	public void restablecerStockDeLosItems() {
		//Aumenta el stock de los productos que estaban en un pedido en estado CONFIRMADO
		for (ItemCatalogo i : items) {
			i.aumentarStock();
		}
	}

	//validaciones
	private void validarItemTieneStock(ItemCatalogo item) {
		if(!item.tieneStock()) {
			throw new IllegalArgumentException("El item no tiene stock");
		}
	}
	
	private void validarPuedeAgregarItems() {
		if(!estado.puedeAgregarItems()) {
			throw new IllegalArgumentException("No se pueden agregar items en esta fase del pedido");
		}
	}
	
	private void validarPuedeRemoverItems() {
		if(!estado.puedeRemoverItems()) {
			throw new IllegalArgumentException("No se pueden remover items en esta fase del pedido");
		}
	}


	
}
