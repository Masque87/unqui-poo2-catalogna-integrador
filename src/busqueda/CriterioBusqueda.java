package busqueda;
import catalogo.ItemCatalogo;

public interface CriterioBusqueda {
	//Proposito: Modelar una interfaz con la funcion de evaluacion de criterios
	boolean cumple(ItemCatalogo item); //Verificar si el item dado cumple el criterio
}
