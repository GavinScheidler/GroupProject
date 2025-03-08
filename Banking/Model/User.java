/**
 * Represents a user in the banking system. This class manages user details
 * such as name, username, password, and a list of accounts (both Checking and Savings accounts).
 * It provides methods to manage user accounts, check passwords, and perform account operations.
 * The class allows users to add savings and checking accounts, retrieve accounts by account number,
 * and verify their passwords.
 * The class maintains user information and provides methods for adding, retrieving, and updating accounts.
 * This code ensures proper management of user data while keeping the account operations separate.
 * 
 * @author Mickael Agustin
 * @version 1.0
 * @since 2025
 */

package Banking.Model;

import java.util.ArrayList;
import java.util.List;

import Banking.Model.Account.Account;

/**
 * Represents a user in the banking system. A user has a name, username, password,
 * and a list of accounts (both Checking and Savings accounts). The User class
 * provides methods to manage the user's accounts and check the user's password.
 */
public class User {
    private String name;
    private String username;
    private String password;
    private List<Account> accounts;

    /**
     * Constructs a new User with the specified name, username, and password.
     * Initializes the accounts list as an empty list.
     * 
     * @param name      the name of the user
     * @param username  the username of the user
     * @param password  the password of the user
     */
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    /**
     * Returns the name of the user.
     * 
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the username of the user.
     * 
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user.
     * 
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns a list of the user's accounts.
     * 
     * @return a list of the user's accounts
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * Adds a new Account to the user's list of accounts with the specified
     * initial balance and interest rate.
     * 
     * @param account account to be added to the user's list of accounts
     */
    public void addAccount(Account account) {
        accounts.add(account);
    }

    /**
     * Returns the account associated with the specified account number, or null if
     * no account is found with that account number.
     * 
     * @param accountNumber the account number of the account to retrieve
     * @return the account with the specified account number, or null if no such account exists
     */
    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Checks if the provided password matches the user's stored password.
     * 
     * @param inputPassword the password to check
     * @return true if the inputPassword matches the user's password, false otherwise
     */
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword); 
    }
}
 