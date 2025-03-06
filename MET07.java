/*
MET07-J Example: Never declare a class method that hides a method declared in a superclass or super interface
Author: Dane Iwema
*/
class GrantAccess {
    public void displayAccountStatus() {
        System.out.println("Account details for admin: XX");
    }
}

class GrantUserAccess extends GrantAccess {
    @Override
    public void displayAccountStatus() {
        System.out.println("Account details for user: XX");
    }
}

public class MET07 {
    public static void choose(String username) {
        GrantAccess account;
        
        if (username.equals("admin")) {
            account = new GrantAccess();
        } else {
            account = new GrantUserAccess();
        }
        
        account.displayAccountStatus(); // Correct dynamic method dispatch
    }

    public static void main(String[] args) {
        choose("user"); // Expected output: "Account details for user: XX"
        choose("admin"); // Expected output: "Account details for admin: XX"
    }
}
