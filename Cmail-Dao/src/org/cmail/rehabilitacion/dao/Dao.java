/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


import org.apache.log4j.Logger;
import org.cmail.rehabilitacion.modelo.core.CmailList;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.modelo.DomainEntity;
import org.hibernate.HibernateException;

/**
 * Clase de acceso a datos que puede usarse con cualquier entidad
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Dao {
    
    protected final Logger log = Logger.getLogger(this.getClass());
    
    /**
     * Obtiene la sesión hibernate.
     * 
     * @return  La sesión de HibernateSessionFactory
     */
    public Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    /**
     * Inicia un transacción de hibernate.
     */
    public void beginTransaction() {
        log.info("Iniciando transacción...");
        getSession().beginTransaction();
        log.info("Iniciando transacción ---> OK");
    }

    /**
     * Confirma la transacción de hibernate.
     * 
     * @throws HibernateException 
     */
    public void commit() throws HibernateException{
        try {
            log.info("Confirmando transacción...");
            getSession().getTransaction().commit();
            log.info("Transacción cofirmada ---> OK");
        } catch (HibernateException e) {
            log.error("Confirmando transacción ---> ERROR", e);
            throw  e;
        }
        
    }

    /**
     * Echa atrás una transaccción hibernate.
     * Se lo debe llamar cuando falle el commit.
     */
    public void rollback() {
        try {
            log.info("Abortando transacción...");
            getSession().getTransaction().rollback();    
            log.info("Transacción abortada ---> OK");
        } catch (Exception e) {
            log.error("Abortando transacción ---> ERROR", e);
        }        
    }
    
    /**
     * Cierra la sessión hibernate.
     */
    public void close() {
        try {
            //getSession().close();
        } catch (Exception e) {
        }
    }        
    
    /**
     * Guarda una entidad en la base datos.
     * 
     * @param instancia la entidad a guardar
     * @return true si se guardó correctamente
     */
    public boolean save(DomainEntity instancia) {
        boolean exito = true;  
        log.info("Guardando..." +  instancia);
        try {
            getSession().clear();
            
            beginTransaction();
            if(instancia.getId().longValue() > 0){
                //getSession().saveOrUpdate(merge(instancia));            
                try{
                    getSession().update(instancia);
                }catch(Exception e){
                    Object mergedIntancia = getSession().merge(instancia);
                    getSession().update(mergedIntancia);
                }
            }else{
                //getSession().saveOrUpdate(instancia);
                getSession().save(instancia);
            }            
            commit();
        } catch (Exception e) {            
            exito = false;
            log.error("Error guardando",e);
            rollback();
        } finally {
            close();
            return exito;
        }
    }
    
    /**
     * Guarda una una entidad sin hacer commit.
     * 
     * @param instancia entidad a guardar
     */
    public void saveOnTx(DomainEntity instancia) throws HibernateException {
        if(instancia.getId().longValue() > 0){
            try {
                getSession().update(instancia);
            } catch (Exception e) {
                getSession().save(getSession().merge(instancia));
            }
        }else{
            getSession().save(instancia);
        }        
    }
    
    /**
     * Elimina una entidad de la base de datos.
     * 
     * @param instancia la entidad a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean delete(DomainEntity instancia) {
        boolean exito = true;
        try {
            beginTransaction();
            getSession().delete(instancia);
            commit();
        } catch (Exception e) {
            log.error(instancia, e);
            exito = false;
            rollback();
        } finally {
            close();
            return exito;
        }
    }
    
    /**
     * Refresca o restaura una entidad con la información actual de la base de datos.
     * 
     * @param instancia la entidad a recargar
     * @return true si la operación concluyó con éxito
     */
    public boolean refresh(DomainEntity instancia) {
        boolean exito = true;
        try {
            beginTransaction();
            getSession().refresh(instancia);
            commit();
        } catch (Exception e) {
            log.error(instancia, e);
            exito = false;
            rollback();
        } finally {
            close();
            return exito;
        }
    }
    
    /**
     * Devuelve el objeto unificando los cambios cuando este se encuentre entre varias sesiones hibernate.
     * 
     * @param instancia instancia persistente
     * @return la misma instancia
     */
    public DomainEntity merge(DomainEntity instancia) {
        try {
            instancia = (DomainEntity) getSession().merge(instancia);            
        } catch (Exception e) {
            log.error(instancia, e);
        }        
        return instancia;
    }

    /**
     * Obtiene una entidad por su id.    
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
    public <T extends DomainEntity> T getById(Class<? extends T> clase, Long id) {
        try {
            Criteria c = getSession().createCriteria(clase);
            c.add(Restrictions.eq("id", id));
            c.setLockMode(LockMode.UPGRADE);
            log.info("getById: " + clase.getSimpleName() +" - " + id);
            return (T)c.uniqueResult();
            //return (T) getSession()..get(clase, id);
        } catch (Exception e) {
            log.error(clase.getName() + ": " + id, e);
            return null;
        }
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
    public <T extends DomainEntity> T getByUniqueProperty(Class<? extends T> clase, String propiedad, Object valor) {
        try {
            Criteria cri = getSession().createCriteria(clase);
            cri.add(Restrictions.eq(propiedad, valor));
            cri.setLockMode(LockMode.UPGRADE);
            return (T)cri.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Verifica si existe una entidad por atributo y valor.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * select count (*) from entidad where propiedad = :value
     * <span>
     * </p>
     * 
     * @param entidad la entidad
     * @param propiedad nombre de la propiedad
     * @param valor valor de la propiedad
     * @return  true si existe
     */
    public boolean exists(DomainEntity entidad, String propiedad, String valor) {
        try {            
            Query q = getSession().createQuery(HqlUtil.exists(entidad, propiedad, valor));            
            List lst = q.list();            
            long o = Long.parseLong(lst.size() > 0 ? lst.get(0).toString() : "0");            
            if(o > 0){
                return true;
            }            
        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
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
    public <T extends DomainEntity> CmailList<T> getAllByProperty(Class<? extends T> clase, String propiedad, Object valor) {
        try {
            log.info("getAllByProperty(): Clase=" + clase+"; Propiedad="+ propiedad + "; Valor="+ valor);
            
            getSession().clear();
            
            Criteria cri = getSession().createCriteria(clase);
            cri.add(Restrictions.eq(propiedad, valor));
            cri.setLockMode(LockMode.UPGRADE);
            
            return new CmailList<T>(cri.list());
        } catch (Exception e) {
            log.error("getAllByProperty() --> Error",e);
            return null;
        }
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
    public <T extends DomainEntity> T getFirstByProperty(Class<? extends T> clase, String propiedad, Object valor) {
        try {
            Criteria cri = getSession().createCriteria(clase);
            cri.add(Restrictions.eq(propiedad, valor));
            cri.setLockMode(LockMode.UPGRADE);            
            cri.setMaxResults(1);
            
            List lst = cri.list();
            if(lst.size() > 0){
                return (T)lst.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
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
    public <T extends DomainEntity> CmailList<T> getAllByHql(Class<? extends T> clase, String hql) {
        try {                                    
            getSession().clear();                
            Query q = getSession().createQuery(hql);            
            return new CmailList<T>(q.list());
        } catch (Exception e) {
            return null;
        }
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
    public <T extends DomainEntity> CmailList<T> getAllByOrLikePropertys(Class<? extends T> clase, Object valor, String... propiedades) {
        try {                                    
            getSession().clear();    
            String sql = HqlUtil.getAllByOrLikePropertys(clase, valor, propiedades);
            Query q = getSession().createQuery(sql);            
            return new CmailList<T>(q.list());
        } catch (Exception e) {
            return null;
        }
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
    public <T extends DomainEntity> CmailList<T> getAllByAndLikePropertys(Class<? extends T> clase, KProperty... propiedades) {
        try {
            String sql = HqlUtil.getAllByAndLikePropertys(clase, propiedades);            
            log.info("getAllByAndLikePropertys: " + sql);            
            getSession().clear();
            Query q = getSession().createQuery(sql);            
            return new CmailList<T>(q.list());
        } catch (Exception e) {
            return null;
        }
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
    public <T extends DomainEntity> CmailList<T> getAllByAndPropertys(Class<? extends T> clase, KProperty... map) {
        try {
            String sql = HqlUtil.getAllByAndPropertys(clase, map);
            
            log.info("getAllByAndLikePropertys: " + sql);
            
            getSession().clear();
            Query q = getSession().createQuery(sql);            
            
            int i = -1;
            for (KProperty it : map) {
                i++;
                Object valor = it.getValor();
                if(valor != null){
                    q.setParameter(i, valor);
                }else{
                    i--;
                }
            }
            
            return new CmailList<T>(q.list());
        } catch (Exception e) {
            return null;
        }
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
    public <T extends DomainEntity> CmailList<T> getAllByInProperty(Class<? extends T> clase, String propiedad, Object... valores) {
        try {
            Criteria cri = getSession().createCriteria(clase);
            cri.add(Restrictions.in(propiedad, valores));
            cri.setLockMode(LockMode.UPGRADE);
            
            return new CmailList<T>(cri.list());
        } catch (Exception e) {
            return null;
        }
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
    public <T extends DomainEntity> CmailList<T> getAllLikeProperty(Class<? extends T> clase, String propiedad, String valor) {
        try {
            log.info("getAllLikeProperty: " + clase.getSimpleName() +"." +  propiedad +"='%"+ valor+"%'");
            Criteria cri = getSession().createCriteria(clase);
            cri.add(Restrictions.like(propiedad, valor, MatchMode.ANYWHERE));
            cri.setLockMode(LockMode.UPGRADE);
            
            return new CmailList<T>(cri.list());
        } catch (Exception e) {
            return null;
        }
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
    public <T extends DomainEntity> CmailList<T> getAll(Class<? extends T> clase) {
        try {
            getSession().clear();
            
            log.info("getAll: " + clase.getSimpleName());
            Criteria c = getSession().createCriteria(clase);
            c.setLockMode(LockMode.UPGRADE);            
            
            return new CmailList<T>(c.list());
        } catch (Exception e) {
            log.error("Error", e);
            return new CmailList();
        }
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
    public <T extends DomainEntity> CmailList<T> getAllByDates(Class<? extends T> clase, String propiedad, Date fechaInicial, Date fechaFinal) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(fechaInicial);
            c.set(Calendar.HOUR, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            Date fi = c.getTime();

            c.setTime(fechaFinal);
            c.set(Calendar.HOUR, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            Date ff = c.getTime();

            Criteria cri = getSession().createCriteria(clase);
            cri.setLockMode(LockMode.UPGRADE);
            cri.add(Restrictions.between(propiedad, fi, ff));
            return new CmailList<T>(cri.list());
        } catch (Exception e) {
            return null;
        }
    }

}
