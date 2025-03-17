/*
 * CODE EXAMPLE FOR OBJ10
 * Author: Mickael Agustin
 * Description: Demonstrates the difference between using private static final fields 
 * and public static nonfinal fields. This example shows that public static nonfinal fields 
 * can be altered, posing potential security risks, while private static final fields are constant 
 * and protected from unauthorized changes.
 */

 public class OBJ10 {
    
    // Private static final field, cannot be altered after initialization
    private static final String finalMessage = "This is the correct message!";

    // Public static nonfinal field, can be altered from anywhere in the program
    public static String nonFinalMessage = "This is the correct message!";

    /**
     * The main method demonstrates the use of private static final and 
     * public static nonfinal fields. It shows that the final field cannot 
     * be altered, while the nonfinal field can be modified.
     */
    public static void main(String[] args) {
        // Printing the final (constant) message
        System.out.println("Printing private static final message:");
        System.out.println(finalMessage); // This message is constant and cannot be changed
        
        System.out.println(); // New line for separation

        // Printing the nonfinal (modifiable) message
        System.out.println("Printing public static nonfinal message:");
        nonFinalMessage = "This message was altered"; // The message is changed
        System.out.println(nonFinalMessage); // This message can be modified and is altered here
    }
}
