/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo;

import java.util.Date;

/**
 * Clase base del dominio que es auditable, es decir toda clase que debe manejar auditoría debe heredad de ésta clase.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class AuditEntity extends DomainEntity{
    
    /**
     * Fecha de creación de la entidad
     */
    private Date auditCreateDate;
    
    /**
     * La última fecha de actualización de la entidad
     */
    private Date auditUpdateDate;
    
    /**
     * Usuario que creó la entidad
     */
    private String auditCreateUser;
    
    /**
     * El último usuario que hizo una modificación de la entidad
     */
    private String auditUpdateUser;

    /**
     * Constructor por defecto
     */
    public AuditEntity() {
    }

    /**
     * @return the auditCreateDate
     */
    public Date getAuditCreateDate() {
        return auditCreateDate;
    }

    /**
     * @param auditCreateDate the auditCreateDate to set
     */
    public void setAuditCreateDate(Date auditCreateDate) {
        this.auditCreateDate = auditCreateDate;
    }

    /**
     * @return the auditUpdateDate
     */
    public Date getAuditUpdateDate() {
        return auditUpdateDate;
    }

    /**
     * @param auditUpdateDate the auditUpdateDate to set
     */
    public void setAuditUpdateDate(Date auditUpdateDate) {
        this.auditUpdateDate = auditUpdateDate;
    }

    /**
     * @return the auditCreateUser
     */
    public String getAuditCreateUser() {
        return auditCreateUser;
    }

    /**
     * @param auditCreateUser the auditCreateUser to set
     */
    public void setAuditCreateUser(String auditCreateUser) {
        this.auditCreateUser = auditCreateUser;
    }

    /**
     * @return the auditUpdateUser
     */
    public String getAuditUpdateUser() {
        return auditUpdateUser;
    }

    /**
     * @param auditUpdateUser the auditUpdateUser to set
     */
    public void setAuditUpdateUser(String auditUpdateUser) {
        this.auditUpdateUser = auditUpdateUser;
    }
    
}
