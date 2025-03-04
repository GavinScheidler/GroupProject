package Banking.Service;

import java.util.ArrayList;
import java.util.List;

import Banking.Model.User;

public class UserHandler{
    private List<User> users;

    public UserHandler() {
        this.users = new ArrayList<>();
    }

    public boolean registerUser(String name, String username, String password) {
        // Check if username is already taken
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already taken.");
                return false;
            }
        }
        users.add(new User(name, username, password));
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
}
