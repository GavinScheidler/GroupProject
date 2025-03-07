/*
 * CODE EXAMPLE FOR NUM52
 * Author: Mickael Agustin
 * Description: Demonstrates the concept of type promotion during arithmetic operations.
 * This example highlights how an int is automatically promoted to a double when added to a double.
 */

 public class NUM52 {
    /**
     * The main method that demonstrates type promotion during arithmetic operations.
     * It shows how an integer is automatically promoted to a double when added to a double.
     */
    public static void main(String[] args) {
        int i = 5;
        double d = 3.2;
        
        // Type promotion: The int 'i' is promoted to double for the operation.
        // The result of adding a double and an int is a double.
        double result = i + d; // Correct result: 8.2

        System.out.println("Result: " + result); // Output: Result: 8.2
    }
}
