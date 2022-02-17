package in.nareshit.raghu.exception;

public class AppoinmentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppoinmentNotFoundException() {
		super();
	}

	public AppoinmentNotFoundException(String message) {
		super(message);
	}
}
