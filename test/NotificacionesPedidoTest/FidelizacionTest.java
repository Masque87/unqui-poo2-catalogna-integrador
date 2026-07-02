package NotificacionesPedidoTest;
import estadoPedido.*;
import notificacionesPedido.*;
import metodoEnvio.EnvioExpress;
import metodoEnvio.MetodoEnvio;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import catalogo.*;

public class FidelizacionTest {
	protected Pedido pedido;
	protected MetodoEnvio metodoEnvio;
	protected ItemCatalogo producto;
	protected ItemCatalogo producto1;
	protected Fidelizacion fidelizacion;
	protected MailSender mailSenderMock;
	
	@BeforeEach
	public void setUp() {
		metodoEnvio = new EnvioExpress(10.00f, 450.00f);
		pedido = new Pedido(metodoEnvio);
		producto = new Producto("Auriculares Bluetooth", "Auriculares inalámbricos",10, "AUR-001", "Electronica", "Sony", 8000.0,2);
		producto1 = new Producto("Celular", "Smartphone",10, "Cel-001", "Electronica", "Samsung", 10000.0,1);
		mailSenderMock = mock(MailSender.class);
		fidelizacion = new Fidelizacion(mailSenderMock);

		pedido.addNotificable(fidelizacion);
		pedido.addItem(producto);
	}

	@Test
	void test_ElNotificadorSeAgregaAlPedido() {
		assertEquals(pedido.getNotificables().size(), 1);
	}
	
	@Test
	void test_SeEnviaUnCuponAlCLienteAlCancelarElPedido() {
		pedido.siguienteEstado();
		pedido.cancelarPedido();
		verify(mailSenderMock).enviarMail("cliente@gmail.com", "Te enviamos un cupón",
				"Lamentamos mucho que hayas cancelado tu pedido. Te dejamos un cupón del 5% para tu próxima compra", "cupón");
	}
	
}