package unqShop;

public class Producto extends ItemCatalogo {

	private String sku;//Stock Keep Unit / Identificador
	private String categoria;
	private String marca;
	private double precioBase;
	public Producto(String nombre, String descripcion, String sku, String categoria, String marca, double precioBase){
		super(nombre, descripcion);
		this.sku = sku;
		this.categoria = categoria;
		this.marca = marca;
		this.precioBase = precioBase;
	}
	
	public void setDescuento(double nuevoDescuento) {
		//establece un nuevo descuento en el producto
		this.descuento = nuevoDescuento;
	}	
		
	@Override
	public double getPrecioBase() {
		//devuelve el precio base sin descuentos aplicados
		return this.precioBase;
	}

}
