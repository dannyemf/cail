/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cmail.rehabilitacion.servicio;

import java.util.Date;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.dao.hql.KQuery;
import org.cmail.rehabilitacion.modelo.DomainEntity;
import org.cmail.rehabilitacion.modelo.core.CmailList;



/**
 * Clase base de lógica de negocio de la que deben heredar los servicios que necesiten tratar de forma genérica una entidad.
 * Tambien se puede instanciar directamente esta clase y en su constructor indicar el tipo genérico de entidad que se va a tratar, 
 * de tal manera que se puedan usar todos sus métodos genéricos.
 * Ejemplo: new GenericServicio<Usuario>(Usuario.class).listarTodos();
 * 
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */

public class GenericServicio<T extends DomainEntity> extends  BaseServicio {    
    
    /** Clase genérica que se debe manejar*/
    private Class<T> claseG = null;
    
    /**
     * Constructor mínimo.
     * @param entidadBase la clase de entidad a tratar como genérica
     */
    public GenericServicio(Class<T> entidadBase) {        
        this.claseG = entidadBase;
    }
    
    /**
     * Obtiene una entidad genérica por su id.    
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where id = :id
     * <span>
     * </p>
     * 
     * @param id id de la entidad
     * @return la entidad con ese id o null si no la encuentra
     */
    public T obtenerPorId( Long id) {
        return super.obtenerPorId(claseG, id);
    }
    
    /**
     * Crea una consulta de la clase genérica
     * @return la consula (KQuery)
     */
    public KQuery<T> from() {
        return KQuery.from(claseG);
    }       
    
    /**
     * Obtiene una única entidad de la clase genérica por nombre de atributo y su valor.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad = :valor limit 1
     * <span>
     * </p>
     *      
     * @param propiedad el nombre de la propiedad única
     * @param valor el valor de la propiedad
     * @return la entidad encontrada o null de lo contrario
     */
    public T obtenerUnicoPor(String propiedad, Object valor){
        return super.obtenerUnicoPor(claseG, propiedad, valor);
    }

    /**
     * Obtiene el primer elemento de la clase genérica cuya propiedad sea igual al valor.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad = :valor limit 1
     * <span>
     * </p>
     * 
     * @param propiedad el nombre de la propiedad
     * @param valor el valor de la propiedad
     * @return la primer entidad encontrada
     */
    public T obtenerPrimerPor(String propiedad, Object valor){
        return super.obtenerPrimerPor(claseG, propiedad, valor);
    }
    
    /**
     * Obtiene una lista de entidades de la clase genérica mediante la consulta hql indicada.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad ....hql.....
     * <span>
     * </p>
     * 
     * @param hql la consulta hql
     * @return la lista de entidades
     */
    public CmailList<T> listarPorHql(String hql){
        return super.listarPorHql(claseG, hql);
    }
    
    /**
     * Obtiene todas las entidades de la clase genérica.
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
    public CmailList<T> listarTodos(){
        return super.listarTodos(claseG);
    }    
    
    /**
     * Obtiene todas las entidades de la clase genérica cuya propiedad sea igual al valor indicado.
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
    public CmailList<T> listarPorPropiedad(String propiedad, Object valor){
        return super.listarPorPropiedad(claseG, propiedad, valor);
    }        
    
    /**
     * Obtiene todas las entidades de la clase genérica donde la propiedad contenga la cadena valor.
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
    public CmailList<T> listarPorPropiedadLike(String propiedad, String valor){
        return super.listarPorPropiedadLike(claseG, propiedad, valor);
    }
    
    /**
     * Obtiene la lista de entidadades de la clase genérica donde la propiedad contenga por lo menos un valor de la lista de valores.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad in (valor1, valor2,... valor(n))
     * <span>
     * </p>
     * 
     * @param propiedad el nombre de la propiedad
     * @param valores la lista de valores
     * @return lista de entidades
     */
    public CmailList<T> listarPorPropiedadIn(String propiedad, Object... valores){
        return super.listarPorPropiedadIn(claseG, propiedad, valores);
    }   
    
    /**
     * Obtiene un listado de entidades de la clase genérica mediante likes y or.
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
    public CmailList<T> listarPorPropiedadesOr(Object valor, String... propiedades){
        return super.listarPorPropiedadesOr(claseG, valor, propiedades);
    }
    
    /**
     * Obtiene un listado de entidades de la clase genérica mediante el listado de propiedades y el agrupador and.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad1 = :valor1 and propiedad2 = :valor2 and propiedad(n) = :valor(n)
     * <span>
     * </p>
     * 
     * @param propiedades lista de propiedades
     * @return lista de entidades
     */
    public CmailList<T> listarPorPropiedadesValores(KProperty... map){
        return super.listarPorPropiedadesValores(claseG, map);
    }
    
    /**
     * Obtiene un listado de entidades de la clase genérica mediante el listado de propiedades, el agrupador and y el operador like.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad1 like '%:valor1%' and propiedad2 like '%:valor2%' and propiedad(n) like '%:valor(n)'
     * <br/>
     * Donde op1, op2, op(n) el el operador de cada Kproperty
     * <span>
     * </p>
     * 
     * @param propiedades lista de propiedades
     * @return lista de entidades
     */
    public CmailList<T> listarPorPropiedadesValoresLike(KProperty... propiedades){
        return super.listarPorPropiedadesValoresLike(claseG, propiedades);
    }
    /**
     * Obtiene un listado de entidades de la clase genérica donde la propiedad esté entre la fecha inicial y final (incluido las fechas inicial y final).
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
    public CmailList<T> listarPorRangoFechas(String propiedad, Date fechaInicial, Date fechaFinal){
        return super.listarPorRangoFechas(claseG, propiedad, fechaInicial, fechaFinal);
    }       
        
}