package expression.generic.operations;

import expression.generic.types.Type;

public class Pow<T> extends AbstractUnaryOperation<T> {

    public Pow(AbstractExpression<T> firstExpression, Type<T> type) {
        super(firstExpression, type, "pow");
    }

    @Override
    protected T calculate(T left) {
        return type.negate(left);
    }
}
