package expression.generic.types;

public class FloatType implements Type<Float> {

    @Override
    public Float add(Float firstArgument, Float secondArgument) {
        return firstArgument + secondArgument;
    }

    @Override
    public Float subtract(Float firstArgument, Float secondArgument) {
        return firstArgument - secondArgument;
    }

    @Override
    public Float multiply(Float firstArgument, Float secondArgument) {
        return firstArgument * secondArgument;
    }

    @Override
    public Float divide(Float firstArgument, Float secondArgument) {
        return firstArgument / secondArgument;
    }

    @Override
    public Float negate(Float argument) {
        return -argument;
    }

    @Override
    public Float parseConst(String value) {
        return Float.parseFloat(value);
    }

    @Override
    public Float valueOf(int value) {
        return (float) value;
    }
}
