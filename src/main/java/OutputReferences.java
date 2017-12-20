import org.w3c.dom.Node;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.Format;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class OutputReferences {
    public  void outputReferences(String path, Node rootReferences){
        //Copy old References into folder with app
        File sourse = new File(path);
        File target = new File(""+"old_"+sourse.getName());
        try{
            Files.copy(sourse.toPath(), target.toPath(), REPLACE_EXISTING);
            System.out.println("Done: Reference file "+sourse.getName()+" was copied into "+target.getAbsolutePath());
        }
        catch (IOException e){
            System.out.println("Error: Impossible copy file "+sourse.getName());
            e.printStackTrace();
        }

        //Saving new reference file
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSourcee = new DOMSource(rootReferences.getOwnerDocument());
            StreamResult result = new StreamResult(sourse);
            transformer.transform(domSourcee, result);
            System.out.println("Done: Reference "+sourse.getName()+" was created into "+sourse.getAbsolutePath());
        }
        catch (TransformerException e){
            System.out.println("Error: Impossible create reference "+sourse.getName()+" into "+sourse.getAbsolutePath());
            e.printStackTrace();
        }


    }
}
