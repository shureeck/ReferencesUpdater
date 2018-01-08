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

        ArrayList<String> listUpdatedObjects= new ArrayList<>(updater.getListUpdatedObkects());

        if (listUpdatedObjects.size()==testList.size()){
            System.out.println("Done: All specified objects from test list were updated");
            Logger.setLog("Done: All specified objects from test list were updated");
        }
        else if(listUpdatedObjects.size()<testList.size()){
            System.out.println("-----------------------------------------------------------------------------");
            Logger.setLog("-----------------------------------------------------------------------------");
            System.out.println("Warning: Objects were not updated:");
            Logger.setLog("Warning: Objects were not updated:");
            i=0;
            while (testList.size()>i){
                int j=0;
                while (true){
                    if (testList.get(i).getObjectName().equalsIgnoreCase(listUpdatedObjects.get(j)) && listUpdatedObjects.size()!=0){
                        break;
                    }
                    j++;
                    if(j>=listUpdatedObjects.size()){
                        System.out.println(testList.get(i).getTestListLine());
                        Logger.setLog(testList.get(i).getTestListLine());
                        break;
                    }
                }
                i++;
            }
            System.out.println("-----------------------------------------------------------------------------");
            Logger.setLog("-----------------------------------------------------------------------------");
        }
        else {
            System.out.println("Error: The number of updated objects is large then number of objects in test list");
            Logger.setLog("Error: The number of updated objects is large then number of objects in test list");
        }
        return rootNodeReference;
    }
}
