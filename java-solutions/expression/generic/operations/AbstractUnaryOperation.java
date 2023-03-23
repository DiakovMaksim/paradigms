package expression.generic.operations;

import expression.generic.types.Type;


public abstract class AbstractUnaryOperation<T> implements AbstractExpression<T> {
    protected AbstractExpression<T> expression;
    protected Type<T> type;

    public AbstractUnaryOperation(AbstractExpression<T> expression, Type<T> type) {
        this.expression = expression;
        this.type = type;
    }

    protected abstract T calculate(T value);

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(expression.evaluate(x, y, z));
    }
    protected String getSymbol() {
        return "";
    }
    @Override
    public String toString() {
        return this.getSymbol() + "(" + expression + ")";
    }
}
