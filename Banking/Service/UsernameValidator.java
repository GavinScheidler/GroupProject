package Banking.Service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UsernameValidator{
    //cant start with a number
    private static final String USERNAME_PATTERN = "^[a-z][a-z0-9._]{5,14}$";

    public static boolean isValidUsername(String username) {
        //IDS11-J (Perform any string modification before validation) 
        String toCheck = username.toLowerCase();

    
        // Create a Pattern object
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        // Create a matcher object
        Matcher matcher = pattern.matcher(toCheck);

        // Check if username matches the pattern
        return matcher.matches();
    }

}
