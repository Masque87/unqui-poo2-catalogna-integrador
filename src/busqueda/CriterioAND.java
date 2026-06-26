package busqueda;
import java.util.List;
import catalogo.ItemCatalogo;

public class CriterioAND extends CriterioCompuesto {
	//Proposito: Implementar un criterio complejo que exija cumplir todos los criterios    
    public CriterioAND(List<CriterioBusqueda> criterios) {
    	//constructor
        super(criterios);
    }

    @Override
    public boolean cumple(ItemCatalogo item) {
        return criterios.stream().allMatch(c -> c.cumple(item));
    }

}
