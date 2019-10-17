import java.io.*;
import java.util.ArrayList;

import static stringconstant.StringsConstants.*;
import static stringconstant.LoggerMessages.*;

/**
 * Created by Poliakov.A on 11/15/2017.
 */
public class ReadFile {

    public void readFile(String path, ArrayList<String> testlist) {
        FileReader sc = null;
        BufferedReader buffer = null;

        if (path != (null)) {
            try {
                sc = new FileReader(path);
                buffer = new BufferedReader(sc);
                String line = buffer.readLine().trim();

                while (line != null) {
                    line.replaceAll(UNVISIBLE_u202A, EMPTY);
                    if (line.trim().matches(TESTLIST_STRING_FORMAT)) {
                        testlist.add(line);
                        Logger.setLog(LINE_READ + line);
                    } else {
                        Logger.setLog(STRING_HAS_WRONG_FORMAT + line);
                    }
                    line = buffer.readLine();
                }//while

                buffer.close();
                sc.close();
                Logger.setLog(FILE + new File(path).getName() + READ_SUCCESSFULLY);
            }//try

            catch (FileNotFoundException e) {
                Logger.setLog(FILE_NOT_FOUND);
                Logger.setLog(e.getStackTrace().toString());
                //   e.printStackTrace();
            }//catch;

            catch (IOException e) {
                Logger.setLog(COULD_NOT_READ_FILE);
                Logger.setLog(e.getStackTrace().toString());
                //  e.printStackTrace();
            }//catch;
        }//if
        else {
            Logger.setLog(PATH_TO_TESTLIST_MISSING);
        }

    }// fileRead

    public ArrayList<String> readFile(String path) {
        ArrayList<String> result = new ArrayList<>();
        FileReader sc = null;
        BufferedReader buffer = null;

        if (path != (null)) {
            try {
                sc = new FileReader(path);
                buffer = new BufferedReader(sc);
                String line = buffer.readLine().trim();

                while (line != null) {
                    line.replaceAll(UNVISIBLE_u202A, EMPTY);

                    result.add(line.trim());
                    Logger.setLog(LINE_READ + line);
                    line = buffer.readLine();
                }//while

                buffer.close();
                sc.close();
                Logger.setLog(FILE + new File(path).getName() + READ_SUCCESSFULLY);
            }//try

            catch (FileNotFoundException e) {
                Logger.setLog(FILE_NOT_FOUND);
                Logger.setLog(e.getStackTrace().toString());
                //   e.printStackTrace();
            }//catch;

            catch (IOException e) {
                Logger.setLog(COULD_NOT_READ_FILE);
                Logger.setLog(e.getStackTrace().toString());
                //  e.printStackTrace();
            }//catch;
        }//if
        else {
            Logger.setLog(PATH_TO_TESTLIST_MISSING);
        }

        return result;

    }// fileRead

    public ArrayList<String> getPercentSFromFile(String path) {
        ArrayList<String> result = new ArrayList<>();
        FileReader sc = null;
        BufferedReader buffer = null;

        if (path != (null)) {
            try {
                sc = new FileReader(path);
                buffer = new BufferedReader(sc);
                String line = buffer.readLine().trim();

                while (line != null) {
                    line.replaceAll(UNVISIBLE_u202A, EMPTY);

                    if (line.contains("%s")) {
                        result.add(line.trim());
                    }
                    Logger.setLog(LINE_READ + line);
                    line = buffer.readLine();
                }//while

                buffer.close();
                sc.close();
                Logger.setLog(FILE + new File(path).getName() + READ_SUCCESSFULLY);
            }//try

            catch (FileNotFoundException e) {
                Logger.setLog(FILE_NOT_FOUND);
                Logger.setLog(e.getStackTrace().toString());
                //   e.printStackTrace();
            }//catch;

            catch (IOException e) {
                Logger.setLog(COULD_NOT_READ_FILE);
                Logger.setLog(e.getStackTrace().toString());
                //  e.printStackTrace();
            }//catch;
        }//if
        else {
            Logger.setLog(PATH_TO_TESTLIST_MISSING);
        }

        return result;

    }// fileRead

}