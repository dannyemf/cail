/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.excepcion;

/**
 * Clase de excepción lanzada cuando un usuario no está activo.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class ActivoException extends Exception{

    public ActivoException() {
        super("Lo sentimos, su cuenta está inactiva");
    }    
}
