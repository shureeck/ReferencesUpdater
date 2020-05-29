package cli;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Stack;

import static cli.stringconstant.LoggerMessages.*;
import static cli.stringconstant.StringsConstants.*;

public class TargetCategory {
    public static Node getCategoryForInsert(Node targetSchema, Node parentNode) {
        Node targetNode = null;

        if (targetSchema != null) {
            NodeList parentList = ((Element) targetSchema).getElementsByTagName(parentNode.getNodeName());
            String parentNodeName = ((Element) parentNode).getAttribute(NAME);

            int i = 0;

            while (parentList.getLength() > i) {
                if (((Element) parentList.item(i)).getAttribute(NAME).equalsIgnoreCase(parentNodeName)) {
                    targetNode = parentList.item(i);
                    Logger.setLog(TARGET_CATEGORY_IS + ((Element) targetNode).getAttribute(NAME));
                    break;
                }//if
                i++;
            }//while
        }//if

        return targetNode;
    }

    public static Stack<Node> getFullPath(Node node) {
        Node tempNode = node.getParentNode();
        Stack<Node> stack = new Stack<>();
        while (tempNode != null && !tempNode.getNodeName().equals(METADATA)) {
            stack.push(tempNode);
            tempNode = tempNode.getParentNode();
        }
        Logger.setLog(String.format(UPDATE_OBJECT, node.getNodeName() + " " + ((Element) node).getAttribute(NAME)));
        return stack;
    }

    public static Node getCategoryByFullPath(Stack<Node> path, Node referNode) {
        Node resultCategory = referNode;
        String category;
        String name;
        String fullPathForLog = "";
        while (path.size() > 0) {
            Node tempNode = path.pop();
            category = tempNode.getNodeName();
            name = ((Element) tempNode).getAttribute(NAME);
            fullPathForLog = fullPathForLog + name + ".";
            NodeList nodeList = ((Element) resultCategory).getElementsByTagName(category);
            resultCategory = getElementByName(nodeList, name);
            if (resultCategory == null) {
                Logger.setLog(String.format(FULL_PATH_WAS_NOT_FOUND, fullPathForLog));
                return null;
            }
        }
        Logger.setLog(String.format(FULL_PATH_FOR_OBJECT, fullPathForLog));
        return resultCategory;
    }

    public static Node getElementByName(NodeList nodeList, String name) {
        Node result = null;
        int i = 0;
        while (i < nodeList.getLength()) {
            Node tmp = nodeList.item(i);
            if (((Element) tmp).getAttribute(NAME).equals(name)) {
                result = tmp;
                break;
            }
            i++;
        }
        return result;
    }
}
