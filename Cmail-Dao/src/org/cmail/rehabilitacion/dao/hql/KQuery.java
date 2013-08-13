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
import org.hibernate.Query;

/**
 * Clase que representa una consulta de una entidad específica (Framework K).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class KQuery<T extends DomainEntity> implements IKResult{
    
    /**
     * La clase genérica
     */
    private Class<T> clase;    
    
    /**
     * La clausula where
     */
    private KWhere<T> where;
    
    /**
     * La clausula order by
     */
    private KOrder<T> order;    
    
    /**
     * Listado de parámetros obtenidas de las propiedades
     */
    private List<Object> parametros;
    
    /**
     * Número máximo de resultados que debe arrojar la consulta, por defecto null.
     */
    private Integer maxResults = null;
    
    /**
     * Número de fila del primer resultado.
     */
    private Integer firstResult = null;
    
    /**
     * Constructor mínimo con la clase genérica.
     * @param clase la clase
     */
    private KQuery(Class<T> clase) {
        this.clase = clase;
    }        
    
    /**
     * Crea una consulta de la clase indicada.
     * @param <T> el tipo genérico
     * @param clase la clase
     * @return la consulta (KQuery)
     */
    public static <T extends DomainEntity> KQuery<T> from(Class<? extends T> clase){
        return new KQuery(clase);
    }        
    
    /**
     * Crea la parte where de una consulta con las propiedades indicadas.
     * 
     * @param propiedades lista de pripiedades
     * @return la parte where de la consulta (KWhere)
     */
    public KWhere<T> where(KProperty... propiedades){
        where = new KWhere<T>(this, propiedades);
        return where;
    }
    
    /**
     * Crea la parte order by de una consulta con el listado de propiedades indicada.
     * @param properties la lista de propiedades a ordenar
     * @return la parte order by (KOrder)
     */
    public KOrder<T> orderBy(KOrderProperty... properties){
        order = new KOrder<T>(this, properties);
        return order;
    }
    
    /**
     * Fija el número máximo de resultados que debe retornar la consulta
     * @param maxResults el número máximo de resultados
     * @return el mismo objeto consulta (KQuery)
     */
    public KQuery<T> maxResults(Integer maxResults){
        this.maxResults = maxResults;
        return this;
    }
    
    /**
     * Fija el número de fila del primer resultado
     * @param firstResult el número de fila
     * @return el mismo objeto consulta (KQuery)
     */
    public KQuery<T> firstResult(Integer firstResult){
        this.firstResult = firstResult;
        return this;
    }
    
    /**
     * Genera el hql de la consulta. Ejemplo: from Usuario where login = 'juan'
     * @return cadena hql generada
     */
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
    
    /**
     * Crea una consulta (Query) de hibernate
     * @param hql el hql
     * @return el objeto Query
     */
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
    
    /**
     * Obtiene la lista de entidades de esta consulta.
     * @return lista de entidades
     */
    public List<T>list(){
        String hql = toHql();
        Query q = createHibernateQuery(hql);
        return q.list();
    }
    
    /**
     * Obtiene una entidad única de esta consulta.
     * @return la entidad o null
     */
    public T unique(){
        String hql = toHql();
        Query q = createHibernateQuery(hql);
        return (T)q.uniqueResult();
    }
    
    /**
     * Obtiene el primer resultado de ésta consulta.
     * @return la entidad o null
     */
    public T first(){
        String hql = toHql();
        Query q = createHibernateQuery(hql);
        q.setMaxResults(1);        
        List<T> l = q.list();
        return l.isEmpty() ? null : l.get(0);
    }
    
    /**
     * Hace un conteo del número de filas que arroja la consulta
     * @return el número de filas
     */
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
