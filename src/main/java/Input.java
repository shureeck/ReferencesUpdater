import java.util.Scanner;

public class Input {
    public String input(String outputString){
        String inputString=null;
                System.out.println(outputString);
        Scanner scanner=null;
        try{
            scanner = new Scanner(System.in);
            inputString=scanner.nextLine().trim();
            System.out.println("Done: Line was successfully read");
             if(inputString.equalsIgnoreCase("") || inputString==null){
                 System.out.println("Warning: The string is empty");
                 inputString=null;
             }
        }
        catch (Exception e){
           System.out.println("Error: Could not read line");
           e.printStackTrace();
        }
       /* finally {
            scanner.close();
        }*/
        return inputString;
    }
}
