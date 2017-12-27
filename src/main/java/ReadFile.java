import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Poliakov.A on 11/15/2017.
 */
public class ReadFile {

    public void readFile (String path, ArrayList<String> testlist) {
        FileReader sc =null;
        BufferedReader buffer=null;
        if (path!=(null)) {
            try {
                sc = new FileReader(path);
                buffer = new BufferedReader(sc);
                String line = buffer.readLine();

                while (line != null) {
                    testlist.add(line);
                    System.out.println("Done: Line was read: " + line);
                    line = buffer.readLine();

                }//while

                buffer.close();
                sc.close();
                System.out.println("Done: File was read successfully");
            }//try

            catch (FileNotFoundException e) {
                System.out.println("Error: File not found");
                e.printStackTrace();
            }//catch;

            catch (IOException e) {
                System.out.println("Error: Could not read file");
                e.printStackTrace();
            }//catch;
        }//if
        else{
            System.out.println("Error: The path to file is missing");
        }

    }// fileRead
}
