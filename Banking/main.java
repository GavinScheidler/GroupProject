package Banking;

import Banking.Service.TransactionHandler;
import Banking.Service.UserHandler;
import Banking.Service.InputHandler;
import Banking.Database.Database;
import Banking.Model.User;
import Banking.Model.Account.Account;

public class main {

    static InputHandler i = new InputHandler();
    static Database e = new Database();
    static UserHandler uh = new UserHandler();
    static TransactionHandler t = new TransactionHandler();

    public static void menu(User user) {
        System.out.println("\nLogin successful! Welcome, " + user.getName());
        double response = 0.0;
        while (response != 4.0) {

            response = i.in("1. View Accounts\n2. Create Account\n3. Modify Account\n4. Exit\n");
            if (response == 1.0) {
                System.out.println("Accounts:\n");
                for (Account x : user.getAccounts()) {
                    System.out.println(x.toString());
                    System.out.println();
                }

            } else if (response == 2.0) {
                response = i.in("1. Savings\n2. Checking?\n");
                if (response == 1.0) {
                    double iB = i.in("What's the initial balance?: ");
                    double iR = i.in("Whats the interest rate?: ");
                    user.addSavingAccount(iB, iR);
                } else if (response == 2.0) {
                    double iB = i.in("What's the initial balance?: ");
                    user.addCheckingAccount(iB);
                } else {
                    System.out.println("Invalid Input.");
                    response = 0.0;
                }

            } else if (response == 3.0) {
                String temp = i.in("Which account: ", 0);
                if (temp != null && user.getAccount(temp) != null) {
                    Account ac = user.getAccount(temp);
                    while (response != 5.0) {
                        System.out.println();
                        response = i.in("1. Withdraw\n2. Deposit\n3. Display Balance\n4. Transfer\n5. Exit\n");
                        if (response == 1.0) {
                            t.withdraw(ac, i.in("How much would you like to withdraw?: "));
                        } else if (response == 2.0) {
                            t.deposit(ac, i.in("How much would you like to deposit?: "));
                        } else if (response == 3.0) {
                            t.displayBalance(ac);
                        } else if (response == 4.0) {
                            t.transfer(ac, user.getAccount(i.in("Transfer to what account?: ", 0)),
                                    i.in("How much?: "));
                        }
                    }
                    response = 0.0;
                }

            } else {
                response = i.in("Logout Attempted, Press 4 again to confirm: ");
            }

        }

    }

    public static boolean loginMenu() {

        double response = i.in("1. Login\n2. Create User\n3. Exit\n");
        if (response == 1.0) {
            String uName = i.in("Username:", 0);
            String pass = i.in("Password:", 0);
            if (uh.login(uName, pass) != null)
                menu(uh.login(uName, pass));
            return true;
            /*
             * Database interaction version.
             * 
             * if (e.getUser(uName, pass) == null)
             * return true;
             * else
             * menu(e.getUser(uName, pass));
             */
        } else if (response == 2.0) {

            String uName = i.in("Username:", 0);
            String pass = i.in("Password:", 0);
            String name = i.in("Name:", 0);

            uh.registerUser(name, uName, pass);
            return true;
            // Database interaction version
            // if (e.setUser(uName, pass))
            // System.out.println("Account Created.");
            // else
            // System.out.println("Error Creating Account.");
            // return true;
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println("Welcome to DMGPD Finacial!\n");

        while (loginMenu()) {

        }

        System.out.println("\nGoodbye!\n");
    }
}
