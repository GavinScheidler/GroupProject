package Banking.Service;

import Banking.Model.Account.Account;
import Banking.Model.Account.CheckingAccount;
import Banking.Model.Account.SavingsAccount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountHandler {

    private static final String ACCOUNT_FILE = "accounts.csv"; // CSV file that holds account data

    /**
     * Retrieves an account by its account number.
     * This method reads from the CSV file and returns the account matching the given account number.
     *
     * @param accountNumber The account number of the account to retrieve.
     * @return The account object if found, otherwise null.
     */
    private Account getAccountByAccountNumber(String accountNumber) {
        Account account = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into account details using comma as the delimiter
                String[] accountDetails = line.split(",");
                
                // Ensure the data has exactly 5 fields: Username, AccountNumber, Type, Balance, Extra
                if (accountDetails.length == 5 && accountDetails[1].equals(accountNumber)) {
                    // Extract account details from the split line (Username at index 0, AccountNumber at index 1, etc.)
                    int type = Integer.parseInt(accountDetails[2]);     // Type is at index 2
                    double balance = Double.parseDouble(accountDetails[3]); // Balance is at index 3
                    double interestRate = Double.parseDouble(accountDetails[4]);   // Extra is at index 4
                
                    if(type == 1){
                        account = new CheckingAccount(balance);
                    }else{
                        account = new SavingsAccount(balance, interestRate);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return account; // Return null if no matching account is found
    }

    private ArrayList<Account> getAccountsByAccountUsername(String username) {
        ArrayList<Account> accounts = new ArrayList<Account>(); 
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            Account account;
            while ((line = reader.readLine()) != null) {
                // Split the line into account details using comma as the delimiter
                String[] accountDetails = line.split(",");
                
                // Ensure the data has exactly 5 fields: Username, AccountNumber, Type, Balance, Extra
                if (accountDetails.length == 5 && accountDetails[0].equals(username)) {
                    // Extract account details from the split line (Username at index 0, AccountNumber at index 1, etc.)
                    String accountNumber = accountDetails[1]; // AccountNumber is at index 1
                    int type = Integer.parseInt(accountDetails[2]);     // Type is at index 2
                    double balance = Double.parseDouble(accountDetails[3]); // Balance is at index 3
                    double interestRate = Double.parseDouble(accountDetails[4]);   // Extra is at index 4
                
                    if(type == 1){
                        account = new CheckingAccount(accountNumber, balance);
                    }else{
                        account = new SavingsAccount(accountNumber, balance, interestRate);
                    }
                    accounts.add(account);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts; // Return null if no matching account is found
    }
    

    /**
     * Deposits an amount into the specified account.
     *
     * @param accountNumber The account number to deposit into.
     * @param amount The amount to deposit.
     * @return A message indicating the result of the deposit.
     */
    public String deposit(Account account, double amount) {
        if (amount <= 0) {
            return "Deposit amount must be greater than 0.";
        }

        account.setBalance(account.getBalance() + amount);
        // Here you would update the CSV file to persist the new balance
        return "Deposit successful. New balance: " + account.getBalance();
    }

    /**
     * Withdraws an amount from the specified account.
     *
     * @param accountNumber The account number to withdraw from.
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
        // Here you would update the CSV file to persist the new balance
        return "Withdrawal successful. New balance: " + account.getBalance();
    }

    /**
     * Transfers an amount between two accounts.
     *
     * @param fromAccountNumber The account number to transfer from.
     * @param toAccountNumber The account number to transfer to.
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

        // Here you would update the CSV file to persist the new balances
        return "Transfer successful. New balance of source account: " + fromAccount.getBalance() + ", New balance of target account: " + toAccount.getBalance();
    }

    /**
     * Gets the balance of an account by account number.
     *
     * @param accountNumber The account number to get the balance for.
     * @return The current balance of the account.
     */
    public double getBalance(Account account) {
        if (account == null) {
            return -1; // Indicating account not found
        }
        return account.getBalance();
    }
}
