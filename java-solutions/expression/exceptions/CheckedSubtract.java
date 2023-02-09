package expression.exceptions;

import expression.AbstractExpression;
import expression.AbstractOperation;

public class CheckedSubtract extends AbstractOperation {
    public CheckedSubtract(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, '-');
    }
    @Override
    public int eval(int left, int right) {
        if (left >= 0 && left - Integer.MAX_VALUE <= right || left < 0 && left - Integer.MIN_VALUE >= right) {
            return left - right;
        }
        throw new OverflowException("Overflow in Subtract(" + left + ", " + right + ")");
    }
}
