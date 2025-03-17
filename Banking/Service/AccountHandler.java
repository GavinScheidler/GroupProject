package Banking.Service;

import Banking.Model.User;
import Banking.Model.Account.Account;
import Banking.Model.Account.CheckingAccount;
import Banking.Model.Account.SavingsAccount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Handles the management and operations of bank accounts. This class provides methods to load, 
 * save, deposit, withdraw, transfer, and retrieve account balances for the banking application.
 * It ensures account data is maintained across sessions and provides operations for account management.
 * 
 * @author Mickael Agustin & Dane Iwema
 * @version 1.0
 * @since 2025
 */
public class AccountHandler {

    private static final String ACCOUNT_FILE = "Banking/Resource/accounts.csv"; // CSV file that holds account data
    private ArrayList<Account> allAccounts; // List to hold all accounts
    private static AccountHandler instance; // Singleton instance of AccountHandler
    private UserHandler userHandler = UserHandler.access(); // UserHandler instance for managing users

    /**
     * Private constructor initializes the list of accounts and loads all accounts from the CSV file.
     */
    private AccountHandler() {
        this.allAccounts = loadAccounts(); // Load accounts from the file into memory
    }

    /**
     * Provides access to the singleton instance of the AccountHandler.
     * 
     * @return The single instance of AccountHandler.
     */
    public static AccountHandler access() {
        if (instance == null) {
            instance = new AccountHandler(); // Create a new instance if it doesn't exist
        }
        return instance; // Return the existing or newly created instance
    }

    // In saveAccounts method:
private void saveAccounts() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(ACCOUNT_FILE))) {
      
        // Iterate over each user and their accounts
        for (User user : userHandler.getUsers()) {
            for (Account account : user.getAccounts()) {
                // Write account data to CSV: Username, AccountNumber, Type, Balance, InterestRate
                String line = user.getUsername() + "," 
                            + account.getAccountNumber() + ","
                            + (account instanceof CheckingAccount ? 1 : 2) + ","
                            + account.getBalance() + ","
                            + (account instanceof SavingsAccount ? ((SavingsAccount) account).getInterestRate() : "0.0");
                writer.println(line); // Write the line to the CSV
            }
        }
    } catch (IOException e) {
        System.out.println("Error saving accounts: " + e.getMessage()); // Handle error during file write
    }
}

// In loadAccounts method:
private ArrayList<Account> loadAccounts() {
    ArrayList<Account> accounts = new ArrayList<>(); // List to hold all the accounts
    try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] accountDetails = line.split(","); // Split the line into account details

            if (accountDetails.length == 4 || accountDetails.length == 5) {
                Account account;
                String username = accountDetails[0]; // Username at index 0
                String accountNumber = accountDetails[1]; // AccountNumber at index 1
                int type = Integer.parseInt(accountDetails[2]); // Type at index 2
                double balance = Double.parseDouble(accountDetails[3]); // Balance at index 3
                double interestRate = 0;
                if(accountDetails.length == 5){
                    interestRate = Double.parseDouble(accountDetails[4]); // Interest rate at index 4
                }

                // Create the appropriate account type (Checking or Savings)
                if (type == 1) {
                    account = new CheckingAccount(accountNumber, balance); // Create Checking account
                } else {
                    account = new SavingsAccount(accountNumber, balance, interestRate); // Create Savings account
                }

                userHandler.getUserByUsername(username).addAccount(account); // Add account to the user
                accounts.add(account); // Add account to the list
            }
        }
    } catch (IOException e) {
        e.printStackTrace(); // Handle IO exception if reading the file fails
    }
    return accounts; // Return the list of accounts
}


    /**
     * Retrieves an account by its account number.
     *
     * @param accountNumber The account number of the account to retrieve.
     * @return The account object if found, otherwise null.
     */
    public Account getAccountByAccountNumber(String accountNumber) {
        for (Account account : allAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account; // Return the account if it matches the account number
            }
        }
        return null; // Return null if no matching account is found
    }

    /**
     * Deposits an amount into the specified account.
     *
     * @param account The account to deposit into.
     * @param amount The amount to deposit.
     * @return A message indicating the result of the deposit.
     */
    public String deposit(Account account, double amount) {
        if (amount <= 0) {
            return "Deposit amount must be greater than 0."; // Validate positive deposit amount
        }

        account.setBalance(account.getBalance() + amount); // Update account balance
        saveAccounts(); // Save accounts after deposit
        return "Deposit successful. New balance: " + account.getBalance(); // Return success message
    }

    /**
     * Withdraws an amount from the specified account.
     *
     * @param account The account to withdraw from.
     * @param amount The amount to withdraw.
     * @return A message indicating the result of the withdrawal.
     */
    public String withdraw(Account account, double amount) {
        if (amount <= 0) {
            return "Withdrawal amount must be greater than 0."; // Validate positive withdrawal amount
        }

        if (amount > account.getBalance()) {
            return "Insufficient funds."; // Check if there are sufficient funds
        }
        account.setBalance(account.getBalance() - amount); // Update account balance
        saveAccounts(); // Save accounts after withdrawal
        return "Withdrawal successful. New balance: " + account.getBalance(); // Return success message
    }

    /**
     * Transfers an amount between two accounts.
     *
     * @param fromAccount The account to transfer from.
     * @param toAccount The account to transfer to.
     * @param amount The amount to transfer.
     * @return A message indicating the result of the transfer.
     */
    public String transfer(Account fromAccount, Account toAccount, double amount) {
        if (amount <= 0) {
            return "Transfer amount must be greater than 0."; // Validate positive transfer amount
        }

        if (fromAccount == null || toAccount == null) {
            return "One or both accounts not found."; // Ensure both accounts are valid
        }

        if (amount > fromAccount.getBalance()) {
            return "Insufficient funds in the source account."; // Check if source account has sufficient funds
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount); // Update source account balance
        toAccount.setBalance(toAccount.getBalance() + amount); // Update target account balance

        saveAccounts(); // Save accounts after transfer
        return "Transfer successful. New balance of source account: " + fromAccount.getBalance() + ", New balance of target account: " + toAccount.getBalance(); // Return success message
    }

    /**
     * Retrieves the balance of an account.
     *
     * @param account The account whose balance is to be retrieved.
     * @return The current balance of the account, or -1 if account is not found.
     */
    public double getBalance(Account account) {
        if (account == null) {
            return -1; // Return -1 if account is not found
        }
        return account.getBalance(); // Return the current balance of the account
    }

    /**
     * Creates a new checking account for a user with the specified initial balance.
     *
     * @param username The username of the user creating the account.
     * @param balance The initial balance of the new account.
     */
    public void newAccount(String username, double balance) {
        CheckingAccount checkingAccount = new CheckingAccount(balance); // Create new Checking account
        userHandler.getUserByUsername(username).addAccount(checkingAccount); // Add the account to the user
        allAccounts.add(checkingAccount); // Add the account to the list of all accounts
        saveAccounts(); // Save accounts after creating new account
    }

    /**
     * Creates a new savings account for a user with the specified initial balance and interest rate.
     *
     * @param username The username of the user creating the account.
     * @param balance The initial balance of the new account.
     * @param interestRate The interest rate for the savings account.
     */
    public void newAccount(String username, double balance, double interestRate) {
        SavingsAccount savingsAccount = new SavingsAccount(balance, interestRate); // Create new Savings account
        userHandler.getUserByUsername(username).addAccount(savingsAccount); // Add the account to the user
        allAccounts.add(savingsAccount); // Add the account to the list of all accounts
        saveAccounts(); // Save accounts after creating new account
    }
}
