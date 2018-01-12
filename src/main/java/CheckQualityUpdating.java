import java.util.ArrayList;

/**
 * Created by Poliakov.A on 1/12/2018.
 */
public class CheckQualityUpdating {
    public void getUnupdatedObjects(ArrayList<TestListString>testList, ArrayList<String> updetedObjects){

        if (updetedObjects.size()==testList.size()){
            System.out.println("Done: All specified objects from test list were updated");
            Logger.setLog("Done: All specified objects from test list were updated");
        }
        else if(updetedObjects.size()<testList.size()){
            System.out.println("-----------------------------------------------------------------------------");
            Logger.setLog("-----------------------------------------------------------------------------");
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
                        System.out.println("Warning: Objects was not updated: " + testList.get(i).getTestListLine());
                        Logger.setLog("Warning: Objects was not updated: " + testList.get(i).getTestListLine());
                        count++;
                        break;
                    }
                }
                i++;
            }
            if (count==0){
                System.out.println("Warning: Test list contain duplicate objects. Check test list please!");
                Logger.setLog("Warning: Test list contain duplicate objects. Check test list please!");
            }
            System.out.println("-----------------------------------------------------------------------------");
            Logger.setLog("-----------------------------------------------------------------------------");
        }
        else {
            System.out.println("Error: The number of updated objects is large then number of objects in test list");
            Logger.setLog("Error: The number of updated objects is large then number of objects in test list");
        }

    }
}
