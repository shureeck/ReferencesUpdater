import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Poliakov.A on 12/11/2017.
 */
public class Logger {
    public static void setLog(String msg){
        //Creating date-time formats
        Date date = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("y-M-d");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        String logName = "ReferenceUpdater_"+formatDate.format(date)+".log";
        File log = new File(logName);

        //Create new file if not exist
        try {
            if (!log.exists()) {
                log.createNewFile();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //Write data in .log file
        BufferedWriter writer=null;
        try {
            writer =new BufferedWriter(new FileWriter(log, true));
            writer.write(formatTime.format(date)+": "+msg+'\n');
        }//try
        catch (IOException e){
            e.printStackTrace();
        }//catch
        finally {
            try {
                writer.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }//finally
    }
}
