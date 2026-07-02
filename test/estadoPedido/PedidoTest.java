package estadoPedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;
import catalogo.Producto;
import notificacionesPedido.*;
import metodoEnvio.*;



class PedidoTest {

	protected Pedido pedido;
	protected MetodoEnvio metodoEnvio;
	protected ItemCatalogo producto;
	protected ItemCatalogo producto1;
	protected NotificadorEmail notificadorEmail;
	protected GeneradorFactura generadorFactura;
	protected Fidelizacion fidelizacion;
	protected MailSender mailSenderMock;
	
	@BeforeEach
	public void setUp() {
		metodoEnvio = new EnvioExpress(10.00, 500.00);
		pedido = new Pedido(metodoEnvio);
		producto = new Producto("Auriculares Bluetooth", "Auriculares inalámbricos",10, "AUR-001", "Electronica", "Sony", 8000.0, 2);
		producto1 = new Producto("Celular", "Smartphone",10, "Cel-001", "Electronica", "Samsung", 10000.0, 1);
		mailSenderMock = mock(MailSender.class);
		notificadorEmail = new NotificadorEmail(mailSenderMock);
		generadorFactura = new GeneradorFactura();
		fidelizacion = new Fidelizacion(mailSenderMock);
	}
	
	
	@Test
	void test_ElEstadoInicialDeUnPedidoEsBorrador() {
		assertTrue(pedido.getEstado() instanceof Borrador);
	}
	
	@Test 
	void test_CancelarPedidoEnEtapaBorrador(){
		pedido.cancelarPedido();
		assertTrue(pedido.getEstado() instanceof Cancelado);	
	}
	
	@Test
	void test_CalcularSumaTotalDeLosProductos() {
		pedido.addItem(producto);
		pedido.addItem(producto1);
	}

	@Test
	void test_PasarDeEstadoBorradorAConfirmado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		assertTrue(pedido.getEstado() instanceof Confirmado);
	}
	@Test 
	void test_CancelarPedidoEnEtapaConfirmado(){
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.cancelarPedido();
		assertTrue(pedido.getEstado() instanceof Cancelado);
	}
	
	@Test
	void test_PasarDeEstadoConfirmadoAEnPreparacion() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertTrue(pedido.getEstado() instanceof EnPreparacion);
	}
	@Test 
	void test_NoEsPosibleCancelarPedidoEnPreparacion(){
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertThrows(IllegalStateException.class, () -> { pedido.cancelarPedido(); });
	}
	
	@Test
	void test_PasarDeEstadoEnPreparacionAEnviado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertTrue(pedido.getEstado() instanceof Enviado);
	}
	@Test
	void test_CancelarPedidoEnEtapaEnviado(){
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.cancelarPedido();
		assertTrue(pedido.getEstado() instanceof Cancelado);
	}
	
	@Test
	void test_PasarDeEstadoEnviadoAEntregado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertTrue(pedido.getEstado() instanceof Entregado);
	}
	
	
	
	@Test
	void test_AgregarNotificablesAlPedido() {
		pedido.addNotificable(notificadorEmail);
		pedido.addNotificable(fidelizacion);
		assertEquals(pedido.getNotificables().size(), 2);
	}
	
	@Test
	void test_RemoverNotificablesDelPedido() {
		pedido.addNotificable(notificadorEmail);
		pedido.addNotificable(fidelizacion);
		pedido.removeNotificable(fidelizacion);
		assertEquals(pedido.getNotificables().size(), 1);
	}
	
	@Test
	void test_SePuedenAgregarItemsEnLaEtapaDeBorrador() {
		pedido.addItem(producto);
		assertEquals(pedido.items.size(), 1);
	}
	
	@Test
	void test_SePuedenRemoverItemsEnLaEtapaDeBorrador() {
		pedido.addItem(producto);
		pedido.addItem(producto1);
		pedido.removeItem(producto);
		assertEquals(pedido.items.size(), 1);
	}
	
	@Test
	void test_NoSePuedeAgregarProductosEnLaEtapaDeConfirmado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		assertThrows(IllegalArgumentException.class, () -> { pedido.addItem(producto); });
	}
	
	@Test
	void test_NoSePuedeRemoverProductosEnLaEtapaDeConfirmado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		assertThrows(IllegalArgumentException.class, () -> {
			pedido.removeItem(producto); });
	}
	
	
	@Test
	void test_NoSePuedenAgregarItemsEnLaEtapaDePreparacion() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertThrows(IllegalArgumentException.class, () -> { pedido.addItem(producto);});
	}
		
	@Test
	void test_NoSePuedeRemoverProductosEnLaEtapaDePreparacion() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertThrows(IllegalArgumentException.class, () -> {
			pedido.removeItem(producto); });
	}
	
	@Test
	void test_NoSePuedenAgregarItemsEnLaEtapaDeEnviado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertThrows(IllegalArgumentException.class, () -> { pedido.addItem(producto);});
	}
		
	@Test
	void test_NoSePuedenRemoverItemsEnLaEtapaDeEnviado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertThrows(IllegalArgumentException.class, () -> { pedido.removeItem(producto);});
	}
	
	@Test
	void test_NoSePuedenAgregarItemsEnLaEtapaDeEntregado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertThrows(IllegalArgumentException.class, () -> { pedido.addItem(producto);});
	}
		
	@Test
	void test_NoSePuedenRemoverItemsEnLaEtapaDeEntregado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertThrows(IllegalArgumentException.class, () -> { pedido.removeItem(producto);});
	}
	
	@Test
	void test_NoSePuedenAgregarItemsEnUnPedidoCancelado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.cancelarPedido();
		assertThrows(IllegalArgumentException.class, () -> { pedido.addItem(producto);});
	}
		
	@Test
	void test_NoSePuedenRemoverItemsEnUnPedidoCancelado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.cancelarPedido();
		assertThrows(IllegalArgumentException.class, () -> { pedido.removeItem(producto);});
	}
	
	@Test
	void test_UnPedidoEntregadoNoPuedeAvanzarAlSiguienteEstado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertThrows(IllegalStateException.class, () -> { pedido.siguienteEstado();});
		
	}
	
	@Test
	void test_UnPedidoCanceladoNoPuedeAvanzarAlSiguienteEstado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.cancelarPedido();
		assertThrows(IllegalStateException.class, () -> { pedido.siguienteEstado();});
	}
	
	@Test
	void test_UnPedidoEntregadoNoPuedeSerCancelado() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertThrows(IllegalStateException.class, () -> { pedido.cancelarPedido();});
	}
	
	@Test
	void test_UnPedidoNoPuedeSerCanceladoDosVeces() {
		pedido.addItem(producto);
		pedido.siguienteEstado();
		pedido.cancelarPedido();
		assertThrows(IllegalStateException.class, () -> { pedido.cancelarPedido();});
	}
	
	@Test
	void test_NoEsPosibleAvanzarDeEstadoEnUnPedidoSinItems() {
		assertThrows(IllegalStateException.class, () -> { 
			pedido.siguienteEstado(); });
	}
	
	@Test
	void test_NoSePuedenAgregarItemsSinStock(){
		producto.setStock(0);
		assertThrows(IllegalArgumentException.class, () -> { 
			pedido.addItem(producto); });
	}
	
	@Test 
	void test_NoSePuedenRemoverItemsQueNoEstenEnElPedido() {
		assertThrows(IllegalArgumentException.class, () -> {
			pedido.removeItem(producto);});
	}


}
	

	


