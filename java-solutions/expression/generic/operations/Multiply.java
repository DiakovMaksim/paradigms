package expression.generic.operations;

import expression.generic.types.Type;

public class Multiply<T> extends AbstractOperation<T> {

    public Multiply(AbstractExpression<T> firstExpression, AbstractExpression<T> secondExpression, Type<T> type) {
        super(firstExpression, secondExpression, type, '+');
    }

    @Override
    protected T calculate(T left, T right) {
        return type.multiply(left, right);
    }
}
