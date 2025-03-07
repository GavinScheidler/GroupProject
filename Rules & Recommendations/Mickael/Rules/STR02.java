import java.text.Collator;
import java.util.Locale;

public class STR02 {
    public static void main(String[] args) {
        String str1 = "résumé";
        String str2 = "resume";

        // Using Collator with the French locale
        Collator collator = Collator.getInstance(Locale.FRENCH);
        collator.setStrength(Collator.PRIMARY); // Ignores case and diacritical differences

        if (collator.compare(str1, str2) == 0) {
            System.out.println("Strings are considered equal (locale-sensitive comparison)");
        } else {
            System.out.println("Strings are NOT equal (locale-sensitive comparison)");
        }
    }
}
