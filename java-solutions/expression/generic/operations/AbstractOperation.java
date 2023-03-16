package expression.generic.operations;

import expression.generic.types.Type;

public abstract class AbstractOperation<T> implements AbstractExpression<T> {
    protected AbstractExpression<T> firstExpression;
    protected AbstractExpression<T> secondExpression;
    protected Type<T> type;
    protected char symbol;

    public AbstractOperation(AbstractExpression<T> firstExpression, AbstractExpression<T> secondExpression, Type<T> type, char symbol) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.type = type;
        this.symbol = symbol;
    }

    protected abstract T calculate(T left, T right);

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(firstExpression.evaluate(x, y, z), secondExpression.evaluate(x, y, z));
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
