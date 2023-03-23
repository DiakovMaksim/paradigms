package expression.generic.operations;

import expression.generic.types.Type;

public abstract class AbstractOperation<T> implements AbstractExpression<T> {
    protected AbstractExpression<T> firstExpression;
    protected AbstractExpression<T> secondExpression;
    protected Type<T> type;

    public AbstractOperation(AbstractExpression<T> firstExpression, AbstractExpression<T> secondExpression, Type<T> type) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.type = type;
    }

    protected abstract T calculate(T left, T right);
    protected String getSymbol() {
        return "";
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(firstExpression.evaluate(x, y, z), secondExpression.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + firstExpression + " " + this.getSymbol() + " " + secondExpression + ")";
    }
}
