package catalogo;
import java.util.ArrayList;
import java.util.List;
public class Paquete extends ItemCatalogo {
/* Prop: modelar elemento del catalogo que agrupa varios items.
 * En base al patron Composite representa: "Composite"
 *  Nota: se decide implentar aqui el add y remove por motivos del uso del patron en este contexto.
 * */
	List <ItemCatalogo> items = new ArrayList<>();
	
	public Paquete(String nombre, String descripcion,int stock, double descuento) {
		super(nombre, descripcion, stock);
		this.descuento = descuento;
	}
	public void addProducto(ItemCatalogo item) {
		//Prop: agrega un item nuevo al paquete
		items.add(item);
	}
	public void remove(ItemCatalogo item) {
		//Prop: remueve el item del paquete
		this.verificarExisteItem(item);
	    items.remove(item);
    }
	private void verificarExisteItem(ItemCatalogo item) {
		if (!items.contains(item)) {
	        throw new IllegalArgumentException("El item no pertenece a este paquete");
	    }
	}
	
	@Override
	public double getPrecioBase() {
		double suma = 0;
        for (ItemCatalogo item : items) {
            suma += item.getPrecioFinal();
        }
        return suma;
	}
}
