package catalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PaqueteTest {

    private Paquete paquete;
    private ItemCatalogo item1;
    private ItemCatalogo item2;

    @BeforeEach
    public void setUp() {
        paquete = new Paquete("Pack Audio Móvil", "Pack de audio", 2, 0.15, "Electronica");
        item1 = mock(ItemCatalogo.class);
        item2 = mock(ItemCatalogo.class);
        when(item1.getPrecioFinal()).thenReturn(8000.0);
        when(item2.getPrecioFinal()).thenReturn(2300.0);
    }

   //getters

    @Test
    public void testGetNombre() {
        assertEquals("Pack Audio Móvil", paquete.getNombre());
    }

    @Test
    public void testGetDescripcion() {
        assertEquals("Pack de audio", paquete.getDescripcion());
    }

    @Test
    public void testGetDescuento() {
        assertEquals(0.15, paquete.getDescuento());
    }

   //precioBase

    @Test
    public void testPrecioBaseVacio() {
        assertEquals(0.0, paquete.getPrecioBase());
    }

    @Test
    public void testPrecioBaseConItems() {
        paquete.addProducto(item1);
        paquete.addProducto(item2);
        assertEquals(10300.0, paquete.getPrecioBase());
    }

    @Test
    public void testPrecioBaseConsultaPrecioFinalDeItems() {
        paquete.addProducto(item1);
        paquete.addProducto(item2);
        paquete.getPrecioBase();
        // verifica que delegó en cada item
        verify(item1).getPrecioFinal();
        verify(item2).getPrecioFinal();
    }

   //PrecioFinal
    
    @Test
    public void testPrecioFinalConDescuento() {
        paquete.addProducto(item1);
        paquete.addProducto(item2);
        assertEquals(8755.0, paquete.getPrecioFinal());
    }

    @Test
    public void testPrecioFinalSinDescuento() {
        Paquete sinDescuento = new Paquete("Pack", "desc", 2,  0.0, "Cat");
        sinDescuento.addProducto(item1);
        assertEquals(8000.0, sinDescuento.getPrecioFinal());
    }


    @Test
    public void testPaqueteAnidadoDentroDeOtroPaquete() {
        paquete.addProducto(item1);
        paquete.addProducto(item2);

        Paquete paqueteExterno = new Paquete("Kit Home Office", "Kit completo", 2, 0.10, "Hogar");
        paqueteExterno.addProducto(paquete);

        // precioBase del externo = precioFinal del paquete interno = 8755.0
        assertEquals(8755.0, paqueteExterno.getPrecioBase());
        assertEquals(8755.0 * 0.90, paqueteExterno.getPrecioFinal(), 0.01);
    }
   
    //add y remove
    
    @Test
    public void testAddYRemoveItem() {
        paquete.addProducto(item1);
        paquete.remove(item1);
        assertEquals(0.0, paquete.getPrecioBase());
    }

    @Test
    public void testRemoveItemInexistenteLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> paquete.remove(item1));
    }
}