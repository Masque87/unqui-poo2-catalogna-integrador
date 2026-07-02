package reporte;

import catalogo.*;

public class ReporteCSV extends Reporte implements Visitor {

	@Override
	public void ImprimirReporte() {
		System.out.println(ImprimirHeader());
		System.out.println(ImprimirItems());
	}
	
	@Override
	public String ImprimirHeader() {
		return "Item;Cantidad;Precio promedio de venta\n";
	}
	
	
	@Override
	public String visitarProducto(Producto p) {
	    return p.getNombre() + ";" + String.valueOf(getCantidadDe(p)) + ";" + "$" + String.valueOf(getPrecioPromedioDe(p));
	}

	@Override
	public String visitarPaquete(Paquete p) {
		return p.getNombre() + ";" + String.valueOf(getCantidadDe(p)) + ";" + "$" + String.valueOf(getPrecioPromedioDe(p));
	}
	
}
