package busqueda;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Producto;

public class CriterioPorCategoriaTest {
//Proposito: Testear la clase CriterioPorCategoria. Nota: revisando casos con mayusculas y minusculas
    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto("Auriculares", "desc", 10, "SKU001", "Electronica", "Sony", 8000, 2.5f);
    }

    @Test
    void cumpleConCategoriaExacta() {
        assertTrue(new CriterioPorCategoria("Electronica").cumple(producto));
    }

    @Test
    void cumpleConDistintasMayusculas() {
        assertTrue(new CriterioPorCategoria("electronica").cumple(producto));
    }

    @Test
    void nocumpleConCategoriaDistinta() {
        assertFalse(new CriterioPorCategoria("Hogar").cumple(producto));
    }
}