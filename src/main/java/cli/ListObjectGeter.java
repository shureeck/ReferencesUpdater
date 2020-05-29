package cli;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Poliakov.A on 10/9/2019.
 */
public class ListObjectGeter {
    public static void main(String... args) {
        Input in = new Input();
        String sctFile = in.input("cli.Input *.sct file path");
        String outFile = in.input("cli.Input output file path file path");
        ReadFile rf = new ReadFile();
        ArrayList<String> lines = new ArrayList(rf.getPercentSFromFile(sctFile));
        File tl = new File(outFile);

        for (String tmp : lines) {
            int startIdx = tmp.indexOf("fullNameSource=\"")+16;
            int endIdx = tmp.indexOf("\"", startIdx + 1);
            String obj = tmp.substring(startIdx, endIdx);
            startIdx = tmp.indexOf("code=\"")+6;
            endIdx = tmp.indexOf("\"", startIdx + 1);
            String ai = tmp.substring(startIdx, endIdx);
            Logger.appendToTestlist(tl,ai+","+ obj);
            Logger.setLog(obj);
        }


    }
}
