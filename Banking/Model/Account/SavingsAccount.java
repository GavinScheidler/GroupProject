package Banking.Model.Account;

public class SavingsAccount extends Account {
    private double interestRate;

    /**
     * OBJ11-J Do not let contructors throw execptions.
     *
     */
    public SavingsAccount(double initialBalance, double interestRate) {
        super(initialBalance);
        if (interestRate < 0) {
            interestRate = 0; // Interest rate can not be negative
        }
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void applyInterest() {
        balance += balance * (interestRate / 100);
    }

    @Override
    public void accountType() {
        System.out.println("This is a Savings Account.");
    }

    public String toString() {
        return ("Account Type: Savings Account\nAccount Number: " + getAccountNumber() + "\nBalance: " + getBalance()
                + "\nInterest Rate: "
                + getInterestRate());

    }
}
