package Banking.Service;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Handles user input for the banking application. This class provides methods 
 * to read strings and doubles from the user, while ensuring the input is sanitized 
 * and meets certain security standards.
 * 
 * It normalizes and checks the input to prevent malicious characters and 
 * ensures that numeric inputs are correctly parsed. The class also enforces 
 * a consistent format for user input to ensure clean data entry.
 * 
 * @author Gavin Schiedler
 * @version 1.0
 * @since 2025
 */
public class InputHandler {

    Scanner scan = new Scanner(System.in); // Scanner object to read user input

    /*
     * MET03 - Security Check Declared Final
     */
    /**
     * Reads a line of input from the user, normalizes it, and checks it for validity.
     * This method ensures that the input contains only alphanumeric characters, 
     * underscores, periods, or hyphens, preventing the inclusion of malicious code.
     * 
     * @return a valid string input from the user
     */
    public final String in() {
        boolean cont = true; // Flag to control the loop until valid input is received
        String temp = ""; // Temporary variable to hold user input
        while (cont) {
            temp = this.scan.nextLine(); // Read the line of input from the user
            temp = temp.strip(); // Strip any leading or trailing spaces from the input

            /*
             * IDS01 - Normalizes the input before performing checks
             */
            temp = Normalizer.normalize(temp, Form.NFKC); // Normalize input to prevent encoding issues

            /*
             * IDS03 - Validates input against a regular expression to ensure it contains
             * only valid characters (letters, numbers, underscores, periods, and hyphens).
             */
            if (!Pattern.matches("[A-Za-z0-9_\\.\\-]+", temp)) {
                // If the input doesn't match the allowed pattern, prompt the user to try again
                System.out.print("Invalid, try again: ");
            } else {
                cont = false; // Exit loop if valid input is received
            }
        }
        return temp; // Return the valid input
    }

    /**
     * Prompts the user with a message and reads a double value from the input. 
     * It ensures that the input is a valid number and keeps prompting until 
     * the user provides a valid numeric input.
     * 
     * @param prompt the message displayed to the user
     * @return the parsed double value from the user input
     */
    public double in(String prompt) {
        System.out.print(prompt); // Display the prompt message
        String temp = in(); // Get the input from the user
        boolean cont = true; // Flag to control the loop until valid input is received
        Double d = 0.0; // Temporary variable to store the parsed double value
        while (cont) {
            try {
                d = Double.parseDouble(temp); // Try to parse the input as a double
                cont = false; // Exit loop if parsing is successful
            } catch (NumberFormatException e) {
                // If the input cannot be parsed as a double, display an error message and prompt again
                System.out.println("Invalid Input.");
                System.out.print(prompt); // Prompt the user again
                temp = in(); // Get new input from the user
            }
        }
        return (double) d; // Return the valid double value
    }

    /**
     * Reads a string input from the user using a custom prompt. This method is
     * an overloaded version of the `in()` method that allows for the inclusion of
     * an additional parameter, though it's not currently utilized.
     * 
     * @param prompt the message displayed to the user
     * @param seperator an additional parameter that is not currently used
     * @return the string input provided by the user
     */
    public String in(String prompt, int seperator) {
        System.out.print(prompt); // Display the prompt message
        return in(); // Call the standard in() method to get the input
    }
}
