/*
 * CODE EXAMPLE FOR FIO08
 * Author: Mickael Agustin
 * Description: Demonstrates reading a file using FileReader and handling exceptions.
 * This example shows how to read each character of a file and print it to the console.
 * The code distinguishes between actual characters or bytes read from the file and the value `-1`, 
 * which indicates the end of the file (EOF). 
 * The method uses a try-with-resources statement to ensure that the file reader is closed automatically.
 */

package FIO08;

import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;

public class FIO08 {
    /**
     * The main method demonstrates file reading using FileReader.
     * It reads characters from a file and prints them to the console.
     * The method distinguishes between actual characters or bytes read from the
     * file and the EOF value `-1`.
     * If `-1` is returned, it indicates the end of the stream and the reading
     * stops.
     * The method uses a try-with-resources statement to ensure that the file reader
     * is closed automatically.
     */
    public static void main(String[] args) {
        // Using try-with-resources to automatically close the reader after use
        try (Reader reader = new FileReader("Rules/Mickael/Rules/FIO08/exampleFile.txt")) {
            int inbuff; // Variable to store the character data as an integer (ASCII value)
            char data; // Variable to store the character after casting from integer

            // Reading each character of the file until the end is reached (EOF)
            while ((inbuff = reader.read()) != -1) {
                // If inbuff is not -1, it means a valid character is read
                data = (char) inbuff; // Convert the integer value to a char
                System.out.print(data); // Print the character to the console
            }
            System.out.println();

        } catch (IOException e) {
            // Catch and print the exception if an error occurs during file reading
            e.printStackTrace();
        }
    }
}
