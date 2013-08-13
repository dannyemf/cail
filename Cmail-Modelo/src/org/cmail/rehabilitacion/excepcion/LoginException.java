/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.excepcion;

/**
 * Clase de excepción lanzada cuando el login no es correcto.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class LoginException extends Exception{

    public LoginException() {
        super("El nombre de usuario o clave son incorrectos");
    }    
}
