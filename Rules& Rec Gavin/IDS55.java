/* 
 * Example for IDS55
 * Author: Gavin Scheidler
*/

import java.util.regex.Pattern;

public class IDS55 {

    public class Splitter {
        // Interpreted as two chars, '\' and 'b'
        // Correctly splits on word boundaries
        private final String WORDS = "\\b";

        public String[] split(String input) {
            Pattern pattern = Pattern.compile(WORDS);
            String[] input_array = pattern.split(input);
            return input_array;
        }
    }

    public static void main(String[] args) {
        IDS55 id = new IDS55();
        Splitter dbPrep = id.new Splitter();
        System.out.println(dbPrep.split("example"));
    }

}
