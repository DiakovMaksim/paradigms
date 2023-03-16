package expression.generic.operations;

import expression.generic.types.Type;

public class Log<T> extends AbstractUnaryOperation<T> {

    public Log(AbstractExpression<T> firstExpression, Type<T> type) {
        super(firstExpression, type, "log");
    }

    @Override
    protected T calculate(T left) {
        return type.negate(left);
    }
}
