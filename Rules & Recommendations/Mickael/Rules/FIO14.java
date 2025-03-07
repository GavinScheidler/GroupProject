import java.io.*;

public class FIO14 {
    private static BufferedReader reader;

    public static void main(String[] args) {
        System.out.println("Opening reader...");
        try {
            reader = new BufferedReader(new FileReader("exampleFile.txt"));
            System.out.println("Reader opened...");

            // Add shutdown hook to properly close the reader
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("File content: " + reader.readLine());
                    System.out.println("Closing reader...");
                    reader.close();
                    System.out.println("Reader was closed.");
                } catch (IOException e) {
                    System.out.println("Error closing reader: " + e.getMessage());
                }
            }));

            // Keep program running to test shutdown hook
            System.out.println("Press Ctrl+C to trigger shutdown hook.");
            Thread.sleep(60000); // Simulate a running program

        } catch (FileNotFoundException x) {
            System.out.println("File was not found.");
        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
