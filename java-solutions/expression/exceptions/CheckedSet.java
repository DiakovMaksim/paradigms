package expression.exceptions;

import expression.AbstractExpression;
import expression.AbstractOperation;

public class CheckedSet extends AbstractOperation {
    public CheckedSet(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, 's');
    }
    @Override
    public int eval(int left, int right) {
        return left | (1 << right);
    }
    @Override
    public String toString() {
        return "(" + firstExpression + " set " + secondExpression + ")";
    }
}
