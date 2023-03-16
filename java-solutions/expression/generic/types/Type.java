package expression.generic.types;

public interface Type<T> {
    T add(T firstArgument, T secondArgument);
    T subtract(T firstArgument, T secondArgument);
    T multiply(T firstArgument, T secondArgument);
    T divide(T firstArgument, T secondArgument);
    T negate(T argument);
    T parseConst(String value);
    T valueOf(int value);
}
