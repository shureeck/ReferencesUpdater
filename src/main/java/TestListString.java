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

    public TestListString(){
        parentObject="";
       childObject="";
       objct="";
       category="";
       item="";
    }

    public TestListString(int num, String category, String parentObject, String childObject){
        this.parentObject = parentObject;
        this.childObject = childObject;
        this.category=category;
        this.num=num;
    }

    public int getObjectNumber(){
        return num;
    }//get
    public void setObjectNumber(int n){
        this.num=n;
    }//set

    public String getРarentObject(){
        return parentObject;
    }//get
    public void setРarentObject(String parentObject){
        this.parentObject=parentObject.trim();
    }//ste

    public String getСhildObject(){
        return childObject;
    }//get
    public void setСhildObject(String childObject){
        this.childObject=childObject.trim();
    }//set

    public String getСategory(){
        return category;
    }//get
    public void setСategory(String category){
        this.category=category.trim();
    }//set

    public String getTestListLine (){
        String line;
        if (parentObject==null) parentObject="";
        line = num+","+parentObject+","+childObject+",";
        return line;
    }//get

    public String getItem(){
        return item;
    }//get
    public void setItem(String item){
        this.item=item.trim();
    }//set

    public String getObjectName(){
        if (parentObject.trim()==null|parentObject.equalsIgnoreCase(""))
            objct=childObject;

        else
            objct=parentObject+"$"+childObject;

        return objct;
    }//get

}
