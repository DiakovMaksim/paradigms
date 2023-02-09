package expression.exceptions;

import expression.AbstractExpression;
import expression.AbstractOperation;
import expression.Const;

public class CheckedCount extends AbstractOperation {
    public CheckedCount(AbstractExpression expression) {
        super(new Const(0), expression, 'C');
    }
    @Override
    public int eval(int left, int right) {
        String string = Integer.toBinaryString(right);
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '1') {
                result++;
            }
        }
        return result;
    }
    @Override
    public String toString() {
        return "count(" + this.secondExpression + ")";
    }
}
