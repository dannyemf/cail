/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.seguridad;

import java.util.HashSet;
import java.util.Set;
import org.cmail.rehabilitacion.modelo.AuditEntity;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Permiso extends AuditEntity {

    private String nombre;
    private String descripcion;    
        
    private Set<Perfil> perfiles = new HashSet<Perfil>();

    public Permiso() {
    }   

    public boolean addPerfil(Perfil perfil) {
        return this.getPerfiles().add(perfil);
    }

    public boolean removePerfil(Perfil perfil) {
        return this.getPerfiles().remove(perfil);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the perfiles
     */
    public Set<Perfil> getPerfiles() {
        return perfiles;
    }

    /**
     * @param perfiles the perfiles to set
     */
    public void setPerfiles(Set<Perfil> perfiles) {
        this.perfiles = perfiles;
    }
    
    
}
