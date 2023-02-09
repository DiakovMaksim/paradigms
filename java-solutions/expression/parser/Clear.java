package expression.parser;

import expression.AbstractExpression;
import expression.AbstractOperation;

public class Clear extends AbstractOperation {
    public Clear(expression.AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, 'c');
    }
    @Override
    public int eval(int left, int right) {
        return left & ~(1 << right);
    }
    @Override
    public String toString() {
        return "(" + firstExpression + " clear " + secondExpression + ")";
    }
}
