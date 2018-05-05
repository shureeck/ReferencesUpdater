import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static  stringconstant.StringsConstants.*;
import static  stringconstant.LoggerMessages.*;

public class TargetSchema {
    public static String getTargetSchemaName(Node object){
        String schemaName=null;
        int i;

        String temp = ((Element)object).getAttribute(ARTIFICAL_SRC_FULL_NAME).toLowerCase();
        schemaName=getSchemaNoArtificiaFullName(object);
//        if(temp.trim()!=""){
//            i = temp.lastIndexOf(SCHEMA)+7;
//            schemaName=temp.substring(i, temp.indexOf(COMMA, i));
//            System.out.println(TARGET_SCHEMA_IS+schemaName);
//            Logger.setLog(TARGET_SCHEMA_IS+schemaName);
//        }
//        else{
//            Logger.setLog(ARTIFICIAL_SRC_FULL_NAME_WAS_NOT_FOUND);
//
//
//        }

        return schemaName;
    }

    public static Node getTargetSchemaNode(Node referenceNode, String schemaName){
        NodeList referSchemas=((Element)referenceNode).getElementsByTagName(SCHEMA);
        Node targetSchema=null;
        int i=0;

        while (i<referSchemas.getLength()){
            if(((Element)referSchemas.item(i)).getAttribute(NAME).equalsIgnoreCase(schemaName)){
                targetSchema=((Element)referSchemas.item(i));
                break;
            }//if
            i++;
        }//while

        if (targetSchema==null){
            System.out.println(SCHEMA_ERR+schemaName+NOT_FOUND_IN_REFERENCE);
            Logger.setLog(SCHEMA_ERR+schemaName+NOT_FOUND_IN_REFERENCE);
        }//if

        return targetSchema;
    }

    private static String getSchemaNoArtificiaFullName(Node tmp ) {
        String schemaName=null;

        while (!tmp.getNodeName().equalsIgnoreCase(SCHEMA)) {
            tmp = tmp.getParentNode();

            if (tmp.getNodeName().equalsIgnoreCase(SCHEMA)) {
                schemaName = ((Element) tmp).getAttribute(NAME);
                Logger.setLog(TARGET_SCHEMA_IS + schemaName);
                break;
            }//if

            if (tmp.getNodeName().equalsIgnoreCase(TREE)) {
                Logger.setLog(COULD_NOT_GET_SCHEMA_NAME);
                schemaName = null;
                break;
            }//if
        }//while
        return schemaName;
    }
}

