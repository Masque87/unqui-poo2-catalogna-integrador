package busqueda;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Producto;

public class CriterioPorPrecioMaximoTest {
//Proposito: Testear la clase CriterioPorPrecioMaximo. Nota: evalua diferentes casos segun el precio de un producto
    private Producto producto;   

    @BeforeEach
    void setUp() {
        producto = new Producto("Auriculares", "desc", 10, "SKU001", "Electronica", "Sony", 8000);
    }

    @Test
    void cumpleConPrecioMenorAlMaximo() {
        assertTrue(new CriterioPorPrecioMaximo(10000).cumple(producto));
    }

    @Test
    void cumpleConPrecioIgualAlMaximo() {
        assertTrue(new CriterioPorPrecioMaximo(8000).cumple(producto));
    }

    @Test
    void noCumpleConPrecioMayorAlMaximo() {
        assertFalse(new CriterioPorPrecioMaximo(5000).cumple(producto));
    }
}
