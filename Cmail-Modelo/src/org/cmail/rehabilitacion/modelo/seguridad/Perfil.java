/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.seguridad;

import org.cmail.rehabilitacion.modelo.AuditEntity;

/**
 *
 * @author Usuario
 */
public class Perfil extends AuditEntity{
    
    private String nombre;
    private String descripcion;
    
    // Atributo no persisnte
    private boolean seleccionado = false;

    public Perfil() {
    }

    public Perfil(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || (obj != null && obj instanceof Perfil == false)){
            return false;
        }else{        
            Perfil p = (Perfil)obj;
            if(p.getId().longValue() == this.getId().longValue()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }
}
