package estadoPedido;

import java.util.ArrayList;
import java.util.List;
import catalogo.ItemCatalogo;
import notificacionesPedido.Notificable;
import java.util.stream.Collectors;

public class Pedido {

	protected Estado estado;
	protected List<Notificable> notificables = new ArrayList<>();
	protected List<ItemCatalogo> items = new ArrayList<>();
	
	public Pedido () {
		this.estado = new Borrador(this);
	}
	
	public void addNotificable(Notificable n) {
		notificables.add(n);
	}
	
	public void removeNotificable(Notificable n) {
		notificables.remove(n);
	}

	public Estado getEstado() {
		return estado;
	}
	
	public void cambiarEstado(Estado estadoDelPedido) {
		this.informarCambioDeEstado(estado, estadoDelPedido);
		this.estado = estadoDelPedido;
	}
	
	public void addItem(ItemCatalogo item){
		this.validarPuedeAgregarItems();
		this.validarItemTieneStock(item);
		this.items.add(item);
	}
	
	public void removeItem(ItemCatalogo item) {
		this.validarPuedeRemoverItems();
		this.validarItemEstaEnElPedido(item);
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
		//TODO
		//Rembolsa al cliente tanto el valor de los productos como el valor del envio.
	}
	
	public void reembolsoProductos () {
		//TODO
		//Reembolsa al cliente la suma de los productos en el pedido.
	}
	
	public void restablecerStockDeLosItems() {
		//Aumenta el stock de los productos que estaban en un pedido en estado CONFIRMADO
		for (ItemCatalogo i : items) {
			i.aumentarStock();
		}
	}	
	
	public void informarCambioDeEstado(Estado anterior, Estado nuevo) {
		for(Notificable n : notificables) {
			n.recibir(anterior, nuevo);
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
	
	private void validarItemEstaEnElPedido(ItemCatalogo i) {
		if(!items.contains(i)) {
			throw new IllegalArgumentException("El item no está en el pedido");
		}
	}

	public double getPrecioFinal() {
		double resultado = 0;
		for(ItemCatalogo i : items) {
			resultado = resultado + i.getPrecioFinal();
		}
		return resultado;
	}
	
	public float getPeso() {
		float resultado = 0;
		for(ItemCatalogo i : items) {
			resultado = resultado + i.getPeso();
		}
		return resultado;
	}
}
