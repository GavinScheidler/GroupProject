/*
 * CODE EXAMPLE FOR FIO05
 * Author: Mickael Agustin
 * Description: Demonstrates the use of CharBuffer for copying, modifying, and reading character data.
 * This example shows how to copy data from an array to a CharBuffer, modify the buffer contents,
 * and observe how the original data array remains unaffected by changes made to the buffer.
 */

import java.nio.CharBuffer;

final public class FIO05 {
    /**
     * This class demonstrates the use of a CharBuffer for copying, modifying, and
     * reading character data.
     * It highlights how data from an array can be copied into a CharBuffer,
     * modified within the buffer,
     * and how changes in the buffer do not affect the original data array.
     */
    private final char[] dataArray;

    /**
     * Constructs an instance of the FIO05 class and initializes the data array with sample characters.
     */
    public FIO05() {
        dataArray = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' };
    }

    /**
     * Creates a copy of the data array in a CharBuffer and prepares it for reading.
     * 
     * @return A CharBuffer containing a copy of the data array.
     */
    public CharBuffer getBufferCopy() {
        CharBuffer cb = CharBuffer.allocate(dataArray.length);
        cb.put(dataArray);
        cb.flip();
        return cb;
    }

     /**
     * The main method demonstrates how to use CharBuffer to copy, modify, and read character data.
     * It shows that modifying the CharBuffer does not affect the original data array.
     */
    public static void main(String[] args) {
        FIO05 wrap = new FIO05();
        CharBuffer buffer = wrap.getBufferCopy();

        System.out.println("Original Data at the beginning: ");
        for (int i = 0; i < wrap.dataArray.length; i++) {
            System.out.print(wrap.dataArray[i] + " ");
        }

        System.out.println(); // New line
        System.out.println(); // New line

        // First read
        System.out.println("Buffer content after copy: ");
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get() + " ");
        }

        System.out.println(); // New line
        System.out.println(); // New line
        System.out.println("Inserting 'x'..."); // New line
        System.out.println(); // New line
        // Modify buffer at index 3
        buffer.put(3, 'x'); // Safe because it's within bounds

        // Reset position before reading again
        buffer.rewind();

        System.out.println("Buffer content after alteration: ");
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get() + " ");
        }

        System.out.println(); // New line
        System.out.println(); // New line

        // Displaying the original data array again after buffer modification
        System.out.println("Original Data at the end: ");
        for (int i = 0; i < wrap.dataArray.length; i++) {
            System.out.print(wrap.dataArray[i] + " ");
        }

        System.out.println(); // New line
        System.out.println(); // New line
    }
}
