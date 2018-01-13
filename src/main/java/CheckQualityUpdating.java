import java.util.ArrayList;
import static stringconstant.LoggerMessages.*;

/**
 * Created by Poliakov.A on 1/12/2018.
 */
public class CheckQualityUpdating {

    public void getUnupdatedObjects(ArrayList<TestListString>testList, ArrayList<String> updetedObjects){

        if (updetedObjects.size()==testList.size()){
            System.out.println(ALL_OBJECTS_UPDATED);
            Logger.setLog(ALL_OBJECTS_UPDATED);
        }
        else if(updetedObjects.size()<testList.size()){
            System.out.println(SEPARATOR);
            Logger.setLog(SEPARATOR);
            int count=0;
            int i=0;

            while (testList.size()>i){
                int j=0;
                while (true){
                    if (testList.get(i).getObjectName().equalsIgnoreCase(updetedObjects.get(j)) && updetedObjects.size()!=0){
                        break;
                    }
                    j++;
                    if(j>=updetedObjects.size()){
                        System.out.println(OBJECTS_WAS_NOT_UPDATED + testList.get(i).getTestListLine());
                        Logger.setLog(OBJECTS_WAS_NOT_UPDATED + testList.get(i).getTestListLine());
                        count++;
                        break;
                    }
                }
                i++;
            }
            if (count==0){
                System.out.println(DUPLICATED_OBJRCTS_IN_TESTLIST);
                Logger.setLog(DUPLICATED_OBJRCTS_IN_TESTLIST);
            }
            System.out.println(SEPARATOR);
            Logger.setLog(SEPARATOR);
        }
        else {
            System.out.println(NUMRER_UPDATED_OBJECTS_MORE_THEN_TEST_LIST);
            Logger.setLog(NUMRER_UPDATED_OBJECTS_MORE_THEN_TEST_LIST);
        }

    }
}
