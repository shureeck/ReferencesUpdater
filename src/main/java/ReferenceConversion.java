import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Poliakov.A on 12/22/2017.
 */
public class ReferenceConversion {
    public static Node ubdateConversionRefernce (File referConv, File targetConv, ArrayList<TestListString> testList ){
        int i=0;
        //Reference xml file will be read
        ReadXML read = new ReadXML();
        Node rootNodeReference = read.readXML(referConv.getPath());
        Element reference = (Element) rootNodeReference;

        //Search for the same elements in reference
        ArrayList<org.w3c.dom.Node> referenceNodes = new ArrayList<>();
        ObjectSearch searchReference = new ObjectSearch();
        referenceNodes.addAll(searchReference.objectSearch(reference, testList));

        //Remove the exist objects
        i=0;
        while (i<referenceNodes.size()){
            org.w3c.dom.Node parent = referenceNodes.get(i).getParentNode();
            parent.removeChild(referenceNodes.get(i));
            System.out.println("DONE: Object "+((Element)referenceNodes.get(i)).getAttribute("name")+" was removed");
            i++;
        }

        //Target xml file will be read
        org.w3c.dom.Node rootNodeTarget = read.readXML(targetConv.getPath());
        Element target = (Element) rootNodeTarget;

        //Search for the same elements in reference
        ArrayList <org.w3c.dom.Node> targetNodes = new ArrayList<>();
        targetNodes.addAll(searchReference.objectSearch(target, testList));

        //Updating of the  reference
        UpdateRefer updater = new UpdateRefer();
        i=0;
        while (i<targetNodes.size()){
            updater.updateEtalon(targetNodes.get(i),rootNodeReference);
            i++;
        }

        return rootNodeReference;
    }
}
