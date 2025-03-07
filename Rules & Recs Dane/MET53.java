/*
MET53-J Example:Detect and handle file-related errors
Author: Dane Iwema
*/
public class MET53 {
    public static void main(String[] args) {
        Derived dev = new Derived();
        try {
            Derived devClone = (Derived) dev.clone(); // Correctly clones as Derived
            devClone.doLogic(); // Prints "Subclass doLogic" as expected
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

class Base implements Cloneable {
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Ensures correct cloning behavior
    }
    
    protected void doLogic() {
        System.out.println("Superclass doLogic");
    }
}

class Derived extends Base {
    public Object clone() throws CloneNotSupportedException {
        return (Derived) super.clone(); // Ensures clone is of type Derived
    }

    protected void doLogic() {
        System.out.println("Subclass doLogic");
    }
}