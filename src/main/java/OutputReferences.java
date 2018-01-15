import org.w3c.dom.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static stringconstant.LoggerMessages.*;
import static stringconstant.StringsConstants.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class OutputReferences {
    public  void outputReferences(String path, Node rootReferences){
        //Copy old References into folder with app
        File dir =new File(TEMP);
        if (!dir.exists()){
            dir.mkdir();
        }
        File sourse = new File(path);
        File target = new File(dir.toPath()+"\\"+OLD+sourse.getName());
        try{
            Files.copy(sourse.toPath(), target.toPath(), REPLACE_EXISTING);
            System.out.println(REFERENCE_FILE+sourse.getName()+COPIED_INTO+target.getAbsolutePath());
            Logger.setLog(REFERENCE_FILE+sourse.getName()+COPIED_INTO+target.getAbsolutePath());

            Files.deleteIfExists(sourse.toPath());

            System.out.println(REFERENCE_FILE+sourse.getName()+DELETED);
            Logger.setLog(REFERENCE_FILE+sourse.getName()+DELETED);
        }
        catch (IOException e){
            System.out.println(IMPOSSIBLE_COPY_FILE+sourse.getName());
            Logger.setLog(IMPOSSIBLE_COPY_FILE+sourse.getName()+"\n"+e.getMessage());
           // Logger.setLog(e.getStackTrace().toString());
            e.printStackTrace();
        }

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

            System.out.println(REFERENCE_FILE+sourse.getName()+CREATED_INTO+sourse.getAbsolutePath());
            Logger.setLog(REFERENCE_FILE+sourse.getName()+CREATED_INTO+sourse.getAbsolutePath());
        }
        catch (TransformerException e){
            System.out.println(IMPOSSIBLE_CREATE_REFERENCE+sourse.getName()+INTO+sourse.getAbsolutePath());
            Logger.setLog(IMPOSSIBLE_CREATE_REFERENCE+sourse.getName()+INTO+sourse.getAbsolutePath()+"\n"+e.getMessage());
           // Logger.setLog(e.getStackTrace().toString());
            e.printStackTrace();
        }
    }
}
