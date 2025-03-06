import java.io.IOException;
import java.io.RandomAccessFile;

public class FIO53 {
    public static void main(String[] args) {
        String fileName = "example.txt";
        
        try (RandomAccessFile file = new RandomAccessFile(fileName, "rw")) {
            // Writing to the file
            String data = "Secure data";
            file.writeUTF(data);

            // Reset file pointer to the beginning before reading
            file.seek(0);

            // Reading from the file
            String readData = file.readUTF();
            System.out.println("Read from file: " + readData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}