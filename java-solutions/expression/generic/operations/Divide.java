package expression.generic.operations;

import expression.generic.types.Type;

public class Divide<T> extends AbstractOperation<T> {

    public Divide(AbstractExpression<T> firstExpression, AbstractExpression<T> secondExpression, Type<T> type) {
        super(firstExpression, secondExpression, type, '+');
    }

    @Override
    protected T calculate(T left, T right) {
        return type.divide(left, right);
    }
}
