/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.localizacion;

import java.util.HashSet;
import java.util.Set;
import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 *
 * @author Usuario
 */
public class Canton extends DomainEntity{
    
    private Provincia provincia;
    private String nombre;    
    private boolean activo;
    private Set<Parroquia> parroquias = new HashSet<Parroquia>();

    public Canton() {
        this.activo = true;        
    }

    /**
     * @return the provincia
     */
    public Provincia getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
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
     * @return the acticado
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param acticado the acticado to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }   

    /**
     * @return the id_provincia
     */
    public Provincia getId_provincia() {
        return getProvincia();
    }

    /**
     * @param id_provincia the id_provincia to set
     */
    public void setId_provincia(Provincia provincia) {
        this.setProvincia(provincia);
    }

    /**
     * @return the parroquias
     */
    public Set<Parroquia> getParroquias() {
        return parroquias;
    }

    /**
     * @param parroquias the parroquias to set
     */
    public void setParroquias(Set<Parroquia> parroquias) {
        this.parroquias = parroquias;
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Canton){
            Canton ot = (Canton)obj;        
            if(this.getId().longValue() == ot.getId().longValue()){
                return true;
            }
        }                
        
        return false;
    }
    
    
    
    @Override
    public String toString() {
        return this.nombre;
    }
    
    public int getNumeroParroquias() {
        return  this.parroquias.size();
    }
}
