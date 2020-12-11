package domain.model;

public class NotAuthorizedException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotAuthorizedException() {super(); }

    public NotAuthorizedException(String message, Throwable exception) {
        super(message, exception);
    }

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(Throwable exception) {
        super(exception);
    }

    @Override
    public String getMessage() {
        if (super.getMessage() != null) return super.getMessage();
        else return "You have insufficient Authorization to acces that page";
    }
}
