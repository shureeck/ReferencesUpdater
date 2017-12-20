import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class main {
    public static void main (String[] args){
        String pathTestList="E:\\IdeaProjects\\TestProject\\test-list-MYSQL_POSTGRE_all.csv";
        String pathTarget="E:\\IdeaProjects\\TestProject\\PostgreSQL_MySQL\\PostgreSQL_MySQL1.sct";
        String pathReference="E:\\IdeaProjects\\TestProject\\PostgreSQL_MySQL\\PostgreSQL_MySQL.sct";

         /*   Input inPutData = new Input();
        pathTestList=inPutData.input("Input the path to the Test List");
        pathTarget=inPutData.input("Input the path to the target.xml (A file with new reference)");
        pathReference=inPutData.input("Input the path to the reference.xml (A file with all reference objects)");
*/
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

        //Update reference for AIs
        //Reference xml file will be read
        ReadXML read = new ReadXML();
        Node rootNodeReferenceAI = read.readXML(pathReference);
        Element referenceAI = (Element) rootNodeReferenceAI;

        //Search AIs for object in reference
        ArrayList <Node> referenceNodesAI = new ArrayList<>();
        ObjectSearch searchAI = new ObjectSearch();
        referenceNodesAI.addAll(searchAI.objectSearchAI(referenceAI, testList));

        //Remove the exist objects
        i=0;
        while (i<referenceNodesAI.size()){
            Node parent = referenceNodesAI.get(i).getParentNode();
            parent.removeChild(referenceNodesAI.get(i));
            i++;
        }

        //Target xml file will be read
        Node rootNodeTargetAI = read.readXML(pathTarget);
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
        output.outputReferences(pathReference, rootNodeReferenceAI);

    }
}
