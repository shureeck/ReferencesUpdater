package cli;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Stack;

import static cli.stringconstant.StringsConstants.*;
import static cli.stringconstant.LoggerMessages.*;


public class UpdateRefer {
    private ArrayList<String> wasUpdated = new ArrayList<>();

    public void updateEtalon(Node nodeObject, Node referenceNode) {
        Node targetNode = null;

        //GetFullPath
        Stack<Node> stackPath = TargetCategory.getFullPath(nodeObject);

        //get category for  object insert
        targetNode = TargetCategory.getCategoryByFullPath(stackPath, referenceNode);

        //update reference file
        if (targetNode != (null)) {
            Document doc = targetNode.getOwnerDocument();
            Node return_carriage = doc.createTextNode("\n\t\t\t\t");
            Node newReferenceNode = doc.importNode(nodeObject, true);
            targetNode.appendChild(newReferenceNode);
            targetNode.appendChild(return_carriage);//a carriage return to a new line  "\n"
            wasUpdated.add(((Element) newReferenceNode).getAttribute(NAME));
            Logger.setLog(DONE + targetNode.getNodeName() + SPACE + ((Element) newReferenceNode).getAttribute(NAME) + WAS_UPDATED);
        }//if
        else
            Logger.setLog(String.format(OBJECT_WAS_NOT_UPDATED, nodeObject.getNodeName(), ((Element) nodeObject).getAttribute(NAME)));
    }

    public void updateEtalonAI(Node nodeObject, Node referenceNode) {
        //get category for  object insert
        NodeList referSchemas = ((Element) referenceNode).getElementsByTagName(UUIDKEY);
        Node parent = referSchemas.item(0).getParentNode();
        Document doc = parent.getOwnerDocument();
        Node return_carriage = doc.createTextNode("\n\t\t\t\t\t\t\t\t");
        Node newReferenceNode = doc.importNode(nodeObject, true);
        parent.appendChild(newReferenceNode);
        parent.appendChild(return_carriage); //a carriage return to a new line "\n"
        Logger.setLog(DONE + ((Element) newReferenceNode).getAttribute(NAME) + WAS_UPDATED);

    }

    public ArrayList<String> getListUpdatedObkects() {
        return wasUpdated;
    }
}
