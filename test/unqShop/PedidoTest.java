package unqShop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unqShop.estadoPedido.Borrador;
import unqShop.estadoPedido.Cancelado;
import unqShop.estadoPedido.Confirmado;
import unqShop.estadoPedido.EnPreparacion;
import unqShop.estadoPedido.Entregado;
import unqShop.estadoPedido.Enviado;



class PedidoTest {

	protected Pedido pedido;
	protected ItemCatalogo producto;
	protected ItemCatalogo producto1;
	
	@BeforeEach
	public void setUp() {
		pedido = new Pedido();
		producto = new Producto("Auriculares Bluetooth", "Auriculares inalámbricos",10, "AUR-001", "Electronica", "Sony", 8000.0);
		producto1 = new Producto("Celular", "Smartphone",10, "Cel-001", "Electronica", "Samsung", 10000.0);
	}
	
	
	@Test
	void testElEstadoInicialDeUnPedidoEsBorrador() {
		assertTrue(pedido.getEstado() instanceof Borrador);
	}
	@Test 
	void testCancelarPedidoEnEtapaBorrador(){
		pedido.cancelarPedido();
		assertTrue(pedido.getEstado() instanceof Cancelado);
		
	}

	@Test
	void testPasarDeEstadoBorradorAConfirmado() {
	pedido.siguienteEstado();
	assertTrue(pedido.getEstado() instanceof Confirmado);
	}
	@Test 
	void testCancelarPedidoEnEtapaConfirmado(){
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.cancelarPedido();
		assertTrue(pedido.getEstado() instanceof Cancelado);
	}
	
	@Test
	void testPasarDeEstadoConfirmadoAEnPreparacion() {
	pedido.siguienteEstado();
	pedido.siguienteEstado();
	assertTrue(pedido.getEstado() instanceof EnPreparacion);
	}
	@Test 
	void testNoEsPosibleCancelarPedidoEnPreparacion(){
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertThrows(IllegalStateException.class, () -> { pedido.cancelarPedido(); });
	}
	
	@Test
	void testPasarDeEstadoEnPreparacionAEnviado() {
	pedido.siguienteEstado();
	pedido.siguienteEstado();
	pedido.siguienteEstado();
	assertTrue(pedido.getEstado() instanceof Enviado);
	}
	@Test
	void testCancelarPedidoEnEtapaEnviado(){
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.cancelarPedido();
		assertTrue(pedido.getEstado() instanceof Cancelado);
	}
	
	@Test
	void testPasarDeEstadoEnviadoAEntregado() {
	pedido.siguienteEstado();
	pedido.siguienteEstado();
	pedido.siguienteEstado();
	pedido.siguienteEstado();
	assertTrue(pedido.getEstado() instanceof Entregado);
	}
	
	@Test
	void testSePuedenAgregarItemsEnLaEtapaDeBorrador() {
		pedido.addItem(producto);
		assertEquals(pedido.items.size(), 1);
	}
	
	@Test
	void testSePuedenRemoverrItemsEnLaEtapaDeBorrador() {
		pedido.addItem(producto);
		pedido.addItem(producto1);
		pedido.removeItem(producto);
		assertEquals(pedido.items.size(), 1);
	}
	
	@Test
	void testNoSePuedeAgregarProductosEnLaEtapaDeConfirmado() {
		pedido.siguienteEstado();
		assertThrows(IllegalArgumentException.class, () -> { pedido.addItem(producto); });
	}
	
	@Test
	void testNoSePuedenAgregarItemsSinStock(){
		producto.setStock(0);
		assertThrows(IllegalArgumentException.class, () -> { pedido.addItem(producto); });
	}
	
	@Test
	void testNoSePuedeRemoverProductosEnLaEtapaDeConfirmado() {
		pedido.siguienteEstado();
		assertThrows(IllegalArgumentException.class, () -> { pedido.removeItem(producto); });
	}
		
}
