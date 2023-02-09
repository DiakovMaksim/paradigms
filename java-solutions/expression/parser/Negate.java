package expression.parser;

import expression.AbstractExpression;
import expression.AbstractUnaryOperation;

public class Negate extends AbstractUnaryOperation {
    public Negate(AbstractExpression expression) {
        super(expression, "-");
    }
    @Override
    public int eval(int expression) {
        return -expression;
    }
    @Override
    public String toString() {
        return "-(" + this.expression + ")";
    }
}
