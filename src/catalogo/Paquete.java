package catalogo;
import java.util.ArrayList;
import java.util.List;

import reporte.Visitor;
public class Paquete extends ItemCatalogo {
/* Prop: modelar elemento del catalogo que agrupa varios items.
 * En base al patron Composite representa: "Composite"
 *  Nota: se decide implentar aqui el add y remove por motivos del uso del patron en este contexto.
 * */
	List <ItemCatalogo> items = new ArrayList<>();
	
	public Paquete(String nombre, String descripcion,int stock, double descuento, String categoria) {
		super(nombre, descripcion, stock, categoria);
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
	public float getPrecioBase() {
		float suma = 0;
        for (ItemCatalogo item : items) {
            suma += item.getPrecioFinal();
        }
        return suma;
	}

	public float getPeso() {
		float suma = 0;
        for (ItemCatalogo item : items) {
            suma += item.getPeso();
        }
        return suma;
	}
	
	 public String aceptar(Visitor visitor) {
	    	return visitor.visitarPaquete(this);
	    }
}
