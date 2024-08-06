import java.util.Stack;

/**
 * This class represents a postfix calculator, which evaluates postfix expressions
 * containing single-digit and/or multiple-digit operands, and the operators '+', '-', '*', and '/'.
 * It provides a method {@code evaluatePostfix} to evaluate a given postfix expression
 * and return the result.
 */
public class PostfixCalculatorUpdate {

    /**
     * Evaluates the given postfix expression and returns the result.
     * The expression should contain single-digit and/or multiple-digit operands,
     * as well as operator(s) '+', '-', '*', and '/'. All operands and
     * operators constitute "elements", and all distinct elements
     * in a postfix expression must be separated with spaces.
     * @param postfixExpression the postfix expression to be evaluated
     * @return the result of the evaluation
     * @throws IllegalArgumentException if the expression is invalid or cannot be evaluated
     */
    public int evaluatePostfix(String postfixExpression) {
        Stack<Integer> stack = new Stack<>();
        String[] elements = postfixExpression.split("\\s+"); // split by one or more spaces

        for (String element : elements) {
            if (element.matches("\\d+")) { // check if token is a number (one or more digits)
                stack.push(Integer.parseInt(element));
            } else if (element.matches("[+\\-*/]")) { // check if token is an operator
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression: Not enough operands");
                }
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = 0;
                switch (element) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        if (operand2 == 0) {
                            throw new IllegalArgumentException("Division by zero");
                        }
                        result = operand1 / operand2;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + element);
                }
                stack.push(result);
            } else {
                throw new IllegalArgumentException("Invalid element: " + element);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: Too many operands");
        }
        return stack.pop();
    }
    /**
     * Demonstrates usage of the evaluatePostfix method by evaluating
     * a few sample postfix expressions and printing the results.
     * @param args
     */
    public static void main(String[] args) {
        PostfixCalculatorUpdate calculator = new PostfixCalculatorUpdate();

        try {
            int result = calculator.evaluatePostfix("8 2 /");
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error evaluating expression: " + e.getMessage());
        }

        try {
            int result2 = calculator.evaluatePostfix("2 6 + 2 * 2 /");
            System.out.println("Result 2: " + result2);
        } catch (IllegalArgumentException e) {
            System.out.println("Error evaluating expression: " + e.getMessage());
        }

        try {
            int result3 = calculator.evaluatePostfix("1 2 + $ 9 22 * / -");
            System.out.println("Result 3: " + result3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error evaluating expression: " + e.getMessage());
        }
    }
}
