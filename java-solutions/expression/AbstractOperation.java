package expression;

public class AbstractOperation extends AbstractExpression {
    protected AbstractExpression firstExpression;
    protected AbstractExpression secondExpression;
    char symbol;
    public AbstractOperation(AbstractExpression firstExpression, AbstractExpression secondExpression, char symbol) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.symbol = symbol;
    }
    @Override
    public int evaluate(int variable) {
        return this.eval(firstExpression.evaluate(variable), secondExpression.evaluate(variable));
    }

    public int eval(int left, int right) {
        return 0;
    }
    public double eval(double left, double right) {
        return 0;
    }

    @Override
    public double evaluate(double variable) {
        return this.eval(firstExpression.evaluate(variable), secondExpression.evaluate(variable));
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return this.eval(firstExpression.evaluate(x, y, z), secondExpression.evaluate(x, y, z));
    }
    @Override
    public String toString() {
        return "(" + firstExpression + " " + symbol + " " + secondExpression + ")";
    }
    @Override
    public boolean equals(Object comparior) {
        return comparior != null && this.getClass() == comparior.getClass() && this.symbol == ((AbstractOperation) comparior).symbol && this.firstExpression.equals(((AbstractOperation) comparior).firstExpression) && this.secondExpression.equals(((AbstractOperation) comparior).secondExpression);
    }
    @Override
    public int hashCode() {
        return switch (symbol) {
            case ('+') -> 601 * (firstExpression.hashCode() * 17 + secondExpression.hashCode() * 19);
            case ('-') -> 701 * (firstExpression.hashCode() * 29 + secondExpression.hashCode() * 31);
            case ('*') -> 809 * (firstExpression.hashCode() * 49 + secondExpression.hashCode() * 53);
            case ('/') -> 907 * (firstExpression.hashCode() * 61 + secondExpression.hashCode() * 67);
            default -> 0;
        };
    }
}
