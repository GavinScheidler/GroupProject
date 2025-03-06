package Banking.Database;

import Banking.Model.User;

import java.io.*;

/**
 * This class is used for any interaction with the text file database
 * the database is set up where each line is like a key for a hashtable.
 * What ever the line holds is the element,
 * 
 * @author Dane Iwema
 */
public class Database {
    private static final String FILE_NAME = "database.txt";

    /**
     * This method will pull the user with
     * the matching username and password with the given
     * user name and password.
     * 
     * @param username
     * @param password
     * @return a user object with matching credentials
     */
    public static User getUser(String username, String password) {
        int location = hashFunction(username + password);

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int currentLine = 0;

            // Read the file line by line until we reach the hashed line number
            while ((line = reader.readLine()) != null) {
                if (currentLine == location) {
                    return parseUser(line);
                }
                currentLine++;
            }
        } catch (IOException e) {
            System.err.println("Error reading database: " + e.getMessage());
        }

        return null; // User not found
    }

    /**
     * This is used to take the line from the file
     * and create the appropriate user and accounts objects
     * from the database.
     * 
     * @param line
     * @return
     */
    private static User parseUser(String line) {
        // Assuming the format is: name,username,password
        String[] parts = line.split(",");
        if (parts.length < 3) {
            return null; // Invalid format
        }
        //TODO parse the user's accounts and add them to their user profile

        return new User(parts[1], parts[0], parts[2]); // (username, name, password)
    }

    /**
     * This method will rewrite the data at the postion of the
     * User given only if the username in the spot is empty or matching
     * 
     * @param request string
     * @return true if the save was successfull and false if it was not successfull
     */
    public static boolean setUser(String username, String password) {
        int location = hashFunction(username + password);
        //TODO navigate to the file location
        //TODO write the data to the file
        return false;
    }

    /**
     * This is the hash function that will be used to find a unique spot
     * for the username and password combination.
     * 
     * @param username_password string
     * @return integer location
     */
    private static int hashFunction(String username_password){
        return Math.abs(username_password.hashCode() % 1000);
    }
}
