package Banking.Service;

import Banking.Model.Account.Account;

public class TransactionHandler {
    private static TransactionHandler instance; // Singleton instance

    // Public method to get the single instance
    public static TransactionHandler getInstance() {
        if (instance == null) {
            instance = new TransactionHandler();
        }
        return instance;
    }

    public void deposit(Account account, double amount) {
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(Account account, double amount) {
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
            return false;
        }
    }

    public void displayBalance(Account account) {
        System.out.println("Account " + account.getAccountNumber() + " Balance: $" + account.getBalance());
    }

    public boolean transfer(Account fromAccount, Account toAccount, double amount) {
        if (fromAccount == null || toAccount == null) {
            System.out.println("Invalid account provided.");
            return false;
        }

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
