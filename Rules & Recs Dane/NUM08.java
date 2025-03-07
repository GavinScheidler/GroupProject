/*
MET09-J Example: Check floating point inputs for exceptional values
Author: Dane Iwema
*/
import java.util.Scanner;

public class NUM08 {
    private double currentBalance = 0.0; // User's cash balance

    public void doDeposit(String userInput) {
        double val = 0;
        try {
            val = Double.valueOf(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Not a number.");
            return;
        }

        if (Double.isInfinite(val)) {
            System.out.println("Error: Value cannot be infinite.");
            return;
        }

        if (Double.isNaN(val)) {
            System.out.println("Error: Value cannot be NaN.");
            return;
        }

        if (val < 0) {
            System.out.println("Error: Cannot deposit a negative amount.");
            return;
        }

        if (val >= Double.MAX_VALUE - currentBalance) {
            System.out.println("Error: Deposit too large.");
            return;
        }

        currentBalance += val;
        System.out.println("Deposit successful. New balance: " + currentBalance);
    }

    public static void main(String[] args) {
        NUM08 account = new NUM08();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter deposit amount: ");
        String userInput = scanner.nextLine();
        account.doDeposit(userInput);

        scanner.close();
    }
}