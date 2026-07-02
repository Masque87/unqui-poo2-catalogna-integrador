package NotificacionesPedidoTest;
import estadoPedido.*;
import notificacionesPedido.*;
import metodoEnvio.EnvioExpress;
import metodoEnvio.MetodoEnvio;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import catalogo.*;

public class notificadorEmailTest {
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
		metodoEnvio = new EnvioExpress(10.00f, 450.00f);
		pedido = new Pedido(metodoEnvio);
		producto = new Producto("Auriculares Bluetooth", "Auriculares inalámbricos",10, "AUR-001", "Electronica", "Sony", 8000.0,2);
		producto1 = new Producto("Celular", "Smartphone",10, "Cel-001", "Electronica", "Samsung", 10000.0,1);
		mailSenderMock = mock(MailSender.class);
		notificadorEmail = new NotificadorEmail(mailSenderMock);
		generadorFactura = new GeneradorFactura();
		fidelizacion = new Fidelizacion(mailSenderMock);
		
		pedido.addNotificable(notificadorEmail);
		pedido.addItem(producto);
	}

	@Test
	void test_ElNotificadorSeAgregaAlPedido() {
		assertEquals(pedido.getNotificables().size(), 1);
	}
	
	@Test
	void test_SeLeNotificaAlClienteQueSuPedidoFueConfirmado() {
		pedido.siguienteEstado();
		verify(mailSenderMock).enviarMail("cliente@gmail.com","Confirmaste tu pedido","Tu pedido fue confirmado y pronto lo prepararemos.","adjunto.pdf");
	}
	
	@Test
	void test_SeLeNotificaAlClienteQueSuPedidoFueEnviado() {
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		verify(mailSenderMock).enviarMail("cliente@gmail.com","Enviamos tu pedido","Tu pedido fue enviado y lo recibirás pronto.","adjunto.pdf");
	}
	
	@Test
	void test_SeLeNotificaAlClienteQueSuPedidoFueEntregado() {
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		pedido.siguienteEstado();
		verify(mailSenderMock).enviarMail("cliente@gmail.com","Pedido entregado","Tu pedido fue entregado, ¡Muchas gracias por tu compra!","adjunto.pdf");
	}
}
