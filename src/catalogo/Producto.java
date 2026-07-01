package catalogo;

import java.util.HashMap;
import java.util.Map;

public class Producto extends ItemCatalogo {
/* Propósito: implementar unidad básica del catalogo.
	   representa en el patron Composite del Catálogo de Productos: "Leaf"
	   
	 */
	private String sku;//Stock Keep Unit / Identificador
	private String marca;
	private double precioBase;
	private float peso;
	private Map<String, Object> atributosDinamicos = new HashMap<>();  //atributos dinamicos
	
	public Producto(String nombre, String descripcion,int stock, String sku, String categoria, String marca, double precioBase, float peso){
		super(nombre, descripcion,stock, categoria);
		this.sku = sku;
		this.marca = marca;
		this.precioBase = precioBase;
		this.peso = peso;
	}
	public void agregarAtributo(String nombre, Object valor) {
		//agrega un atributo dinámico al producto
		atributosDinamicos.put(nombre, valor);
	}

	public Object getAtributo(String nombre) {
		//devuelve el atributo solicitado si existe
		this.validarExisteAtributo(nombre);
		return atributosDinamicos.get(nombre);
	}
	
	public Object removeAtributo(String nombre) {
		//remueve el atributo solicitado si existe
		this.validarExisteAtributo(nombre);
		return atributosDinamicos.remove(nombre);
	}
	
	private void validarExisteAtributo(String nombre) {
		//indica la existencia de un atributo dinámico
	    if (!atributosDinamicos.containsKey(nombre)) {
	        throw new IllegalArgumentException("El atributo '" + nombre + "' no existe");
	    }
	}
	public boolean esValido() {
	    // retorna si los atributos son validos 
	   return super.esValido() && esSkuValido() && esAtributoDinamicoValido();  
	}
	
	private boolean esSkuValido() {
		//responde si un sku es valido
		return (sku != null &&  !sku.isBlank());
	}
	
	
	private boolean esPesoValido() {
		//responde si un peso es valido
		return (peso > 0);
	}
	
	private boolean esAtributoDinamicoValido() {
		 // valida que ningún atributo dinámico tenga valor null
	    for (Object valor : atributosDinamicos.values()) {
	        if (valor == null) return false;
	    }
	    return true;
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
	public String getMarca() {
		//devuelve la marca del producto
		return this.marca;
	}
	public String getSKU() {
		//devuelve la sku del producto
		return this.sku;
	}
	
	public String getCategoria() {
		//devuelve la categoria del producto
		return this.categoria;
	}
	
	public float getPeso() {
		//devuelve el peso del producto
		return this.peso;
	}
}
