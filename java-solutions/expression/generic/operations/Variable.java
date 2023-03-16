package expression.generic.operations;

import expression.exceptions.ParsingException;

public class Variable<T> implements AbstractExpression<T> {
    public final char name;

    public Variable(String name) {
        this.name = name.charAt(0);
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return switch (name) {
            case 'x' -> x;
            case 'y' -> y;
            case 'z' -> z;
            default -> throw new ParsingException("");
        };
    }

    @Override
    public String toString() {
        return Character.toString(name);
    }
}
