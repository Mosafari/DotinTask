import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader; // to read from file
import java.io.FileWriter;
import java.io.IOException; // Import this class to handle errors
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestOne{
    public static void main(String[] args) throws ParseException{
        try (BufferedReader reader = new BufferedReader(new FileReader("trx.txt"))) {
            String line;

            // store data in hashmap 
            Map<String, Map<String, String>> cleandata = new HashMap<>();
            Map<String, String> threaddata ;

            while ((line = reader.readLine()) != null) {
                
                String[] data = line.split(" "); //split one line by " "(space)
                threaddata = new HashMap<>();
                
                // Checking status
                if( data[4].equals("starting") ) {
                    threaddata.put("startdate" + data[2], data[0] +"T"+ data[1]);            
                    threaddata.put("status" + data[2], data[4]);
                    threaddata.put("body" + data[2], data[5]+data[6]+data[7]);
                    cleandata.put(data[2], threaddata);
                    
                }else if ( data[4].equals("processing") ){
                    threaddata = cleandata.get(data[2]);
                    threaddata.put("status" + data[2], data[4]);
                    threaddata.put("processtime" + data[2], data[0] + "T" + data[1]);

                // continue
                }else if ( data[4].equals("end") ){
                    threaddata = cleandata.get(data[2]);
                    threaddata.put("status" + data[2], data[4]);
                    threaddata.put("endcode" + data[2], data[5].split("\"")[3]); // split to scrab end code
                    threaddata.put("endtime" + data[2], data[0] + "T" + data[1]);
                    // store data to txt file
                    // If the file doesn't exist, create it
                    File result = new File("result1.txt");
                    // System.out.println(threaddata.get("body" + data[2]).split("\"")[11]);
                    if (!result.exists()) {
                        result.createNewFile();
                    }

                    // Open the file in append mode
                    FileWriter fw = new FileWriter(result, true);
                    BufferedWriter bw = new BufferedWriter(fw);

                    // Write the text to the file
                    String[] bodyitems = threaddata.get("body" + data[2]).split("\"");
                    // Define the date-time format
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss,SSS");
                    // Parse the strings into LocalDateTime objects
                    Date  startDateTime = sdf.parse(threaddata.get("startdate" + data[2]));
                    Date  processDateTime = sdf.parse(threaddata.get("processtime" + data[2]));
                    Date  endDateTime = sdf.parse(threaddata.get("endtime" + data[2]));

                    long tstep1 = processDateTime.getTime() - startDateTime.getTime();
                    long tstep2 = endDateTime.getTime() - processDateTime.getTime();

                    bw.write(bodyitems[11]+','+bodyitems[7]+','+threaddata.get("endcode" + data[2]) + ',' + tstep1 + ',' + tstep2 );
                    bw.newLine(); // Add a new line after the text

                    // Close the BufferedWriter
                    bw.close();
                    // Del after add to file
                    // threaddata.remove(data[2]);
                }
                // System.out.println(threaddata);
                // System.out.println(cleandata);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}