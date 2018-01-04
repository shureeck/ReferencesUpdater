import java.io.*;
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
                String line = buffer.readLine().trim();

                while (line != null) {
                    line.replaceAll("\\u202A", "");
                    if(line.trim().matches("^\\d+"+"[,]"+".+$"))
                    {
                        testlist.add(line);
                        System.out.println("Done: Line was read: " + line);
                        Logger.setLog("Done: Line was read: " + line);
                    }
                    else{
                        System.out.println("Error: Read string has wrong format");
                        Logger.setLog("Error: Read string has wrong format");
                    }
                    line = buffer.readLine();
                }//while

                buffer.close();
                sc.close();
                System.out.println("Done: File "+new File(path).getName()+" was read successfully");
                Logger.setLog("Done: File "+new File(path).getName()+" was read successfully");
            }//try

            catch (FileNotFoundException e) {
                System.out.println("Error: File not found");
                Logger.setLog("Error: File not found");
             //   e.printStackTrace();
            }//catch;

            catch (IOException e) {
                System.out.println("Error: Could not read file");
                Logger.setLog("Error: Could not read file");
              //  e.printStackTrace();
            }//catch;
        }//if
        else{
            System.out.println("Error: The path to file is missing");
            Logger.setLog("Error: The path to file is missing");
        }

    }// fileRead
}
