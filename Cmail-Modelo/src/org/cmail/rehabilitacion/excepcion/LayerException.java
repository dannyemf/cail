/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.excepcion;

/**
 *
 * @author Usuario
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
