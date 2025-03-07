/**
CODE EXAMPLE FOR OBJ05-J
@author Phillip Nguyen
*/

import java.util.ArrayList;
import java.util.List;

public class OBJ05J {
    private final List<Integer> numbers = new ArrayList<>();

    public static void main(String[] args){
        OBJ05J obj = new OBJ05J();
        List<Integer> list = obj.getNumbers();

        list.add(50); // Modifies the copy instead
        System.out.println("Modified copy: " + list); 
        System.out.println("Original private list: " + obj.getNumbers()); 
    }

    /**
     * returns a copy of the list
     * @return : List<Integer>
     */
    public List<Integer> getNumbers(){
        return new ArrayList<>(numbers); //copy of the list
    }
}
