package NotificacionesPedidoTest;
import estadoPedido.*;
import notificacionesPedido.*;
import metodoEnvio.EnvioExpress;
import metodoEnvio.MetodoEnvio;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import catalogo.*;

public class GeneradorFacturaTest {
	protected Pedido pedido;
	protected MetodoEnvio metodoEnvio;
	protected ItemCatalogo producto;
	protected ItemCatalogo producto1;
	protected GeneradorFactura generadorFactura;
	
	@BeforeEach
	public void setUp() {
		metodoEnvio = new EnvioExpress(10.00, 450.00);
		pedido = new Pedido(metodoEnvio);
		producto = new Producto("Auriculares Bluetooth", "Auriculares inalámbricos",10, "AUR-001", "Electronica", "Sony", 8000.0,2);
		producto1 = new Producto("Celular", "Smartphone",10, "Cel-001", "Electronica", "Samsung", 10000.0,1);
		generadorFactura = new GeneradorFactura();

		pedido.addNotificable(generadorFactura);
		pedido.addItem(producto);
		pedido.addItem(producto1);
	}

	@Test
	void test_ElNotificadorSeAgregaAlPedido() {
		assertEquals(pedido.getNotificables().size(), 1);
	}
	
	@Test 
	void test_SeGeneraUnaFactura() {
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		assertEquals(1, generadorFactura.getFacturas().size());
	}
	
}