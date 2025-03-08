package Banking.Model.Account;

/**
 * Represents a savings account. This class extends the abstract Account class
 * and adds functionality for managing the interest rate and applying interest
 * to the account balance. It also overrides the `accountType` method to specify 
 * that this is a savings account and provides a custom string representation 
 * for the account.
 * 
 * The class ensures that the interest rate cannot be negative and provides a method 
 * to apply the interest to the account balance.
 * 
 * @author Mickael Agustin
 * @version 1.0
 * @since 2025
 */
public class SavingsAccount extends Account {
    private double interestRate;

    /**
     * Constructs a SavingsAccount with the specified initial balance and interest rate.
     * The constructor ensures that the interest rate cannot be negative.
     * If the provided interest rate is negative, it is set to zero.
     * 
     * @param initialBalance the initial balance for the savings account
     * @param interestRate   the interest rate for the savings account
     */
    public SavingsAccount(double initialBalance, double interestRate) {
        super(initialBalance); // Calls the constructor of the Account class to set the balance and account number
        if (interestRate < 0) {
            interestRate = 0; // Interest rate cannot be negative, so it's set to 0 if the provided rate is negative
        }
        this.interestRate = interestRate; // Sets the interest rate for the account
    }

    public SavingsAccount(String accountNumber, double initialBalance, double interestRate) {
        super(accountNumber, initialBalance); // Calls the constructor of the Account class to set the balance and account number
        if (interestRate < 0) {
            interestRate = 0; // Interest rate cannot be negative, so it's set to 0 if the provided rate is negative
        }
        this.interestRate = interestRate; // Sets the interest rate for the account
    }

    /**
     * Returns the interest rate for the savings account.
     * 
     * @return the interest rate for the savings account
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Applies the interest to the current balance. The balance is updated
     * by adding the interest calculated based on the interest rate.
     * The interest is calculated as a percentage of the current balance.
     */
    public void applyInterest() {
        balance += balance * (interestRate / 100); // Adds interest to the balance
    }

     /**
     * Returns a int representation of the account type
     * This method overrides the abstract `accountType()` method from the Account class.
     * @return  int representation of the account type specific to Account (w)
     */
    @Override
    public int accountType() {
        return 2;
    }

    /**
     * Returns a string representation of the savings account, including its type,
     * account number, balance, and interest rate.
     * 
     * @return a string with the account details specific to SavingsAccount
     */
    public String toString() {
        return ("Account Type: Savings Account\nAccount Number: " + getAccountNumber() + "\nBalance: " + getBalance()
                + "\nInterest Rate: " + getInterestRate());
    }
}
