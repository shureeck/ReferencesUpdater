import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class UpdateRefer {
    public void updateEtalon(Node nodeObject,Node referenceNode){
        String schemaName=null;
        Node parentNode=nodeObject.getParentNode();
        Node tmp=nodeObject.getParentNode();
        Node targetNode=null;

        //get target schema name
        while(!tmp.getNodeName().equalsIgnoreCase("schema"))
        {
            tmp=tmp.getParentNode();

            if (tmp.getNodeName().equalsIgnoreCase("schema")){
                schemaName = ((Element)tmp).getAttribute("name");
                System.out.println("Target schema name is "+schemaName);
                Logger.setLog("Target schema name is "+schemaName);
                break;
            }//if

            if(tmp.getNodeName().equalsIgnoreCase("tree")){
                System.out.println("Could not get schema name");
                Logger.setLog("Could not get schema name");
                schemaName=null;
                break;
            }//if
        }//while

        //get category for  object insert
        NodeList referSchemas=((Element)referenceNode).getElementsByTagName("schema");
        int i=0;
        NodeList parentList=null;

        while (i<referSchemas.getLength()){
            if(((Element)referSchemas.item(i)).getAttribute("name").equalsIgnoreCase(schemaName)){
               parentList = ((Element)referSchemas.item(i)).getElementsByTagName(parentNode.getNodeName());
               break;
            }//if
            i++;
        }//while

        if (parentList.equals(null)){
            System.out.println("Schema "+schemaName+" in reference file was not found");
            Logger.setLog("Schema "+schemaName+" in reference file was not found");
        }//if
        else{
            i=0;
            while (parentList.getLength()>i){
                 if(((Element)parentList.item(i)).getAttribute("name").equalsIgnoreCase(((Element)parentNode).getAttribute("name"))){
                     targetNode=parentList.item(i);
                     System.out.println("Target category is "+((Element)targetNode).getAttribute("name"));
                     Logger.setLog("Target category is "+((Element)targetNode).getAttribute("name"));
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
            System.out.println("Done:" + targetNode.getNodeName()+" "+((Element) newReferenceNode).getAttribute("name")+" was updated" );
            Logger.setLog("Done:" + targetNode.getNodeName()+" "+((Element) newReferenceNode).getAttribute("name")+" was updated" );
        }//if
    }
    public void updateEtalonAI(Node nodeObject,Node referenceNode){
        //get category for  object insert
        NodeList referSchemas=((Element)referenceNode).getElementsByTagName("uuidkey");
        Node parent = referSchemas.item(0).getParentNode();
        Document doc = parent.getOwnerDocument();
        Node newReferenceNode = doc.importNode(nodeObject, true);
        parent.appendChild(newReferenceNode);
        System.out.println("Done:" + parent.getNodeName()+" "+((Element) newReferenceNode).getAttribute("name")+" was updated" );
        Logger.setLog("Done:" + parent.getNodeName()+" "+((Element) newReferenceNode).getAttribute("name")+" was updated" );

    }
}
