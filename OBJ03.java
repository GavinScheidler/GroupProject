//Dane Iwema

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class OBJ03 {
    // Generic method enforcing type safety
    private static <T> void addToList(List<T> list, T obj) {
        list.add(obj); // No unchecked warning
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        
        // Using Collections.checkedList() to prevent heap pollution
        List<String> checkedList = Collections.checkedList(list, String.class);
        
        // Adding a valid string
        addToList(checkedList, "SafeString");
        
        // Uncommenting the line below would cause a runtime exception, preventing unsafe additions
        // addToList(checkedList, 42); 
        
        System.out.println(checkedList.get(0)); // Output: SafeString
    }
}