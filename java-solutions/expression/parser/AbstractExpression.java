package expression.parser;

public class AbstractExpression implements TripleExpression {

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
    public double evaluate(double x) {
        return 0;
    }
}
