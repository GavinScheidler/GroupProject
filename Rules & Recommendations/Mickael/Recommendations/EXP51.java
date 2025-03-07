public class EXP51 {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;

    
        if (a != b) {
            System.out.println("Now it's working as expected!");
        }   
        System.out.println("a: " + a);
        System.out.println("b: " + b);

        if (a = b) {
            System.out.println("This is an unintended behavior!");
        }

        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }
}
