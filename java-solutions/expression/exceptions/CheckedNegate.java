package expression.exceptions;

import expression.AbstractExpression;
import expression.AbstractOperation;
import expression.Const;

public class CheckedNegate extends AbstractOperation {
    public CheckedNegate(AbstractExpression expression) {
        super(new Const(0), expression, '-');
    }
    @Override
    public int eval(int left, int right) {
        if (right > Integer.MIN_VALUE) {
            return left - right;
        }
        throw new OverflowException("Overflow in Minus(" + right + ")");
    }
    @Override
    public String toString() {
        return "-(" + this.secondExpression + ")";
    }
}
