package expression.generic.types;

import expression.exceptions.ParsingException;

import java.math.BigInteger;

public class DoubleType implements Type<Double> {

    @Override
    public Double add(Double firstArgument, Double secondArgument) {
        return firstArgument + secondArgument;
    }

    @Override
    public Double subtract(Double firstArgument, Double secondArgument) {
        return firstArgument - secondArgument;
    }

    @Override
    public Double multiply(Double firstArgument, Double secondArgument) {
        return firstArgument * secondArgument;
    }

    @Override
    public Double divide(Double firstArgument, Double secondArgument) {
        return firstArgument / secondArgument;
    }

    @Override
    public Double negate(Double argument) {
        return -argument;
    }

    @Override
    public Double parseConst(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new ParsingException("");
        }
    }

    @Override
    public Double valueOf(int value) {
        return (double) value;
    }
}
