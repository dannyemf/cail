/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cmail.rehabilitacion.servicio;

import java.util.Date;
import org.cmail.rehabilitacion.dao.Dao;
import org.cmail.rehabilitacion.modelo.core.CmailList;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.dao.hql.KQuery;
import org.cmail.rehabilitacion.modelo.DomainEntity;


/**
 * Clase base de lógica de negocio de la que deben heredar todos los servicios.
 * Esta clase tiene métodos que se usan independientemente del tipo de entidad que se esté tratando.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class BaseServicio  {
    
    /**Intancia de la capa de acceso a datos*/
    private Dao dao = new Dao();    
    
    /**
     * Constructor por defecto
     */
    public BaseServicio() {
    }
    
    /**
     * Gurada una entidad en la base de datos.
     * 
     * @param model la entidad
     * @return true si se guardó
     */
    public boolean guardar(DomainEntity model){        
         return dao.save(model);
    }
    
    /**
     * Recarga una entidad con la infomación actualizada.
     * 
     * @param model al entidad
     * @return true si se recargó
     */
    public boolean refrescar(DomainEntity model){
        return dao.refresh(model);
    }
    
    /**
     * Elimina una entidad de la base de datos.
     * 
     * @param model la entidad
     * @return true si se eliminó
     */
    public boolean eliminar(DomainEntity model){        
         return dao.delete(model);
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
     * @param <T> tipo genérico de la entidad
     * @param clase clase de la entidad
     * @param id id de la entidad
     * @return la entidad con ese id o null si no la encuentra
     */
    public <T extends DomainEntity> T obtenerPorId( Class<? extends T> type, Long id) {
        return (T)dao.getById(type, id);
    }
    
    /**
     * Retorna una consulta (Framework K) de la clase genérica indicada.     
     * @param <T> tipo genérico
     * @param type la clase de entidad
     * @return la consulta (KQuery)
     */
    public <T extends DomainEntity> KQuery<T> from( Class<? extends T> type) {
        return KQuery.from(type);
    }
    
    /**
     * Verifica si existe una entidad por un nombre de propiedad y por su valor.
     * Se usa con la finalidad de ver duplicados en atributos(columnas) únicos.
     * 
     * @param entidad la entidad
     * @param propiedad el nombre de la propiedad
     * @param valor el valor de la propiedad
     * @return true si ya existe otro registro con el mismo valor en la propiedad indicada
     */
    public boolean existe(DomainEntity entidad, String propiedad, String valor){
        return  dao.exists(entidad, propiedad, valor);
    }
    
    /**
     * Obtiene una única entidad por nombre de atributo y su valor.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad = :valor limit 1
     * <span>
     * </p>
     * 
     * @param <T> el tipo genérico
     * @param clase la clase de entidad
     * @param propiedad el nombre de la propiedad única
     * @param valor el valor de la propiedad
     * @return la entidad encontrada o null de lo contrario
     */
    public <T extends DomainEntity> T obtenerUnicoPor(Class<? extends T> clase, String propiedad, Object valor){
        return dao.getByUniqueProperty(clase, propiedad, valor);
    }
    
    /**
     * Obtiene el primer elemento cuya propiedad sea igual al valor.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad = :valor limit 1
     * <span>
     * </p>
     * 
     * @param <T> tipo genérico
     * @param clase la clase de entidad
     * @param propiedad el nombre de la propiedad
     * @param valor el valor de la propiedad
     * @return la primer entidad encontrada
     */
    public <T extends DomainEntity> T obtenerPrimerPor(Class<? extends T> clase, String propiedad, Object valor){
        return dao.getFirstByProperty(clase, propiedad, valor);
    }
    
    /**
     * Obtiene todas las entidades de una clase en específico.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad
     * <span>
     * </p>
     * 
     * @param <T> tipo genérico
     * @param clase la clase de la entidad
     * @return listado de entidades
     */
    public <T extends DomainEntity> CmailList<T> listarTodos(Class<? extends T> clase){
        return dao.getAll(clase);
    }
    
    /**
     * Obtiene una lista de entidades genérica mediante la consulta hql indicada.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad ....hql.....
     * <span>
     * </p>
     * 
     * @param <T> tipo genérico
     * @param clase la clase de entidad
     * @param hql la consulta hql
     * @return la lista de entidades
     */
    public <T extends DomainEntity> CmailList<T> listarPorHql(Class<? extends T> clase, String hql){
        return dao.getAllByHql(clase, hql);
    }
    
    /**
     * Obtiene todas las entidades cuya propiedad sea igual al valor indicado.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad = :valor
     * <span>
     * </p>     
     * 
     * @param <T> tipo genérico
     * @param clase la clase de entidad
     * @param propiedad el nombre de la propiedad
     * @param valor el valor de la propiedad
     * @return la lista de entidades
     */
    public <T extends DomainEntity> CmailList<T> listarPorPropiedad(Class<? extends T> clase, String propiedad, Object valor){
        return dao.getAllByProperty(clase, propiedad, valor);
    }        
    
    /**
     * Obtiene todas las entidades donde la propiedad contenga la cadena valor.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad like '%:valor%'
     * <span>
     * </p>
     * 
     * @param <T> tipo genérico
     * @param clase clase de entidad
     * @param propiedad el nombre de la propiedad
     * @param valor el valor de la propiedad
     * @return lista de entidades
     */
    public <T extends DomainEntity> CmailList<T> listarPorPropiedadLike(Class<? extends T> clase, String propiedad, String valor){
        return dao.getAllLikeProperty(clase, propiedad, valor);
    }
    
    /**
     * Obtiene la lista de entidadades donde la propiedad contenga por lo menos un valor de la lista de valores.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad in (valor1, valor2,... valor(n))
     * <span>
     * </p>
     * 
     * @param <T> tipo genérico
     * @param clase la clase de entidad
     * @param propiedad el nombre de la propiedad
     * @param valores la lista de valores
     * @return lista de entidades
     */
    public <T extends DomainEntity> CmailList<T> listarPorPropiedadIn(Class<? extends T> clase, String propiedad, Object... valores){
        return dao.getAllByInProperty(clase, propiedad, valores);
    }    
    
    /**
     * Obtiene un listado de entidades genérica mediante likes y or.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad1 like '%:valor%' or propiedad2 like '%:valor%' or ... propiedad(n) like '%valor%'
     * <span>
     * </p>
     * 
     * @param <T> tipo genérico
     * @param clase la clase de entidad
     * @param valor el valor
     * @param propiedades los nombres de la propiedades
     * @return lista de entidades
     */
    public <T extends DomainEntity> CmailList<T> listarPorPropiedadesOr(Class<? extends T> clase, Object valor, String... propiedades){
        return dao.getAllByOrLikePropertys(clase, valor, propiedades);
    }
        
    /**
     * Obtiene un listado de entidades genérica mediante el listado de propiedades y el agrupador and.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad1 = :valor1 and propiedad2 = :valor2 and propiedad(n) = :valor(n)
     * <span>
     * </p>
     * 
     * @param <T> tipo genérico
     * @param clase clase de entidad
     * @param propiedades lista de propiedades
     * @return lista de entidades
     */
    public <T extends DomainEntity> CmailList<T> listarPorPropiedadesValores(Class<? extends T> clase, KProperty... propiedades){
        return dao.getAllByAndPropertys(clase, propiedades);
    }
    
    /**
     * Obtiene un listado de entidades genérica mediante el listado de propiedades, el agrupador and y el operador like.
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
     * @param <T> tipo genérico
     * @param clase clase de entidad
     * @param propiedades lista de propiedades
     * @return lista de entidades
     */
    public <T extends DomainEntity> CmailList<T> listarPorPropiedadesValoresLike(Class<? extends T> clase, KProperty... propiedades){
        return dao.getAllByAndLikePropertys(clase, propiedades);
    }
        
    /**
     * Obtiene un listado de entidades donde la propiedad esté entre la fecha inicial y final (incluido las fechas inicial y final).
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad &gt;= :fechaInicial and propiedad &lt;= :fechaFInal
     * <span>
     * </p>
     * 
     * @param <T> tipo genérico
     * @param clase clase de la entidad
     * @param propiedad nombre de la propiedad
     * @param fechaInicial fecha inicial
     * @param fechaFinal fecha final
     * @return lista de entidades
     */
    public <T extends DomainEntity> CmailList<T> listarPorRangoFechas(Class<? extends T> clase, String propiedad, Date fechaInicial, Date fechaFinal){
        return dao.getAllByDates(clase, propiedad, fechaInicial, fechaFinal);
    }       
    
}