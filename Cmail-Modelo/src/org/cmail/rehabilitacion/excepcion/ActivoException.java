/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.excepcion;

/**
 *
 * @author Usuario
 */
public class ActivoException extends Exception{

    public ActivoException() {
        super("Lo sentimos, su cuenta está inactiva");
    }    
}
