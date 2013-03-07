/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.excepcion;

/**
 *
 * @author Usuario
 */
public class ClaveException extends Exception{

    public ClaveException() {
        super("El nombre de usuario o clave son incorrectos");
    }    
}
