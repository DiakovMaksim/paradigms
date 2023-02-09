package expression.exceptions;
import expression.AbstractExpression;
import expression.AbstractOperation;

public class CheckedAdd extends AbstractOperation {
    public CheckedAdd(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, '+');
    }
    @Override
    public int eval(int left, int right) {
        if (left >= 0 && Integer.MAX_VALUE - left >= right || left < 0 && Integer.MIN_VALUE - left <= right) {
            return left + right;
        }
        throw new OverflowException("Overflow in Add(" + left + ", " + right + ")");
    }
}
