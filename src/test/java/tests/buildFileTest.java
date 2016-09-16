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
public class buildFileTest {

    SqlScriptFormater sf;
    File sqlfile;

    public static void deleteGeneratedTestFile() {
        File file = new File("generated_create.sql");
        try {
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            System.out.println("Before failed to delete test file");
        }
    }

    @BeforeClass
    public static void setUpClass() {
        deleteGeneratedTestFile();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        sf = new SqlScriptFormater();
        sf.setOp(OperationsEnum.REMOVE);
        sf.setOpStr("_lut");
    }

    @After
    public void tearDown() {
    }

//    @Test
    public void testBuildFileWithHeader() {
        sqlfile = new File("create.sql");
        sf.setOpFile(sqlfile);
        boolean b = sf.run();

        assertTrue(b);
    }

    @Test
    public void testBuildFileWithOutHeader() {
        sqlfile = new File("create.sql");
        sf.setOpFile(sqlfile);
        sf.buildWithoutHeader();
        boolean b = sf.run();

        assertTrue(b);
    }
}
