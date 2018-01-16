import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static stringconstant.LoggerMessages.*;
import static stringconstant.StringsConstants.*;

public class TargetCategory {
    public static Node getCategoryForInsert(Node targetSchema, Node parentNode){
        Node targetNode=null;

        if (targetSchema!=null){
        NodeList parentList = ((Element) targetSchema).getElementsByTagName(parentNode.getNodeName());
        String parentNodeName = ((Element) parentNode).getAttribute(NAME);

          int  i=0;

            while (parentList.getLength()>i){
                if(((Element)parentList.item(i)).getAttribute(NAME).equalsIgnoreCase(parentNodeName)){
                    targetNode=parentList.item(i);
                    System.out.println(TARGET_CATEGORY_IS+((Element)targetNode).getAttribute(NAME));
                    Logger.setLog(TARGET_CATEGORY_IS+((Element)targetNode).getAttribute(NAME));
                    break;
                }//if
                i++;
            }//while
        }//if

        return targetNode;
    }
}
