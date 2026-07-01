package metodoEnvio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;
import catalogo.Producto;
import estadoPedido.Borrador;
import estadoPedido.Pedido;

class EnvioTest {
	
	private Pedido pedido;
	protected ItemCatalogo producto;
	protected ItemCatalogo producto1;
	protected EnvioEstandar envioEstandar;
	protected EnvioExpress envioExpress;
	protected RetiroSucursal retiroSucursal;
	
	@BeforeEach
	void setUp() throws Exception {
		pedido = new Pedido();
		producto = new Producto("Auriculares Bluetooth", "Auriculares inalámbricos",10, "AUR-001", "Electronica", "Sony", 8000.0, 0.75f);
		producto1 = new Producto("Celular", "Smartphone",10, "Cel-001", "Electronica", "Samsung", 10000.0, 2);
		pedido.addItem(producto);
		pedido.addItem(producto1);
		envioEstandar = new EnvioEstandar(pedido);
		envioExpress = new EnvioExpress(pedido);
		retiroSucursal = new RetiroSucursal(pedido);
	}

	@Test
	void testElEnvioEstandarCalculaElCostoEnBaseAlPesoYDistancia() {	
		//Test Double Configuration
		when(envioEstandar.getCosto()).thenReturn(1300.0);
				
		//Exercise
		;
				
		//Verify
		verify(otroGuerrero, times(1)).descontarVida(500);
	}
	
	@Test
	void testElEnvioExpressCalculaElCostoEnBaseAlValorDelPedido() {
		fail("Not yet implemented");
	}
	
	@Test
	void testElRetiroEnSucursalEsInmediatoSiHayStockEnLaSucursal() {
		//Test Double Configuration
		when(retiroSucursal.conEntregaInmediata()).thenReturn(true);
		
		assertEquals("Envio inmediato.", retiroSucursal.getTiempoDeEntrega());
	}
	
	@Test
	void testElRetiroEnSucursalNoEsInmediatoSiNoHayStockEnLaSucursal() {
		//Test Double Configuration
		when(retiroSucursal.conEntregaInmediata()).thenReturn(false);
				
		assertEquals("Entrega hasta 3 dias despues de la compra.", retiroSucursal.getTiempoDeEntrega());
	}
	

}
