package expression;

public class Subtract extends AbstractOperation{
    public Subtract(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, '-');
    }
    @Override
    public int eval(int left, int right) {
        return left - right;
    }
    @Override
    public double eval(double left, double right) {
        return left - right;
    }
}
