package busqueda;
import java.util.List;
import catalogo.ItemCatalogo;

public class CriterioOR extends CriterioCompuesto {
	//Proposito: Implementar un criterio complejo que exija cumplir alguno de los criterios
	    
	 public CriterioOR(List<CriterioBusqueda> criterios) {
		 //constructor
		 super(criterios);
	 }

	 @Override
	 public boolean cumple(ItemCatalogo item){
		 return criterios.stream().anyMatch(c -> c.cumple(item));
	 }
}
