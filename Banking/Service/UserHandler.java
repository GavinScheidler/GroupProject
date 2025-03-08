package Banking.Service;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Banking.Model.User;

/**
 * Manages user registration, login, and persistent storage of user data.
 * This class provides methods to register new users, validate login credentials, 
 * and load/save user data from/to a CSV file. It ensures that usernames are unique 
 * and follow a specific format defined by the `UsernameValidator` class.
 * 
 * The class follows the Singleton pattern to ensure there is only one instance 
 * of the user handler managing all users in the system. It also provides a mechanism 
 * to load users from a CSV file on startup and save them back to the file when there are changes.
 * 
 * @author Mickael Agustin
 * @version 1.0
 * @since 2025
 */
public class UserHandler {
    private List<User> users;
    private static final String USER_FILE = "Banking/Resource/users.csv"; // The file that stores the user data
    private static UserHandler instance; // Singleton instance of the UserHandler class
    private static UsernameValidator usernameValidator = new UsernameValidator(); // Class responsible for validating usernames

    /**
     * Private constructor to prevent direct instantiation. Initializes the list of users 
     * and loads users from the file.
     */
    private UserHandler() {
        this.users = new ArrayList<>();
        loadUsers(); // Load users from the CSV file on initialization
        
        //FIO14-J: Perform proper cleanup at program termination. 
        // Register the shutdown hook to save users when the program terminates
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                saveUsers(); // Save the list of users when the program shuts down
            }
        });
    }

    /**
     * Public method to access the single instance of UserHandler.
     * If the instance is null, it creates a new instance.
     * 
     * @return the single instance of UserHandler
     */
    public static UserHandler access() {
        if (instance == null) {
            instance = new UserHandler(); // Create a new instance if it doesn't exist
        }
        return instance;
    }

    /**
     * Registers a new user with the provided name, username, and password.
     * Validates the username format and checks if it is already taken. 
     * If the username is valid and not taken, the user is added to the list and saved to the file.
     * 
     * @param name      the name of the user
     * @param username  the username of the user
     * @param password  the password of the user
     * @return          true if the user is successfully registered, false otherwise
     */
    public boolean registerUser(String name, String username, String password) {
        // Validate username format
        if (!usernameValidator.isValidUsername(username)) {
            System.out.println("Invalid username! It must start with a letter, contain only letters, numbers, periods, or underscores, and be between 6 and 15 characters.");
            return false;
        }

        // Check if the username is already taken
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already taken.");
                return false;
            }
        }

        // Add new user and save users to file
        users.add(new User(name, username, password));
        saveUsers(); // Save the updated list of users to the file
        System.out.println("User registered successfully!");
        return true;
    }

    /**
     * Allows a user to login by checking their username and password.
     * If a user with the matching credentials is found, it returns the user object.
     * If not, it returns null.
     * 
     * @param username  the username of the user
     * @param password  the password of the user
     * @return          the User object if credentials are valid, null otherwise
     */
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                return user; // Return the user if credentials are correct
            }
        }
        System.out.println("Invalid username or password.");
        return null; // Return null if credentials are incorrect
    }

    /**
     * Saves the list of users to a CSV file. Each user's name, username, and password 
     * are written in a comma-separated format. If an error occurs while saving, it is caught and 
     * an error message is printed.
     */
    private void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                // Write user details to the CSV file
                writer.println(user.getName() + "," + user.getUsername() + "," + user.getPassword());
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage()); // Handle errors during file write
        }
    }

    /**
     * Loads the list of users from a CSV file. Each line is read, split by commas, 
     * and used to create a new User object which is then added to the list of users. 
     * If no file is found or an error occurs, it prints a message.
     */
    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line by commas and create a new User object
                String[] data = line.split(",");
                if (data.length == 3) { // Ensure the line contains the correct number of elements
                    users.add(new User(data[0], data[1], data[2])); // Add the user to the list
                }
            }
        } catch (IOException e) {
            System.out.println("No existing users found."); // Handle errors during file read
        }
    }

    // Method to get a user by username
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // Return null if no user found with the username
    }

    // Getter for users list
    public List<User> getUsers() {
        return users;
    }
}
