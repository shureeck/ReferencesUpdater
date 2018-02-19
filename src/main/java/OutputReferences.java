import org.w3c.dom.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import static stringconstant.LoggerMessages.*;
import static stringconstant.StringsConstants.*;

public class OutputReferences {
    public  void outputReferences(String path, Node rootReferences){
        //Copy old References into folder with app


        File sourse = new File(path);
        ReferenceCopy.createFileCopy(sourse);

        //Saving new reference file
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSourcee = new DOMSource(rootReferences.getOwnerDocument());

            transformer.setOutputProperty(OutputKeys.INDENT, YES);
            transformer.setOutputProperty(OutputKeys.METHOD, XML);
            transformer.setOutputProperty(INDENT_AMOUNT, "4");

            StreamResult result = new StreamResult(sourse);
            transformer.transform(domSourcee, result);

            Logger.setLog(REFERENCE_FILE+sourse.getName()+CREATED_INTO+sourse.getAbsolutePath());
        }
        catch (TransformerException e){
            Logger.setLog(IMPOSSIBLE_CREATE_REFERENCE+sourse.getName()+INTO+sourse.getAbsolutePath()+"\n"+e.getMessage());
           // Logger.setLog(e.getStackTrace().toString());
            e.printStackTrace();
        }
    }
}
