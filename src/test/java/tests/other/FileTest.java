/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.other;

import java.io.File;

/**
 *
 * @author Eugen
 */
public class FileTest {
    
    public static void main1(String[] args) {
        
        File f = new File("./create.sql");
        System.out.println(f.exists());
        String[] files = f.list();
        
        for (String s : files) {
            System.out.println(s);
        }
    }
    
    public static void main(String[] args) {
        
        File f = new File("create.sql");
        String filepath = f.getAbsolutePath();
        String oldname = f.getName();
        String newname = "generated_" + f.getName();
        System.out.println(filepath.replace(oldname, newname));
        
    }
    
}
