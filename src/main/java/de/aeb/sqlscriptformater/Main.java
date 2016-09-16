/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.aeb.sqlscriptformater;

import java.io.File;

/**
 *
 * @author Eugen
 */
public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            SqlScriptFormater sfArgs = new SqlScriptFormater(args, false);
            sfArgs.run();
            sfArgs.printStatus();
            if (!sfArgs.isSuccess()) {
                sfArgs.printErrors();
            }
        } else {
            SqlScriptFormater.printUsage();
        }
    }
}
