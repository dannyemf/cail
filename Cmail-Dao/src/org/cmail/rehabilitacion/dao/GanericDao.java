/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cmail.rehabilitacion.dao;

import java.util.Date;
import java.util.List;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 *
 * @author Desarrollador
 */
public class GanericDao<E extends DomainEntity> extends Dao{

    private Class<E> clase;
    
    public GanericDao(Class<E> clase) {
        this.clase = clase;
    }       

    public E getById(Long id){
        return (E)super.getById(clase, id);
    }
    
    public List<E> getAllByProperty(String propiedad, Object valor){
        return (List<E>)super.getAllByProperty(clase, propiedad, valor);
    }        
    
    public List<E> getAllByOrLikePropertys(Object valor, String... propiedades){
        return (List<E>)super.getAllByOrLikePropertys(clase, valor, propiedades);
    }
    
    public List<E> getAllByAndLikePropertys(KProperty... map ){
        return (List<E>)super.getAllByAndLikePropertys(clase, map);
    }
    
    public List<E> getAllLikeProperty(String propiedad, String valor){
        return (List<E>)super.getAllLikeProperty(clase, propiedad, valor);
    }    

    /*public List<E> getAlLikeNumero(String propiedad, String valor){
        return (List<E>)super.getAlLikeNumero(clase, propiedad, valor);
    }*/
    
    public List<E> getAllByDates(String propiedad, Date fechaInicial, Date fechaFinal){
        return (List<E>)super.getAllByDates(clase, propiedad, fechaInicial, fechaFinal);
    }

    public List<E> getAll(){
        return (List<E>)super.getAll(clase);
    }
    
}
