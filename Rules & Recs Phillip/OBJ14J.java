/**
CODE EXAMPLE FOR OBJ14-J
@author Phillip Nguyen
info page is a stub but it's similar to an issue in C++ so I included it maybe I shouldn't have since it contradicts OBJ54-J
*/

public class OBJ14J {
    public static void main(String[] args){
        Object obj = new Object();
        obj.process(); 
    }
}

public class Object {
    public void process(){
        String data = "Hello";
        System.out.println(data); 
        data = null; // set to null (is this not contradictory to OBJ54-J??)
    }
}



