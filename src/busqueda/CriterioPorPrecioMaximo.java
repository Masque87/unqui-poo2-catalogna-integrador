package busqueda;

import catalogo.ItemCatalogo;

public class CriterioPorPrecioMaximo implements CriterioBusqueda {
//Proposito: Modelar el criterio basado en un precio maximo	
	private double precioMax;
	public CriterioPorPrecioMaximo(double precioMax) {
		//constructor
		this.precioMax = precioMax; 
	}

	@Override
	public boolean cumple(ItemCatalogo item) {
		return item.getPrecioBase() <= precioMax;
		}
}
