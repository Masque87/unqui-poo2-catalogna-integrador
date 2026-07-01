package notificacionesPedido;
import estadoPedido.*;

public class Fidelizacion implements Notificable{
	private MailSender mailsender;
	
	
	public Fidelizacion(MailSender mailsender) {
		this.mailsender = mailsender;
	}
	public void recibir(Estado anterior, Estado nuevo) {
		this.enviarCorreoDeFidelizacion(nuevo);
	}
	
	
	public void enviarCorreoDeFidelizacion(Estado estado) {
		estado.enviarCorreoConCupon(this);
		}
	

	public void enviarCuponAlCliente() {
		mailsender.enviarMail("cliente@gmail.com", "Te enviamos un cupón",
				"Lamentamos mucho que hayas cancelado tu pedido. Te dejamos un cupón del 5% para tu próxima compra", "cupón");	
	}
	
}
