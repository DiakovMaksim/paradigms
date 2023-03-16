package expression.generic.operations;

import expression.generic.types.Type;

public class Count<T> extends AbstractUnaryOperation<T> {

    public Count(AbstractExpression<T> firstExpression, Type<T> type) {
        super(firstExpression, type, "count");
    }

    @Override
    protected T calculate(T left) {
        return type.negate(left);
    }
}
