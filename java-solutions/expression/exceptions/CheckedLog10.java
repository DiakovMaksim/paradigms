package expression.exceptions;

import expression.AbstractExpression;
import expression.AbstractOperation;
import expression.Const;

public class CheckedLog10 extends AbstractOperation {
    public CheckedLog10(AbstractExpression expression) {
        super(new Const(10), expression, 'l');
    }
    @Override
    public int eval(int left, int right) {
        if (right > 0) {
            return Integer.toString(right).length() - 1;
        }
        throw new IAException("Illegal argument for Log10(" + right + ")");
    }
    @Override
    public String toString() {
        return "log10(" + this.secondExpression + ")";
    }
}
