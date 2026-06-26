package busqueda;

import catalogo.ItemCatalogo;

public class CriterioPorNombre implements CriterioBusqueda {
	//Proposito: Modelar el tipo de criterio basado en nombre
	private String nombreABuscar;
    public CriterioPorNombre(String nombre) { 
    	//constructor
    	this.nombreABuscar = nombre;
    	}

    @Override
    public boolean cumple(ItemCatalogo item) {
        return item.getNombre().toLowerCase().contains(nombreABuscar.toLowerCase()); //revisar
    }
}
