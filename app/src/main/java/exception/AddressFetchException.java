package exception;

public class AddressFetchException extends Exception {

    public AddressFetchException(String message) {
        super(message);
    }

    public AddressFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
