package notificacionesPedido;

public interface MailSender {
	//Proposito: Simula un mailsender
	public void enviarMail(String direcciónDestino, String titulo, String mensaje, String adjunto);
}
