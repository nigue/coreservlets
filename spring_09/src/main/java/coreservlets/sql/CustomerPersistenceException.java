package coreservlets.sql;

public class CustomerPersistenceException extends RuntimeException {

    private static final long serialVersionUID = -8045993020852418664L;

    public CustomerPersistenceException() {
        super();
    }

    public CustomerPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerPersistenceException(String message) {
        super(message);
    }

    public CustomerPersistenceException(Throwable cause) {
        super(cause);
    }

}
