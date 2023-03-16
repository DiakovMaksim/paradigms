package expression.generic.operations;

public class Const<T> implements AbstractExpression<T> {
    public final T value;

    public Const(T value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object comparior) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
