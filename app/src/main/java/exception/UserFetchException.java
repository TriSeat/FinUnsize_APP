package exception;

public class UserFetchException extends Exception {

    public UserFetchException(String message) {
        super(message);
    }

    public UserFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
