/* 
 * Example for STR03
 * Author: Gavin Scheidler
*/

import java.math.BigInteger;

public class STR03 {
    public static void bad() {
        BigInteger x = new BigInteger("530500452766");
        System.out.print("Original Value: ");
        System.out.println(x);
        byte[] byteArray = x.toByteArray();
        String s = new String(byteArray);
        byteArray = s.getBytes();
        x = new BigInteger(byteArray);
        System.out.print("Final Value: ");
        System.out.println(x); // Corrupted
    }

    public static void good() {
        BigInteger x = new BigInteger("530500452766");
        System.out.print("Original Value: ");
        System.out.println(x);
        String s = x.toString(); // Valid character data
        byte[] byteArray = s.getBytes();
        String ns = new String(byteArray);
        x = new BigInteger(ns);
        System.out.print("Final Value: ");
        System.out.println(x); // Not corrupted.
    }

    public static void main(String[] args) {
        // Bad form
        bad();
        // Good form
        good();

    }
}
