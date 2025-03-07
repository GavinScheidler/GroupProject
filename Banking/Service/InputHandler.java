package Banking.Service;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputHandler {

    Scanner scan = new Scanner(System.in);

    /*
     * MET03 - Security Check Declared Final
     */
    public final String in() {
        boolean cont = true;
        String temp = "";
        while (cont) {
            temp = this.scan.nextLine();
            temp = temp.strip();
            /*
             * IDS01 - Normalizes before check
             */
            temp = Normalizer.normalize(temp, Form.NFKC);
            /*
             * IDS03 - Checks for scripting
             */
            if (!Pattern.matches("[A-Za-z0-9_\\.\\-]+", temp))
                System.out.print("Invalid, try again: ");
            else
                cont = false;
        }
        return temp;
    }

    public double in(String prompt) {
        System.out.print(prompt);
        String temp = in();
        boolean cont = true;
        Double d = 0.0;
        while (cont)
            try {
                d = Double.parseDouble(temp);
                cont = false;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input.");
                System.out.print(prompt);
                temp = in();
            }
        return (double) d;

    }

    public String in(String prompt, int seperator) {

        System.out.print(prompt);
        return in();

    }

}
