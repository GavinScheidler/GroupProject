package FIO08;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;

public class FIO08 {
    public static void main(String[] args) {
        try(Reader reader = new FileReader("exampleFile.txt")){
            int inbuff;
            char data;

            while((inbuff = reader.read()) != -1){
                data = (char) inbuff;
                System.out.print(data);
            }
            System.out.println();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
