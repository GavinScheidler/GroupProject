/* 
 * Example for MET03
 * Author: Gavin Scheidler
*/
public class MET03 {

    public static final void readSensitiveFile() {
        try {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) { // Check for permission to read file
                sm.checkRead("/temp/tempFile");
            }
            System.out.println("File accessed");
        } catch (SecurityException se) {
            System.out.println("File not allowed");
        }
    }

    public static void main(String[] args) {
        readSensitiveFile(); // Security cannot be bypassed by creating a subclass, since its declared final.
    }

}
