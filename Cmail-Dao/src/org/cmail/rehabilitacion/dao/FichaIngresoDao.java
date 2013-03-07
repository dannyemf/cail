/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao;

import java.util.ArrayList;
import java.util.List;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario
 */
public class FichaIngresoDao extends GanericDao<FichaIngreso> {

    public FichaIngresoDao() {
        super(FichaIngreso.class);
    }

    public List<FichaIngreso> obtenerOpciones(String nombres, String apellidos, String cedula) {
        try {
            Query q = getSession().createQuery("from FichaIngreso as ficha where ficha.adolescente.nombres like %" + nombres != null ? nombres : ""
                    + " and FichaIngreso as ficha where ficha.adolescente.apellidos like %" + apellidos != null ? apellidos : ""
                    + " and FichaIngreso as ficha where ficha.adolescente.cedula like %" + cedula != null ? cedula : "");
            q.setCacheMode(CacheMode.REFRESH);
            return q.list();
        } catch (Exception e) {
            return new ArrayList<FichaIngreso>();
        }
    }
    
    public boolean save(FichaIngreso instancia) {
        boolean b = false;
        Transaction tx = null;
        
        log.info("Guardando ficha....");
        
        try {
            tx = getSession().beginTransaction();
            
            //merge(instancia);
            log.info("Madre: " + instancia.getAdolescente().getMadre().getNombres());
            
            getSession().saveOrUpdate(merge(instancia.getAdolescente()));
            getSession().saveOrUpdate(merge(instancia));
            
            tx.commit();
            b=true;
        } catch (Exception e) {
            log.error("Error guardar ficha", e);
            if(tx !=null) tx.rollback();
            
            b = false;
        }
        
        return b;
    }
    
    
}
