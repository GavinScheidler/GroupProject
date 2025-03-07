package Banking.Model.Account;

import java.util.UUID;

public abstract class Account {
    protected String accountNumber;
    protected double balance;

    public Account(double initialBalance) {
        this.accountNumber = UUID.randomUUID().toString(); // Generates a unique account number
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toString() {
        return ("Account Number: " + getAccountNumber() + "\nBalance: " + getBalance());
    }

    public abstract void accountType(); // Abstract method to be implemented by subclasses
}
