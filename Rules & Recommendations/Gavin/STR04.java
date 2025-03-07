/* 
 * Example for STR04
 * Author: Gavin Scheidler
*/

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class STR04 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("SomeFile");
            DataInputStream dis = new DataInputStream(fis);
            byte[] data = new byte[1024];
            dis.readFully(data);
            String result = new String(data, "UTF-16LE");
        } catch (IOException x) {
            System.out.println("File not found");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException x) {
                    // Forward to handler
                }
            }
        }
    }
}
