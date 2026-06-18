package unqShop;

public abstract class ItemCatalogo {
		/* Propósito: comportamiento común a implementar por todos los elementos del catálogo.
		   representa en el Composite del Catálogo de Productos: "Component"
		   Se decide que sea una clase abstracta por contexto y necesidades del ejercicio, luego no se implementa add, remove y getChild ya que entraría en conflicto con la leaf producto.   
		 */
	protected String nombre;
    protected String descripcion;
    protected double descuento; //descuento en porcentaje
    
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getDescuento() { return descuento; }
    public abstract double getPrecioBase();
    public double getPrecioFinal() {
    	//devuelve el precio final del producto, en caso de tener descuento con una reduccion de su precio, sino con el mismo precio base establecido.
    	return  (this.getPrecioBase() * (1- this.getDescuento() ));
    }

    public ItemCatalogo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        descuento = 0;
    }
}
