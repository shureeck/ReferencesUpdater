import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;

import static stringconstant.LoggerMessages.*;
import static stringconstant.StringsConstants.SCHEMA;

/**
 * Created by Poliakov.A on 7/26/2019.
 */
public class TestListGenerator {

    public static void main(String... args) {
        //Set test Schema
        Input inPutData = new Input();
        String schemaName = inPutData.input(SET_SCHEMA);
        String objectsPath = inPutData.input(SET_PATH_TO_OBJECT_TYPE_LIST);
        String targetPath = inPutData.input(SET_PATH_TO_TARGET_XML);

        //read file with files type
        ArrayList<String> objectType = new ArrayList<>();
        objectType.addAll(new ReadFile().readFile(new File(objectsPath).getAbsolutePath()));

        File targetXML = new File(targetPath);
        ReadXML read = new ReadXML();
        Node rootNodeReference = read.readXML(targetXML.getAbsolutePath());
        Element element = (Element) rootNodeReference;
        Node node = new ObjectSearch().getNodeByName(element, SCHEMA, schemaName);

        genarateTestList(node, objectType);
    }

    public static void genarateTestList(Node schema, ArrayList<String> objectTypeList) {
        File testList = new File("TestList.csv");
        int count = 1;
        for (String s : objectTypeList) {

            NodeList nodeList = ((Element) schema).getElementsByTagName(s);

            if (nodeList.getLength() == 0) {
                Logger.setLog(String.format(TYPE_NOT_FOUND, s));
            }

            for (int i = 0; i < nodeList.getLength(); i++) {
                TestListString testListString = new TestListString();
                String tmp = testListString.nodeToTestList(nodeList.item(i), count++).getTestListLine();
                Logger.appendToTestlist(testList, tmp);
                Logger.setLog(String.format(LINE_ADDED_IN_TL, tmp));
            }
        }
    }

}
