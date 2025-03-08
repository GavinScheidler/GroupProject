package Banking.Service;

import Banking.Model.Account.Account;

import java.io.*;
import java.util.*;

/**
 * Handles transactions such as deposits, withdrawals, balance displays, 
 * and transfers between accounts. This class uses the Singleton pattern 
 * to ensure only one instance manages all transactions in the system.
 * 
 * The methods provided allow the user to interact with accounts by depositing 
 * or withdrawing funds, displaying account balances, and transferring funds 
 * between different accounts. Additionally, all transactions are logged in a separate CSV file.
 * 
 * @author Mickael Agustin & Dane Iwema
 * @version 1.1
 * @since 2025
 */
public class TransactionHandler {
    private static final String LOG_FILE = "Banking/Resource/transactions.csv"; // Path to the transaction log file
    private static TransactionHandler instance; // Singleton instance of TransactionHandler
    private static AccountHandler accountHandler = AccountHandler.access();

    // Private constructor to prevent instantiation from outside the class
    private TransactionHandler() {
    }

    /**
     * Provides access to the singleton instance of the TransactionHandler.
     * 
     * @return The single instance of TransactionHandler.
     */
    public static TransactionHandler access() {
        if (instance == null) {
            instance = new TransactionHandler(); // Create new instance if it doesn't exist
        }
        return instance; // Return the existing or newly created instance
    }

    /**
     * Logs a transaction to the CSV file for a single account (deposit or withdrawal).
     * 
     * @param type The type of the transaction (Deposit/Withdrawal).
     * @param accountNum The account number of the account involved in the transaction.
     * @param amount The amount of money involved in the transaction.
     */
    private void logTransaction(String type, String accountNum, double amount) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            // Log the transaction with type, account number, amount, and timestamp
            writer.println(type + "," + accountNum + "," + amount + "," + new Date());
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage()); // Handle error if logging fails
        }
    }

    /**
     * Logs a transaction to the CSV file for a transfer between two accounts.
     * 
     * @param type The type of the transaction (Transfer).
     * @param fromAccount The account from which money is being transferred.
     * @param toAccount The account to which money is being transferred.
     * @param amount The amount of money being transferred.
     */
    private void logTransaction(String type, Account fromAccount, Account toAccount, double amount) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            // Log the transaction with type, both account numbers, amount, and timestamp
            writer.println(type + "," + fromAccount.getAccountNumber() + "," + toAccount.getAccountNumber() + "," + amount + "," + new Date());
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage()); // Handle error if logging fails
        }
    }

    /**
     * Deposits an amount into the specified account.
     * 
     * @param account The account to deposit into.
     * @param amount The amount to deposit.
     */
    public void deposit(Account account, double amount) {
        if (account != null && amount > 0) {
            System.out.println(accountHandler.deposit(account, amount));
            logTransaction("Deposit", account.getAccountNumber(), amount); // Log the deposit transaction
        } else {
            System.out.println("Invalid deposit."); // Handle invalid deposit
        }
    }

    /**
     * Withdraws an amount from the specified account.
     * 
     * @param account The account to withdraw from.
     * @param amount The amount to withdraw.
     * @return True if the withdrawal was successful, false otherwise.
     */
    public boolean withdraw(Account account, double amount) {
        if (account != null && amount > 0 && amount <= account.getBalance()) {
            System.out.println(accountHandler.withdraw(account, amount));
            logTransaction("Withdrawal", account.getAccountNumber(), amount); // Log the withdrawal transaction
            return true; // Return true indicating successful withdrawal
        } else {
            System.out.println("Invalid withdrawal or insufficient funds."); // Handle invalid withdrawal or insufficient funds
            return false; // Return false indicating failure
        }
    }

    public String getBalance(Account account) {
        if(account != null){
            return "Current Balance: " + account.getBalance();
        }else{
            return "Account does not exist.";
        }
    }

    /**
     * Transfers an amount from one account to another.
     * 
     * @param fromAccount The account to transfer funds from.
     * @param toAccount The account to transfer funds to.
     * @param amount The amount to transfer.
     * @return True if the transfer was successful, false otherwise.
     */
    public boolean transfer(Account fromAccount, Account toAccount, double amount) {
        //EXP53-J: Use parentheses for precedence of operations. 
        if ((fromAccount != null) && (toAccount != null) && (fromAccount.getBalance() >= amount)) {
            System.out.println(accountHandler.transfer(fromAccount, toAccount, amount)); // Deposit to the target account after successful withdrawal
            logTransaction("Transfer", fromAccount, toAccount, amount); // Log the transfer transaction
            return true; // Return true indicating successful transfer
        } else {
            System.out.println("Transfer failed."); // Handle failed transfer
            return false; // Return false indicating failure
        }
    }
}
