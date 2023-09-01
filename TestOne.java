import java.io.BufferedReader;
import java.io.FileReader; // to read from file
import java.io.IOException; // Import this class to handle errors

public class TestOne{
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("trx.txt"))) {
            String line;
            line = reader.readLine();
            System.out.println(line);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}