package expression.generic.operations;

import expression.generic.types.Type;

public class Add<T> extends AbstractOperation<T> {

    public Add(AbstractExpression<T> firstExpression, AbstractExpression<T> secondExpression, Type<T> type) {
        super(firstExpression, secondExpression, type);
    }
    @Override
    protected String getSymbol() {
        return "+";
    }
    @Override
    protected T calculate(T left, T right) {
        return type.add(left, right);
    }
}
