package expression.exceptions;

public class DBZException extends EvaluatingException {

    public DBZException(String message) {
        super(message);
    }

    public DBZException(String message, Throwable cause) {
        super(message, cause);
    }
}
