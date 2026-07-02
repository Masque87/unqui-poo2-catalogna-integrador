package reporte;

import catalogo.*;

public class ReporteTextoPlano extends Reporte implements Visitor {

	@Override
	public void ImprimirReporte() {
		System.out.println(ImprimirHeader());
		System.out.println(ImprimirItems());
	}
	
	@Override
	public String ImprimirHeader() {
		return "Item\tCantidad\tPrecio promedio de venta\n";
	}
	
	
	@Override
	public String visitarProducto(Producto p) {
	    return p.getNombre() + "\t" + String.valueOf(getCantidadDe(p)) + "\t" + "$" + String.valueOf(getPrecioPromedioDe(p));
	}

	@Override
	public String visitarPaquete(Paquete p) {
		return p.getNombre() + "\t" + String.valueOf(getCantidadDe(p)) + "\t" + "$" + String.valueOf(getPrecioPromedioDe(p));
	}
	
}
