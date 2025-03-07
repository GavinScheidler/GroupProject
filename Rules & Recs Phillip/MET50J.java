/**
CODE EXAMPLE FOR MET50-J
@author Phillip Nguyen
*/
public class MET50J {
    public static void main(String[] args){
        MET50J example = new MET50J();
        example.printMessage("Hello");
        example.printIntegerMessage(50);
    }
    
    public void printMessage(String message){
        System.out.println("String: " + message);
    }

    public void printIntegerMessage(int number){
        System.out.println("Integer: " + number);
    }
}
