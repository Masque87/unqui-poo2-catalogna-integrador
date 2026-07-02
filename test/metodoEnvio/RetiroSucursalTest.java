package metodoEnvio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;
import catalogo.Producto;
import estadoPedido.*;

class RetiroSucursalTest {
	
	protected Pedido pedido;
	protected ItemCatalogo producto;
	protected ItemCatalogo producto1;
	protected RetiroSucursal retiroSucursal;
	protected Sucursal sucursalMock;
	
	
	@BeforeEach
	void setUp() {
		sucursalMock = mock(Sucursal.class);
		retiroSucursal = new RetiroSucursal();
		pedido = new Pedido(retiroSucursal);
		producto = new Producto("Auriculares Bluetooth", "Auriculares inalámbricos",10, "AUR-001", "Electronica", "Sony", 8000.0,2);
		producto1 = new Producto("Celular", "Smartphone",10, "Cel-001", "Electronica", "Samsung", 10000.0, 2);
		pedido.addItem(producto);
		pedido.addItem(producto1);
		retiroSucursal.setSucursal(sucursalMock);
	}
	
	@Test
	void test_ElCostoDeUnRetiroEnSucursalEsSiempreCero() {
		assertEquals(0, retiroSucursal.calcularCosto(pedido));
	}
	
	@Test
	void test_LaSucursalNoTieneStockDeLosItems() {
		when(sucursalMock.tieneStock(pedido)).thenReturn(false);
		assertEquals("Entrega hasta 3 dias despues de la compra.", retiroSucursal.getTiempoDeEntrega(pedido));
		assertEquals(false, sucursalMock.tieneStock(pedido));
	}
	
	@Test
	void test_LaSucursalTieneStockDeLosItems() {
		when(sucursalMock.tieneStock(pedido)).thenReturn(true);
		assertEquals("Entrega inmediata", retiroSucursal.getTiempoDeEntrega(pedido));
		assertEquals(true, sucursalMock.tieneStock(pedido));
	}

}