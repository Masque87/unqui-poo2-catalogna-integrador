package busqueda;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Producto;
import java.util.List;

public class CriterioORTest {
//Proposito: Testear la clase CriterioOR. Nota: En base a 3 casos de la tabla de verdad OR
    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto("Auriculares Bluetooth", "desc", 10, "SKU001", "Electronica", "Sony", 8000);
    }

    @Test
    void cumpleSiUnCriterioSeCumple() {
        CriterioOR criterio = new CriterioOR(List.of(
            new CriterioPorNombre("Auriculares"),
            new CriterioPorCategoria("Hogar")
        ));
        assertTrue(criterio.cumple(producto));
    }

    @Test
    void cumpleSiTodosLosCriteriosSeCumplen() {
        CriterioOR criterio = new CriterioOR(List.of(
            new CriterioPorNombre("Auriculares"),
            new CriterioPorCategoria("Electronica")
        ));
        assertTrue(criterio.cumple(producto));
    }

    @Test
    void noCumpleSiNingunCriterioSeCumple() {
        CriterioOR criterio = new CriterioOR(List.of(
            new CriterioPorNombre("Teclado"),
            new CriterioPorCategoria("Hogar")
        ));
        assertFalse(criterio.cumple(producto));
    }
}