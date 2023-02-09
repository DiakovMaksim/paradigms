package expression;

public class Set extends AbstractOperation{
    public Set(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, 's');
    }
    @Override
    public int eval(int left, int right) {
        return left | (1 << right);
    }
    @Override
    public String toString() {
        return "(" + firstExpression + " set " + secondExpression + ")";
    }
}
