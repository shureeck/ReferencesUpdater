import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.io.File;
import java.util.ArrayList;

public class main {
    public static void main (String[] args){
       /* String pathTestList="E:\\AutoTests\\Temp\\PostgreSQL_MySQL\\test_list.csv";
        String pathTarget="E:\\AutoTests\\Temp\\PostgreSQL_MySQL\\PostgreSQL_MySQL.sct";
        String pathReference="E:\\AutoTests\\Temp\\PostgreSQL_MySQL\\Reference1.sct";
*/

        Input inPutData = new Input();
        String pathTestList=inPutData.input("Input the path to the Test List");
        String pathTarget=inPutData.input("Input the path to the project folder:");
        String pathReference=inPutData.input("Input the path to the reference folder:\n(ATTENTION: IN THE REFERENCE FOLDER MUST BE PRESENTED REFERENCES FOR CURRENT PAIR ONLY)");

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

        //Get paths for AI tests
        File targAI = GetPath.getSctPath( new File(pathTarget));
        File referAI=GetPath.getSctPath( new File(pathReference));

        //Update reference for AIs
        Node rootNodeReferenceAI = ReferenceAI.UpdateRerenceAI(referAI, targAI, testList);

        //Output Reference AI file
        OutputReferences output = new OutputReferences();
        output.outputReferences(referAI.getPath(), rootNodeReferenceAI);

        //Get path for Conversion tests
        File referConv = GetPath.getReferenceXMLPath(new File(pathReference));
        File targConv = GetPath.getTargetXMLPath(new File(pathTarget));

        //Update Conversion reference
        Node rootNodeReference=ReferenceConversion.ubdateConversionRefernce(referConv, targConv,testList);

        //Output Reference file
        output.outputReferences(referConv.getPath(), rootNodeReference);
    }
}
