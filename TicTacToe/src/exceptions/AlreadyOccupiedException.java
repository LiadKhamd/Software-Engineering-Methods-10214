package exceptions;

public class AlreadyOccupiedException extends Exception {

	private static final long serialVersionUID = 2667166839476387610L;

	public AlreadyOccupiedException() {
		super();
	}

	public AlreadyOccupiedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AlreadyOccupiedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AlreadyOccupiedException(String message) {
		super(message);
	}

	public AlreadyOccupiedException(Throwable cause) {
		super(cause);
	}

}
