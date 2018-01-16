import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import static stringconstant.LoggerMessages.*;

/**
 * Created by Poliakov.A on 11/16/2017.
 */
public class ReadXML {
    public Node readXML(String path)
    {
        Document doc=null;
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            doc=docBuilder.parse(path);
            doc.getDocumentElement().normalize();
            System.out.println(FILE+new File(path).getName()+READ_SUCCESSFULLY);
            Logger.setLog(FILE+new File(path).getName()+READ_SUCCESSFULLY);

        }//try

        catch(javax.xml.parsers.ParserConfigurationException e){
            System.out.println(WRONG_PARSER_CONFIG);
            Logger.setLog(WRONG_PARSER_CONFIG);
            //Logger.setLog(e.getStackTrace().toString());
            e.printStackTrace();
        }//catch
        catch(org.xml.sax.SAXException e){
            System.out.println(ERROR);
            Logger.setLog(ERROR + e.getMessage());
           // Logger.setLog(e.getStackTrace().toString());
            e.printStackTrace();
        }//catch
        catch(java.io.IOException e){
            System.out.println(ERROR + e.getMessage());
            Logger.setLog(ERROR + e.getMessage());
            //Logger.setLog(e.getStackTrace().toString());
            e.printStackTrace();
        }//catch
        Node root = doc.getDocumentElement();
         return root;

    }


}
