package Banking.Model.Account;

import java.util.Random;

public abstract class Account {
    // Protected properties for the account number and balance
    protected String accountNumber;
    protected double balance;

    /**
     * Constructor for creating a new account with the specified initial balance. 
     * A unique 6-digit account number is generated.
     * 
     * @param initialBalance the initial balance of the account
     */
    public Account(double initialBalance) {
        this.accountNumber = generateAccountNumber(); // Generates a 6-digit account number
        this.balance = initialBalance; // Sets the initial balance of the account
    }

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance; // Sets the initial balance of the account
    }

    /**
     * Generates a random 6-digit account number.
     * 
     * @return a 6-digit string representing the account number
     */
    private String generateAccountNumber() {
        Random random = new Random();
        int accountNumberInt = 100000 + random.nextInt(900000); // Generates a random number between 100000 and 999999
        return String.valueOf(accountNumberInt); // Converts the int to a String
    }

    /**
     * Returns the account number of the account.
     * 
     * @return the account number of the account
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Returns the current balance of the account.
     * 
     * @return the balance of the account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account to a new value.
     * 
     * @param balance the new balance to set for the account
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Returns a string representation of the account, including the account number and balance.
     * 
     * @return a string with account details
     */
    public String toString() {
        return ("Account Number: " + getAccountNumber() + "\nBalance: " + getBalance());
    }

    /**
     * Abstract method that must be implemented by subclasses to define the specific type of account.
     * Each subclass (e.g., CheckingAccount, SavingsAccount) will define its own version of accountType().
     * @return int 1: checking 2: savings
     */
    public abstract int accountType(); // Abstract method to be implemented by subclasses
}
