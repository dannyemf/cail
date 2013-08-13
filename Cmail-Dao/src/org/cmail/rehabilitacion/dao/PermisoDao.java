/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.cmail.rehabilitacion.modelo.seguridad.Perfil;

import org.cmail.rehabilitacion.modelo.seguridad.Permiso;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;

/**
 * Clase de acceso a datos para manejar los perfiles.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class PermisoDao extends GanericDao<Permiso> {
    
    protected final Logger log = Logger.getLogger(this.getClass());

    /**
     * Constructor por defecto
     */
    public PermisoDao() {
        super(Permiso.class);
    }
    
    /**
     * Obtiene los permisos para un usuario en específico, tomando en cuenta los perfiles del mismo.
     * 
     * @param usuario el usuario del que se debe obtener los permisos
     * @return lista de permisos
     */
    public List<Permiso> obtenerPermisos(Usuario usuario){
        try {
            if(usuario.getPerfiles().size() > 0){
                Long[] perfiles = new Long[usuario.getPerfiles().size()];
                int i = 0;
                for (Iterator<Perfil> it = usuario.getPerfiles().iterator(); it.hasNext();) {
                    Perfil p = it.next();
                    perfiles[i] = p.getId().longValue();
                    i++;
                }

                Criteria c = getSession().createCriteria(Permiso.class);
                c.setLockMode(LockMode.UPGRADE);  
                c.createAlias("perfiles", "pf");
                
                c.add(Restrictions.in("pf.id", perfiles ));                

                return c.list();
            }
        } catch (Exception e) {
            log.error("Error obtenerOpciones",e);           
        }        
        
        return new ArrayList<Permiso>();
    }
    
}
