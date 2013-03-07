/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.hql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 *
 * @author Usuario
 */
public class KWhere<T extends DomainEntity> implements IKResult<T> {
    
    private KQuery<T> query;
    private List<KProperty> propiedades = new ArrayList<KProperty>();    
    
    public KWhere(KQuery<T> query, KProperty... k) {
        propiedades.addAll(Arrays.asList(k));
        this.query = query;
    }   
    
    public String toHql(List<Object> parametros){
        
        try {            
            String where = "";
            
            int i = 0;
            for (KProperty it : propiedades) {
                if(i > 0){
                    where += it.isAnd() ? " and " : " or ";
                }
                where +=  it.toHql(parametros);
                i++;
            }                                                
            
            return where.length() == 0 ? "" : " Where " + where;
        } catch (Exception e) {
            return "";
        }
        
    }
    
    public KOrder<T> orderBy(KOrderProperty... properties){
        return query.orderBy(properties);        
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
