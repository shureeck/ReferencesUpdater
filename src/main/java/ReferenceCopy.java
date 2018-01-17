import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static stringconstant.LoggerMessages.*;;
import static stringconstant.StringsConstants.*;

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
            System.out.println(REFERENCE_FILE+sourse.getName()+COPIED_INTO+target.getAbsolutePath());
            Logger.setLog(REFERENCE_FILE+sourse.getName()+COPIED_INTO+target.getAbsolutePath());

            Files.deleteIfExists(sourse.toPath());

            System.out.println(REFERENCE_FILE+sourse.getName()+DELETED);
            Logger.setLog(REFERENCE_FILE+sourse.getName()+DELETED);
        }
        catch (IOException e){
            System.out.println(IMPOSSIBLE_COPY_FILE+sourse.getName());
            Logger.setLog(IMPOSSIBLE_COPY_FILE+sourse.getName()+"\n"+e.getMessage());
            // Logger.setLog(e.getStackTrace().toString());
            e.printStackTrace();
        }
    }
}
