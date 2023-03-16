package expression.generic.operations;

import expression.generic.types.Type;

public class Negate<T> extends AbstractUnaryOperation<T> {

    public Negate(AbstractExpression<T> expression, Type<T> type) {
        super(expression, type, "-");
    }

    @Override
    protected T calculate(T value) {
        return type.negate(value);
    }
}
