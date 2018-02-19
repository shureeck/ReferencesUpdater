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
        Node targetNode=null;

        //get target schema name
        schemaName = TargetSchema.getTargetSchemaName(nodeObject);

        //get Node target schema
        Node targetSchema = TargetSchema.getTargetSchemaNode(referenceNode,schemaName);

        //get category for  object insert
        targetNode = TargetCategory.getCategoryForInsert(targetSchema,parentNode);

        //update reference file
        if (!targetNode.equals(null)) {
            Document doc = targetNode.getOwnerDocument();
            Node newReferenceNode = doc.importNode(nodeObject, true);
            targetNode.appendChild(newReferenceNode);
            wasUpdated.add(((Element) newReferenceNode).getAttribute(NAME));
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
        Logger.setLog(DONE +((Element) newReferenceNode).getAttribute(NAME)+WAS_UPDATED );

    }

    public ArrayList<String> getListUpdatedObkects() {
        return wasUpdated;
    }
}
