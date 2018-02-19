import java.util.ArrayList;
import static stringconstant.LoggerMessages.*;

/**
 * Created by Poliakov.A on 1/12/2018.
 */
public class CheckQualityUpdating {

    public void getUnupdatedObjects(ArrayList<TestListString>testList, ArrayList<String> updetedObjects){

        if (updetedObjects.size()==testList.size()){
            Logger.setLog(ALL_OBJECTS_UPDATED);
        }
        else if(updetedObjects.size()<testList.size()){
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
                        Logger.setLog(OBJECTS_WAS_NOT_UPDATED + testList.get(i).getTestListLine());
                        count++;
                        break;
                    }
                }
                i++;
            }
            if (count==0){
                Logger.setLog(DUPLICATED_OBJRCTS_IN_TESTLIST);
            }
            Logger.setLog(SEPARATOR);
        }
        else {
            Logger.setLog(NUMRER_UPDATED_OBJECTS_MORE_THEN_TEST_LIST);
        }

    }
}
