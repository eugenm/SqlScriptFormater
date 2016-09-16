/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import de.aeb.sqlscriptformater.OperationsEnum;
import de.aeb.sqlscriptformater.SqlScriptFormater;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eugen
 */
public class RemoveOnDifferentSqlsTest {

    SqlScriptFormater sf;
    File sqlfile;

    public static void deleteGeneratedTestFiles() {
        File file1 = new File(".\\testsqls\\generated_create1.sql");
        File file2 = new File(".\\testsqls\\generated_create2.sql");
        File file3 = new File(".\\testsqls\\generated_create3.sql");
        File file4 = new File(".\\testsqls\\generated_create4.sql");
        File file5 = new File(".\\testsqls\\generated_create5.sql");
        File file6 = new File(".\\testsqls\\generated_create6.sql");
        try {
            if (file1.exists()) {
                file1.delete();
            }
            if (file2.exists()) {
                file2.delete();
            }
            if (file3.exists()) {
                file3.delete();
            }
            if (file4.exists()) {
                file4.delete();
            }
            if (file5.exists()) {
                file5.delete();
            }
            if (file6.exists()) {
                file6.delete();
            }
        } catch (Exception e) {
            System.out.println("Before failed to delete test files");
        }
    }

    @BeforeClass
    public static void setUpClass() {
        deleteGeneratedTestFiles();
    }

    @AfterClass
    public static void tearDownClass() {
        deleteGeneratedTestFiles();
    }

    @Before
    public void setUp() {
        sf = new SqlScriptFormater();
        sf.setOp(OperationsEnum.REMOVE);
        sf.setOpStr("_lut");
    }

    // Tests    
    @Test
    public void removeOnFile1() {
        sqlfile = new File(".\\testsqls\\create1.sql");
        sf.setOpFile(sqlfile);
        boolean b = sf.run();
        assertTrue(b);
    }

    @Test
    public void removeOnFile2() {
        sqlfile = new File(".\\testsqls\\create2.sql");
        sf.setOpFile(sqlfile);
        boolean b = sf.run();
        assertTrue(b);
    }

    @Test
    public void removeOnFile3() {
        sqlfile = new File(".\\testsqls\\create3.sql");
        sf.setOpFile(sqlfile);
        boolean b = sf.run();
        assertTrue(b);
    }

    @Test
    public void removeOnFile4() {
        sqlfile = new File(".\\testsqls\\create4.sql");
        sf.setOpFile(sqlfile);
        boolean b = sf.run();
        assertTrue(b);
    }

    @Test
    public void removeOnFile5() {
        sqlfile = new File(".\\testsqls\\create5.sql");
        sf.setOpFile(sqlfile);
        boolean b = sf.run();
        assertTrue(b);
    }

    @Test
    public void removeOnFile6() {
        sqlfile = new File(".\\testsqls\\create6.sql");
        sf.setOpFile(sqlfile);
        boolean b = sf.run();
        assertTrue(b);
    }
}
