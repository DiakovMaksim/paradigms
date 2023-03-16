package expression.generic.types;

public class ShortType implements Type<Short> {
    @Override
    public Short add(Short firstArgument, Short secondArgument) {
        return (short) (firstArgument + secondArgument);
    }

    @Override
    public Short subtract(Short firstArgument, Short secondArgument) {
        return (short) (firstArgument - secondArgument);
    }

    @Override
    public Short multiply(Short firstArgument, Short secondArgument) {
        return (short) (firstArgument * secondArgument);
    }

    @Override
    public Short divide(Short firstArgument, Short secondArgument) {
        return (short) (firstArgument / secondArgument);
    }

    @Override
    public Short negate(Short argument) {
        return (short) -argument;
    }

    @Override
    public Short parseConst(String value) {
        return Short.parseShort(value);
    }

    @Override
    public Short valueOf(int value) {
        return (short) value;
    }
}
