import org.w3c.dom.Element;
import org.w3c.dom.Node;

import static stringconstant.StringsConstants.*;

/**
 * Created by Poliakov.A on 11/17/2017.
 */
public class TestListString {
    private int num;
    private String parentObject;
    private String childObject;
    private String objct;
    private String category;
    private String item;

    public TestListString() {
        parentObject = EMPTY;
        childObject = EMPTY;
        objct = EMPTY;
        category = EMPTY;
        item = EMPTY;
    }

    public TestListString(int num, String category, String parentObject, String childObject) {
        this.parentObject = parentObject;
        this.childObject = childObject;
        this.category = category;
        this.num = num;
    }

    public int getObjectNumber() {
        return num;
    }//get

    public void setObjectNumber(int n) {
        this.num = n;
    }//set

    public String getРarentObject() {
        return parentObject;
    }//get

    public void setРarentObject(String parentObject) {
        this.parentObject = parentObject.trim();
    }//ste

    public String getСhildObject() {
        return childObject;
    }//get

    public void setСhildObject(String childObject) {
        this.childObject = childObject.trim();
    }//set

    public String getСategory() {
        return category;
    }//get

    public void setСategory(String category) {
        this.category = category.trim();
    }//set

    public String getTestListLine() {
        String line;
        if (parentObject == null) parentObject = EMPTY;
        line = num + COMMA + category + COMMA + parentObject + COMMA + childObject + COMMA;
        return line;
    }//get

    public String getItem() {
        return item;
    }//get

    public void setItem(String item) {
        this.item = item.trim();
    }//set

    public String getObjectName() {
        if (parentObject.trim() == null | parentObject.equalsIgnoreCase(EMPTY))
            objct = childObject;

        else
            objct = parentObject + DOLLAR + childObject;

        return objct;
    }//get

    public TestListString nodeToTestList(Node relationObject, int number) {
        TestListString testList = new TestListString();
        String objectName = ((Element) relationObject).getAttribute(NAME);

        testList.setObjectNumber(number);
        testList.setСategory(((Element) relationObject).getAttribute(TYPE));
        testList.setСhildObject(objectName);

        return testList;
    }

}
