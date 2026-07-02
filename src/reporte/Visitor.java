package reporte;

//import java.util.HashMap;
//import java.util.Map;
//import java.util.ArrayList;
//import java.util.stream.Collectors;
import catalogo.Paquete;
import catalogo.Producto;

public interface Visitor {
	/* Propósito: comportamiento común a implementar por todos los reportes, deben poder visitar cada una de las subclases de ItemCatalogo.
	 * y tambien al Periodo del Reporte, NO ES SOBRE ITEMS DEL PEDIDO.
	   representa en el Visitor de Reportes: "Visitor".
   		Interfaz comun a ser implementada por los distintos tipos de reportes.
	 */
	
	//(protected List<ItemCatalogo> itemsVendidosDuranteElPeriodo;
	
	//cada vez que procesarEstado es Entregado, lo agrega a la lista de productos
	/*
	 * Entonces, cuando yo agarro un rango de dias, puedo tener todos los productos que se vendieron en esos dias.
	 */
	//Map [Dia, List<productoVendidoEnDia>]
	
	public void GenerarReporte();
	
	public void ImprimirReporte();
	
	public String ImprimirHeader();
	
	
	public String ImprimirItems();
	
	/*
	 * Visitar el producto, me permite saber la cantidad de productos que tiene dicho pedido, y asi saber su cantidad
	 * y precioFinal.
	 */
	public String visitarProducto(Producto p);
	/*
	 * Visitar el paquete, me permite chequear lo mismo que el producto, pero es necesario un metodo aparte por claridad
	 * y por posibles cambios futuros.
	 */
	public String visitarPaquete(Paquete p);
	
	// itemsDelPeriodo[] es con lo que tengo que quedarme, eso me va a permitir sacar el precio al que se vendieron cada uno,
	//para sacar un promedio y tambien sacar la cantidad de items que se vendieron y contarlos.
	//private Map<String, double, float> itemsMasVendidos
	
	
	
	
	
}
