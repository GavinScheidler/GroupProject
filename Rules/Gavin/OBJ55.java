/* 
 * Example for OBJ55
 * Author: Gavin Scheidler
*/

import java.util.ArrayList;
import java.util.List;

public class OBJ55 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(Integer.valueOf(1));
        }
        while (!list.isEmpty()) {
            System.out.print(list.get(0) + " ");
            list.remove(0); // Remove object rather than skipping index once used.
        }
    }

}
