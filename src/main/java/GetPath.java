import java.io.File;
import java.util.Arrays;
import java.util.List;


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
                if (temp.endsWith(".sct")) {
                    outFile = lst.get(i);
                    count++;
                }
                i++;
            }
            if (count > 1) {
                System.out.println("Error: There are " + count + " *.sct files in directory " + directory.getPath() + ". Must be only one.");
                outFile = null;
            } else if (count < 1) {
                System.out.println("Error: There are no *.sct files. Must be only one.");
                outFile = null;
            }
        }//if
        else {
            System.out.println("Error: There are no files in the directory, or specified folder are missing \n" +
                    directory.getPath());
            Logger.setLog("Error: There are no files in the directory, or specified folder are missing \n" +
                    directory.getPath());
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
                if (temp.equalsIgnoreCase("target.xml")) {
                    outFile = lst.get(i);
                    System.out.println("Done:File " + outFile.getPath() + " was found");
                    Logger.setLog("Done:File " + outFile.getPath() + " was found");
                    break;
                }
                i++;
            }
        }//if
        else {
            System.out.println("Error: There are no file target.xml in the directory, or specified folder are missing \n" +
                    directory.getPath());
            Logger.setLog("Error: There are no file target.xml in the directory, or specified folder are missing \n" +
                    directory.getPath());
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
                if (temp.endsWith(".xml")) {
                    outFile = lst.get(i);
                    count++;
                    System.out.println("Done:Reference file " + outFile.getPath() + " was found");
                    Logger.setLog("Done:Reference file " + outFile.getPath() + " was found");
                }
                i++;
            }

            if (count > 1) {
                System.out.println("Error: There are " + count + " *.xml files in directory " + directory.getPath() + ". Must be only one.");
                Logger.setLog("Error: There are " + count + " *.xml files in directory " + directory.getPath() + ". Must be only one.");
                outFile = null;
            } else if (count < 1) {
                System.out.println("Error: There are no *.xml files. Must be only one.");
                Logger.setLog("Error: There are no *.xml files. Must be only one.");
                outFile = null;
            }
        }//if
        else {
            System.out.println("Error: There are no file *.xml in the directory, or specified folder are missing \n" +
                    directory.getPath());
            Logger.setLog("Error: There are no file *.xml in the directory, or specified folder are missing \n" +
                    directory.getPath());
        }
        return outFile;
    }
}
