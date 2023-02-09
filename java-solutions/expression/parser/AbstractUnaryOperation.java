package expression.parser;

import expression.AbstractExpression;

public class AbstractUnaryOperation extends expression.AbstractExpression {
    protected expression.AbstractExpression expression;
    String symbol;
    public AbstractUnaryOperation(AbstractExpression expression, String symbol) {
        this.expression = expression;
        this.symbol = symbol;
    }
    @Override
    public int evaluate(int variable) {
        return this.eval(expression.evaluate(variable));
    }

    public int eval(int value) {
        return 0;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.eval(expression.evaluate(x, y, z));
    }
    @Override
    public String toString() {
        return symbol + "(" + expression + ")";
    }
    @Override
    public boolean equals(Object comparior) {
        return comparior != null && this.getClass() == comparior.getClass() && this.symbol == ((AbstractUnaryOperation) comparior).symbol && this.expression.equals(((AbstractUnaryOperation) comparior).expression);
    }
    @Override
    public int hashCode() {
        return switch (symbol) {
            case ("-") -> 601 * (expression.hashCode() * 19);
            default -> 0;
        };
    }
}
