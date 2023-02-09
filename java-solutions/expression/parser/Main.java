package expression.parser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionParser expressionParser = new ExpressionParser();
        String input = scanner.nextLine();
        try {
            System.out.println(expressionParser.parse(input));
        } catch (Exception e) {
            System.out.println("err");
        }
    }
}
