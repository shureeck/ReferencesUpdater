import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashSet;

import static stringconstant.StringsConstants.*;
import static stringconstant.LoggerMessages.*;

public class ObjectSearch {
    public ArrayList<Node> objectSearch(Element rootXML, ArrayList <TestListString> testlist){
        String category;
        String objct;
        int i=0;
        TestListString tlString;
        ArrayList<Node> nodeArrys = new ArrayList<>();
        HashSet<Node> nodeSet = new HashSet<>();

        while (i<testlist.size()){
            tlString=testlist.get(i);
            category=tlString.getСategory().toLowerCase().trim();
            objct=tlString.getObjectName();

            NodeList nodelist = rootXML.getElementsByTagName(category);

            int j=0;
            int count=0;

            while (j<nodelist.getLength()){
                Node elNode = nodelist.item(j);
                Element elObjct = (Element)elNode;

                if(objct.equalsIgnoreCase(elObjct.getAttribute(NAME))){
                   nodeSet.add(elNode);
            //       nodeArrys.add(elNode);
                   count++;
                }//if
                j++;
            }//while
            Logger.setLog(DONE+category+COLON+objct+FOUND+count);
            i++;
        }//while
        nodeArrys.addAll(nodeSet);
        return nodeArrys;
    }//objectSearch

    public Node getNodeByName(Element element, String type, String name){
        NodeList nodeType = element.getElementsByTagName(type);
        int i =0;
        while (i<nodeType.getLength()){
            String tmp =((Element)nodeType.item(i)).getAttribute(NAME);
            if (tmp.equals(name.trim())){
                return nodeType.item(i);
            }
            i++;
        }
        return null;
    }//getNodeByName



    public ArrayList<Node> objectSearchAI(Element rootXML, ArrayList <TestListString> testlist){
        String category= UUIDKEY;
        String subcategory= MESSAGE_ACTION;
        String objct;
        int i=0;
        TestListString tlString;
        ArrayList<Node> nodeArrys = new ArrayList<>();
        HashSet<Node> nodeSet = new HashSet<>();
        NodeList nodelist = rootXML.getElementsByTagName(category);

        while (i<testlist.size()){
            tlString=testlist.get(i);
            objct=tlString.getСhildObject().toLowerCase().trim();

            int j=0;
            int count=0;
            if(objct.equals(EMPTY)){
                i++;
                continue;
            }
            while (j<nodelist.getLength()){
                Node elNode = nodelist.item(j);
                Element elObjct = (Element)elNode;
                Element elAIMessage =(Element) elObjct.getElementsByTagName(subcategory).item(0);
                if (elAIMessage != null) {
                    String fullNameSource =((elAIMessage.getAttribute(FULL_NAME_SOURCE)).toLowerCase().trim())+DOT;

                    if(fullNameSource.contains(objct+DOT)){
                        nodeSet.add(elNode);
                        // nodeArrys.add(elNode);
                        count++;
                    }//if

                }
                j++;
            }//while
            Logger.setLog(DONE+category+COLON+objct+FOUND+count);
            i++;
        }//while
        nodeArrys.addAll(nodeSet);
        return nodeArrys;
    }//objectSearch

    public  ArrayList<Node> searchRelatedObjects(Element rootXML, ArrayList <TestListString> testlist){
        NodeList relatedObjectsList=null;
        ArrayList<Node>  relation = new ArrayList<>();
        //get all related objects
        NodeList fullRelationList = rootXML.getElementsByTagName(RELATION);
        ArrayList <Node> relatedObjectsInTestList= new ArrayList<>();

        int i =0;
        //search related objects for objects from test list
        while (i<testlist.size()){
            relatedObjectsInTestList.addAll(checkRelatedObject(fullRelationList, testlist.get(i)));
            i++;
        }//while
       //Get list with relation objects
        i=0;
        while (relatedObjectsInTestList.size()>i){
            relatedObjectsList =((Element)relatedObjectsInTestList.get(i)).getElementsByTagName(RELATION);
            for (int j=0;relatedObjectsList.getLength()>j;j++){
                relation.add(relatedObjectsList.item(j));
            }
            i++;
        }//while
        return relation;
    }// searchRelatedObjects

    private ArrayList <Node> checkRelatedObject( NodeList fullRelationList, TestListString testListString) {
        ArrayList<Node> relationElements = new ArrayList<>();

        String objectName = testListString.getObjectName();
        String category = testListString.getСategory();

        int j = 0;
        while (j < fullRelationList.getLength()) {

            Element relatedElement = (Element) fullRelationList.item(j);
            if (relatedElement.getAttribute(NAME).equalsIgnoreCase(objectName) && relatedElement.getAttribute(TYPE).equalsIgnoreCase(category)) {
                Node temp = relatedElement.getParentNode();
                if (temp.getChildNodes().getLength() > 3) {
                    temp.removeChild(relatedElement.getNextSibling());
                    temp.removeChild(relatedElement);
                    relationElements.add(temp);
                    Logger.setLog(RELATED_OBJECTS_WAS_FOUND + relatedElement.getAttribute(TYPE) + SPACE + relatedElement.getAttribute(NAME));
                } else
                {
                    Logger.setLog(RELATED_OBJECTS_WAS_NOT_FOUND + relatedElement.getAttribute(TYPE) + SPACE + relatedElement.getAttribute(NAME));}
                break;
            }
            j++;
        }
        return relationElements;
    }//checkRelatedObject
}
