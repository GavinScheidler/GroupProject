/* 
 * Example for IDS01
 * Author: Gavin Scheidler
*/

import java.util.regex.*;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class IDS01 {

    public static void check(String s) {
        Pattern pattern = Pattern.compile("[<>]");
        Matcåher matcher = pattern.matcher(s);

        if (matcher.find()) {
            throw new IllegalStateException();
        } else {
            System.out.println("Clean string");
        }
    }

    public static void main(String[] args) {
        String s = "\uFE64" + "content" + "\uFE65";
        // Before normalizing, script passes check
        check(s);

        s = Normalizer.normalize(s, Form.NFKC);
        // After normalizing, script fails the security check
        check(s);

    }
}
