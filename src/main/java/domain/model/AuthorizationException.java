package domain.model;

public class AuthorizationException extends Exception {

    private static final long serialVersionUID = 1L;

    public AuthorizationException() {super(); }

    public AuthorizationException(String message, Throwable exception) {
        super(message, exception);
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(Throwable exception) {
        super(exception);
    }

    @Override
    public String getMessage() {
        if (super.getMessage() != null) return super.getMessage();
        else return "You have insufficient Authorization to acces that page";
    }
}
