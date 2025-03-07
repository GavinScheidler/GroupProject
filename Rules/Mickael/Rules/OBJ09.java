import java.lang.*;
class A {}
class B {}

public class OBJ09 {
    public static void main(String[] args) {
        A obj1 = new A();
        A obj2 = new A();
        B obj3 = new B();

        nameComparison(obj1, obj2);
        classComparison(obj1, obj3);
    }

    // Insecure comparison (comparing class names as Strings)
    private static void nameComparison(Object obj1, Object obj2) {
        if (obj1.getClass().getName().equals(obj2.getClass().getName())) {
            System.out.println("Classes are the same");
        } else {
            System.out.println("Classes are not the same.");
        }
    }

    // Secure comparison (comparing actual Class objects)
    private static void classComparison(Object obj1, Object obj2) {
        if (obj1.getClass() == obj2.getClass()) {
            System.out.println("Classes are the same");
        } else {
            System.out.println("Classes are not the same.");
        }
    }
}
