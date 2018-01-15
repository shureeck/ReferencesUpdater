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
            System.out.println(DONE+category+COLON+objct+FOUND+count);
            Logger.setLog(DONE+category+COLON+objct+FOUND+count);
        //    testlist.get(i).setItem(item);
         ///   System.out.println("Номер "+item);
            i++;
        }//while
        nodeArrys.addAll(nodeSet);
        return nodeArrys;
    }//objectSearch

    public ArrayList<Node> objectSearchAI(Element rootXML, ArrayList <TestListString> testlist){
        String category= UUIDKEY;
        String subcategory= MESSAGE_ACTION;
        String objct;
        int i=0;
        TestListString tlString;
        ArrayList<Node> nodeArrys = new ArrayList<>();
        HashSet<Node> nodeSet = new HashSet<>();

        while (i<testlist.size()){
            tlString=testlist.get(i);
            objct=tlString.getСhildObject().toLowerCase().trim();

            NodeList nodelist = rootXML.getElementsByTagName(category);

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
            System.out.println(DONE+category+COLON+objct+FOUND+count);
            Logger.setLog(DONE+category+COLON+objct+FOUND+count);
            //    testlist.get(i).setItem(item);
            ///   System.out.println("Номер "+item);
            i++;
        }//while
        nodeArrys.addAll(nodeSet);
        return nodeArrys;
    }//objectSearch
}
