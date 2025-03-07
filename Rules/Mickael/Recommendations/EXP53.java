/*
 * CODE EXAMPLE FOR EXP53-J
 * Author: Mickael Agustin
 * Description: Demonstrates the importance of operator precedence in arithmetic expressions.
 * This example highlights how using parentheses can change the order of operations and affect results.
 */

 public class EXP53 {
    /**
     * The main method that demonstrates how operator precedence affects arithmetic expressions.
     * It compares results with and without parentheses to show how parentheses can change the order of operations.
     */
    public static void main(String[] args) {
        int a = 1, b = 3, c = 4;
        
        // Calculating results with and without parentheses
        int withoutParentheses = a + b * c;  // Multiplication happens first due to operator precedence
        int withParentheses = (a + b) * c;  // Parentheses change the order, making addition happen first

        // Printing the results
        System.out.println("Without parentheses: " + withoutParentheses); // Output: 1 + (3 * 4) = 13
        System.out.println("With parentheses: " + withParentheses); // Output: (1 + 3) * 4 = 16
    }
}
