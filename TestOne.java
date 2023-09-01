import java.io.BufferedReader;
import java.io.FileReader; // to read from file
import java.io.IOException; // Import this class to handle errors
import java.util.HashMap;
import java.util.Map;

public class TestOne{
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("trx.txt"))) {
            String line;

            // store data in hashmap 
            Map<String, Map<String, String>> cleandata = new HashMap<>();
            Map<String, String> threaddata ;

            for (int i=0; i < 10;i++){
                line = reader.readLine();
                String[] data = line.split(" "); //split one line by " "(space)
                threaddata = new HashMap<>();
                threaddata.put("startdate" + data[2], data[0] +" "+ data[1]);            
                threaddata.put("status" + data[2], data[4]);

                // Checking status
                if( data[4].equals("starting") ) {
                    threaddata.put("body" + data[2], data[5]+data[6]+data[7]);
                    cleandata.put(data[2], threaddata);
                    
                }else if ( data[4].equals("processing") ){
                    threaddata.put("status" + data[2], data[4]);
                    threaddata.put("processtime", data[0] + " " + data[1]);

                // continue
                }else if ( data[4].equals("end") ){
                    threaddata.put("endcode" + data[2], data[5]);
                    threaddata.put("endtime", data[0] + " " + data[1]);
                    // Del after add to file
                    threaddata.remove(data[2]);
                    }
                    // System.out.println(cleandata);
                    System.out.println(threaddata);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}