/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.exception;

import org.hibernate.CallbackException;

/**
 *
 * @author Usuario
 */
public class AuditException extends CallbackException{

    public AuditException(Exception root) {
        super(root);
    }

    public AuditException(String message) {
        super(message);
    }

    public AuditException(String message, Exception e) {
        super(message, e);
    }
        
}
