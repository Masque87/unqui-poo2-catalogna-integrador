package busqueda;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Producto;
import java.util.List;

public class CriterioANDTest {
//Proposito: Testear la clase CriterioAND. Nota: Evalua 3 casos segun la tabla de verdad del AND
    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto("Auriculares Bluetooth", "desc", 10, "SKU001", "Electronica", "Sony", 8000, 2.5f);
    }

    @Test
    void cumpleSiTodosLosCriteriosSeCumplen() {
        CriterioAND criterio = new CriterioAND(List.of(
            new CriterioPorNombre("Auriculares"),
            new CriterioPorCategoria("Electronica"),
            new CriterioPorPrecioMaximo(10000)
        ));
        assertTrue(criterio.cumple(producto));
    }

    @Test
    void noCumpleSiUnCriterioNoSeCumple() {
        CriterioAND criterio = new CriterioAND(List.of(
            new CriterioPorNombre("Auriculares"),
            new CriterioPorCategoria("Hogar")
        ));
        assertFalse(criterio.cumple(producto));
    }

    @Test
    void noCumpleSiNingunCriterioSeCumple() {
        CriterioAND criterio = new CriterioAND(List.of(
            new CriterioPorNombre("Teclado"),
            new CriterioPorCategoria("Hogar")
        ));
        assertFalse(criterio.cumple(producto));
    }
}
