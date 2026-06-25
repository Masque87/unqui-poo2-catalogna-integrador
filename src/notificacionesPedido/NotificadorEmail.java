package notificacionesPedido;
import estadoPedido.*;

public class NotificadorEmail implements Notificable{
	protected MailSender mailSender;
	
	public NotificadorEmail(MailSender m) {
		this.mailSender = m;
	}
	
	public void recibir(Estado anterior, Estado nuevoEstado) {
		 nuevoEstado.notificarAlCliente(this);
	}
	
	public void notificarPedidoConfirmado() {
		mailSender.enviarMail("cliente@gmail.com","Confirmaste tu pedido","Tu pedido fue confirmado y pronto lo prepararemos.","adjunto.pdf");
	}
	
	public void notificarPedidoEnviado() {
		mailSender.enviarMail("cliente@gmail.com","Enviamos tu pedido","Tu pedido fue enviado y lo recibirás pronto.","adjunto.pdf");
	}
	
	public void notificarPedidoEntregado() {
		mailSender.enviarMail("cliente@gmail.com","Pedido entregado","Tu pedido fue entregado, ¡Muchas gracias por tu compra!","adjunto.pdf");
	}
}
