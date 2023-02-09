package expression;

public class Const extends AbstractExpression{
    final int valueInt;
    final double valueDouble;
    final boolean isInt;
    public Const(int value) {
        this.valueInt = value;
        this.valueDouble = 0;
        this.isInt = true;
    }
    public Const(double value) {
        this.valueInt = 0;
        this.valueDouble = value;
        this.isInt = false;
    }
    @Override
    public int evaluate(int variable) {
        return valueInt;
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return valueInt;
    }
    @Override
    public double evaluate(double variable) {
        return valueDouble;
    }
    @Override
    public String toString() {
        if (isInt) {
            return String.valueOf(valueInt);
        }
        return String.valueOf(valueDouble);
    }
    @Override
    public boolean equals(Object comparior) {
        return comparior != null && comparior.getClass() == Const.class && ((Const) comparior).valueInt == this.valueInt && ((Const) comparior).valueDouble == this.valueDouble;
    }
    @Override
    public int hashCode() {
        if (isInt) {
            return valueInt * 97;
        }
        return (int) valueDouble * 101;
    }
}
