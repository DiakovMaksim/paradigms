package expression.parser;
import expression.*;
public final class ExpressionParseStarter {
    public static AbstractExpression parse(final String source) {
        return parse(new StringSource(source));
    }
    public static AbstractExpression parse(final CharSource source) {
        return new ExpressionParserMachine(source).parseExpression();
    }
    private static class ExpressionParserMachine extends BaseParser {
        public ExpressionParserMachine(CharSource source) {
            super(source);
        }
        public AbstractExpression parseExpression() {
            AbstractExpression result = parseAbstractExpression(parseElement(), "", 0);
            while (!eof()) {
                result = parseAbstractExpression(result, "",0);
            }
            return result;
        }
        private int priority(char enter) {
            return switch (enter) {
                case '+', '-' -> 2;
                case '*', '/' -> 3;
                case 'c', 's' -> 1;
                default -> 5;
            };
        }
        private AbstractExpression parseAbstractExpression(AbstractExpression leftSide, String start, int to) {
            skipWhitespace();
            if (!eof()) {
                if (now(')')) {
                    if (start == "(") {
                        take(')');
                    }
                    return leftSide;
                }
                if (priority(see()) <= to) {
                    return leftSide;
                }
                if (take('+')) {
                    return parseAbstractExpression(new Add(leftSide, parseAbstractExpression(parseElement(), "", 2)), start, to);
                }
                if (take('-')) {
                    return parseAbstractExpression(new Subtract(leftSide, parseAbstractExpression(parseElement(), "", 2)), start, to);
                }
                if (take('*')) {
                    return parseAbstractExpression(new Multiply(leftSide, parseAbstractExpression(parseElement(), "", 3)), start, to);
                }
                if (take('/')) {
                    return parseAbstractExpression(new Divide(leftSide, parseAbstractExpression(parseElement(), "", 3)), start, to);
                }
                if (take('s')) {
                    expect("et");
                    return parseAbstractExpression(new Set(leftSide, parseAbstractExpression(parseElement(), "", 1)), start, to);
                }
                if (take('c')) {
                    expect("lear");
                    return parseAbstractExpression(new Clear(leftSide, parseAbstractExpression(parseElement(), "", 1)), start, to);
                }
            }
            return leftSide;
        }

        private AbstractExpression parseElement() {
            skipWhitespace();
            if (between('a', 'z')) {
                return parseVariable();
            } else if (between('0', '9')) {
                return parseConst("");
            } else if (take('(')) {
                return parseAbstractExpression(parseElement(), "(", 0);
            } else if (take('-')) {
                if (between('1', '9')) {
                    return parseConst("-");
                } else {
                    return new Negate(parseElement());
                }
            } else {
                throw error("Unsupported input: '" + take() + "'");
            }
        }

        private Variable parseVariable() {
            return new Variable(String.valueOf(take()));
        }
        private Const parseConst(String sign) {
            StringBuilder constant = new StringBuilder(sign);
            while (between('0', '9')) {
                constant.append(take());
            }
            return new Const(Integer.parseInt(constant.toString()));
        }
    }
}
