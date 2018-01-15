import org.w3c.dom.Node;
import java.io.File;
import java.util.ArrayList;

import static stringconstant.StringsConstants.*;

public class main {
    public static void main (String[] args){
       /* String pathTestList="E:\\Auto
       \Tests\\Temp\\PostgreSQL_MySQL\\test_list.csv";
        String pathTarget="E:\\AutoTests\\Temp\\PostgreSQL_MySQL\\PostgreSQL_MySQL.sct";
        String pathReference="E:\\AutoTests\\Temp\\PostgreSQL_MySQL\\Reference1.sct";
*/

        Input inPutData = new Input();
        String pathTestList=inPutData.input(INPUT_PATH_TO_TESTLIST);
        String pathTarget=inPutData.input(INPUT_PATH_TO_PROJECT);
        String pathReference=inPutData.input(INPUT_PATH_TO_REFERNCE+"\n"+ATTENTION);

        //Testlist will be read from file
        ArrayList<String> stringsTestList = new ArrayList<>();
        ReadFile fileReader = new ReadFile();
        fileReader.readFile(pathTestList, stringsTestList);

        //Parsing and creation TestLis will be start
        ArrayList<TestListString> testList = new ArrayList<>();

        int i=0;
        TestListParser parser = new TestListParser();
        while(i<stringsTestList.size()) {
            testList.add(parser.testListStringParser(stringsTestList.get(i)));
            i++;
        }

        OutputReferences output = new OutputReferences();

        //Get paths for AI tests
        if (pathTarget!=null && pathReference!=null) {
            File targAI = GetPath.getSctPath(new File(pathTarget));
            File referAI = GetPath.getSctPath(new File(pathReference));

            if (targAI!=null && referAI!=null) {
                //Update reference for AIs
                Node rootNodeReferenceAI = ReferenceAI.UpdateRerenceAI(referAI, targAI, testList);

                //Output Reference AI file
                output.outputReferences(referAI.getPath(), rootNodeReferenceAI);
            }

            //Get path for Conversion tests
            File referConv = GetPath.getReferenceXMLPath(new File(pathReference));
            File targConv = GetPath.getTargetXMLPath(new File(pathTarget));

            if (referConv!=null && targConv!=null) {
                //Update Conversion reference
                Node rootNodeReference = ReferenceConversion.ubdateConversionRefernce(referConv, targConv, testList);

                //Output Reference file
                output.outputReferences(referConv.getPath(), rootNodeReference);
            }
        }
    }
}
