package expression.exceptions;

public class UnparsableConstException extends ParsingException {

    public UnparsableConstException(String message) {
        super(message);
    }
    public UnparsableConstException(String message, Throwable cause) {
        super(message, cause);
    }
}
