/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

import org.cmail.rehabilitacion.modelo.seguridad.Opcion;
import org.cmail.rehabilitacion.modelo.seguridad.Perfil;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.modelo.seguridad.VwOpcion;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 * Clase de acceso a datos para manejar las opciones de menú.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class OpcionDao extends GanericDao<Opcion> {
    
    protected final Logger log = Logger.getLogger(this.getClass());

    /**
     * Constructor por defecto
     */
    public OpcionDao() {
        super(Opcion.class);
    }
    
    /**
     * Obtiene una lista de opciones por usuario y por opción padre, donde las opciones tengan los mismos perfiles que el usuario.
     * 
     * @param usuario el usuario
     * @param padre la opción padre
     * @return lista de opciones
     */
    public List<VwOpcion> obtenerOpciones(Usuario usuario, VwOpcion padre){
        try {
            if(usuario.getPerfiles().size() > 0){
                Long[] perfiles = new Long[usuario.getPerfiles().size()];
                int i = 0;
                for (Iterator<Perfil> it = usuario.getPerfiles().iterator(); it.hasNext();) {
                    Perfil p = it.next();
                    perfiles[i] = p.getId().longValue();
                    i++;
                }

                Criteria c = getSession().createCriteria(VwOpcion.class);
                c.setLockMode(LockMode.UPGRADE);
                c.add(Restrictions.in("idPerfil", perfiles));

                if(padre == null){
                    c.add(Restrictions.isNull("idPadre"));
                }else{
                    c.add(Restrictions.eq("idPadre",padre.getId()));
                }

                return c.list();
            }
        } catch (Exception e) {
            log.error("Error obtenerOpciones",e);           
        }        
        
        return new ArrayList<VwOpcion>();
    }
    
    /**
     * Obtiene las opciones cuyo padre sea el el indicado en el parámetro.
     * 
     * @param padre la opción padre
     * @return lista de opciones
     */
    public List<Opcion> obtenerOpciones(Opcion padre){
        try {
            Query q = getSession().createQuery("from Opcion as o where o.padre "  + (padre == null ? " is null " : "="+padre.getId() + " "));
            q.setCacheMode(CacheMode.REFRESH);
            return q.list();
        } catch (Exception e) {
            return new ArrayList<Opcion>();
        }        
    }
    
    /**
     * Guarda una opción, permitiendo remover los perfiles en forma jerárquica de las opciones hijas.
     * 
     * @param opcion la opción a guardar
     * @param removed la lista de perfiles
     * @return true si la transación culminó con éxito
     */
    public boolean guardar(Opcion opcion, List<Perfil> removed) {
        boolean s = true;
        try {
            
            getSession().clear();
            
            beginTransaction();

            List<Opcion> lst = obtenerOpciones(opcion);
            this.recursivoGuardar(opcion, removed, lst);
            
            commit();            
        } catch (Exception e) {
            log.info("Error: ", e);
            s = false;
            rollback();
        }finally{
            return s;
        }        
    }
    
    /**
     * Método privado y recursivo invocado por el método guardar.
     * 
     * @param opcion la opción a guardar
     * @param removed lista de perfiles
     * @param hijos lista de opciones hijas
     */
    private void recursivoGuardar(Opcion opcion, List<Perfil> removed, List<Opcion> hijos) {
        
        if(opcion.getId().longValue() == -1){
            getSession().saveOrUpdate(opcion);
        }else{        
            getSession().saveOrUpdate(merge(opcion));
        }
        // Eliminar los perfiles eliminados de los hijos            
        for (Iterator<Opcion> it = hijos.iterator(); it.hasNext();) {
            Opcion opp = it.next();
            List<Opcion> lst = obtenerOpciones(opp);
            recursivoGuardar(opp, removed, lst);
        }
    }
    
    /**
     * Elimina una opcion y sus perfiles
     * 
     * @param opcion la opcion a eliminar
     * @return true si la transacción culmina con éxito
     */
    public boolean eliminar(Opcion opcion){
        try {
            log.info("Borrando opcion " + opcion);
            getSession().clear();
            
            beginTransaction();
            
            getSession().createSQLQuery("delete from seg_opcion_perfil where id_opcion = " + opcion.getId()).executeUpdate();
            int r = getSession().createQuery("delete Opcion where id = " + opcion.getId()).executeUpdate();            
            commit();
            
            log.info("Borrado.... OK: " + r + " filas");
            
            if(r>0) return true;
            return false;
        } catch (Exception e) {
            log.error(opcion, e);
            rollback();
            return false;
        }
    }
                            
}
