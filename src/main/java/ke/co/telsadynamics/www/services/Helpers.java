/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.services;

/**
 *
 * @author tracom9
 */
public class Helpers {
    public static boolean isStringInt(String s) {
        try {
            int a = Integer.parseInt(s);
            return a == (int) a;         
        } catch (NumberFormatException ex) {
            return false;
        }
    }    
}
