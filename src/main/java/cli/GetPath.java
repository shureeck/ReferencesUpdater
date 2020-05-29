package cli;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static cli.stringconstant.LoggerMessages.*;
import static cli.stringconstant.StringsConstants.*;


public class GetPath {
    public static File getSctPath( File directory){
        File outFile=null;
        File[] arrFiles=null;

        if (directory!=null) {
            arrFiles = directory.listFiles();
        }

        if (arrFiles!=null) {
            List<File> lst = Arrays.asList(arrFiles);

            int i = 0;
            int count = 0;
            while (i < lst.size()) {
                String temp = lst.get(i).getName().trim().toLowerCase();
                if (temp.endsWith(SCT)) {
                    outFile = lst.get(i);
                    count++;
                }
                i++;
            }
            if (count > 1) {
                Logger.setLog(THERE_ARE + count + SCT_FILES_IN_DIRECTORY + directory.getPath() + MUST_BE_ONLY_ONE);
                outFile = null;
            } else if (count < 1) {
                Logger.setLog(NO_SCT_FILES_IN_DIRECTORY_MUST_BE_ONE);
                outFile = null;
            }
        }//if
        else {
            Logger.setLog(NO_FILES_IN_DIRECORY_FOLDER_MISSIING + directory.getPath());
        }
        return outFile;
    }

    public static File getTargetXMLPath( File directory){
        File outFile=null;
        File[] arrFiles=null;

        if (directory!=null) {
            arrFiles = directory.listFiles();
        }
        if(arrFiles!=null) {

            List<File> lst = Arrays.asList(arrFiles);

            int i = 0;
            while (i < lst.size()) {
                String temp = lst.get(i).getName().trim().toLowerCase();
                if (temp.equalsIgnoreCase(TARGET_XML)) {
                    outFile = lst.get(i);
                    Logger.setLog(FILE + outFile.getPath() +FOUND);
                    break;
                }
                i++;
            }
        }//if
        else {
            Logger.setLog(NO_TARGET_XML_IN_DIRECTORY_FOLDER_MISSING + directory.getPath());
        }
        return outFile;
    }

    public static File getReferenceXMLPath( File directory){
        File outFile=null;
        File[] arrFiles=null;

        if (directory!=null) {
            arrFiles = directory.listFiles();
        }

        if (arrFiles!=null) {
            List<File> lst = Arrays.asList(arrFiles);

            int i = 0;
            int count = 0;
            while (i < lst.size()) {
                String temp = lst.get(i).getName().trim().toLowerCase();
                if (temp.endsWith(DOT_XML)) {
                    outFile = lst.get(i);
                    count++;
                    Logger.setLog(REFERENCE_FILE + outFile.getPath() + FOUND);
                }
                i++;
            }

            if (count > 1) {
                Logger.setLog(THERE_ARE + count + XML_FILES_IN_DIRECTORY + directory.getPath() + MUST_BE_ONLY_ONE);
                outFile = null;
            } else if (count < 1) {
                Logger.setLog(NO_XML_FILES_IN_DIRECTORY_MUST_BE_ONE);
                outFile = null;
            }
        }//if
        else {
            Logger.setLog(NO_XML_FILES_IN_DIRECTORY_DIRECTORY_MISSING + directory.getPath());
        }
        return outFile;
    }
}
