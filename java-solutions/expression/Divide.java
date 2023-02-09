package expression;

public class Divide extends AbstractOperation {
    public Divide(AbstractExpression firstExpression, AbstractExpression secondExpression) {
        super(firstExpression, secondExpression, '/');
    }
    @Override
    public int eval(int left, int right) {
        return left / right;
    }
    @Override
    public double eval(double left, double right) {
        return left / right;
    }
}
