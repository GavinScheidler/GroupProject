package Banking.Service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Validates whether a username meets the specified criteria.
 * 
 * The criteria for a valid username are:
 * 
 * Must start with a lowercase letter</li>
 * Can contain lowercase letters, digits, periods (.), and underscores (_)</li>
 * Must be between 6 and 15 characters in length</li>
 * 
 * 
 * This class uses regular expressions to check the format of the username.
 * 
 * @author Mickael Agustin
 * @version 1.0
 * @since 2025
 */
public class UsernameValidator {

    // Regular expression pattern for a valid username
    // Must start with a letter and can include letters, digits, periods, or underscores
    private static final String USERNAME_PATTERN = "^[a-z][a-z0-9._]{5,14}$";

    /**
     * Checks if the given username is valid based on the predefined pattern.
     * The username must start with a letter and meet the specified length and character constraints.
     * 
     * @param username the username to validate
     * @return true if the username matches the pattern, false otherwise
     */
    public boolean isValidUsername(String username) {
        //IDS11-J : Perform any string modification before validation.
        
        // Convert username to lowercase to make validation case-insensitive
        String toCheck = username.toLowerCase();

        // Create a Pattern object based on the regular expression
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);

        // Create a Matcher object to check the input username against the pattern
        Matcher matcher = pattern.matcher(toCheck);

        // Return true if the username matches the pattern, false otherwise
        return matcher.matches();
    }
}
