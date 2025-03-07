package Banking.Service;

import Banking.Model.Account.Account;

/**
 * Handles transactions such as deposits, withdrawals, balance displays, 
 * and transfers between accounts. This class uses the Singleton pattern 
 * to ensure only one instance manages all transactions in the system.
 * 
 * The methods provided allow the user to interact with accounts by depositing 
 * or withdrawing funds, displaying account balances, and transferring funds 
 * between different accounts.
 * 
 * @author Mickael Agustin
 * @version 1.0
 * @since 2025
 */
public class TransactionHandler {
    private static TransactionHandler instance; // Singleton instance to ensure only one instance of the class

    /**
     * Private constructor to prevent direct instantiation. 
     * The Singleton instance will be created through the `access()` method.
     */
    private TransactionHandler(){};

    /**
     * Public method to get the single instance of the `TransactionHandler` class.
     * If the instance is null, a new instance is created.
     * 
     * @return the singleton instance of the TransactionHandler
     */
    public static TransactionHandler access() {
        if (instance == null) {
            instance = new TransactionHandler(); // Create a new instance if it doesn't exist
        }
        return instance;
    }

    /**
     * Deposits a specified amount into the given account. 
     * If the amount is valid (greater than 0), it updates the account's balance.
     * 
     * @param account the account where money is to be deposited
     * @param amount  the amount to deposit into the account
     */
    public void deposit(Account account, double amount) {
        if (amount > 0) {
            // Increase the account balance by the deposit amount
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    /**
     * Withdraws a specified amount from the given account.
     * If the amount is valid (greater than 0) and there are sufficient funds, 
     * the account balance is updated. Otherwise, an error message is displayed.
     * 
     * @param account the account from which money is to be withdrawn
     * @param amount  the amount to withdraw from the account
     * @return        true if the withdrawal was successful, false otherwise
     */
    public boolean withdraw(Account account, double amount) {
        if (amount > 0 && amount <= account.getBalance()) {
            // Deduct the withdrawal amount from the account balance
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
            return false;
        }
    }

    /**
     * Displays the balance of the specified account.
     * 
     * @param account the account whose balance is to be displayed
     */
    public void displayBalance(Account account) {
        // Display the account number and balance
        System.out.println("Account " + account.getAccountNumber() + " Balance: $" + account.getBalance());
    }

    /**
     * Transfers a specified amount of money from one account to another.
     * First, it withdraws the amount from the `fromAccount`, and if successful, 
     * deposits it into the `toAccount`.
     * 
     * @param fromAccount the account from which money is to be withdrawn
     * @param toAccount   the account to which money is to be deposited
     * @param amount      the amount to transfer
     * @return            true if the transfer was successful, false otherwise
     */
    public boolean transfer(Account fromAccount, Account toAccount, double amount) {
        if (fromAccount == null || toAccount == null) {
            System.out.println("Invalid account provided.");
            return false;
        }

        // Withdraw from the source account and deposit into the destination account
        if (withdraw(fromAccount, amount)) {
            deposit(toAccount, amount);
            System.out.println("Transfer of $" + amount + " from " + fromAccount.getAccountNumber() + " to " + toAccount.getAccountNumber() + " successful.");
            return true;
        } else {
            System.out.println("Transfer failed due to insufficient funds.");
            return false;
        }
    }
}
