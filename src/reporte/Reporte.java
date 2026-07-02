package reporte;

import catalogo.ItemCatalogo;
import catalogo.Paquete;
import catalogo.Producto;
import estadoPedido.Pedido;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Reporte implements Visitor {

	public List<ItemCatalogo> itemsVendidos;
	public List<Pedido> pedidosVendidos;
	
	
	/*
	 * COntabilizo pedidos
	 * Cada pedido tiene sus items
	 * SI se cancela un pedido, saco el pedido de la lista
	 * Cuando voy a generar el reporte, genero los 2 map de items // item - gananciaTotal // item - cantidadVendida
	 * item - gananciaTotal dividido cantidadVendida
	 */
	
	public Map<ItemCatalogo, Integer> cantidadVendida = new HashMap<>();
	
	public Map<ItemCatalogo, Float> gananciaTotal = new HashMap<>();
	
	public List<ItemCatalogo> itemsOrdenadosPorCantidadDeVendidos;
	
	
	
	public void GenerarReporte() { return; }
	
	public void ImprimirReporte() { return; };
	
	public void agregarPedidoVendido(Pedido pedidoVendido){
			pedidosVendidos.add(pedidoVendido);
	}
	
	public void removerPedidoCancelado(Pedido pedidoCancelado){
			if (pedidosVendidos.contains(pedidoCancelado)) {
				pedidosVendidos.remove(pedidoCancelado);
			}
	}
	
	//------
	
	public void agregarItemsDePedidoVendido(Pedido pedidoVendido){
		for (ItemCatalogo item : pedidoVendido.getListaDeItems()) {
			itemsVendidos.add(item);
		}
	}
	
	public void removerItemsDePedidoCancelado(Pedido pedidoCancelado){
		for (ItemCatalogo itemCancelado : pedidoCancelado.getListaDeItems()) {
			if (itemsVendidos.contains(itemCancelado)) {
				itemsVendidos.remove(itemCancelado);
			}
		}
	}
	
	public String ImprimirItems() {
		String resultado = "";
		for (ItemCatalogo item : itemsVendidos) {
			resultado = resultado + item.aceptar(this);
		}
		return resultado;
	}
	
	
	/* contabilizar items vendidos por cada pedido.
	 * (sumar una unidad vendida, por cada item en el pedido)
	 * contabilizar precio de venta de cada item en el pedido
	 * (sumar cada precio de venta a las ganancias totales)
	 * generar una lista de items, ordenandolos de mayor a menor por cantidad vendida
	 * imprimir todos los objetos de esa lista
	 */
	
	
	public void ContabilizarPedidosVendidos() {
		for (Pedido p : pedidosVendidos) {
			ContabilizarItemsVendidosDePedido(p);	
		}
	}
	
	
	public void ContabilizarItemsVendidosDePedido(Pedido pedido) {
		for (ItemCatalogo item : pedido.getListaDeItems()) {
			contabilizarVenta(item);
		}
	}
	
	public void contabilizarVenta(ItemCatalogo item) {
		contabilizarGanancias(item);
		contabilizarUnidadVendida(item);
	}
	
	public void contabilizarUnidadVendida(ItemCatalogo item) {
		if (gananciaTotal.containsKey(item)) {
			sumarUnidadVendida(item);
		}
		else {
			crearVenta(item);
		}
	}
	
	public void sumarUnidadVendida(ItemCatalogo item) {
		cantidadVendida.put(item, cantidadVendida.get(item) + 1);
	}
	
	public void crearVenta(ItemCatalogo item) {
		cantidadVendida.put(item, 1);
	}
	
	public void contabilizarGanancias(ItemCatalogo item) {
		if (gananciaTotal.containsKey(item)) {
			sumarGanancia(item);
		}
		else {
			crearGanancia(item);
		}
	}
	
	public void sumarGanancia(ItemCatalogo item) {
		gananciaTotal.put(item, gananciaTotal.get(item) + item.getPrecioFinal());
	}
	
	public void crearGanancia(ItemCatalogo item) {
		gananciaTotal.put(item, item.getPrecioFinal());
	}
	
	
	public double getCantidadDe(ItemCatalogo itemAContar) {
		double contador = 0;
		for (ItemCatalogo item : itemsVendidos)
			contador = contador + (item.equals(itemAContar) ? 1 : 0); 
		return contador;
	}
	
	public float getPrecioPromedioDe(ItemCatalogo item) {
		float precio = gananciaTotal.get(item);
		float cantidad = cantidadVendida.get(item);
		return precio / cantidad;
	}
	
	public Reporte() {
	}
}
