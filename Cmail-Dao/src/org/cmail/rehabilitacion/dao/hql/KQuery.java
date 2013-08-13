/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.hql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.cmail.rehabilitacion.dao.HibernateSessionFactory;
import org.cmail.rehabilitacion.modelo.DomainEntity;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.hibernate.Query;

/**
 *
 * @author Usuario
 */
public class KQuery<T extends DomainEntity> implements IKResult{
    
    private Class<T> clase;    
    private KWhere<T> where;
    private KOrder<T> order;    
    private List<Object> parametros;
    
    private Integer maxResults = null;
    private Integer firstResult = null;
    
    private KQuery(Class<T> clase) {
        this.clase = clase;
    }        
    
    public static <T extends DomainEntity> KQuery<T> from(Class<? extends T> clase){
        return new KQuery(clase);
    }        
    
    public KWhere<T> where(KProperty... propiedades){
        where = new KWhere<T>(this, propiedades);
        return where;
    }
    
    public KOrder<T> orderBy(KOrderProperty... properties){
        order = new KOrder<T>(this, properties);
        return order;
    }
    
    public KQuery<T> maxResults(Integer maxResults){
        this.maxResults = maxResults;
        return this;
    }
    
    public KQuery<T> firstResult(Integer firstResult){
        this.firstResult = firstResult;
        return this;
    }
    
    
    
    private String toHql(){
        
        parametros = new ArrayList<Object>();
        
        String h = "from " + clase.getSimpleName();
        
        if(where != null){            
            h += where.toHql(parametros);
        }
        
        if(order != null){            
            h += order.toHql();
        }
        
        return h;
    }
    
    private Query createHibernateQuery(String hql){       
        
        System.out.println(hql);
        
        HibernateSessionFactory.getSession().clear();
        
        Query q = HibernateSessionFactory.getSession().createQuery(hql);
        
        int i = 0;
        for (Iterator<Object> it = parametros.iterator(); it.hasNext();) {
            Object prm = it.next();
            q.setParameter(i, prm);            
            i++;
        }
        
        if(maxResults != null){
            q.setMaxResults(maxResults);
        }
        
        
        if(firstResult != null){
            q.setFirstResult(firstResult);
        }
        
        return q;
    }   
    
    public List<T>list(){
        String hql = toHql();
        Query q = createHibernateQuery(hql);
        return q.list();
    }
    
    public T unique(){
        String hql = toHql();
        Query q = createHibernateQuery(hql);
        return (T)q.uniqueResult();
    }
    
    public T first(){
        String hql = toHql();
        Query q = createHibernateQuery(hql);
        q.setMaxResults(1);        
        List<T> l = q.list();
        return l.isEmpty() ? null : l.get(0);
    }
    
    public Long count(){
        String hql = "select count(*) " + toHql();
        Query q = createHibernateQuery(hql);
        return (Long)q.uniqueResult();
    }   
    
    public Class<T> getClase() {
        return clase;
    }

    public void setClase(Class<T> clase) {
        this.clase = clase;
    }
    
    
}
