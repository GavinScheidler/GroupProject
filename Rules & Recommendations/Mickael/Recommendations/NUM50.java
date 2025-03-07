

public class NUM50 {
    public static void main(String[] args) {
        int a = 5;
        int b = 2;
        
        double resultNoCovert = a / b;
        double resultConvert = (double) a / b; // Correct result

        System.out.println("Result without converstion first: " + resultNoCovert);
        System.out.println("Result with converstion first: " + resultConvert); // Output will be 2.5

    }
}
