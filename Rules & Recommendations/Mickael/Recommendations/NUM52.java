

public class NUM52 {
    public static void main(String[] args) {
        int i = 5;
        double d = 3.2;
        
        // The int i is promoted to double for the operation
        double result = i + d; // Result is a double
        System.out.println("Result: " + result); // Correct output: Result: 8.2
    }
}
