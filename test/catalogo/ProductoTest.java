package catalogo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductoTest {
//proposito: testear la mayor cantidad posible de metodos y funcionalidades en Producto
    private Producto producto;

    @BeforeEach
    public void setUp() {
        producto = new Producto("Auriculares Bluetooth", "Auriculares inalámbricos", 10, "AUR-001", "Electronica", "Sony", 8000.0, 0.75f);
    }

  //setters y getters

    @Test
    public void testGetNombre() {
        assertEquals("Auriculares Bluetooth", producto.getNombre());
    }

    @Test
    public void testGetDescripcion() {
        assertEquals("Auriculares inalámbricos", producto.getDescripcion());
    }
    
    @Test
	void testGetStock() {
		assertEquals(producto.getStock(), 10);
	}

    @Test
    public void testGetSKU() {
        assertEquals("AUR-001", producto.getSKU());
    }

    @Test
    public void testGetCategoria() {
        assertEquals("Electronica", producto.getCategoria());
    }

    @Test
    public void testGetMarca() {
        assertEquals("Sony", producto.getMarca());
    }

    @Test
    public void testGetPrecioBase() {
        assertEquals(8000.0, producto.getPrecioBase());
    }

    

    @Test
    public void testDescuentoInicialEsCero() {
        assertEquals(0, producto.getDescuento());
    }

    @Test
    public void testPrecioFinalSinDescuento() {
        assertEquals(8000.0, producto.getPrecioFinal());
    }

    @Test
    public void testPrecioFinalConDescuento() {
        producto.setDescuento(0.15);
        assertEquals(6800.0, producto.getPrecioFinal());
    }

   //atributos dinamicos
    
    @Test
    public void testAgregarYObtenerAtributo() {
        producto.agregarAtributo("Peso", 0.3);
        assertEquals(0.3, producto.getAtributo("Peso"));
    }

    @Test
    public void testGetAtributoInexistenteLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> producto.getAtributo("Color"));
    }

    @Test
    public void testRemoveAtributo() {
        producto.agregarAtributo("Peso", 0.3);
        producto.removeAtributo("Peso");
        assertThrows(IllegalArgumentException.class, () -> producto.getAtributo("Peso"));
    }

    @Test
    public void testRemoveAtributoInexistenteLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> producto.removeAtributo("Inexistente"));
    }


    //validaciones

    @Test
    public void testEsValidoProductoCompleto() {
        assertTrue(producto.esValido());
    }
    
    @Test 
    void testTieneStock() {
    	assertTrue(producto.tieneStock());
    }
    
    @Test 
    void testNoTieneStock() {
    	producto.setStock(0);
    	assertFalse(producto.tieneStock());
    }

    @Test
    public void testEsValidoConAtributosDinamicosCompletos() {
        producto.agregarAtributo("Peso", 0.3);
        producto.agregarAtributo("Alto", 15.0);
        assertTrue(producto.esValido());
    }

    @Test
    public void testNoEsValidoConAtributoDinamicoNull() {
        producto.agregarAtributo("Peso", null);
        assertFalse(producto.esValido());
    }

    @Test
    public void testNoEsValidoSinSKU() {
        Producto sinSku = new Producto("Auriculares Bluetooth", "Auriculares inalámbricos",10 ,null, "Electronica", "Sony", 8000.0, 0.75f);
        assertFalse(sinSku.esValido());
    }
    @Test
    public void testNoEsValidoConSKUVacio() {
        Producto sinSku = new Producto("Auriculares", "desc", 10,"", "Electronica", "Sony", 8000.0, 0.75f);
        assertFalse(sinSku.esValido());
    }

    @Test
    public void testNoEsValidoConNombreVacio() {
        Producto sinNombre = new Producto("", "desc",10, "AUR-001", "Electronica", "Sony", 8000.0, 0.75f);
        assertFalse(sinNombre.esValido());
    }
    @Test
    public void testNoEsValidoConSKUNull() {
        Producto sinSku = new Producto("Auriculares", "desc",10, null, "Electronica", "Sony", 8000.0, 0.75f);
        assertFalse(sinSku.esValido());
    }

    @Test
    public void testNoEsValidoConNombreNull() {
        Producto sinNombre = new Producto(null, "desc",10, "AUR-001", "Electronica", "Sony", 8000.0, 0.75f);
        assertFalse(sinNombre.esValido());
    }
    
    @Test
    public void testNoEsValidoConPesoCero() {
        Producto sinPeso = new Producto("Auriculares", "desc",10, "AUR-001", "Electronica", "Sony", 8000.0, 0);
        assertFalse(sinPeso.esValido());
    }
    
    @Test
    public void testNoEsValidoConPesoNegativo() {
        Producto pesoNegativo = new Producto("Auriculares", "desc",10, "AUR-001", "Electronica", "Sony", 8000.0, -15.0f);
        assertFalse(pesoNegativo.esValido());
    }
    
    //TODO: Falta agregar los tests que validan un precio valido (precio igual o mayor a cero)
}
