/*
MET56-J Example:Detect and handle file-related errors
Author: Dane Iwema
*/
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.util.Arrays;

public class MET56 {
    public static boolean keysEqual(Key key1, Key key2) {
        if (key1.equals(key2)) {
            return true;
        }
        if (Arrays.equals(key1.getEncoded(), key2.getEncoded())) {
            return true;
        }
        if (key1 instanceof RSAPrivateKey && key2 instanceof RSAPrivateKey) {
            RSAPrivateKey rsaKey1 = (RSAPrivateKey) key1;
            RSAPrivateKey rsaKey2 = (RSAPrivateKey) key2;
            return rsaKey1.getModulus().equals(rsaKey2.getModulus()) &&
                   rsaKey1.getPrivateExponent().equals(rsaKey2.getPrivateExponent());
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair1 = keyGen.generateKeyPair();
        KeyPair pair2 = keyGen.generateKeyPair();

        System.out.println("Same Key Comparison: " + keysEqual(pair1.getPrivate(), pair1.getPrivate())); // true
        System.out.println("Different Key Comparison: " + keysEqual(pair1.getPrivate(), pair2.getPrivate())); // false
    }
}