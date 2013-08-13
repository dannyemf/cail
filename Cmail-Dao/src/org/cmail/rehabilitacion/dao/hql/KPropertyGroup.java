/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.hql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa una agrupación de propiedades mediante and u or (Framework K).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class KPropertyGroup extends KProperty{       

    /**
     * Indica si se debe agrupar con and (true) o con or (false)
     */
    private boolean and = true;
    
    /**
     * Lista de propiedades a agrupar
     */
    private List<KProperty> propiedades = new ArrayList<KProperty>();
    
    public KPropertyGroup(boolean and, KProperty... propiedades) {
        this.propiedades = Arrays.asList(propiedades);
        this.and = and;
    }
    
    /**
     * Genera el hql de esta propiedad.
     * 
     * @param parametros lista de parámetros
     * @return hql generado
     */
    public String toHql(List<Object> parametros){        
        String s = "(";              
        
        int i = 0;
        for (Iterator<KProperty> it = propiedades.iterator(); it.hasNext();) {
            KProperty kv = it.next();
            if(i > 0){
                s += and ? " and " : " or ";                        
            }
            s += kv.toHql(parametros);
            
            i++;
        }
        
        s += ")";
        
        return s;
    }

    /**
     * @return the and
     */
    public boolean isAnd() {
        return and;
    }

    /**
     * @param and the and to set
     */
    public void setAnd(boolean and) {
        this.and = and;
    }

    /**
     * @return the propiedades
     */
    public List<KProperty> getPropiedades() {
        return propiedades;
    }

    /**
     * @param propiedades the propiedades to set
     */
    public void setPropiedades(List<KProperty> propiedades) {
        this.propiedades = propiedades;
    }
    
    
}
