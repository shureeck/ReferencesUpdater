import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.util.ArrayList;

import static stringconstant.LoggerMessages.*;

public class RelatedObjects {
    private ArrayList<TestListString> relatedObjectsTestList = new ArrayList<>();

    public ArrayList<String> getRelatedObjects(File targetAI, ArrayList<TestListString> testList){
        //Reference xml file will be read
        ReadXML read = new ReadXML();
        Node rootNodeTargetAI = read.readXML(targetAI.getPath());
        int i;

        ObjectSearch searchRelated = new ObjectSearch();

        ArrayList<Node> relatedObjects= new ArrayList<>(searchRelated.searchRelatedObjects((Element)rootNodeTargetAI,testList));
        ArrayList<String> relatedObjectsTLString = new ArrayList<>();

        int number = testList.get(testList.size()-1).getObjectNumber();

        i=0;
        TestListString testListString = new TestListString();
        while (relatedObjects.size()>i){

            testListString=testListString.nodeToTestList(relatedObjects.get(i), ++number);
            int j=0;
            //Exclude of duplication of related objects in test list
            while (testList.size()>j){
                if (testList.get(j).getObjectName().equalsIgnoreCase(testListString.getObjectName())){
                    Logger.setLog(DONE+testListString.getObjectName()+ALREADY_PRESENT_IN_TEST_LIST);
                    break;
                }
                j++;
                if(testList.size()<=j){
                    relatedObjectsTLString.add(testListString.getTestListLine());
                    this.relatedObjectsTestList.add(testListString);
                }
            }
            i++;
        }

        return relatedObjectsTLString;
    }

    public ArrayList<TestListString> getRelatedObjectsTestList() {
        return relatedObjectsTestList;
    }
}
