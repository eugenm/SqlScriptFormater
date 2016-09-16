/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.other;

import de.aeb.sqlscriptformater.SqlScriptFormater;
import java.io.File;

/**
 *
 * @author Eugen
 */
public class SFMainTest {

    public static void main(String[] args) {
        // test printing
        SqlScriptFormater p = new SqlScriptFormater();
        File f = new File("create.sql");
        p.setOpFile(f);
        p.printFile();

    }

    public static void main1(String[] args) {
        //test logging
        String[] arg = {"-r", "_lut", "create.sql"};
        SqlScriptFormater p = new SqlScriptFormater(arg, false);
        p.run();

    }
}
