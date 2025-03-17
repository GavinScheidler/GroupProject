/*
 * CODE EXAMPLE FOR STR02
 * Author: Mickael Agustin
 * Description: Demonstrates locale-sensitive string comparison using `Collator`.
 * This example compares two strings ("résumé" and "resume") using the French locale and `Collator`.
 * The comparison ignores case and diacritical (accent) differences by setting the strength to `PRIMARY`.
 * The result is based on the locale-specific rules for string comparison.
 */

 import java.text.Collator;
 import java.util.Locale;
 
 /**
  * Demonstrates locale-sensitive string comparison using {@code Collator}.
  * This example compares two strings ("résumé" and "resume") in the French locale.
  * It ignores case and diacritical differences by setting the strength to {@code PRIMARY}.
  */
 public class STR02 {
    /**
     * The main method demonstrating locale-sensitive string comparison.
     */
     public static void main(String[] args) {
         String str1 = "résumé";
         String str2 = "resume";
 
         // Using Collator with the French locale
         Collator collator = Collator.getInstance(Locale.FRENCH);
         collator.setStrength(Collator.PRIMARY); // Ignores case and diacritical differences
 
         // Compare the two strings according to the French locale rules
         if (collator.compare(str1, str2) == 0) {
             System.out.println("Strings are considered equal (locale-sensitive comparison)");
         } else {
             System.out.println("Strings are NOT equal (locale-sensitive comparison)");
         }
     }
 }
 