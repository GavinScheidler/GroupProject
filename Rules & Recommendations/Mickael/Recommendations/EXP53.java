public class EXP53 {
    public static void main(String[] args) {
        int a = 1, b = 3, c = 4;
        
        // Calculating results with and without parentheses
        int withoutParentheses = a + b * c;  // Multiplication happens first
        int withParentheses = (a + b) * c;  // Addition happens first

        // Printing the results
        System.out.println("Without parentheses: " + withoutParentheses);
        System.out.println("With parentheses: " + withParentheses);
    }
}
