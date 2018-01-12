import java.util.Scanner;

public class Input {
    public String input(String outputString){
        String inputString=null;
                System.out.println(outputString);
        Scanner scanner=null;
        try{
            scanner = new Scanner(System.in);
            inputString=scanner.nextLine().trim();
            inputString=inputString.replaceAll("\\u202A", "");
            System.out.println("Done: Line was successfully read");
            Logger.setLog("Done: Line was successfully read");

             if(inputString.equalsIgnoreCase("") || inputString==null){
                 System.out.println("Warning: The string is empty");
                 Logger.setLog("Warning: The string is empty");

                 inputString=null;
             }
        }
        catch (Exception e){
           System.out.println("Error: Could not read line");
           Logger.setLog("Error: Could not read line"+"\n"+e.toString());
           Logger.setLog(e.getStackTrace().toString());
        }
       /* finally {
            scanner.close();
        }*/
        return inputString;
    }
}
