/**
CODE EXAMPLE FOR OBJ01-J
@author Phillip Nguyen
*/

public class OBJ01J {
    private String name;

    public static void main(String[] args){
        OBJ01J p = new OBJ01J("Alice");
        System.out.println("Person's name: " + p.getName());
    }
    
    public OBJ01J(String name){
        this.name = name;
    }

    /**
     * gets the Name
     * @return : String
     */
    public String getName(){
        return name;
    }
}
