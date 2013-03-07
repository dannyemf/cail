/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.hql;

/**
 *
 * @author Usuario
 */
public class KOrderProperty {
    
    private String key;
    private boolean asc;   

    public KOrderProperty(String key) {
        this.key = key;
        this.asc = true;
    }
    
    public KOrderProperty(String key, boolean asc) {
        this.key = key;
        this.asc = asc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }
    
    
            
    
    
}
