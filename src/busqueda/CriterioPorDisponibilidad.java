package busqueda;

import catalogo.ItemCatalogo;

public class CriterioPorDisponibilidad implements CriterioBusqueda {
//proposito: Criterio basado en la disponibilidad de un producto. 
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		return item.tieneStock();
	}

}
