/*
CODE EXAMPLE FOR MET06-J
Author: Phillip Nguyen
*/

public class MET06J implements Cloneable {
    private String data;

    public static void main(String[] args){
        MET06J original = new MET06J("Hello");
        MET06J cloned = original.clone();

        System.out.println("Original: " + original.getData());
        System.out.println("Cloned: " + cloned.getData());
    }

    public MET06J(String data){
        this.data = data;
    }

    @Override
    protected MET06J clone(){
        MET06J clonedObject = null;
        try{
            clonedObject = (MET06J) super.clone();
        } 
        catch (CloneNotSupportedException e){
            throw new AssertionError(); // Never happens
        }
        return clonedObject;
    }

    public String getData(){
        return data;
    }
}


