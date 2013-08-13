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
 * Clase que representa la parte where de una consulta (Framework K).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class KWhere<T extends DomainEntity> implements IKResult<T> {
    
    /**
     * La consulta sobre la que opera el where
     */
    private KQuery<T> query;
    
    /**
     * Lista de propiedades de la parte where
     */
    private List<KProperty> propiedades = new ArrayList<KProperty>();    
    
    /**
     * Constructor mínimo.
     * 
     * @param query la consulta
     * @param propiedades lista de propiedades del where
     */
    public KWhere(KQuery<T> query, KProperty... propiedades) {
        this.propiedades.addAll(Arrays.asList(propiedades));
        this.query = query;
    }
    
    /**
     * Agrega una propiedad a la parte where
     * @param propiedad la propiedad
     */
    public void addProperty(KProperty propiedad){
        propiedades.add(propiedad);
    }
    
    /**
     * Genera el hql de la parte where
     * @param parametros lista de parámetros
     * @return cadena hql generada
     */
    public String toHql(List<Object> parametros){
        
        try {            
            String where = "";
            
            int i = 0;
            for (KProperty it : propiedades) {                                
                if(i > 0){
                    where += " and ";
                }
                where +=  it.toHql(parametros);
                i++;
                                            
            }                                                
            
            return where.length() == 0 ? "" : " Where " + where;
        } catch (Exception e) {
            return "";
        }
        
    }
    
    /**
     * Agrega la parte order by a la consulta
     * @param propiedades lista de propiedades de ordenación
     * @return la clausula order (KOrder)
     */
    public KOrder<T> orderBy(KOrderProperty... propiedades){
        return query.orderBy(propiedades);        
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
