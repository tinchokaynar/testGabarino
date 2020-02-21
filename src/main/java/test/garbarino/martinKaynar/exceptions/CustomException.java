package test.garbarino.martinKaynar.exceptions;

public class CustomException extends RuntimeException{

	private static final long serialVersionUID = -705427488571227788L;

	public CustomException(String mensaje) {
		super(mensaje);
	}
	
}
