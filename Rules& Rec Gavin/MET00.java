/* 
 * Example for MET00
 * Author: Gavin Scheidler
*/
public class MET00 {

    public static void addNonZeroInt(int o1, int o2) {
        // Check to ensure arguments are valid before performing operation
        if (o1 > 0 && o2 > 0) {
            System.out.println(o1 + o2);
        } else {
            System.out.println("Not non zeros");
        }
    }

    public static void main(String[] args) {
        // Method adds any value above zero
        addNonZeroInt(0, 1);
        addNonZeroInt(-1, 1);
        addNonZeroInt(1, 1);

    }

}
