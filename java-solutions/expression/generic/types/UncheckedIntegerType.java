package expression.generic.types;

public class UncheckedIntegerType implements Type<Integer> {
    @Override
    public Integer add(Integer firstArgument, Integer secondArgument) {
        return firstArgument + secondArgument;
    }

    @Override
    public Integer subtract(Integer firstArgument, Integer secondArgument) {
        return firstArgument - secondArgument;
    }

    @Override
    public Integer multiply(Integer firstArgument, Integer secondArgument) {
        return firstArgument * secondArgument;
    }

    @Override
    public Integer divide(Integer firstArgument, Integer secondArgument) {
        return firstArgument / secondArgument;
    }

    @Override
    public Integer negate(Integer argument) {
        return -argument;
    }

    @Override
    public Integer parseConst(String value) {
        return Integer.parseInt(value);
    }

    @Override
    public Integer valueOf(int value) {
        return value;
    }
}
