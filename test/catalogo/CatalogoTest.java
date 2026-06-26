package catalogo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import busqueda.CriterioPorNombre;

public class CatalogoTest {
//Proposito: Testear la clase Catalogo, con casos generalizados
    private Catalogo catalogo;
    private Producto auriculares;
    private Producto teclado;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo();
        auriculares = new Producto("Auriculares Bluetooth", "desc", 10, "SKU001", "Electronica", "Sony", 8000);
        teclado = new Producto("Teclado Mecánico", "desc", 5, "SKU002", "Electronica", "Logitech", 12000);
        catalogo.agregar(auriculares);
        catalogo.agregar(teclado);
    }

    @Test
    void buscarConCriterioQueMatcheaUnItem() {
        var resultado = catalogo.buscar(new CriterioPorNombre("Auriculares"));
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(auriculares));
    }

    @Test
    void buscarConCriterioQueMatcheaTodos() {
        var resultado = catalogo.buscar(new CriterioPorNombre("a")); // pq ambos contienen la letra a
        assertEquals(2, resultado.size());
    }

    @Test
    void buscarConCriterioQueNoMatcheaNinguno() {
        var resultado = catalogo.buscar(new CriterioPorNombre("mouse"));
        assertTrue(resultado.isEmpty());
    }

    @Test
    void buscarEnCatalogoVacio() {
        Catalogo vacio = new Catalogo();
        var resultado = vacio.buscar(new CriterioPorNombre("algo"));
        assertTrue(resultado.isEmpty());
    }
}