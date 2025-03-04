/*
CODE EXAMPLE FOR OBJ13-J
Author: Phillip Nguyen
*/

public class OBJ13J {
    private final int[] values = {1, 2, 3};

    public static void main(String[] args){
        OBJ13J obj = new OBJ13J();
        int[] values = obj.getValues();
        
        values[0] = 99;
        System.out.println("Original first value: " + obj.getValues()[0]);
    }

    public int[] getValues(){
        return values.clone(); // copy instead of the actual reference
    }
}
