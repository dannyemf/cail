/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.hql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 *
 * @author Usuario
 */
public class KOrder<T extends DomainEntity> implements IKResult{            

    private List<KOrderProperty> propiedades = new ArrayList<KOrderProperty>();
    private KQuery<T> query;
            
    public KOrder(KQuery<T> query, KOrderProperty... properties) {
        this.propiedades.addAll(Arrays.asList(properties));
        this.query = query;
    }
    
    public String toHql(){
        String s = propiedades.isEmpty() ? "" : " Order By " ;
        
        for (Iterator<KOrderProperty> it = propiedades.iterator(); it.hasNext();) {
            KOrderProperty p = it.next();
            s += p.getKey() + (p.isAsc() ? " asc" : " desc");
            if(it.hasNext()){
                s += ", ";
            }
        }        
        return s;
    }

    public List<T> list() {
        return query.list();
    }

    public T unique() {
        return query.unique();
    }

    public T first() {
        return query.first();
    }
    
    public Long count(){
        return query.count();
    }
    
    
    
    
}
