package expression;

public class AbstractExpression implements Expression, TripleExpression, DoubleExpression{

    @Override
    public int evaluate(int variable) {
        return 0;
    }

    @Override
    public boolean equals(Object comparior) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return 0;
    }
    @Override
    public double evaluate(double x) {
        return 0;
    }
}
