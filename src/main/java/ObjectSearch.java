import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class ObjectSearch {
    public ArrayList<Node> objectSearch(Element rootXML, ArrayList <TestListString> testlist){
        String category;
        String objct;
        int i=0;
        TestListString tlString;
        ArrayList<Node> nodeArrys = new ArrayList<>();

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

                if(objct.equalsIgnoreCase(elObjct.getAttribute("name"))){
                   nodeArrys.add(elNode);
                   count++;
                }//if
                j++;
            }//while
            System.out.println("Done: "+category+": "+objct+" was found "+count);
        //    testlist.get(i).setItem(item);
         ///   System.out.println("Номер "+item);
            i++;
        }//while
        return nodeArrys;
    }//objectSearch

    public ArrayList<Node> objectSearchAI(Element rootXML, ArrayList <TestListString> testlist){
        String category= "uuidkey";
        String subcategory= "MessageAction";
        String objct;
        int i=0;
        TestListString tlString;
        ArrayList<Node> nodeArrys = new ArrayList<>();

        while (i<testlist.size()){
            tlString=testlist.get(i);
            objct=tlString.getObjectName().toLowerCase().trim();

            NodeList nodelist = rootXML.getElementsByTagName(category);

            int j=0;
            int count=0;
            if(objct.equals("")){
                i++;
                continue;
            }
            while (j<nodelist.getLength()){
                Node elNode = nodelist.item(j);
                Element elObjct = (Element)elNode;
                Element elAIMessage =(Element) elObjct.getElementsByTagName(subcategory).item(0);
                if (elAIMessage != null) {
                    String fullNameSource =((elAIMessage.getAttribute("fullNameSource")).toLowerCase().trim())+".";

                    if(fullNameSource.contains(objct+".")){
                        nodeArrys.add(elNode);
                        count++;
                    }//if

                }
                j++;
            }//while
            System.out.println("Done: "+category+": "+objct+" was found "+count);
            //    testlist.get(i).setItem(item);
            ///   System.out.println("Номер "+item);
            i++;
        }//while
        return nodeArrys;
    }//objectSearch
}
