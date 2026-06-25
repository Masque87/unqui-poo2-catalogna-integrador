package metodoEnvio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;
import catalogo.Producto;
import estadoPedido.Pedido;

class EnvioTest {
	
	protected Pedido pedido;
	protected ItemCatalogo producto;
	protected ItemCatalogo producto1;
	
	@BeforeEach
	void setUp() throws Exception {
		pedido = new Pedido();
		producto = new Producto("Auriculares Bluetooth", "Auriculares inalámbricos",10, "AUR-001", "Electronica", "Sony", 8000.0);
		producto1 = new Producto("Celular", "Smartphone",10, "Cel-001", "Electronica", "Samsung", 10000.0);
	}

	@Test
	void testElEnvioEstandarCalculaElCostoEnBaseAlPesoYDistancia() {
		fail("Not yet implemented");
	}
	
	@Test
	void testElEnvioExpressCalculaElCostoEnBaseAlValorDelPedido() {
		fail("Not yet implemented");
	}
	
	@Test
	void testElRetiroEnSucursalEsInmediatoSiHayStockEnLaSucursal() {
		fail("Not yet implemented");
	}
	
	@Test
	void testElRetiroEnSucursalNoEsInmediatoSiNoHayStockEnLaSucursal() {
		fail("Not yet implemented");
	}
	

}
