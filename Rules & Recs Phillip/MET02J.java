/** 
CODE EXAMPLE FOR MET02-J
@author Phillip Nguyen
*/
import java.time.LocalDate; //instead of java.util.Date (deprecated)

public class MET02J{
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2025, 2, 29);
        System.out.println("Safe Date: " + date);
    }
}
