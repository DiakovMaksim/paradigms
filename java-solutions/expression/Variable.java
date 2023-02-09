package expression;

public class Variable extends AbstractExpression {
    final char name;
    public Variable(String name) {
        this.name = name.charAt(0);
    }
    @Override
    public int evaluate(int variable) {
        return variable;
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return switch (name) {
            case ('x') -> x;
            case ('y') -> y;
            case ('z') -> z;
            default -> 0;
        };
    }
    @Override
    public double evaluate(double variable) {
        return variable;
    }
    @Override
    public String toString() {
        return Character.toString(name);
    }
    @Override
    public boolean equals(Object comparior) {
        return comparior != null && comparior.getClass() == Variable.class && ((Variable) comparior).name == this.name;
    }
    @Override
    public int hashCode() {
        return name * 113;
    }
}
