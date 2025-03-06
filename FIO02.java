//Dane Iwema

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FIO02 {
    public static void deleteFileLegacy(String filePath) {
        File file = new File(filePath);
        if (!file.delete()) {
            System.err.println("Error: Failed to delete file: " + filePath);
        } else {
            System.out.println("File deleted successfully: " + filePath);
        }
    }

    public static void deleteFileNIO(String filePath) {
        Path path = new File(filePath).toPath();
        try {
            Files.delete(path);
            System.out.println("File deleted successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Error: Failed to delete file: " + filePath + " - " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java SecureFileDeletion <file_path>");
            return;
        }

        String filePath = args[0];

        // Legacy File API method (checks return value)
        deleteFileLegacy(filePath);

        // Modern NIO method (handles exceptions properly)
        deleteFileNIO(filePath);
    }
}
