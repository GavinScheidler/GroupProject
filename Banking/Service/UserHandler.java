package Banking.Service;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Banking.Model.User;

public class UserHandler {
    private List<User> users;
    private static final String USER_FILE = "users.csv";
    private static UserHandler instance; // Singleton instance

    // Private constructor to prevent direct instantiation
    private UserHandler() {
        this.users = new ArrayList<>();
        loadUsers();
    }

    // Public method to get the single instance
    public static UserHandler getInstance() {
        if (instance == null) {
            instance = new UserHandler();
        }
        return instance;
    }

    public boolean registerUser(String name, String username, String password) {
        // Validate username
        if (!UsernameValidator.isValidUsername(username)) {
            System.out.println("Invalid username! It must start with a letter, contain only letters, numbers, periods, or underscores, and be between 6 and 15 characters.");
            return false;
        }

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already taken.");
                return false;
            }
        }
        
        users.add(new User(name, username, password));
        saveUsers();
        System.out.println("User registered successfully!");
        return true;
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                System.out.println("Login successful! Welcome, " + user.getName());
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    private void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                writer.println(user.getName() + "," + user.getUsername() + "," + user.getPassword());
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    users.add(new User(data[0], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing users found.");
        }
    }
}
