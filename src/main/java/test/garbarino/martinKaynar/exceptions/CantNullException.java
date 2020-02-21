package test.garbarino.martinKaynar.exceptions;

public class CantNullException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CantNullException(String objeto) {
		super(objeto + " is Mandatory");
	}
}
