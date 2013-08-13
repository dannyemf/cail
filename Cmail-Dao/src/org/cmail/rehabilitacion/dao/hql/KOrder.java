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
 * Clase que representa la clausula order by de una consulta (Framework K).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class KOrder<T extends DomainEntity> implements IKResult{            

    /**
     * La consulta actual
     */
    private KQuery<T> query;
    
    /**
     * Lista de propiedades de ordenación
     */
    private List<KOrderProperty> propiedades = new ArrayList<KOrderProperty>();        
    
    /**
     * Constructor mínimo.
     * 
     * @param query la consulta sobre la que opera el order by
     * @param properties lista de propiedades de ordenación
     */
    public KOrder(KQuery<T> query, KOrderProperty... properties) {
        this.propiedades.addAll(Arrays.asList(properties));
        this.query = query;        
    }
    
    /**
     * Genera el hql de la parte order by
     * @return cadena hql generada
     */
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

    /**
     * Obtiene la lista de entidades de la consulta
     * @return lista de entidades
     */
    public List<T> list() {
        return query.list();
    }

    /**
     * Obtiene una entidad única de esta consulta.
     * @return la entidad o null
     */
    public T unique() {
        return query.unique();
    }

    /**
     * Obtiene el primer resultado de ésta consulta.
     * @return la entidad o null
     */
    public T first() {
        return query.first();
    }
    
    /**
     * Hace un conteo del número de filas que arroja la consulta
     * @return el número de filas
     */
    public Long count(){
        return query.count();
    }
    
    
    
    
}
