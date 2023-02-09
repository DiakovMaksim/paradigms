package expression.exceptions;

import expression.AbstractExpression;
import expression.AbstractOperation;
import expression.Const;

public class CheckedPow10 extends AbstractOperation {
    public CheckedPow10(AbstractExpression expression) {
        super(new Const(1), expression, '-');
    }
    @Override
    public int eval(int left, int right) {
        if (right >= 0 && right < 10) {
            for (int i = 0; i < right; i++) {
                left *= 10;
            }
            return left;
        }
        throw new OverflowException("Overflow in Pow10(" + right + ")");
    }
    @Override
    public String toString() {
        return "pow10(" + this.secondExpression + ")";
    }
}
