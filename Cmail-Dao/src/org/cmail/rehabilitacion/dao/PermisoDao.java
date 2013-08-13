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
 *
 * @author Usuario
 */
public class PermisoDao extends GanericDao<Permiso> {
    
    protected final Logger log = Logger.getLogger(this.getClass());

    public PermisoDao() {
        super(Permiso.class);
    }
    
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
