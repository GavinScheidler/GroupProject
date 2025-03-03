package Banking.Model.Account;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(double initialBalance, double interestRate) {
        super(initialBalance);
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
}