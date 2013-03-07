/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class AuditEntity extends DomainEntity{
    
    private Date auditCreateDate;
    private Date auditUpdateDate;
    private String auditCreateUser;
    private String auditUpdateUser;

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
