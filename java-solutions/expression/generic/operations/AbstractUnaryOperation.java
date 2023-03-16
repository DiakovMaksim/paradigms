package expression.generic.operations;

import expression.generic.types.Type;

import java.util.Objects;

public abstract class AbstractUnaryOperation<T> implements AbstractExpression<T> {
    protected AbstractExpression<T> expression;
    protected Type<T> type;
    String symbol;

    public AbstractUnaryOperation(AbstractExpression<T> expression, Type<T> type, String symbol) {
        this.expression = expression;
        this.type = type;
        this.symbol = symbol;
    }

    protected abstract T calculate(T value);

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(expression.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return symbol + "(" + expression + ")";
    }

    @Override
    public boolean equals(Object comparior) {
        return comparior != null && this.getClass() == comparior.getClass() &&
                Objects.equals(this.symbol, ((AbstractUnaryOperation<?>) comparior).symbol) &&
                this.expression.equals(((AbstractUnaryOperation<?>) comparior).expression);
    }

    @Override
    public int hashCode() {
        return switch (symbol) {
            case ("-") -> 601 * (expression.hashCode() * 19);
            default -> 0;
        };
    }
}
