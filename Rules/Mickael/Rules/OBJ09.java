/*
 * CODE EXAMPLE FOR OBJ09
 * Author: Mickael Agustin
 * Description: Demonstrates the comparison of classes using two different methods:
 * 1. Insecure comparison using class names as Strings.
 * 2. Secure comparison using Class objects.
 * This example shows how comparing class names directly as Strings can be insecure due to potential 
 * mismatches in class loader contexts, while comparing Class objects ensures a more accurate and secure comparison.
 * 
 * The main method compares two objects of class 'A' and one object from class 'B' using both comparison methods.
 */

 // Class A
 class A {} 
 
 // Class B
 class B {} 
 
 public class OBJ09 {
     
     /**
      * The main method demonstrates how to compare objects using two different methods:
      * 1. Insecure comparison based on class names as Strings.
      * 2. Secure comparison based on Class objects.
      * 
      * This method compares two objects of class 'A' and one object of class 'B' using both methods.
      */
     public static void main(String[] args) {
         
         // Create two objects of class A
         A obj1 = new A();
         A obj2 = new A();
         
         // Create an object of class B
         B obj3 = new B();
 
         // Print out the comparison between two objects of class 'A'
         System.out.println("Comparing obj1 and obj2 which are both of class 'A'");
 
         // Comparing using their class names (insecure method)
         System.out.println("Using their class name:");
         nameComparison(obj1, obj2); // Call nameComparison method
         
         System.out.println(); // Print an empty line for separation
 
         // Comparing using their class objects (secure method)
         System.out.println("Using their class object:");
         classComparison(obj1, obj2); // Call classComparison method
 
         System.out.println(); // Print an empty line for separation
 
         // Print out the comparison between two objects, one from class 'A' and the other from class 'B'
         System.out.println("Comparing obj1 of class 'A' and obj3 of class 'B'");
 
         // Comparing using their class names (insecure method)
         System.out.println("Using their class name:");
         nameComparison(obj1, obj3); // Call nameComparison method
         
         System.out.println(); // Print an empty line for separation
 
         // Comparing using their class objects (secure method)
         System.out.println("Using their class object:");
         classComparison(obj1, obj3); // Call classComparison method
     }
 
     /**
      * Insecure comparison method that compares class names as Strings.
      * This method can lead to potential issues in class loading contexts, 
      * especially when classes with the same name are loaded by different class loaders.
      * 
      * @param obj1 The first object to compare.
      * @param obj2 The second object to compare.
      */
     private static void nameComparison(Object obj1, Object obj2) {
         // Compare the fully qualified class name of both objects
         if (obj1.getClass().getName().equals(obj2.getClass().getName())) {
             System.out.println("Classes are the same");
         } else {
             System.out.println("Classes are not the same.");
         }
     }
 
     /**
      * Secure comparison method that compares actual Class objects.
      * This method ensures an accurate comparison of the objects' types, 
      * avoiding potential issues with class loading contexts.
      * 
      * @param obj1 The first object to compare.
      * @param obj2 The second object to compare.
      */
     private static void classComparison(Object obj1, Object obj2) {
         // Compare the Class objects directly
         if (obj1.getClass() == obj2.getClass()) {
             System.out.println("Classes are the same");
         } else {
             System.out.println("Classes are not the same.");
         }
     }
 }
