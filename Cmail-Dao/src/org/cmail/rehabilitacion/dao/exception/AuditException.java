/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.exception;

import org.hibernate.CallbackException;

/**
 * Clase de excepción que se debe lanzar cuando falle la auditoría de una entidad.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class AuditException extends CallbackException{

    /**
     * Constructor básico
     * @param root la excepción original
     */
    public AuditException(Exception root) {
        super(root);
    }

    /**
     * Constructor con mensaje personalizado
     * @param message el mensaje personalizado
     */
    public AuditException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje personalizado y excepción original
     * @param message mensaje personalizado
     * @param root excepción original
     */
    public AuditException(String message, Exception root) {
        super(message, root);
    }
        
}
