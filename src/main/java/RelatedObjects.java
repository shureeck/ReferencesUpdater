import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.util.ArrayList;

public class RelatedObjects {

    public ArrayList<String> getRelatedObjects(File targetAI, ArrayList<TestListString> testList){
        //Reference xml file will be read
        ReadXML read = new ReadXML();
        Node rootNodeTargetAI = read.readXML(targetAI.getPath());
        int i;

        ObjectSearch searchRelated = new ObjectSearch();

        ArrayList<Node> relatedObjects= new ArrayList<>(searchRelated.searchRelatedObjects((Element)rootNodeTargetAI,testList));
        ArrayList<String> relatedObjectsTLString = new ArrayList<>();
        i=0;
        TestListString testListString = new TestListString();
        while (relatedObjects.size()>i){
            relatedObjectsTLString.add(testListString.nodeToTestList(relatedObjects.get(i)).getTestListLine());
            i++;
        }

        return relatedObjectsTLString;
    }
}
