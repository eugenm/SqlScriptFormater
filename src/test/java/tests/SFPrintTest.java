/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

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
public class SFPrintTest {

    @Test
    public void testPrintFileInDifferentPath() {
        SqlScriptFormater p = new SqlScriptFormater();
        File f = new File(".\\testsqls\\create1.sql");
        p.setOpFile(f);
        p.printFile();
    }
}
