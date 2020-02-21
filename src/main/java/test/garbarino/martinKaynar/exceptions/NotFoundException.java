package test.garbarino.martinKaynar.exceptions;

public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NotFoundException(String objeto, Long id) {
		super(objeto + " id not found : " + id);
	}
}
