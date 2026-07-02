package metodoEnvio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;
import catalogo.Producto;
import estadoPedido.*;

class EnvioEstandarTest {
	
	protected Pedido pedido;
	protected ItemCatalogo producto;
	protected ItemCatalogo producto1;
	protected EnvioEstandar envioEstandar;
	protected EnvioExpress envioExpress;
	protected RetiroSucursal retiroSucursal;
	protected CorreoArgentina correoArgentinaMock;
	protected Direccion direccionMock;
	
	
	@BeforeEach
	void setUp() {
		direccionMock = mock(Direccion.class);
		correoArgentinaMock = mock(CorreoArgentina.class);
		envioEstandar = new EnvioEstandar(direccionMock, correoArgentinaMock);
		pedido = new Pedido(envioEstandar);
		producto = new Producto("Auriculares Bluetooth", "Auriculares inalámbricos",10, "AUR-001", "Electronica", "Sony", 8000.0,2);
		producto1 = new Producto("Celular", "Smartphone",10, "Cel-001", "Electronica", "Samsung", 10000.0, 2);
		pedido.addItem(producto);
		pedido.addItem(producto1);
	}

	@Test
	void test_ElEnvioEstandarCalculaElCosto() {	
		envioEstandar.calcularCosto(pedido);
		verify(correoArgentinaMock).estimarEnvio(4, direccionMock);
	}
	
	@Test
	void test_ElEnvioEstandarDevuelveElTiempoEstimado() {
		assertEquals(envioEstandar.getTiempoDeEntrega(pedido), "Entrega de 5 a 7 dias habiles despues de la compra.");
	}
}