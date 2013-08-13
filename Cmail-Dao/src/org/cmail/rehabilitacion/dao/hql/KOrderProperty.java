/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.hql;

/**
 * Propiedad de ordeación usada en la clausula order by de una sentencia hql (Framework K).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class KOrderProperty {
    
    /**
     * Nombre de la propiedad
     */
    private String key;
    
    /**
     * Indica si se debe ordenar ascendentemente (por defecto) o descendentemente (false).
     */
    private boolean asc;   

    /**
     * Constructor mínimo con ordenación por defecto (ascendente).
     * 
     * @param key nombre de la propiedad
     */
    public KOrderProperty(String key) {
        this.key = key;
        this.asc = true;
    }
    
    /**
     * Constructor completo.
     * 
     * @param key la propiedad
     * @param asc true para ascendente y false para descendente
     */
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
