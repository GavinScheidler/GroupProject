/*
 * CODE EXAMPLE FOR FIO14
 * Author: Mickael Agustin
 * Description: Demonstrates the use of a shutdown hook to close a BufferedReader.
 * This example shows how to read from a file using BufferedReader and ensures the reader is closed
 * properly when the program exits, either normally or due to an interruption (e.g., Ctrl+C).
 * A shutdown hook is added to handle the cleanup of resources.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class demonstrates the use of a shutdown hook to ensure that a
 * BufferedReader
 * is properly closed when the program terminates, either normally or due to an
 * interruption.
 */
public class FIO14 {
    private static BufferedReader reader;
    
    /**
     * The main method demonstrates reading a file using BufferedReader and ensuring that it is
     * properly closed using a shutdown hook. The program waits for one minute or until
     * interrupted (e.g., Ctrl+C), at which point the shutdown hook executes.
     */
    public static void main(String[] var0) {
        System.out.println("Opening reader...");

        try {
            reader = new BufferedReader(new FileReader("exampleFile.txt"));
            System.out.println("Reader opened...");
            // Adding a shutdown hook to clean up and close the reader when the program is
            // interrupted
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    // Read the first line and close the reader
                    System.out.println("File content: " + reader.readLine());
                    System.out.println("Closing reader...");
                    reader.close();
                    System.out.println("Reader was closed.");
                } catch (IOException var1) {
                    System.out.println("Error closing reader: " + var1.getMessage());
                }

            }));
            System.out.println("Press Ctrl+C to trigger shutdown hook.");
            Thread.sleep(60000L); // Wait for 1 minute before closing automatically
        } catch (FileNotFoundException var2) {
            System.out.println("File was not found.");
        } catch (InterruptedException | IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }
}
