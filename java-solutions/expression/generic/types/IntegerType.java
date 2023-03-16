package expression.generic.types;

import expression.exceptions.DBZException;
import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;

public class IntegerType implements Type<Integer> {

    @Override
    public Integer add(Integer firstArgument, Integer secondArgument) {
        if (firstArgument >= 0 && Integer.MAX_VALUE - firstArgument >= secondArgument || firstArgument < 0 && Integer.MIN_VALUE - firstArgument <= secondArgument) {
            return firstArgument + secondArgument;
        }
        throw new OverflowException("Overflow in Add(" + firstArgument + ", " + secondArgument + ")");
    }

    @Override
    public Integer subtract(Integer firstArgument, Integer secondArgument) {
        if (firstArgument >= 0 && firstArgument - Integer.MAX_VALUE <= secondArgument || firstArgument < 0 && firstArgument - Integer.MIN_VALUE >= secondArgument) {
            return firstArgument - secondArgument;
        }
        throw new OverflowException("Overflow in Subtract(" + firstArgument + ", " + secondArgument + ")");
    }

    @Override
    public Integer multiply(Integer firstArgument, Integer secondArgument) {
        if (firstArgument == 0 || secondArgument == 0
                || firstArgument > 0 && (
                secondArgument > 0 && Integer.MAX_VALUE / firstArgument >= secondArgument || secondArgument < 0 && Integer.MIN_VALUE / firstArgument <= secondArgument
        ) || firstArgument < 0 && (
                secondArgument > 0 && Integer.MIN_VALUE / secondArgument <= firstArgument || secondArgument < 0 && Integer.MAX_VALUE / firstArgument <= secondArgument
        )) {
            return firstArgument * secondArgument;
        }
        throw new OverflowException("Overflow in Multiply(" + firstArgument + ", " + secondArgument + ")");
    }

    @Override
    public Integer divide(Integer firstArgument, Integer secondArgument) {
        if (secondArgument != 0 && (firstArgument > Integer.MIN_VALUE || secondArgument != -1)) {
            return firstArgument / secondArgument;
        }
        throw new DBZException("Division by zero");
    }

    @Override
    public Integer negate(Integer argument) {
        if (argument > Integer.MIN_VALUE) {
            return -argument;
        }
        throw new OverflowException("Overflow in Minus(" + argument + ")");
    }

    @Override
    public Integer parseConst(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ParsingException("");
        }
    }

    @Override
    public Integer valueOf(int value) {
        return value;
    }
}
