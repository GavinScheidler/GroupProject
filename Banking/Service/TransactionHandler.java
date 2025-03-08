package Banking.Service;

import Banking.Model.Account.Account;
import Banking.Model.Account.CheckingAccount;
import Banking.Model.Account.SavingsAccount;

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
 * @author Mickael Agustin
 * @version 1.1
 * @since 2025
 */
public class TransactionHandler {
    private static final String LOG_FILE = "Banking/Resource/transactions.csv";
    private static TransactionHandler instance;
    private Map<String, Account> accounts;

    private TransactionHandler() {
    }

    public static TransactionHandler access() {
        if (instance == null) {
            instance = new TransactionHandler();
        }
        return instance;
    }

    private void logTransaction(String type, String accountNum, double amount) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(type + "," + accountNum + "," + amount + "," + new Date());
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }

    private void logTransaction(String type, String fromAccount, String toAccount, double amount) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(type + "," + fromAccount + "," + toAccount + "," + amount + "," + new Date());
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }

    public void deposit(Account account, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null && amount > 0) {
            account.setBalance(account.getBalance() + amount);
            logTransaction("Deposit", accountNumber, amount);
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit.");
        }
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null && amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            logTransaction("Withdrawal", accountNumber, amount);
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Invalid withdrawal or insufficient funds.");
            return false;
        }
    }

    public void displayBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account " + accountNumber + " Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = accounts.get(fromAccountNumber);
        Account toAccount = accounts.get(toAccountNumber);

        if (fromAccount != null && toAccount != null && withdraw(fromAccountNumber, amount)) {
            deposit(toAccountNumber, amount);
            logTransaction("Transfer", fromAccountNumber, toAccountNumber, amount);
            return true;
        } else {
            System.out.println("Transfer failed.");
            return false;
        }
    }
}
