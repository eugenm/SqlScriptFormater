/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.other;

/**
 *
 * @author Eugen
 */
public class StringTest {

    public static void main(String[] args) {
//        String opStr = "_lut";
//        System.out.println("LowerCase: " + opStr.toLowerCase() + "\t UpperCase: " + opStr.toUpperCase());

        String USAGE = "-------------------------------------------------------------------------------\n"
                + "Usage: java -jar sqlsf.jar OPERATION OPSTRING FILE/PATH [OPTIONALS]\n"
                + "\t\tOPERATION:\t-r/-remove | -a/-add | -e/-exists\n"
                + "\t\tOPSTRING:\tex. \"_lut\"\n"
                + "\t\tFILE/PATH:\tex.\"create.sql\" or \"C:\\scripts\\create.sql\"\n"
                + "\t\t[OPTIONALS]:\t-h (add HEADER) | -f (replace Generated Files)\n"
                + "-------------------------------------------------------------------------------\n";
        System.out.println(USAGE);

//        String HEADER = "--##############################################\n" + "--### File is generated by SQLScriptFormater ###\n" + "--##############################################\n \n";
//        System.out.println(HEADER);
    }
}
