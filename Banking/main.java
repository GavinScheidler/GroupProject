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

    public static void menu(User user) {

        double response = 0.0;
        while (response != 4.0) {

            response = i.in("1. View Accounts\n2. Create Account\n3.Modify Account 4. Exit\n");
            if (response == 1.0) {
                System.out.println("Accounts:");
                for (Account x : user.getAccounts()) {
                    System.out.println(x.toString());
                }

            } else if (response == 2.0) {
                // TODO

            } else if (response == 3.0) {
                // TODO

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

            if (e.getUser(uName, pass) == null)
                return true;
            else
                menu(e.getUser(uName, pass));
        } else if (response == 2.0) {

            String uName = i.in("Username:", 0);
            String pass = i.in("Password:", 0);

            if (e.setUser(uName, pass))
                System.out.println("Account Created.");
            else
                System.out.println("Error Creating Account.");
            return true;
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
