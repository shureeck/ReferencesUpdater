import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Poliakov.A on 12/22/2017.
 */
public class ReferenceAI {
    public static Node UpdateRerenceAI(File referAI, File targAI, ArrayList<TestListString>testList ){
        int i=0;
        //Reference xml file will be read
        ReadXML read = new ReadXML();
        Node rootNodeReferenceAI = read.readXML(referAI.getPath());
        Element referenceAI = (Element) rootNodeReferenceAI;

        //Search AIs for object in reference
        ArrayList<org.w3c.dom.Node> referenceNodesAI = new ArrayList<>();
        ObjectSearch searchAI = new ObjectSearch();
        referenceNodesAI.addAll(searchAI.objectSearchAI(referenceAI, testList));


        //Remove the exist objects
        i=0;
        while (i<referenceNodesAI.size()){
            org.w3c.dom.Node parentAI = referenceNodesAI.get(i).getParentNode();
            parentAI.removeChild(referenceNodesAI.get(i).getNextSibling());
            parentAI.removeChild(referenceNodesAI.get(i));
            i++;
        }

        //Target sct file will be read
        org.w3c.dom.Node rootNodeTargetAI = read.readXML(targAI.getPath());
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

        return rootNodeReferenceAI;

    }

}
