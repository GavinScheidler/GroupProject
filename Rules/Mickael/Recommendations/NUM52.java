/*
 * CODE EXAMPLE FOR NUM52
 * Author: Mickael Agustin
 * Description: Demonstrates the concept of type promotion during arithmetic operations.
 * This example highlights how an int is automatically promoted to a double when added to a double.
 */

 public class NUM52 {
    private static final int i = 5;
    private static final double d = 3.2;
    /**
     * The main method that demonstrates type promotion during arithmetic operations.
     * It shows how an integer is automatically promoted to a double when added to a double.
     */
    public static void main(String[] args) {
        // Type promotion: The int 'i' is promoted to double for the operation.
        // The result of adding a double and an int is a double.
        double result = i + d; // Correct result: 8.2

        System.out.println("Result: " + result); // Output: Result: 8.2
    }
}
