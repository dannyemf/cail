/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.seguridad;

import java.util.HashSet;
import java.util.Set;
import org.cmail.rehabilitacion.modelo.AuditEntity;

/**
 * Entidad que representa el permiso de un usuario para ingresar a una página o bien para hacer click sobre con control.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Permiso extends AuditEntity {

    /**
     * Nombre único del permiso. Ejemplo: "usuario.editar"
     */
    private String nombre;
    
    /**
     * Descripción de para que es el permiso
     */
    private String descripcion;    
    
    /**
     * Lista de perfiles de usuarios a los que se le aplica el permiso.
     */
    private Set<Perfil> perfiles = new HashSet<Perfil>();

    /**
     * Constructor por defecto
     */
    public Permiso() {
    }   

    /**
     * Agrega un perfil al permiso
     * @param perfil el perfil a agregar
     * @return true si se agregó
     */
    public boolean addPerfil(Perfil perfil) {
        return this.getPerfiles().add(perfil);
    }

    /**
     * Remueve un perfil del permiso
     * @param perfil el perfil a remover
     * @return true si se remoció
     */
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
