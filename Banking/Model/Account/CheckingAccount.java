package Banking.Model.Account;

/**
 * Represents a checking account. This class extends the abstract Account class
 * and provides methods to withdraw funds, check the account type, and display 
 * account details specific to a checking account.
 * 
 * It allows for withdrawal of funds and ensures the account balance is sufficient 
 * to perform the transaction. It also overrides the `accountType` method to specify 
 * that this is a checking account and provides a custom string representation for 
 * the account.
 * 
 * @author Mickael Agustin
 * @version 1.0
 * @since 2025
 */
public class CheckingAccount extends Account {

    /**
     * Constructs a CheckingAccount with the specified initial balance. 
     * Calls the constructor of the superclass (Account) to initialize the account number 
     * and set the initial balance.
     * 
     * @param initialBalance the initial balance for the checking account
     */
    public CheckingAccount(double initialBalance) {
        super(initialBalance); // Calls the constructor of the Account class to set the balance and account number
    }

    /**
     * Withdraws a specified amount from the checking account. 
     * If the balance is sufficient, the amount is subtracted from the balance.
     * If there are insufficient funds, the withdrawal fails.
     * 
     * @param amount the amount to withdraw from the account
     * @return true if the withdrawal was successful, false otherwise
     */
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount; // Subtracts the amount from the balance
            return true; // Successful withdrawal
        } else {
            System.out.println("You are broke!"); // Error message when balance is insufficient
            return false; // Withdrawal failed due to insufficient funds
        }
    }

    /**
     * Returns a int representation of the account type
     * This method overrides the abstract `accountType()` method from the Account class.
     * @return  int representation of the account type specific to CheckingAccount (1)
     */
    @Override
    public int accountType() {
        return 1;
    }

    /**
     * Returns a string representation of the checking account, including its type,
     * account number, and current balance.
     * 
     * @return a string with the account details specific to CheckingAccount
     */
    public String toString() {
        return ("Account Type: Checking\nAccount Number: " + getAccountNumber() + "\nBalance: " + getBalance());
    }

}
