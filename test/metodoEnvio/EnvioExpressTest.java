package metodoEnvio;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import catalogo.ItemCatalogo;
import catalogo.Producto;
import estadoPedido.Pedido;

public class EnvioExpressTest {

	
	protected Pedido pedido;
	protected ItemCatalogo producto;
	protected ItemCatalogo producto1;
	protected EnvioExpress envioExpress;
	
	@BeforeEach
	void setUp() {
		envioExpress = new EnvioExpress(0.10f, 1000.00f);
		pedido = new Pedido(envioExpress);
		producto = new Producto("Auriculares Bluetooth", "Auriculares inalámbricos",10, "AUR-001", "Electronica", "Sony", 8000.0,2);
		producto1 = new Producto("Celular", "Smartphone",10, "Cel-001", "Electronica", "Samsung", 10000.0, 2);
		pedido.addItem(producto);
		pedido.addItem(producto1);
	}
	
	@Test
	void test_ElEnvioCalculaElCosto() {
		assertEquals(2800.00, envioExpress.calcularCosto(pedido));
	}
	
	@Test
	void test_ElEnvioDevuelveElTiempoDeEntrega() {
		assertEquals("Entrega un dia habil despues de la compra.",envioExpress.getTiempoDeEntrega(pedido));
	}
}
