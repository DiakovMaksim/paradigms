package expression.generic.operations;

import expression.generic.types.Type;

public class Set<T> extends AbstractOperation<T> {

    public Set(AbstractExpression<T> firstExpression, AbstractExpression<T> secondExpression, Type<T> type) {
        super(firstExpression, secondExpression, type, 's');
    }

    @Override
    protected T calculate(T left, T right) {
        return type.add(left, right);
    }
}
