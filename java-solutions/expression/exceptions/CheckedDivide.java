package expression.exceptions;

import expression.AbstractExpression;
import expression.AbstractOperation;

public class CheckedDivide extends AbstractOperation {
    public CheckedDivide(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, '/');
    }
    @Override
    public int eval(int left, int right) {
        if (right != 0 && (left > Integer.MIN_VALUE || right != -1)) {
            return left / right;
        }
        throw new DBZException("Division by zero");
    }
}

