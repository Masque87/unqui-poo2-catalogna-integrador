package busqueda;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Producto;

public class CriterioPorDisponibilidadTest {
//Testear la Clase CriterioPorDisponibilidad, revisando casos segun el stock
    private CriterioPorDisponibilidad criterio;
    private Producto producto;

    @BeforeEach
    void setUp() {
        criterio = new CriterioPorDisponibilidad();
    }

    @Test
    void cumpleConProductoConStock() {
        producto = new Producto("Auriculares", "desc", 10, "SKU001", "Electronica", "Sony", 8000, 2.5f);
        assertTrue(criterio.cumple(producto));
    }

    @Test
    void nocumpleConProductoSinStock() {
        producto = new Producto("Auriculares", "desc", 0, "SKU001", "Electronica", "Sony", 8000, 2.5f);
        assertFalse(criterio.cumple(producto));
    }
}
