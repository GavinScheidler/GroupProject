/* 
 * Example for IDS52
 * Author: Gavin Scheidler
*/

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class IDS52 {

    private static void evalScript(String firstName) throws ScriptException {
        // Allow only alphanumeric and underscore chars in firstName
        // (modify if firstName may also include special characters)
        if (!firstName.matches("[\\w]*")) {
            // String does not match whitelisted characters
            throw new IllegalArgumentException();
        }

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        engine.eval("print('" + firstName + "')");
    }

    public static void main(String[] args) {

        try {
            evalScript("<somescript>");
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
