package expression.exceptions;

import expression.AbstractExpression;
import expression.Const;
import expression.Variable;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringSource;

public final class ExpressionParseStarter {
    public static AbstractExpression parse(final String source) {
        checkBrackets(source);
        return parse(new StringSource(source));
    }
    public static AbstractExpression parse(final CharSource source) {
        return new ExpressionParserMachine(source).parseExpression();
    }
    private static void checkBrackets(final String source) {
        int brackets = 0;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) == '(') {
                brackets++;
            }
            if (source.charAt(i) == ')') {
                brackets--;
            }
            if (brackets < 0) {
                throw new UnparsableBrecketsException("Problem with brackets: find ')' without '('");
            }
        }
        if (brackets != 0) {
            throw new UnparsableBrecketsException("Problem with brackets: find '(' without ')'");
        }
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
                default -> throw new ParsingException("Expect operation symbol, actual: " + take());
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
                    return parseAbstractExpression(new CheckedAdd(leftSide, parseAbstractExpression(parseElement(), "", 2)), start, to);
                }
                if (take('-')) {
                    return parseAbstractExpression(new CheckedSubtract(leftSide, parseAbstractExpression(parseElement(), "", 2)), start, to);
                }
                if (take('*')) {
                    return parseAbstractExpression(new CheckedMultiply(leftSide, parseAbstractExpression(parseElement(), "", 3)), start, to);
                }
                if (take('/')) {
                    return parseAbstractExpression(new CheckedDivide(leftSide, parseAbstractExpression(parseElement(), "", 3)), start, to);
                }
                if (take('s')) {
                    try {
                        expect("et");
                    } catch (IllegalArgumentException e) {
                        throw new ParsingException("Expect: 'set', Actual: s" + take(), e);
                    }
                    return parseAbstractExpression(new CheckedSet(leftSide, parseAbstractExpression(parseElement(), "", 1)), start, to);
                }
                if (take('c')) {
                    try {
                        expect("lear");
                    } catch (IllegalArgumentException e) {
                        throw new ParsingException("Expect: 'clear', Actual: c" + take(), e);
                    }
                    return parseAbstractExpression(new CheckedClear(leftSide, parseAbstractExpression(parseElement(), "", 1)), start, to);
                }
            }
            return leftSide;
        }

        private AbstractExpression parseElement() {
            skipWhitespace();
            if (between('x', 'z')) {
                return parseVariable();
            } else if (between('0', '9')) {
                return parseConst("");
            } else if (take('(')) {
                return parseAbstractExpression(parseElement(), "(", 0);
            } else if (take('-')) {
                if (between('1', '9')) {
                    return parseConst("-");
                } else {
                    return new CheckedNegate(parseElement());
                }
            } else {
                throw new ParsingException("Unsupported start of element: '" + take() + "'");
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
            if (now('s') || now('c')) {
                throw new WhitespacesException("Expect whitespaces before set and clear");
            }
            try {
                return new Const(Integer.parseInt(constant.toString()));
            } catch (NumberFormatException e) {
                throw new UnparsableConstException("Unparsable const: " + constant, e);
            }
        }
    }
}
