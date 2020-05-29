package cli;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static cli.stringconstant.LoggerMessages.*;;
import static cli.stringconstant.StringsConstants.*;

/**
 * Created by Poliakov.A on 1/17/2018.
 */
public class ReferenceCopy {
    public static void createFileCopy(File sourse){
        File dir =new File(TEMP);
        if (!dir.exists()){
            dir.mkdir();
        }

        File target = new File(dir.toPath()+"\\"+OLD+sourse.getName());
        try{
            Files.copy(sourse.toPath(), target.toPath(), REPLACE_EXISTING);
            Logger.setLog(REFERENCE_FILE+sourse.getName()+COPIED_INTO+target.getAbsolutePath());

            Files.deleteIfExists(sourse.toPath());

            Logger.setLog(REFERENCE_FILE+sourse.getName()+DELETED);
        }
        catch (IOException e){
            Logger.setLog(IMPOSSIBLE_COPY_FILE+sourse.getName()+"\n"+e.getMessage());
            // cli.Logger.setLog(e.getStackTrace().toString());
            e.printStackTrace();
        }
    }
}
