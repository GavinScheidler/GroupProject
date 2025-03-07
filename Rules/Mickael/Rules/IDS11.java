/*
 * CODE EXAMPLE FOR IDS11
 * Author: Mickael Agustin
 * Description: Demonstrates email validation using regular expressions.
 * This example validates whether an email address is properly formatted according to a defined regular expression.
 * The `isValidEmail` method ensures the email address follows the pattern of a valid email.
 * The `message` method provides a user-friendly result, indicating whether the email is valid or not.
 */

/**
 * A class that validates email addresses using regular expressions.
 * It includes methods to check if an email address is properly formatted and 
 * to generate a user-friendly validation message.
 */
 public class IDS11 {

    /**
     * Validates an email address based on a predefined regular expression pattern.
     *
     * @param email The email address to be validated.
     * @return {@code true} if the email is valid, otherwise {@code false}.
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false; // Return false if the email is null
        }

        // Remove white space from inputted string
        email = email.trim();

        // Convert the email string to lowercase
        email = email.toLowerCase();

        // Define the regular expression for a valid email address
        String emailRegEx = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        // Perform validation after modifications
        return email.matches(emailRegEx); // Return true if the email matches the regex, false otherwise
    }

    /**
     * Generates a validation message indicating whether an email address is valid or not.
     *
     * @param email The email address to be checked.
     * @return A string message stating whether the email is valid or not.
     */
    public static String message(String email) {
        if (!isValidEmail(email)) {
            return "'" + email + "'" + " is NOT valid"; // Return message for invalid email
        } else {
            return "'" + email + "'" + " is valid"; // Return message for valid email
        }
    }

    /**
     * The main method demonstrating the email validation.
     */
    public static void main(String[] args) {
        // Example of an invalid email
        String invalidEmail = "MyNameis*THis()@email.com";
        System.out.println(message(invalidEmail));

        System.out.println(); // New line

        // Example of a valid email
        String validEmail = "thisIsAvalidemail@email.com";
        System.out.println(message(validEmail));
    }
}
