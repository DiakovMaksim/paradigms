package expression.parser;

import expression.Multiply;
import expression.Subtract;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Add(new Subtract(new expression.Multiply(new Variable("x"), new Variable("x")), new Multiply(new Const(2), new Variable("x"))), new Const(1)).evaluate(Integer.parseInt(args[0])));
    }
}
