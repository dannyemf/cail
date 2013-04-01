/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.List;
import org.cmail.rehabilitacion.dao.GanericDao;
import org.cmail.rehabilitacion.dao.HqlUtil;
import org.cmail.rehabilitacion.dao.hql.KProperty;
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

    //comprueba si ya existe una persona con la cedula enviada y
    //y determina si es otra persona que se esta editando
//    public boolean existePersonaByCedula(String cedula, Persona persona) {
//        boolean existe = false;
//        if (persona != null) {
//            Persona persona_aux = (Persona) obtenerUnicoPor(Persona.class, "cedula", cedula);
//            if (persona_aux != null && (!persona.getId().equals(persona_aux.getId()))) {
//                existe = true;
//            }
//        }
//        return existe;
//    }

       

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
   
}
