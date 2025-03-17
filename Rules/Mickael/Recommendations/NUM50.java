/*
 * CODE EXAMPLE FOR NUM50-J
 * Author: Mickael Agustin
 * Description: Demonstrates the importance of type conversion in arithmetic operations.
 * This example highlights how integer division can lead to unintended results if not explicitly converted to double.
 */

public class NUM50 {
    private static final int a = 5, b = 2;
    /**
     * The main method that demonstrates the impact of type conversion on arithmetic
     * operations.
     * It shows how integer division can result in truncation and how casting to
     * double can fix this issue.
     */
    public static void main(String[] args) {
        // Integer division: Since both 'a' and 'b' are integers, the result will be an
        // integer.
        double resultNoConvert = a / b; // This will be 2 instead of 2.5 due to integer division.

        // Explicit type conversion: Casting 'a' to double ensures floating-point
        // division.
        double resultConvert = (double) a / b; // Correct result: 2.5

        // Printing results
        System.out.println("a: " + a + " b: " + b);
        System.out.println("Performing a / b:");
        System.out.println("Result without conversion first: " + resultNoConvert); // Output: 2.0
        System.out.println("Result with conversion first: " + resultConvert); // Output: 2.5
    }
}
