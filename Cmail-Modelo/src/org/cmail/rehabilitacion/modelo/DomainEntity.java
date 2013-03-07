/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public abstract class DomainEntity implements Serializable {
    
    private Long id = -1L;    

    public DomainEntity() {        
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getId().toString();
    }
    
    
    
}
