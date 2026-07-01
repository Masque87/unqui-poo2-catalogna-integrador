package estadoPedido;

import java.util.ArrayList;
import java.util.List;
import catalogo.ItemCatalogo;
import notificacionesPedido.Notificable;
import metodoEnvio.*;
import java.util.stream.Collectors;

public class Pedido {

	protected Estado estado;
	protected List<Notificable> notificables = new ArrayList<>();
	protected List<ItemCatalogo> items = new ArrayList<>();
	protected MetodoEnvio metodoDeEnvio;
	
	public Pedido (MetodoEnvio metodoDeEnvio) {
		this.setMetodoDeEnvio(metodoDeEnvio);
		this.estado = new Borrador(this);
	}
	
	public void addNotificable(Notificable n) {
		notificables.add(n);
	}
	
	public void removeNotificable(Notificable n) {
		notificables.remove(n);
	}
	
	public void setMetodoDeEnvio(MetodoEnvio metodoDeEnvio) {
		this.metodoDeEnvio = metodoDeEnvio;
	}

	public Estado getEstado() {
		return estado;
	}
	
	public List<Notificable> getNotificables() {
		return this.notificables;
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
		this.validarQueElPedidoNoEstéVacío();
		estado.siguienteEstado();
		estado.procesarEstado();
		
	}
	
	public void cancelarPedido() {
		estado.cancelarPedido();
		estado.procesarEstado();
	}

	
	public void reembolsar(Double monto) {
		//Reembolsa al cliente el monto correspondiente y genera una nota de credito.
		this.generarNotaDeCredito(monto);
	}
	
	public String generarNotaDeCredito(Double monto) {
		//Genera una nota de credito informando el monto reembolsado al cliente
		return("Se le reembolsó $" + monto +" al cliente");
	}
	
	public Double costoTotalProductos() {
		//Devuelve la suma de todos los productos en el pedido
		Double total = items.stream().mapToDouble(ItemCatalogo::getPrecioFinal).sum();
		return (total);
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

		
		private void validarQueElPedidoNoEstéVacío() {
		if(items.isEmpty()) {
			throw new IllegalStateException("El pedido se encuentra vacío");
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
