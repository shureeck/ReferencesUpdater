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

        //Get paths to AI tests
        File targAI = GetPath.getSctPath( new File(pathTarget));
        File referAI=GetPath.getSctPath( new File(pathReference));

        //Update reference for AIs
        //Reference xml file will be read
        ReadXML read = new ReadXML();
        Node rootNodeReferenceAI = read.readXML(referAI.getPath());
        Element referenceAI = (Element) rootNodeReferenceAI;

        //Search AIs for object in reference
        ArrayList <Node> referenceNodesAI = new ArrayList<>();
        ObjectSearch searchAI = new ObjectSearch();
        referenceNodesAI.addAll(searchAI.objectSearchAI(referenceAI, testList));

        //Remove the exist objects
        i=0;
        while (i<referenceNodesAI.size()){
            Node parentAI = referenceNodesAI.get(i).getParentNode();
            parentAI.removeChild(referenceNodesAI.get(i));
            i++;
        }

        //Target sct file will be read
        Node rootNodeTargetAI = read.readXML(targAI.getPath());
        Element targetAI = (Element) rootNodeTargetAI;

        //Search AIs for object in target
        ArrayList <Node> targetNodesAI = new ArrayList<>();
        targetNodesAI.addAll(searchAI.objectSearchAI(targetAI, testList));

        //Updating of the  reference
        UpdateRefer updater = new UpdateRefer();
        i=0;
        while (i<targetNodesAI.size()){
            updater.updateEtalonAI(targetNodesAI.get(i),rootNodeReferenceAI);
            i++;
        }
        
        //Output Reference file
        OutputReferences output = new OutputReferences();
        output.outputReferences(referAI.getPath(), rootNodeReferenceAI);
    }
}
