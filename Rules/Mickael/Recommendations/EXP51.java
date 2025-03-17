/*
 * CODE EXAMPLE FOR EXP51-J
 * Author: Mickael Agustin
 * Description: Demonstrates the importance of avoiding assignment (`=`) inside conditional statements.
 * This example highlights how using `=` instead of `==` can lead to unintended behavior.
 */

public class EXP51 {
    private static boolean a = true;
    private static boolean b = false;

    /**
     * Main method that demonstrates assignment inside conditional statements.
     * This method shows the difference between the assignment (`=`) and comparison
     * (`==`) operators.
     * It prints the results to the console to highlight how unintended behavior
     * occurs when using assignment in conditions.
     */
    public static void main(String[] args) {
        // Printing initial values
        System.out.println("Original assignments: ");
        System.out.println("a: " + a);
        System.out.println("b: " + b);

        System.out.println();

        // Checking logical comparison between 'a' and 'b'
        System.out.println("Testing: 'a != b'");
        if (a != b) { // '!=' checks if 'a' and 'b' are different (true in this case)
            System.out.println("Working as expected!");
        } else {
            System.out.println("Not working as expected!");
        }

        System.out.println();

        // Printing values after first comparison
        System.out.println("After first comparison using '==' : ");
        System.out.println("a: " + a);
        System.out.println("b: " + b);

        System.out.println();

        System.out.println("Testing: 'a = b'");
        if (a = b) { // 'a = b' assigns the value of 'b' to 'a', which is not a comparison
            System.out.println("This is an unintended behavior!");
        }

        System.out.println("After second 'comparison' using '=' : ");
        System.out.println("a: " + a); // 'a' has now taken the value of 'b' (false)
        System.out.println("b: " + b); // 'b' remains false
    }
}
