/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.excepcion;

/**
 * Clase de excepción lanzada cuando la clave no es correcta.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class ClaveException extends Exception{

    public ClaveException() {
        super("El nombre de usuario o clave son incorrectos");
    }    
}
