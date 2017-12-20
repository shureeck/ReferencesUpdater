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

        try {
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
            System.out.println("Done: TestList string was successfully parsed"+strTestListString.getTestListLine());
        }//try
        catch(NumberFormatException e){
            System.out.println("Error: Object Number has invalid format. Please check TestListString");
            System.out.println(e);
        }//catch

        return strTestListString;
    }
}
