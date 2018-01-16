import static stringconstant.LoggerMessages.*;

public class TestListParser {
    public TestListString testListStringParser(String str){

        TestListString strTestListString = new TestListString();
        int num=0;
        String category;
        String childObject;
        String parentObject;
        int i=0;
        int j=0;
        i=str.indexOf(',');

            num = Integer.parseInt(str.substring(j, i).trim());

            j=i+1;
            i=str.indexOf(',',j);
            category=str.substring(j,i).trim();
            j=i+1;
            i=str.indexOf(',',j);
            parentObject=str.substring(j,i).trim();
            j=i+1;
            i=str.indexOf(',',j);
            childObject=str.substring(j,i).trim();

            strTestListString.setObjectNumber(num);
            strTestListString.setСategory(category);
            strTestListString.setРarentObject(parentObject);
            strTestListString.setСhildObject(childObject);

            System.out.println(TESTLIST_SUCCESSFULLY_PARSED+strTestListString.getTestListLine());
            Logger.setLog(TESTLIST_SUCCESSFULLY_PARSED+strTestListString.getTestListLine());

        return strTestListString;
    }
}
