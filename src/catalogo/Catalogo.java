package catalogo;
import java.util.ArrayList;
import java.util.List;
import busqueda.CriterioBusqueda;

public class Catalogo {
	//Proposito: modelar la clase Catalogo de UNQ-shop, contando con el catalogo de items de la compañia.
	private List<ItemCatalogo> items = new ArrayList<>();
	
	public void agregar(ItemCatalogo item){
		//agrega el item dado al catalogo.
		items.add(item);
	}
	
	public List<ItemCatalogo> buscar(CriterioBusqueda criterio) {
		//devuelve la lista de items propios del catalogo que satisfacen el criterio dado.
        return items.stream()
                    .filter(item -> criterio.cumple(item))  //criterio::cumple
                    .toList();
    }
}
