/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.cmail.rehabilitacion.dao.exception.AuditException;

import org.cmail.rehabilitacion.modelo.AuditEntity;
import org.cmail.rehabilitacion.modelo.AuditoriaEntidad;
import org.cmail.rehabilitacion.modelo.AuditoriaPropiedad;
import org.cmail.rehabilitacion.modelo.DomainEntity;
import org.hibernate.EmptyInterceptor;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

/**
 *
 * @author Usuario
 */
public class AuditInterceptor extends EmptyInterceptor {
    
    protected final Logger log = Logger.getLogger(this.getClass());
    
    private int updates;
    private int creates;
    private int loads;
    
    private List<AuditoriaEntidad> auditorias = new ArrayList<AuditoriaEntidad>();

    public void onDelete(Object entity,
                         Serializable id,
                         Object[] state,
                         String[] propertyNames,
                         Type[] types) {
        // do nothing
        if(entity instanceof AuditEntity){ // Se debe auditar
            AuditEntity e = (AuditEntity)entity;
            String user = e.getAuditUpdateUser();
            log.info("Delete: " + e.getId());
            if(user == null){
               throw new AuditException("No se ha fijado el usuario para la auditoria");
            }else{
                auditorias.add(new AuditoriaEntidad(e, AuditoriaEntidad.ACCION_ELIMINACION, user, propertyNames, state));
            }
            
        }
    }

    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types) {

        if ( entity instanceof AuditEntity ) {
            updates++;
            boolean alt = false;        
            //aqui se queda (no encuentra el usuario
            AuditEntity e = (AuditEntity)entity;
            String user = e.getAuditUpdateUser();
            log.info("Update enti: "+ e.getId());
            
            if(user == null){
               throw new AuditException("No se ha fijado el usuario para la auditoria");
            }
            
            for ( int i=0; i < propertyNames.length; i++ ) {
                if ( "auditCreateDate".equals( propertyNames[i] ) ) {
                    if(currentState[i] == null){
                        currentState[i] = new Date();
                        alt = true;
                    }
                }
                
                if ( "auditUpdateDate".equals( propertyNames[i] ) ) {
                    currentState[i] = new Date();
                    alt = true;
                }
                
                if ( "auditCreateUser".equals( propertyNames[i])) {
                    if(currentState[i] == null){
                        currentState[i] = user;
                        alt = true;
                    }
                }                
                if ( "auditUpdateUser".equals( propertyNames[i] )) {
                    currentState[i] = user;
                    alt = true;
                }                
            }
                        
            auditorias.add(new AuditoriaEntidad(e, AuditoriaEntidad.ACCION_ACTUALIZACION, user, propertyNames, previousState, currentState));
            
            return alt;
        }
        return false;
    }

    public boolean onLoad(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {
        if ( entity instanceof DomainEntity ) {
            loads++;
        }
        return false;
    }

    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {

        if ( entity instanceof AuditEntity ) {
      
            creates++;
            boolean alt = true;
            AuditEntity e = (AuditEntity)entity;
            String user = e.getAuditUpdateUser();            
            if(user == null){
               throw new AuditException("No se ha fijado el usuario para la auditoria");
            }            
            for ( int i=0; i<propertyNames.length; i++ ) {
                if ( "auditCreateDate".equals( propertyNames[i] ) ) {
                    if(state[i] == null){
                        state[i] = new Date();
                        alt = true;
                    }
                }
                if ( "auditUpdateDate".equals( propertyNames[i] ) ) {
                    state[i] = new Date();
                    alt = true;
                }
                
                if ( "auditCreateUser".equals( propertyNames[i])) {
                    if(state[i] == null){
                        state[i] = user;
                        alt = true;
                    }
                }
                
                if ( "auditUpdateUser".equals( propertyNames[i])) {
                    state[i] = user;
                    alt = true;
                }
            }
            
            auditorias.add(new AuditoriaEntidad(e, AuditoriaEntidad.ACCION_CREACION, user, propertyNames, state));
            return alt;
        }
        return false;
    }  
    
    public void afterTransactionCompletion(Transaction tx) {
        if ( tx.wasCommitted() ) {
            System.out.println("Creations: " + creates + ", Updates: " + updates + ", Loads: " + loads);
            
            Transaction txl = null;
            try {
                if(auditorias.size()> 0){
                    StatelessSession ses = HibernateSessionFactory.getSessionFactory().openStatelessSession();
                    txl = ses.beginTransaction();
                    for (Iterator<AuditoriaEntidad> it = auditorias.iterator(); it.hasNext();) {
                        AuditoriaEntidad a = it.next();
                        a.initId();
                        
                        ses.insert(a);                        
                        System.out.println(a.getId());
                        for (Iterator<AuditoriaPropiedad> it1 = a.getPropiedades().iterator(); it1.hasNext();) {
                            AuditoriaPropiedad p = it1.next();                            
                            ses.insert(p);
                        }
                    }                
                    txl.commit();
                }
            } catch (Exception e) {
                if(txl != null){
                    txl.rollback();
                }
            }finally{
                auditorias.clear();
            }
        }
        updates=0;
        creates=0;
        loads=0;
        
    }
}
