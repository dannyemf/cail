/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core;

/**
 *
 * @author Usuario
 */
public class StringUtil {
    
    public static boolean isNullOrEmpty(String cadena){        
        
        if(cadena == null){
            return true;
        }else{
            if(cadena.trim().length() == 0){
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean isNullOrEmpty(String... cadenas){
        int cn = 0;
        for (String c : cadenas) {
            if(c == null){
                cn += 1;
            }else{
                if(c.trim().length() == 0){
                    cn += 1;
                }
            }
        }
        
        if(cn == cadenas.length){
            return true;
        }else{
            return false;
        }                
    }
    
}
