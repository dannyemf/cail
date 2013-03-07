/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.excepcion;

/**
 *
 * @author Usuario
 */
public class LoginException extends Exception{

    public LoginException() {
        super("El nombre de usuario o clave son incorrectos");
    }    
}
