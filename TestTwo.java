import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader; // to read from file
import java.io.FileWriter;
import java.io.IOException;

public class TestTwo {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("result1.txt"))) {
            String line;
            // balance bill charge
            int ba =0;
            long start_process_ba = 0;
            long end_process_ba = 0;
            int bi = 0;
            long start_process_bi = 0;
            long end_process_bi = 0;
            int ch = 0; 
            long start_process_ch = 0;
            long end_process_ch = 0;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); //split one line by ","
                // System.out.println(data[1]);
                if (data[1].equals("balance")){
                    start_process_ba += Integer.parseInt(data[3]);
                    end_process_ba +=  Integer.parseInt(data[4]);
                    ba+=1;
                }
                if (data[1].equals("bill")){
                    start_process_bi += Integer.parseInt(data[3]);
                    end_process_bi += Integer.parseInt(data[4]);
                    bi+=1;
                }
                if (data[1].equals("charge")){
                    start_process_ch += Integer.parseInt(data[3]);
                    end_process_ch += Integer.parseInt(data[4]);
                    ch+=1;
                }

            }
            // If the file doesn't exist, create it
                File result = new File("result2.txt");
                // System.out.println(threaddata.get("body" + data[2]).split("\"")[11]);
                if (!result.exists()) {
                    result.createNewFile();
                }

                // Open the file in append mode
                FileWriter fw = new FileWriter(result, true);
                BufferedWriter bw = new BufferedWriter(fw);

                // add avarage for each step (total/count)
                //  write to result2
                bw.write("balance"+","+start_process_ba/ba +','+end_process_ba/ba);
                bw.newLine(); // Add a new line after the text
                bw.write("bill"+","+start_process_bi/bi +','+end_process_bi/bi);
                bw.newLine(); 
                bw.write("charge"+","+start_process_ch/ch +','+end_process_ch/ch);
                bw.newLine(); 
                // Close the BufferedWriter
                bw.close();
                
        }
         catch (IOException e) {
            e.printStackTrace();
        }
    }
}
