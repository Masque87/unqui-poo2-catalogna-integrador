package busqueda;

import catalogo.ItemCatalogo;

public class CriterioPorCategoria implements CriterioBusqueda {
	String categoriaABuscar;
	
	public CriterioPorCategoria(String categoria) {
		//constructor
		categoriaABuscar = categoria;
	}
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		return item.getCategoria().equalsIgnoreCase(categoriaABuscar);
	}

}
