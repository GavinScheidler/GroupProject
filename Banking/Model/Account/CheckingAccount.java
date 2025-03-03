package Banking.Model.Account;

public class CheckingAccount extends Account {

    public CheckingAccount(double initialBalance) {
        super(initialBalance);
    }


    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("You are broke!");
            return false;
        }
    }

    @Override
    public void accountType() {
        System.out.println("This is a Checking Account.");
    }
}
