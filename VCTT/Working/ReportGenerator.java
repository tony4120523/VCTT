import java.util.Scanner;
import java.io.*;

public class ReportGenerator {
    public static void main(String[] args) throws IOException {

        try {
            File result = new File("junit_result.txt");
            Scanner scan = new Scanner(result);
            String line = new String();
            String class_name = args[0];
            String[] eid = new String[100];
            String[] emethod = new String[100];
            int i = 0, j = 0, ecount = 0;

            while(scan.hasNext()) {
                line = scan.nextLine();
                if(line.indexOf("There w") != -1) {
                    System.out.println(line + "\n");
                }
                else if(line.indexOf("(" + class_name + "Test)") != -1) {
                    eid[i++] = line.substring(line.indexOf(")")+1, line.indexOf("(")).trim().substring(4);
                    ecount++;
    //System.out.println(";" + eid[i-1] + ";");
                    System.out.println(line);
                }
                else if(line.indexOf("expected") != -1) {
                    System.out.println(line + "\n");
                }
            }

            File def = new File(class_name + ".txt");
            Scanner sc = new Scanner(def);
            while(sc.hasNext()) {
                line = sc.nextLine();
                if(line.indexOf("Test id")!=-1 && line.substring(line.indexOf(":")+1).trim().equals(eid[j])) {
    //System.out.println("OBOV  ;" + eid[0] + ";   ;" + line.substring(line.indexOf(":")+1).trim() + ";");
    
                    sc.nextLine();
                    line = sc.nextLine();
                    emethod[j++] = line.substring(line.indexOf(":")+1).trim();
                }
            }

            File table = new File("table.txt");
            Scanner scanner = new Scanner(table);
            while(scanner.hasNext()) {
                
                line = scanner.nextLine();
                if(line.indexOf("Email") != -1)  continue;
                if(line.indexOf("---") != -1)  continue;
                int k = 0;
                String[] tvar = new String[]{"", "", "", ""};
                while(line.indexOf("|") != -1) {
                    tvar[k++] = line.substring(0, line.indexOf("|")).trim();
                    line = line.substring(line.indexOf("|")+1);
                }
                tvar[k] = line.trim();  //Email
                for(k=0; k<ecount; k++) {
                    if(class_name.equals(tvar[0]) && emethod[k].equals(tvar[1])) {
                        System.out.println("Class : " + tvar[0]);
                        System.out.println("Method : " + tvar[1] + " , failure in test" + eid[k]);
                        System.out.println("Programmer : " + tvar[2]);
                        System.out.println("Email : " + tvar[3] + "\n");
                    }
                }
    //System.out.println(scanner.nextLine());
            }
            
        }
        catch(IOException e) {
            System.out.println("IO Exception");
        }
    }
}
