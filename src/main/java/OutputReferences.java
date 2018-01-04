import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.w3c.dom.Node;

import javax.xml.transform.OutputKeys;
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
        File dir =new File("TEMP");
        if (!dir.exists()){
            dir.mkdir();
        }
        File sourse = new File(path);
        File target = new File(dir.toPath()+"\\"+"old_"+sourse.getName());
        try{
            Files.copy(sourse.toPath(), target.toPath(), REPLACE_EXISTING);
            System.out.println("Done: Reference file "+sourse.getName()+" was copied into "+target.getAbsolutePath());
            Logger.setLog("Done: Reference file "+sourse.getName()+" was copied into "+target.getAbsolutePath());
            Files.deleteIfExists(sourse.toPath());
            System.out.println("Done: Reference file "+sourse.getName()+" was deleted");
            Logger.setLog("Done: Reference file "+sourse.getName()+" was deleted");
        }
        catch (IOException e){
            System.out.println("Error: Impossible copy file "+sourse.getName());
            Logger.setLog("Error: Impossible copy file "+sourse.getName()+"\n"+e.toString());
            e.printStackTrace();
        }

        //Saving new reference file
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSourcee = new DOMSource(rootReferences.getOwnerDocument());
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            StreamResult result = new StreamResult(sourse);
            transformer.transform(domSourcee, result);

            System.out.println("Done: Reference "+sourse.getName()+" was created into "+sourse.getAbsolutePath());
            Logger.setLog("Done: Reference "+sourse.getName()+" was created into "+sourse.getAbsolutePath());
        }
        catch (TransformerException e){
            System.out.println("Error: Impossible create reference "+sourse.getName()+" into "+sourse.getAbsolutePath());
            Logger.setLog("Error: Impossible create reference "+sourse.getName()+" into "+sourse.getAbsolutePath()+"\n"+e.toString());
            e.printStackTrace();
        }
    }
}
