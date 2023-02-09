package expression.exceptions;

public class UnparsableBrecketsException extends ParsingException {
    public UnparsableBrecketsException(String message) {
        super(message);
    }
    public UnparsableBrecketsException(String message, Throwable source) {
        super(message, source);
    }
}
