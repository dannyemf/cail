/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cmail.rehabilitacion.dao;

import java.util.Date;
import java.util.List;
import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 * Clase genérica de acceso a datos que puede usarse con toda entidad que herede de la clase DomainEntity.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class GanericDao<E extends DomainEntity> extends Dao{

    /**
     * La clase genérica que se va a manejar. Ejemplo: Usuario.class
     */
    private Class<E> clase;
    
    /**
     * Constructor único.
     * 
     * @param clase la clase de dominio que se va a manejar. Ejemplo: Usuario.class
     */
    public GanericDao(Class<E> clase) {
        this.clase = clase;
    }       

    /**
     * Obtiene la entidad genérica con el id indicado
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where id = :id
     * <span>
     * </p>
     * 
     * @param id el id de la entidad
     * @return la entidad genérica
     */
    public E getById(Long id){
        return (E)super.getById(clase, id);
    }
    
    /**
     * Obtiene todas las entidades genéricas cuya propiedad sea igual al valor indicado.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad = :valor
     * <span>
     * </p>     
     * 
     * @param propiedad el nombre de la propiedad
     * @param valor el valor de la propiedad
     * @return la lista de entidades
     */
    public List<E> getAllByProperty(String propiedad, Object valor){
        return (List<E>)super.getAllByProperty(clase, propiedad, valor);
    }        
    
    /**
     * Obtiene un listado de entidades genéricas mediante likes y or.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad1 like '%:valor%' or propiedad2 like '%:valor%' or ... propiedad(n) like '%valor%'
     * <span>
     * </p>
     * 
     * @param valor el valor
     * @param propiedades los nombres de la propiedades
     * @return lista de entidades
     */
    public List<E> getAllByOrLikePropertys(Object valor, String... propiedades){
        return (List<E>)super.getAllByOrLikePropertys(clase, valor, propiedades);
    }        
    
    /**
     * Obtiene todas las entidades genéricas donde la propiedad contenga la cadena valor.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad like '%:valor%'
     * <span>
     * </p>
     * 
     * @param propiedad el nombre de la propiedad
     * @param valor el valor de la propiedad
     * @return lista de entidades
     */
    public List<E> getAllLikeProperty(String propiedad, String valor){
        return (List<E>)super.getAllLikeProperty(clase, propiedad, valor);
    }    
    
    /**
     * Obtiene un listado de entidades genéricas donde la propiedad esté entre la fecha inicial y final (incluido las fechas inicial y final).
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad &gt;= :fechaInicial and propiedad &lt;= :fechaFInal
     * <span>
     * </p>
     * 
     * @param propiedad nombre de la propiedad
     * @param fechaInicial fecha inicial
     * @param fechaFinal fecha final
     * @return lista de entidades
     */
    public List<E> getAllByDates(String propiedad, Date fechaInicial, Date fechaFinal){
        return (List<E>)super.getAllByDates(clase, propiedad, fechaInicial, fechaFinal);
    }

    /**
     * Obtiene todas las entidades genéricas de la clase a tratar.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad
     * <span>
     * </p>
     * 
     * @return listado de entidades
     */
    public List<E> getAll(){
        return (List<E>)super.getAll(clase);
    }
    
}
