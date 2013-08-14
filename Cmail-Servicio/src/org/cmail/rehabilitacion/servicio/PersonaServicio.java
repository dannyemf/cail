/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.List;
import org.cmail.rehabilitacion.dao.HqlUtil;
import org.cmail.rehabilitacion.dao.hql.K;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.dao.hql.KWhere;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.PersonaRol;

/**
 * Clase de lógica de negocio para manejar personas, empleados y adolescentes.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class PersonaServicio extends GenericServicio<Persona> {    
    
    /**
     * Constructor por defecto
     */
    public PersonaServicio() {
        super(Persona.class);        
    }
    
    /**
     * Crea un objeto persona
     * @return la persona
     */
    public Persona crearPersona(){
        Persona p = new Persona();        
        p.addRol(PersonaRol.GENERAL);        
        return p;
    }
    
    /**
     * Lista las personas que tengan el rol adolescente y que la cédula, los nombres o los apellidos contenga el valor indicado.
     * @param cedula la cédula
     * @param nombres los nombres
     * @param apellidos los apellidos
     * @param incluirOtrosRoles incluir personas que no tengan el rol adolescente
     * @return lista de adolescentes
     */
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
    
    /**
     * Lista las personas que tengan el rol empleado y que la cédula, los nombres o los apellidos contenga el valor indicado.
     * @param cedula la cédula
     * @param nombres los nombres
     * @param apellidos los apellidos
     * @param incluirOtrosRoles incluir personas que no tengan el rol empleado
     * @return lista de empleados
     */
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
    
    /**
     * Lista las personas que tengan el rol indicado, que no tenga el rol (noRol) y que uno de los atributos: cédula nombres o apellidos contenga el texto indicado.
     * @param texto texto que debe estar en cédula en los nombre a apellidos
     * @param rol el rol que debe tener
     * @param noRol el rol que no debe tener
     * @return lista de personas
     */
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
    
    /**
     * Verifica que otra persona no tenga el mismo número de cédula que la persona indicada.
     */
    public boolean existePersonaByCedula(String cedula, Persona persona) {
        boolean existe = false;
        if (persona != null) {
            Persona persona_aux = (Persona) obtenerUnicoPor(Persona.class, "cedula", cedula);
            if (persona_aux != null && (!persona.getId().equals(persona_aux.getId()))) {
                existe = true;
            }
        }
        return existe;
    }
   
}
