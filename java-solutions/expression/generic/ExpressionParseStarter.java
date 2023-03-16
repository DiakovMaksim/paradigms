package expression.generic;

import expression.exceptions.*;
import expression.generic.operations.*;
import expression.generic.types.Type;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringSource;

public final class ExpressionParseStarter<T> {
    Type<T> type;

    public ExpressionParseStarter(Type<T> type) {
        this.type = type;
    }

    public AbstractExpression<T> parse(final String source, Type<T> type) {
        checkBrackets(source);
        return parse(new StringSource(source), type);
    }

    public AbstractExpression<T> parse(final CharSource source, Type<T> type) {
        return new ExpressionParserMachine(source, type).parseExpression();
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
    private class ExpressionParserMachine extends BaseParser {
        private final Type<T> type;
        public ExpressionParserMachine(CharSource source, Type<T> type) {
            super(source);
            this.type = type;
        }
        public AbstractExpression<T> parseExpression() {
            AbstractExpression<T> result = parseAbstractExpression(parseElement(), "", 0);
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
                default -> throw new IllegalOperationException("Illegal operation symbol: '" + take() + "'");
            };
        }
        private AbstractExpression<T> parseAbstractExpression(AbstractExpression<T> leftSide, String start, int to) {
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
                    return parseAbstractExpression(new Add<>(leftSide, parseAbstractExpression(parseElement(), "", 2), type), start, to);
                }
                if (take('-')) {
                    return parseAbstractExpression(new Subtract<>(leftSide, parseAbstractExpression(parseElement(), "", 2), type), start, to);
                }
                if (take('*')) {
                    return parseAbstractExpression(new Multiply<>(leftSide, parseAbstractExpression(parseElement(), "", 3), type), start, to);
                }
                if (take('/')) {
                    return parseAbstractExpression(new Divide<T>(leftSide, parseAbstractExpression(parseElement(), "", 3), type), start, to);
                }
                if (take('s')) {
                    try {
                        expect("et");
                    } catch (IllegalArgumentException e) {
                        throw new ParsingException("Expect: 'set', Actual: 's" + take() +"'", e);
                    }
                    if (between('x', 'z') || between('0', '9')) {
                        throw new ParsingException("Illegal operation: 'set" + take() + "'");
                    }
                    return parseAbstractExpression(new Set<>(leftSide, parseAbstractExpression(parseElement(), "", 1), type), start, to);
                }
                if (take('c')) {
                    try {
                        expect("lear");
                    } catch (IllegalArgumentException e) {
                        throw new ParsingException("Expect: 'clear', Actual: 'c" + take() + "'", e);
                    }
                    if (between('x', 'z') || between('0', '9')) {
                        throw new ParsingException("Illegal operation: 'clear" + take() + "'");
                    }
                    return parseAbstractExpression(new Clear<>(leftSide, parseAbstractExpression(parseElement(), "", 1), type), start, to);
                }
            }
            return leftSide;
        }

        private AbstractExpression<T> parseElement() {
            skipWhitespace();
            if (between('x', 'z')) {
                return parseVariable();
            } else if (between('0', '9')) {
                return parseConst("");
            } else if (take('(')) {
                return parseAbstractExpression(parseElement(), "(", 0);
            } else if (take('c')) {
                try {
                    expect("ount");
                } catch (IllegalArgumentException e) {
                    throw new ParsingException("Expect 'count', actual: 'c" + take() + "'", e);
                }
                if (between('x', 'z') || between('0', '9')) {
                    throw new ParsingException("Illegal operation: 'count" + take() + "'");
                }
                return new Count<>(parseElement(), type);
            } else if (take('-')) {
                if (between('1', '9')) {
                    return parseConst("-");
                } else {
                    return new Negate<>(parseElement(), type);
                }
            } else if (take('l')) {
                try {
                    expect("og10");
                } catch (IllegalArgumentException e) {
                    throw new ParsingException("Expect 'log10', actual: 'l" + take() + "'", e);
                }
                if (between('x', 'z') || between('0', '9')) {
                    throw new ParsingException("Illegal operation: 'log10" + take() + "'");
                }
                return new Log<>(parseElement(), type);
            } else if (take('p')) {
                try {
                    expect("ow10");
                } catch (IllegalArgumentException e) {
                    throw new ParsingException("Expect 'pow10', actual: 'p" + take() + "'", e);
                }
                if (between('x', 'z') || between('0', '9')) {
                    throw new ParsingException("Illegal operation: 'pow10" + take() + "'");
                }
                return new Pow<T>(parseElement(), type);
            } else {
                throw new ParsingException("Unsupported start of element: '" + take() + "'");
            }
        }

        private Variable<T> parseVariable() {
            return new Variable(String.valueOf(take()));
        }
        private Const<T> parseConst(String sign) {
            StringBuilder constant = new StringBuilder(sign);
            while (between('0', '9')) {
                constant.append(take());
            }
            if (now('s') || now('c')) {
                throw new WhitespacesException("Expect whitespaces before 'set' and 'clear'");
            }
            return new Const<>(type.parseConst(constant.toString()));
        }
    }
}
