package busqueda;

import java.util.List;

import catalogo.ItemCatalogo;

public abstract class CriterioCompuesto implements CriterioBusqueda{
	//Proposito: Implementar la base para criterios complejos que exijan tenga varios criterios
    protected List<CriterioBusqueda> criterios;
    public CriterioCompuesto(List<CriterioBusqueda> criterios) {
    	//constructor
        this.criterios = criterios;
    }
    
	@Override
	public abstract boolean cumple(ItemCatalogo item);
}
