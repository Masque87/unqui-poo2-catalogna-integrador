package busqueda;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Producto;

public class CriterioNOTTest {
//Proposito: Testear la clase CriterioNOT. Nota: En base a los 2 casos de la tabla de verdad NOT
    private Producto producto;
    private CriterioNOT criterio;

    @BeforeEach
    void setUp() {
        producto = new Producto("Auriculares Bluetooth", "desc", 10, "SKU001", "Electronica", "Sony", 8000);
        criterio = new CriterioNOT(new CriterioPorNombre("Auriculares"));
    }

    @Test
    void noCumpleSiElCriterioSeCumple() {
        assertFalse(criterio.cumple(producto));
    }

    @Test
    void cumpleSiElCriterioNoSeCumple() {
        assertTrue(new CriterioNOT(new CriterioPorNombre("Teclado")).cumple(producto));
    }
}