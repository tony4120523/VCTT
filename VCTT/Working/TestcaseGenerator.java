import java.io.*;

public class TestcaseGenerator {
    public static void main(String[] args) throws IOException {

        System.out.printf("import static org.junit.Assert.assertEquals;\n");
        System.out.printf("import org.junit.Test;\n");
        System.out.printf("public class %sTest {\n", args[0]);

        try {
            //read input
            TestcaseGenerator gen = new TestcaseGenerator();
            while(System.in.available()!=0){
                gen.generate_test();
            }
        }
        catch(IOException e) {
            System.out.println("Error");
        }

        System.out.printf("}\n");
    }

    public void generate_test() throws IOException {
        
        String test_id, class_name, method_name, expected_result, object_name, expected_type;
        String[] input;
        int c, flag = 0, i = 0, tmp = 0;
        char ch;

        try {
            test_id = new String();
            class_name = new String();
            method_name = new String();
            expected_type = new String();
            expected_result = new String();
            input = new String[]{"","","","","","","","","",""};

            while ((c = System.in.read()) != -1) {
                if(tmp == 10 && c == 10)  break;
                tmp = c;

                ch = (char)c;
                if(ch == ' ')  continue;
                if(ch == ':' || ch == '\n')  {
                    flag++;
                    continue;
                }
                switch(flag) {
                    case 1 :
                        test_id += ch;
                        break;
                    case 3 :
                        class_name += ch;
                        break;
                    case 5 :
                        method_name += ch;
                        break;
                    case 7 :
                        if(ch == ',') {
                            i++;
                            continue;
                        }
                        input[i] += ch;
                        break;
                    case 9 :
                        expected_type += ch;
                        break;
                    case 11 :
                        expected_result += ch;
                        break;
                }
            }
            object_name = class_name.toLowerCase();


            //Junit test file name depend on test class name
            System.out.printf("    @Test\n");
            System.out.printf("    public void test%s() {\n", test_id);

            System.out.printf("        %s %s = new %s(", class_name, object_name, class_name);
            for(int j=0; j<=i; j++) {
                System.out.printf("%s", input[j]);
                if(j != i)  System.out.print(",");
            }
            System.out.printf(");\n");

            if("String".equals(expected_type)) {
                System.out.printf("        %s expected_result = \"%s\";\n", expected_type, expected_result);
            }
            else {
                System.out.printf("        %s expected_result = %s;\n", expected_type, expected_result);
            }

            System.out.printf("        assertEquals(expected_result, %s.%s());\n", object_name, method_name);
            System.out.printf("    }\n");
        }
        catch(IOException e) {
            System.out.println("Error");
        }
    }
}
 
