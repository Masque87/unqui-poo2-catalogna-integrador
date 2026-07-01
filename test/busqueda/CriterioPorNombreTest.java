package busqueda;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Producto;

public class CriterioPorNombreTest {
//Proposito: Testear la clase CrierioPorNombre. Nota: basado en casos segun el nombre
    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto("Auriculares Bluetooth", "desc", 10, "SKU001", "Electronica", "Sony", 8000, 2.5f);
    }

    @Test
    void cumpleConNombreExacto() {
        assertTrue(new CriterioPorNombre("Auriculares Bluetooth").cumple(producto));
    }

    @Test
    void cumpleConNombreIncompleto() {
        assertTrue(new CriterioPorNombre("Auriculares").cumple(producto));
    }

    @Test
    void cumpleConDistintasMayusculas() {
        assertTrue(new CriterioPorNombre("auriculares").cumple(producto));
    }

    @Test
    void noCumpleConNombreDistinto() {
        assertFalse(new CriterioPorNombre("Teclado").cumple(producto));
    }
}