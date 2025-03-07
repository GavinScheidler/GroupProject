/**
CODE EXAMPLE FOR OBJ04-J
@author Phillip Nguyen
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

   /**
    * copy method that returns a copy of Data
    * @return : Data
    */
    public Data copy(){ 
        return new Data(this.value);
    }

    /**
     * sets the value
     */
    public void setValue(int value){
        this.value = value;
    }

    /**
     * gets the value
     * @return : int
     */
    public int getValue(){
        return value;
    }
}

