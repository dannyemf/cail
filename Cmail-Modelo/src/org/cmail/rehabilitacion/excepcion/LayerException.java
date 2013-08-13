/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.excepcion;

/**
 * Clase de excepción personalizada para la lógica de negocio.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class LayerException extends Exception{

    private String errorCode;
    
    public LayerException(String errorCode) {
        super(errorCode);
        
        this.errorCode = errorCode;
    }    

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    
}
