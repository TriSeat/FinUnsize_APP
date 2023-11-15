package exception;

public class PurchaseItemFetchException extends Exception {

    public PurchaseItemFetchException(String message) {
        super(message);
    }

    public PurchaseItemFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
