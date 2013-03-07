/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.test;

import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 *
 * @author Usuario
 */
public class Cat extends DomainEntity{
    
    private String raza;

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

    @Override
    public boolean equals(Object obj) {                
        
        if(obj != null && obj instanceof Cat){
            Cat ot = (Cat)obj;
            if(ot.getId().longValue() == this.getId().longValue()){
                return true;
            }
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "id: "+getId() + ", raza: " + raza;
    }
    
    
    
}
