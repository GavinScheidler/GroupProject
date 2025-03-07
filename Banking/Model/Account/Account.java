/**
 * Represents a general bank account. This abstract class holds common properties such as the account 
 * number and balance, and provides methods to retrieve and update these properties. It also includes 
 * an abstract method `accountType()` that must be implemented by subclasses to define the specific 
 * type of account (e.g., CheckingAccount, SavingsAccount).
 * 
 * The class generates a unique account number using UUID and allows the user to view and modify 
 * the account's balance. The toString() method provides a string representation of the account 
 * details for easy display.
 * 
 * Some important points to consider are:
 * 
 * The account number is generated uniquely using the `UUID` class.</li>
 * Subclasses must implement the `accountType()` method to define specific account behaviors.</li>
 * Balance is stored as a `double` to allow for fractional amounts, supporting typical bank operations.</li>
 * 
 *
 * The class is intended to be extended by specific types of accounts (e.g., CheckingAccount, SavingsAccount),
 * which will implement the abstract method to provide specific functionality.
 * 
 * @author      Mickael Agustin
 * @version     1.0
 * @since       2025
 */

 package Banking.Model.Account;

 import java.util.UUID;
 
 public abstract class Account {
     // Protected properties for the account number and balance
     protected String accountNumber;
     protected double balance;
 
     /**
      * Constructor for creating a new account with the specified initial balance. 
      * A unique account number is generated using UUID.
      * 
      * @param initialBalance the initial balance of the account
      */
     public Account(double initialBalance) {
         this.accountNumber = UUID.randomUUID().toString(); // Generates a unique account number using UUID
         this.balance = initialBalance; // Sets the initial balance of the account
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
 