package expression.exceptions;

import expression.AbstractExpression;
import expression.AbstractOperation;

public class CheckedMultiply extends AbstractOperation {
    public CheckedMultiply(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, '*');
    }
    @Override
    public int eval(int left, int right) {
        if (left == 0 || right == 0
                || left > 0 && (
                        right > 0 && Integer.MAX_VALUE / left >= right || right < 0 && Integer.MIN_VALUE / left <= right
                ) || left < 0 && (
                        right > 0 && Integer.MIN_VALUE / right <= left || right < 0 && Integer.MAX_VALUE / left <= right
        )) {
            return left * right;
        }
        throw new OverflowException("Overflow in Multiply(" + left + ", " + right + ")");
    }
}
