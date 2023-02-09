package expression.exceptions;

public class IAException extends EvaluatingException {
    public IAException(String message) {
        super(message);
    }

    public IAException(String message, Throwable cause) {
        super(message, cause);
    }
}
