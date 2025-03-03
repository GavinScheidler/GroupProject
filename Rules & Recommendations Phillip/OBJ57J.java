/*
CODE EXAMPLE FOR OBJ57-J
Author: Phillip Nguyen
*/
public class OBJ57J {
    public static void main(String[] args){
        OBJ57J obj = new OBJ57J();
        obj.log();
    }
    private final void log(){ //make it final to prevent method overriding
        System.out.println("Logging");
    }
}
