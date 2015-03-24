package coreservlets;

public class CustomerQueryException extends RuntimeException {

    private static final long serialVersionUID = 8236260638073331480L;

    public CustomerQueryException() {
        super();
    }

    public CustomerQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerQueryException(String message) {
        super(message);
    }

    public CustomerQueryException(Throwable cause) {
        super(cause);
    }

}
