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
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


import org.apache.log4j.Logger;
import org.cmail.rehabilitacion.modelo.core.CmailList;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.modelo.DomainEntity;
import org.hibernate.HibernateException;
import org.hibernate.mapping.Table;

/**
 *
 * @author Desarrollador
 */
public class Dao {

    protected final Logger log = Logger.getLogger(this.getClass());
    
    /**
     * Obtiene la sesión hibernate
     * @return  La sesión
     */
    public Session getSession() {
        return HibernateSessionFactory.getSession();
    }

    /**
     * Inici un transacción hibernate
     */
    public void beginTransaction() {
        log.info("Iniciando transacción...");
        getSession().beginTransaction();
        log.info("Iniciando transacción ---> OK");
    }

    /**
     * Confirma la transacción hibernate
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
     * Cierra la sessión hibernate
     */
    public void close() {
        try {
            //getSession().close();
        } catch (Exception e) {
        }
    }
    
    public Table getTable(DomainEntity entidad){
        return HibernateSessionFactory.getTable(entidad.getClass());
    }
    
    public <T extends DomainEntity> Table getTable(Class<T> clase){
        return HibernateSessionFactory.getTable(clase);
    }
    
    public <T extends DomainEntity> String getTableName(Class<T> clase){
        return HibernateSessionFactory.getTable(clase).getName();
    }
    
    public String getTableName(DomainEntity entidad){
        return HibernateSessionFactory.getTable(entidad.getClass()).getName();
    }
    
    public String getLRLike(Object obj){
        if(obj == null || obj.toString().length() == 0){
            return "%";
        }        
        return "%" + obj.toString() + "%";
    }
    
    /**
     * Guarda una entidad en la base datos
     * @param instancia Instncia persistente
     * @return true o false
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
     * Guarda una una entidad sin hacer commit. Se debe seguir:
     * @param instancia Entidad persistente
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
     * Elimina una entidad de la base de datos
     * @param instancia Entidad persistente
     * @return true o false
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
     * Refresca o restaura una entidad con la información actual de la base de datos
     * @param instancia Entidad persistente
     * @return true o false
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
     * Devuelve el objeto unificando los cambios cuando este se encuentre entre varias sesiones hibernate
     * @param instancia Instancia persistente
     * @return La misma instancia
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
     * Obtiene una entidad por su id
     * @param <T> Clase de entidad
     * @param clase Tipo de entidad
     * @param id Id de la entidad
     * @return Entidad o null
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

    public <T extends DomainEntity> T getByUniqueProperty(Class<? extends T> clase, String propiedad, Object value) {
        try {
            Criteria cri = getSession().createCriteria(clase);
            cri.add(Restrictions.eq(propiedad, value));
            cri.setLockMode(LockMode.UPGRADE);
            return (T)cri.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean exists(DomainEntity entidad, String propiedad, String value) {
        try {            
            Query q = getSession().createQuery(HqlUtil.exists(entidad, propiedad, value));            
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
    
    public <T extends DomainEntity> CmailList<T> getAllByHql(Class<? extends T> clase, String hql) {
        try {                                    
            getSession().clear();                
            Query q = getSession().createQuery(hql);            
            return new CmailList<T>(q.list());
        } catch (Exception e) {
            return null;
        }
    }
    
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
    
    public <T extends DomainEntity> CmailList<T> getAllByAndLikePropertys(Class<? extends T> clase, KProperty... map) {
        try {
            String sql = HqlUtil.getAllByAndLikePropertys(clase, map);            
            log.info("getAllByAndLikePropertys: " + sql);            
            getSession().clear();
            Query q = getSession().createQuery(sql);            
            return new CmailList<T>(q.list());
        } catch (Exception e) {
            return null;
        }
    }    
    
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

    public <T extends DomainEntity> CmailList<T> getByExample(Class<? extends T> clase, Object objectExample) {
        try {
            Criteria cri = getSession().createCriteria(clase);
            cri.add(Example.create(objectExample));
            cri.setLockMode(LockMode.UPGRADE);
            
            return new CmailList<T>(cri.list());
        } catch (Exception e) {
            return null;
        }
    }
    
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
