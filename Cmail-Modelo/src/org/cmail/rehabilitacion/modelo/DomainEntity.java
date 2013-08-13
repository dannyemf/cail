/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo;

import java.io.Serializable;

/**
 * Clase base del dominio. De ésta clase debe heredar toda clase del dominio.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public abstract class DomainEntity implements Serializable {
    
    /**
     * El id o la clave primaria
     */
    private Long id = -1L;    

    /**
     * Constructor por defecto
     */
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
