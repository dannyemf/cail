/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.test;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class SpCat implements Serializable{
    
    private Long id;
    private String raza;

    public SpCat() {
    }

    @Override
    public String toString() {
        return "{id: "+id+"; raza: "+raza+"}";
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

    /**
     * @return the raza
     */
    public String getRaza() {
        return raza;
    }

    /**
     * @param raza the raza to set
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }
}
