/* 
 * Example for IDS03
 * Author: Gavin Scheidler
*/

import java.util.regex.Pattern;

public class IDS03 {

    public static String sanitizeUser(String username) {
        return Pattern.matches("[A-Za-z0-9_]+", username)
                ? username
                : "unauthorized user";
    }

    public static void login(String username, boolean loginSuccessful) {
        // Prevents script injection into log display

        if (loginSuccessful) {
            System.out.println("User login succeeded for: " + sanitizeUser(username));
        } else {
            System.out.println("User login failed for: " + sanitizeUser(username));
        }
    }

    public static void main(String[] args) {

        login("<Script>", true);

    }

}
