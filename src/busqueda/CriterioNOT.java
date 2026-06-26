package busqueda;

import catalogo.ItemCatalogo;

public class CriterioNOT implements CriterioBusqueda {
	//Proposito: Implementar un criterio complejo que exija no cumplir un criterio.

	private CriterioBusqueda criterio;
	public CriterioNOT(CriterioBusqueda criterio) { 
		//constructor
		this.criterio = criterio; 
	}

	@Override
	public boolean cumple(ItemCatalogo item) {
		return !criterio.cumple(item);
	}
}
