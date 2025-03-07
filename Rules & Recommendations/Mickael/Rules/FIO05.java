import java.nio.CharBuffer;

final public class FIO05 {
    private final char[] dataArray;

    public FIO05() {
        dataArray = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
    }

    public CharBuffer getBufferCopy() {
        CharBuffer cb = CharBuffer.allocate(dataArray.length);
        cb.put(dataArray);
        cb.flip(); 
        return cb;
    }

    public static void main(String[] args) {
        FIO05 wrap = new FIO05();
        CharBuffer buffer = wrap.getBufferCopy();

        System.out.println("Original Data at the beginning: ");
        for(int i = 0; i < wrap.dataArray.length; i++){
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

        System.out.println("Original Data at the end: ");
        for(int i = 0; i < wrap.dataArray.length; i++){
            System.out.print(wrap.dataArray[i] + " ");
        }

        System.out.println(); // New line
        System.out.println(); // New line
    }
}
