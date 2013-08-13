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
 *
 * @author Usuario
 */
public class KPropertyGroup extends KProperty{       

    private boolean and = true;
    
    private List<KProperty> propiedades = new ArrayList<KProperty>();
    
    public KPropertyGroup(boolean and, KProperty... propiedades) {
        this.propiedades = Arrays.asList(propiedades);
        this.and = and;
    }
    
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
