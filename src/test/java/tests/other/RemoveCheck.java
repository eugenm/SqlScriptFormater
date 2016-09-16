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
public class RemoveCheck {

    String colStr;

    public RemoveCheck(String colStr) {
        this.colStr = colStr;
    }

    public boolean check(String toCheck) {
        return toCheck.contains(colStr);
    }

}
