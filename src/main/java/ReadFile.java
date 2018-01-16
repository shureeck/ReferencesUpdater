import java.io.*;
import java.util.ArrayList;

import static stringconstant.StringsConstants.*;
import static stringconstant.LoggerMessages.*;

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
                    line.replaceAll(UNVISIBLE_u202A, EMPTY);
                    if(line.trim().matches(TESTLIST_STRING_FORMAT))
                    {
                        testlist.add(line);
                        System.out.println(LINE_READ + line);
                        Logger.setLog(LINE_READ + line);
                    }
                    else{
                        System.out.println( STRING_HAS_WRONG_FORMAT + line);
                        Logger.setLog( STRING_HAS_WRONG_FORMAT + line);
                    }
                    line = buffer.readLine();
                }//while

                buffer.close();
                sc.close();
                System.out.println(FILE + new File(path).getName()+READ_SUCCESSFULLY);
                Logger.setLog(FILE+new File(path).getName()+READ_SUCCESSFULLY);
            }//try

            catch (FileNotFoundException e) {
                System.out.println(FILE_NOT_FOUND);
                Logger.setLog(FILE_NOT_FOUND);
                Logger.setLog(e.getStackTrace().toString());
             //   e.printStackTrace();
            }//catch;

            catch (IOException e) {
                System.out.println(COULD_NOT_READ_FILE);
                Logger.setLog(COULD_NOT_READ_FILE);
                Logger.setLog(e.getStackTrace().toString());
              //  e.printStackTrace();
            }//catch;
        }//if
        else{
            System.out.println(PATH_TO_TESTLIST_MISSING);
            Logger.setLog(PATH_TO_TESTLIST_MISSING);
        }

    }// fileRead
}
