package expression.exceptions;

public class WhitespacesException extends ParsingException {

    public WhitespacesException(String message) {
        super(message);
    }

    public WhitespacesException(String message, Throwable cause) {
        super(message, cause);
    }
}
