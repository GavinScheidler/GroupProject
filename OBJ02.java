//Dane Iwema

class Account {
    // Maintains all banking-related data such as account balance
    private double balance;

    boolean withdraw(double amount) {
        if ((balance - amount) >= 0) {
            balance -= amount;
            System.out.println("Withdrawal successful. The balance is: " + balance);
            return true;
        }
        return false;
    }
}

// Secure wrapper class using composition
class SecureBankAccount {
    private final Account account;

    SecureBankAccount(Account account) {
        this.account = account;
    }

    private boolean securityCheck() {
        // Simulate authentication check
        System.out.println("Performing security check...");
        return true;
    }

    public boolean withdraw(double amount) {
        if (!securityCheck()) {
            throw new SecurityException("Unauthorized access attempt detected!");
        }
        return account.withdraw(amount);
    }
}

public class OBJ02 {
    public static void main(String[] args) {
        SecureBankAccount secureAccount = new SecureBankAccount(new Account());
        
        // Enforce security check before withdrawal
        boolean result = secureAccount.withdraw(200.0);
        
        System.out.println("Withdrawal successful? " + result);
    }
}
