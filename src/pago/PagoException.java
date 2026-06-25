package pago;

public class PagoException extends RuntimeException  {
	//Proposito: modelar excepciones genericas para los pagos 
	private static final long serialVersionUID = 1L; //exigencia de eclipse

	public PagoException(String mensaje) {
        super(mensaje);
    }
}
