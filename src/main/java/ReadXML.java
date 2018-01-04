import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Poliakov.A on 11/16/2017.
 */
public class ReadXML {
    public Node readXML(String path)
    {
        Document doc=null;
        try {
            NodeList dbTree;

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            doc=docBuilder.parse(path);
            doc.getDocumentElement().normalize();
            System.out.println("Done: file "+new File(path).getName()+" was successfully read and parsed");
            Logger.setLog("Done: file "+new File(path).getName()+" was successfully read and parsed");

        }//try

        catch(javax.xml.parsers.ParserConfigurationException e){
            System.out.println("Error: You have wrong parser configuration");
            Logger.setLog("Error: You have wrong parser configuration");
            e.printStackTrace();
        }//catch
        catch(org.xml.sax.SAXException e){
            System.out.println("Error:  See stack trace");
            Logger.setLog("Error:  See stack trace\n"+e.toString());
            e.printStackTrace();
        }//catch
        catch(java.io.IOException e){
            System.out.println("Error: Input/Output Error");
            Logger.setLog("Error: Input/Output Error");
            e.printStackTrace();
        }//catch
        Node root = doc.getDocumentElement();
         return root;

    }


}
