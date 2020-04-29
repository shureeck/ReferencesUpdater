import org.w3c.dom.Node;

import java.io.File;
import java.util.ArrayList;

import static stringconstant.LoggerMessages.*;
import static stringconstant.StringsConstants.*;

public class ReferenceUpdater {
    private String pathTestList;
    private String pathTarget;
    private String pathReference;

    public ReferenceUpdater(String... args) {
        if (args != null && args.length == 3) {
            pathTestList = args[0];
            Logger.setLog(String.format(TESTLIST_FILE, pathTestList));
            pathTarget = args[1];
            Logger.setLog(String.format(TARGET_PATH, pathTarget));
            pathReference = args[2];
            Logger.setLog(String.format(REFERENCES_PATH, pathReference));


        } else {
            Input inPutData = new Input();
            pathTestList = inPutData.input(INPUT_PATH_TO_TESTLIST);
            pathTarget = inPutData.input(INPUT_PATH_TO_PROJECT);
            pathReference = inPutData.input(INPUT_PATH_TO_REFERNCE + "\n" + ATTENTION);
        }

    }

    public void updateReference() {

        //Testlist will be read from file
        ArrayList<String> stringsTestList = new ArrayList<>();
        ReadFile fileReader = new ReadFile();
        fileReader.readFile(pathTestList, stringsTestList);

        //Parsing and creation TestLis will be start
        ArrayList<TestListString> testList = new ArrayList<>();

        int i = 0;
        TestListParser parser = new TestListParser();
        while (i < stringsTestList.size()) {
            testList.add(parser.testListStringParser(stringsTestList.get(i)));
            i++;
        }

        OutputReferences output = new OutputReferences();

        //Get paths for AI tests
        if (pathTarget != null && pathReference != null) {
            File targAI = GetPath.getSctPath(new File(pathTarget));
            File referAI = GetPath.getSctPath(new File(pathReference));


            if (targAI != null && referAI != null) {
                //Update reference for AIs
                Node rootNodeReferenceAI = ReferenceAI.UpdateRerenceAI(referAI, targAI, testList);

                //Output Reference AI file
                output.outputReferences(referAI.getPath(), rootNodeReferenceAI);
            }

            //Get path for Conversion tests
            File referConv = GetPath.getReferenceXMLPath(new File(pathReference));
            File targConv = GetPath.getTargetXMLPath(new File(pathTarget));

            if (referConv != null && targConv != null) {
                //Update Conversion reference
                Node rootNodeReference = ReferenceConversion.ubdateConversionRefernce(referConv, targConv, testList);

                //Output Reference file
                output.outputReferences(referConv.getPath(), rootNodeReference);
            }

            //Get related object list
            RelatedObjects relatedObjects = new RelatedObjects();
            ArrayList<String> relatedObjectsList = new ArrayList<>(relatedObjects.getRelatedObjects(targAI, testList));

            if (relatedObjectsList.size() > 0) {
                Logger.appendToTestlist(new File(pathTestList), "\n" + RELTED_OBJECTS);
                Logger.setLog(SEPARATOR + "\n" + RELTED_OBJECTS);
                i = 0;
                while (relatedObjectsList.size() > i) {
                    Logger.appendToTestlist(new File(pathTestList), relatedObjectsList.get(i));
                    Logger.setLog(relatedObjectsList.get(i));
                    i++;
                }
                Logger.setLog(SEPARATOR);
            }
        }


    }
}
