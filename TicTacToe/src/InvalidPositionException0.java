package RRRR;

public class InvalidPositionException extends Exception {

	private static final long serialVersionUID = -3007576910224700623L;

	public InvalidPositionException() {
		super();
	}

	public InvalidPositionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidPositionException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPositionException(String message) {
		super(message);
	}

	public InvalidPositionException(Throwable cause) {
		super(cause);
	}

}
