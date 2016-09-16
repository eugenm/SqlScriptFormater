/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import de.aeb.sqlscriptformater.OperationsEnum;
import de.aeb.sqlscriptformater.SqlScriptFormater;
import java.io.File;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Eugen
 */
public class SFTest {

    @Before
    public void setup() {
        File file = new File("generated_create.sql");
        try {
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            System.out.println("Before failed to delete test file");
        }
    }

    @Test
    public void testPrintFile() {
        SqlScriptFormater p = new SqlScriptFormater();
        File sqlFile = new File("create.sql");
        p.setOpStr("_lut");
        p.setOpFile(sqlFile);

        assertTrue(p.printFile());
    }

    @Test
    public void testRunOnFile() {
        SqlScriptFormater p = new SqlScriptFormater();
        File f = new File("create.sql");

        assertTrue(p.runOnFile(f, OperationsEnum.REMOVE, "_lut"));
    }

    @AfterClass
    public static void cleanup() {
        File file = new File("generated_create.sql");
        try {
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            System.out.println("AfterClass failed to delete test file");
        }
    }
}
