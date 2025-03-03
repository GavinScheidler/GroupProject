package Banking.Model;

import java.util.ArrayList;
import java.util.List;

import Banking.Model.Account.Account;
import Banking.Model.Account.SavingsAccount;
import Banking.Model.Account.CheckingAccount;

public class User {
    private String name;
    private String username;
    private String password;
    private List<Account> accounts;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public void addSavingAccount(double initialBalance, double interestRate) {
        accounts.add(new SavingsAccount(initialBalance, interestRate));
    }

    public void addCheckingAccount(double initialBalance) {
        accounts.add(new CheckingAccount(initialBalance));
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword); 
    }
}

