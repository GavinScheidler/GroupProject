import java.io.*;
import java.util.*;

/**
 * Superclass demonstrating secure object construction.
 */
class SecureBase {

    /**
     * OBJ11-J: Constructor handles exceptions internally.
     */
    public SecureBase() {
        try {
            initialize();
        } catch (IOException e) {
            // Handle exception safely
            System.err.println("Initialization failed: " + e.getMessage());
        }
    }

    /**
     * MET05-J: Non-overridable method called from constructor.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void initialize() throws IOException {
        // Safe initialization code here
        System.out.println("SecureBase initialized safely.");
    }
}

/**
 * Demonstrates secure coding practices in Java based on common rules.
 */
public class JavaSecureCodingExamples extends SecureBase {

    /**
     * FIO09-J: Writes a byte to a file, validating the range.
     *
     * @param data The integer to write.
     * @throws IOException If an I/O error occurs.
     */
    public void writeByte(int data) throws IOException {
        try (OutputStream os = new FileOutputStream("output.txt")) {
            if (data >= 0 && data <= 255) {
                os.write(data);
            } else {
                throw new IllegalArgumentException("Data out of byte range (0-255)");
            }
        }
    }

    /**
     * FIO14-J: Reads the contents of a file safely using try-with-resources.
     *
     * @param fileName The name of the file to read.
     */
    public void readFile(String fileName) {
        try (FileReader fr = new FileReader(fileName)) {
            // Read file contents here
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * EXP00-J: Demonstrates handling method return values properly.
     *
     * @param input The input string.
     */
    public void printUppercase(String input) {
        // Incorrect: Ignoring the return value
        input.toUpperCase();

        // Correct: Using the returned value
        String result = input.toUpperCase();
        System.out.println(result);
    }

    /**
     * EXP02-J: Compares two integer arrays for content equality.
     *
     * @param arr1 The first array.
     * @param arr2 The second array.
     * @return true if arrays have identical contents, false otherwise.
     */
    public boolean compareArrays(int[] arr1, int[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    /**
     * ERR08-J: Prints the length of a string if it's not null.
     *
     * @param s The input string.
     */
    public void printStringLength(String s) {
        if (s != null) {
            System.out.println("Length: " + s.length());
        } else {
            System.out.println("String is null");
        }
    }

    /**
     * OBJ51-J: Demonstrates proper encapsulation by limiting field accessibility.
     */
    private String password;

    /**
     * Sets the user's password.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the user's password (for demonstration only).
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * MET55-J: Returns a list of names, or an empty list if none are available.
     *
     * @return A non-null list of names.
     */
    public List<String> getNames() {
        return new ArrayList<>();
    }

    /**
     * ERR54-J: Reads the first line of a file safely.
     *
     * @param fileName The name of the file to read.
     */
    public void readFirstLine(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * FIO50-J: Attempts to create a new file and reports the result.
     *
     * @param fileName The name of the file to create.
     */
    public void createFile(String fileName) {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + fileName);
            } else {
                System.out.println("File already exists or failed to create");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to demonstrate the examples.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        JavaSecureCodingExamples example = new JavaSecureCodingExamples();

        // Example usages
        try {
            example.writeByte(100);
        } catch (IOException e) {
            e.printStackTrace();
        }

        example.printUppercase("hello");
        example.printStringLength("test");
        System.out.println(example.compareArrays(new int[]{1, 2}, new int[]{1, 2}));
        example.setPassword("securePassword");
        System.out.println("Password: " + example.getPassword());
        System.out.println("Names: " + example.getNames());
        example.createFile("newfile.txt");
    }
}
