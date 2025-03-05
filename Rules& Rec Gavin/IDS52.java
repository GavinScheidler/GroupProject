/* 
 * Example for IDS52
 * Author: Gavin Scheidler
*/

import javax.script.ScriptException;

public class IDS52 {

    private static void evalScript(String input) throws ScriptException {
        // Allow only alphanumeric and underscore chars in firstName
        // (modify if firstName may also include special characters)
        if (!input.matches("[\\w]*")) {
            // String does not match whitelisted characters
            throw new IllegalArgumentException();
        }
        System.out.println("No script Detected");
    }

    public static void main(String[] args) {

        try {
            evalScript("hello");
            evalScript("<somescript>");
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }

}
