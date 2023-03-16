package expression.generic.types;

import expression.exceptions.ParsingException;

import java.math.BigInteger;

public class BigIntegerType implements Type<BigInteger> {

    @Override
    public BigInteger add(BigInteger firstArgument, BigInteger secondArgument) {
        return firstArgument.add(secondArgument);
    }

    @Override
    public BigInteger subtract(BigInteger firstArgument, BigInteger secondArgument) {
        return firstArgument.subtract(secondArgument);
    }

    @Override
    public BigInteger multiply(BigInteger firstArgument, BigInteger secondArgument) {
        return firstArgument.multiply(secondArgument);
    }

    @Override
    public BigInteger divide(BigInteger firstArgument, BigInteger secondArgument) {
        return firstArgument.divide(secondArgument);
    }

    @Override
    public BigInteger negate(BigInteger argument) {
        return argument.negate();
    }

    @Override
    public BigInteger parseConst(String value) {
        try {
            return new BigInteger(value);
        } catch (NumberFormatException e) {
            throw new ParsingException("");
        }
    }

    @Override
    public BigInteger valueOf(int value) {
        return BigInteger.valueOf(value);
    }
}
