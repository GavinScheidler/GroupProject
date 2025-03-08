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

public class AccountHandler {

    private static final String ACCOUNT_FILE = "Banking/Resource/accounts.csv"; // CSV file that holds account data
    private ArrayList<Account> allAccounts; // List to hold all accounts
    private static AccountHandler instance;
    private UserHandler userHandler = UserHandler.access();

    /**
     * Constructor initializes the list and loads all accounts from the file.
     */
    private AccountHandler() {
        this.allAccounts = loadAccounts();
    }

    public static AccountHandler access(){
        if (instance == null) {
            instance = new AccountHandler(); // Create a new instance if it doesn't exist
        }
        return instance;
    }

    /**
     * Loads all accounts from the CSV file into memory.
     * 
     * @return List of all accounts.
     */
    private ArrayList<Account> loadAccounts() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountDetails = line.split(",");

                if (accountDetails.length == 5) {
                    Account account;
                    String username = accountDetails[0]; // Username at index 0
                    String accountNumber = accountDetails[1]; // AccountNumber at index 1
                    int type = Integer.parseInt(accountDetails[2]); // Type at index 2
                    double balance = Double.parseDouble(accountDetails[3]); // Balance at index 3
                    double interestRate = Double.parseDouble(accountDetails[4]); // Extra (interest rate) at index 4

                    // Create the appropriate account type (Checking or Savings)
                    if (type == 1) {
                        account = new CheckingAccount(accountNumber, balance);
                    } else {
                        account = new SavingsAccount(accountNumber, balance, interestRate);
                    }

                    userHandler.getUserByUsername(username).addAccount(account);
                    accounts.add(account);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    // Save accounts to CSV
    private void saveAccounts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ACCOUNT_FILE))) {
            for (User user : userHandler.getUsers()) {
                for (Account account : user.getAccounts()) {
                    // For each account, write a line in the CSV file
                    // Format: Username,AccountNumber,Type,Balance,InterestRate
                    String line = user.getUsername() + "," 
                                + account.getAccountNumber() + ","
                                + (account instanceof CheckingAccount ? 1 : 2) + ","
                                + account.getBalance() + ","
                                + (account instanceof SavingsAccount ? ((SavingsAccount) account).getInterestRate() : "0.0");
    
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage()); // Handle errors during file write
        }
    }
    

    /**
     * Retrieves an account by its account number from the loaded accounts.
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
            return "Deposit amount must be greater than 0.";
        }

        account.setBalance(account.getBalance() + amount);
        saveAccounts(); // Save accounts after deposit
        return "Deposit successful. New balance: " + account.getBalance();
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
            return "Withdrawal amount must be greater than 0.";
        }

        if (amount > account.getBalance()) {
            return "Insufficient funds.";
        }

        account.setBalance(account.getBalance() - amount);
        saveAccounts(); // Save accounts after withdrawal
        return "Withdrawal successful. New balance: " + account.getBalance();
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
            return "Transfer amount must be greater than 0.";
        }

        if (fromAccount == null || toAccount == null) {
            return "One or both accounts not found.";
        }

        if (amount > fromAccount.getBalance()) {
            return "Insufficient funds in the source account.";
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        saveAccounts(); // Save accounts after transfer
        return "Transfer successful. New balance of source account: " + fromAccount.getBalance() + ", New balance of target account: " + toAccount.getBalance();
    }

    /**
     * Gets the balance of an account.
     *
     * @param account The account whose balance is to be retrieved.
     * @return The current balance of the account.
     */
    public double getBalance(Account account) {
        if (account == null) {
            return -1; // Indicating account not found
        }
        return account.getBalance();
    }

    public void newAccount(String username, double balance){
        CheckingAccount checkingAccount = new CheckingAccount(balance);
        userHandler.getUserByUsername(username).addAccount(checkingAccount);
        allAccounts.add(checkingAccount);
        saveAccounts(); // Save accounts after creating new account
    }

    public void newAccount(String username, double balance, double interestRate){
        SavingsAccount savingsAccount = new SavingsAccount(balance, interestRate);
        userHandler.getUserByUsername(username).addAccount(savingsAccount);
        allAccounts.add(savingsAccount);
        saveAccounts(); // Save accounts after creating new account
    }
}
