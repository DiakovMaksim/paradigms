package expression.exceptions;

public class EvaluatingException extends RuntimeException {
    public EvaluatingException(String message) {
        super(message);
    }
    public EvaluatingException(String message, Throwable cause) {
        super(message, cause);
    }
}
