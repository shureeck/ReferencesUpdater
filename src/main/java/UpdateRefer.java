import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;

import static stringconstant.StringsConstants.*;
import static stringconstant.LoggerMessages.*;


public class UpdateRefer {
    private ArrayList<String> wasUpdated = new ArrayList<>();
    public void updateEtalon(Node nodeObject,Node referenceNode){
        String schemaName=null;
        Node parentNode=nodeObject.getParentNode();
        Node tmp=nodeObject.getParentNode();
        Node targetNode=null;

        //get target schema name
        while(!tmp.getNodeName().equalsIgnoreCase(SCHEMA))
        {
            tmp=tmp.getParentNode();

            if (tmp.getNodeName().equalsIgnoreCase(SCHEMA)){
                schemaName = ((Element)tmp).getAttribute(NAME);
                System.out.println(TARGET_SCHEMA_IS+schemaName);
                Logger.setLog(TARGET_SCHEMA_IS+schemaName);
                break;
            }//if

            if(tmp.getNodeName().equalsIgnoreCase(TREE)){
                System.out.println(COULD_NOT_GET_SCHEMA_NAME);
                Logger.setLog(COULD_NOT_GET_SCHEMA_NAME);
                schemaName=null;
                break;
            }//if
        }//while

        //get category for  object insert
        NodeList referSchemas=((Element)referenceNode).getElementsByTagName(SCHEMA);
        int i=0;
        NodeList parentList=null;

        while (i<referSchemas.getLength()){
            if(((Element)referSchemas.item(i)).getAttribute(NAME).equalsIgnoreCase(schemaName)){
               parentList = ((Element)referSchemas.item(i)).getElementsByTagName(parentNode.getNodeName());
               break;
            }//if
            i++;
        }//while

        if (parentList==null){
            System.out.println(SCHEMA_ERR+schemaName+NOT_FOUND_IN_REFERENCE);
            Logger.setLog(SCHEMA_ERR+schemaName+NOT_FOUND_IN_REFERENCE);
        }//if
        else{
            i=0;
            while (parentList.getLength()>i){
                 if(((Element)parentList.item(i)).getAttribute(NAME).equalsIgnoreCase(((Element)parentNode).getAttribute(NAME))){
                     targetNode=parentList.item(i);
                     System.out.println(TARGET_CATEGORY_IS+((Element)targetNode).getAttribute(NAME));
                     Logger.setLog(TARGET_CATEGORY_IS+((Element)targetNode).getAttribute(NAME));
                     break;
                }//if
                i++;
            }//while
        }//else

        //update reference file
        if (!targetNode.equals(null)) {
            Document doc = targetNode.getOwnerDocument();
            Node newReferenceNode = doc.importNode(nodeObject, true);
            targetNode.appendChild(newReferenceNode);
            wasUpdated.add(((Element) newReferenceNode).getAttribute(NAME));
            System.out.println(DONE + targetNode.getNodeName()+SPACE+((Element) newReferenceNode).getAttribute(NAME)+WAS_UPDATED );
            Logger.setLog(DONE + targetNode.getNodeName()+SPACE+((Element) newReferenceNode).getAttribute(NAME)+WAS_UPDATED );
        }//if
    }

    public void updateEtalonAI(Node nodeObject,Node referenceNode){
        //get category for  object insert
        NodeList referSchemas=((Element)referenceNode).getElementsByTagName(UUIDKEY);
        Node parent = referSchemas.item(0).getParentNode();
        Document doc = parent.getOwnerDocument();
        Node newReferenceNode = doc.importNode(nodeObject, true);
        parent.appendChild(newReferenceNode);
        System.out.println(DONE +((Element) newReferenceNode).getAttribute(NAME)+WAS_UPDATED );
        Logger.setLog(DONE +((Element) newReferenceNode).getAttribute(NAME)+WAS_UPDATED );

    }

    public ArrayList<String> getListUpdatedObkects() {
        return wasUpdated;
    }
}
