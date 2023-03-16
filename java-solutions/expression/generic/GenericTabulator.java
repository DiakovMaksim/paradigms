package expression.generic;
import expression.generic.operations.AbstractExpression;
import expression.generic.types.*;

import java.util.Arrays;

public class GenericTabulator implements Tabulator {

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        return switch (mode) {
            case ("i") -> fillMatrix(new IntegerType(), expression, x1, x2, y1, y2, z1, z2);
            case ("bi") -> fillMatrix(new BigIntegerType(), expression, x1, x2, y1, y2, z1, z2);
            case ("d") -> fillMatrix(new DoubleType(), expression, x1, x2, y1, y2, z1, z2);
            case ("u") -> fillMatrix(new UncheckedIntegerType(), expression, x1, x2, y1, y2, z1, z2);
            case ("f") -> fillMatrix(new FloatType(), expression, x1, x2, y1, y2, z1, z2);
            case ("s") -> fillMatrix(new ShortType(), expression, x1, x2, y1, y2, z1, z2);
            default -> null;
        };
    }
    private <T> Object[][][] fillMatrix(Type<T> type,  String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        ExpressionParseStarter<T> parser = new ExpressionParseStarter<>(type);
        AbstractExpression<T> parsedExpression = parser.parse(expression, type);
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    try {
                        result[x - x1][y - y1][z - z1] = parsedExpression.evaluate(type.valueOf(x), type.valueOf(y), type.valueOf(z));
                    } catch (Exception e) {
                        result[x - x1][y - y1][z - z1] = null;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.deepToString(new GenericTabulator().tabulate(args[0], args[1], -2, 2, -2, 2, -2, 2)));
    }
}
