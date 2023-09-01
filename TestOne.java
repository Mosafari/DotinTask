import java.io.BufferedReader;
import java.io.FileReader; // to read from file
import java.io.IOException; // Import this class to handle errors

public class TestOne{
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("trx.txt"))) {
            String line;
            line = reader.readLine();
            String[] data = line.split(" "); //split one line by " "(space)
            
            for(String d: data){
                System.out.println(d); // print each element
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}