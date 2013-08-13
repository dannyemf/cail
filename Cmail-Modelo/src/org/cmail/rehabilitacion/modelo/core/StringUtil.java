/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core;

/**
 * Clase de utilidad para tratar cadenas.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class StringUtil {
    
    /**
     * Verifica si una cadena es nula o vacía eliminando espacios iniciales y finales.
     * 
     * @param cadena la cedena
     * @return true si es nula o vacía
     */
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
    
    /**
     * Verifica si todas las cadenas son nulas o vacías.
     * Si por lo menos una cadena no es vacía retorna false.
     * 
     * @param cadenas las cadenas
     * @return true si es verdad
     */
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
