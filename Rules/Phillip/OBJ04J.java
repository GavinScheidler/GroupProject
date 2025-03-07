/*
CODE EXAMPLE FOR OBJ04-J
Author: Phillip Nguyen
*/

public class OBJ04J {
    public static void main(String[] args){
        Data original = new Data(10);
        Data copy = original.copy(); 

        copy.setValue(20); 

        System.out.println("Original: " + original.getValue()); 
        System.out.println("Copy: " + copy.getValue()); 
    }
}

class Data {
    private int value;

    public Data(int value){
        this.value = value;
    }

    //copy method
    public Data copy(){ 
        return new Data(this.value);
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}

