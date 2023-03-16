package expression.generic.operations;

import expression.generic.types.Type;


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
}
