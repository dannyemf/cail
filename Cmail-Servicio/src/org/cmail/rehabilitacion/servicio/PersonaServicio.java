/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.List;
import org.cmail.rehabilitacion.dao.GanericDao;
import org.cmail.rehabilitacion.dao.HqlUtil;
import org.cmail.rehabilitacion.dao.hql.K;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.dao.hql.KWhere;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.PersonaRol;

/**
 *
 * @author Usuario
 */
public class PersonaServicio extends GenericServicio<Persona> {

    private GanericDao<Persona> dao;
    
    public PersonaServicio() {
        super(Persona.class);
        dao = new GanericDao<Persona>(Persona.class);
    }
    
    public Persona crearPersona(){
        Persona p = new Persona();        
        p.addRol(PersonaRol.GENERAL);        
        return p;
    }
       
    public List<Persona> listarAdolescentes(String cedula, String nombres, String apellidos, boolean incluirOtrosRoles) {        
        KProperty[] props = new KProperty[incluirOtrosRoles ? 3 : 4];
        props[0]=new KProperty("cedula", cedula);
        props[1]=new KProperty("nombres", nombres);
        props[2]=new KProperty("apellidos", apellidos);
        if(!incluirOtrosRoles){
            props[3]=new KProperty("roles", PersonaRol.ADOLESCENTE);
        }
        
        String hql = HqlUtil.getAllByAndLikePropertys(Persona.class, props);        
        return super.listarPorHql(hql);
    }
    
    public List<Persona> listarEmpleados(String cedula, String nombres, String apellidos, boolean incluirOtrosRoles) {        
        KProperty[] props = new KProperty[incluirOtrosRoles ? 3 : 4];
        props[0]=new KProperty("cedula", cedula);
        props[1]=new KProperty("nombres", nombres);
        props[2]=new KProperty("apellidos", apellidos);
        if(!incluirOtrosRoles){
            props[3]=new KProperty("roles", PersonaRol.EMPLEADO);
        }
        
        String hql = HqlUtil.getAllByAndLikePropertys(Persona.class, props);        
        return super.listarPorHql(hql);
    }
    
    public List<Persona> listarPersonas(String texto, PersonaRol rol, PersonaRol noRol) {                
        
        KWhere where = super.from().where(K.like("roles", rol.name()));        
        
        if(noRol != null){
            where.addProperty(K.notLike("roles", noRol.name()));
        }
        
        where.addProperty(
                K.or(
                    K.like("cedula", texto), 
                    K.like("nombres", texto), 
                    K.like("apellidos",texto), 
                    K.like("(nombres||' '||apellidos)", texto)
                )
        );       
                
        return where.list();
    }    
   
}
