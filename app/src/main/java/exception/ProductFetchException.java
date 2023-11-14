package exception;


public class ProductFetchException extends Exception {

    public ProductFetchException(String message) {
        super(message);
    }

    public ProductFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}