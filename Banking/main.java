package Banking;

import Banking.Service.TransactionHandler;
import Banking.Service.UserHandler;
import Banking.Service.InputHandler;
import Banking.Database.Database;
import Banking.Model.User;
import Banking.Model.Account.Account;

/**
 * Main class that acts as the entry point to the banking application.
 * This class handles user login, registration, and displays a menu for
 * managing bank accounts.
 * 
 * @author Gavin Schiedler
 * @version 1.0
 * @since 2025
 */
public class main {

    // Declare static objects for handling input, database, user and transaction operations
    static InputHandler i = new InputHandler();
    static Database e = new Database();
    static UserHandler uh = UserHandler.access();
    static TransactionHandler t = TransactionHandler.access();

    /**
     * Displays the main menu for the user once they have logged in.
     * Allows the user to view, create, and modify their accounts.
     * 
     * @param user the logged-in user
     */
    public static void menu(User user) {
        System.out.println("\nLogin successful! Welcome, " + user.getName());
        double response = 0.0;

        // Repeatedly display menu until the user chooses to exit
        while (response != 4.0) {

            // Display the main options for managing accounts
            response = i.in("1. View Accounts\n2. Create Account\n3. Modify Account\n4. Exit\n");
            if (response == 1.0) {
                // Display the list of the user's accounts
                System.out.println("Accounts:\n");
                for (Account x : user.getAccounts()) {
                    System.out.println(x.toString());
                    System.out.println();
                }

            } else if (response == 2.0) {
                // Allow the user to create a new account (savings or checking)
                response = i.in("1. Savings\n2. Checking?\n");
                if (response == 1.0) {
                    // Create a savings account
                    double iB = i.in("What's the initial balance?: ");
                    double iR = i.in("What's the interest rate?: ");
                    user.addSavingAccount(iB, iR);
                } else if (response == 2.0) {
                    // Create a checking account
                    double iB = i.in("What's the initial balance?: ");
                    user.addCheckingAccount(iB);
                } else {
                    // Handle invalid input
                    System.out.println("Invalid Input.");
                    response = 0.0;
                }

            } else if (response == 3.0) {
                // Modify an existing account
                String temp = i.in("Which account: ", 0);
                if (temp != null && user.getAccount(temp) != null) {
                    Account ac = user.getAccount(temp);
                    while (response != 5.0) {
                        // Display account modification options
                        System.out.println();
                        response = i.in("1. Withdraw\n2. Deposit\n3. Display Balance\n4. Transfer\n5. Exit\n");
                        if (response == 1.0) {
                            // Withdraw funds from the account
                            t.withdraw(ac, i.in("How much would you like to withdraw?: "));
                        } else if (response == 2.0) {
                            // Deposit funds into the account
                            t.deposit(ac, i.in("How much would you like to deposit?: "));
                        } else if (response == 3.0) {
                            // Display account balance
                            t.displayBalance(ac);
                        } else if (response == 4.0) {
                            // Transfer funds between accounts
                            t.transfer(ac, user.getAccount(i.in("Transfer to what account?: ", 0)),
                                    i.in("How much?: "));
                        }
                    }
                    response = 0.0;
                }

            } else {
                // Log out if the user selects 4
                response = i.in("Logout Attempted, Press 4 again to confirm: ");
            }

        }

    }

    /**
     * Displays the login and user creation menu. Allows the user to log in
     * or create a new account.
     * 
     * @return true if login or user creation was successful, false if user chose to exit
     */
    public static boolean loginMenu() {

        // Display login or create account options
        double response = i.in("1. Login\n2. Create User\n3. Exit\n");
        if (response == 1.0) {
            // Login flow
            String uName = i.in("Username:", 0);
            String pass = i.in("Password:", 0);
            if (uh.login(uName, pass) != null)
                menu(uh.login(uName, pass)); // Proceed to the user's menu upon successful login
            return true;
        } else if (response == 2.0) {
            // User registration flow
            String uName = i.in("Username:", 0);
            String pass = i.in("Password:", 0);
            String name = i.in("Name:", 0);

            uh.registerUser(name, uName, pass); // Register the new user
            return true;
        }
        return false;
    }

    /**
     * Method to clean up resources properly upon program termination.
     */
    public static void cleanup() {
        System.out.println("Shutting down...");

        // Close any resources that need to be cleaned up (e.g., file readers/writers, database connections)
        try {
            // Assuming we may have some cleanup in the database or file services
            //e.close(); // If your Database class implements a close method
            
            System.out.println("Cleanup successful.");
        } catch (Exception e) {
            System.out.println("Error during cleanup: " + e.getMessage());
        }
    }

    /**
     * Main method that runs the banking application.
     * Continuously prompts for login or user creation until the user decides to exit.
     */
    public static void main(String[] args) {

        // Register the shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                cleanup();
            }
        });

        System.out.println("Welcome to DMGPD Financial!\n");

        // Continuously show the login menu
        while (loginMenu()) {

        }

        System.out.println("\nGoodbye!\n"); // Exit the application when the user chooses to exit
    }
}
