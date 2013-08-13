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
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Provincia extends DomainEntity{
    
    private Long codigoRegion;
    private String nombre;
    private String capital;
    private boolean activa;
    private Set<Canton> cantones = new HashSet<Canton>();

    public Provincia() {
        activa = true;
    }

    /**
     * @return the cod_region
     */
    public Long getCodigoRegion() {
        return codigoRegion;
    }

    /**
     * @param cod_region the cod_region to set
     */
    public void setCodigoRegion(Long codigoRegion) {
        this.codigoRegion = codigoRegion;
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
     * @return the capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * @return the activa
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * @param activa the eliminado to set
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return  nombre;
    }

    /**
     * @return the cantones
     */
    public Set<Canton> getCantones() {
        return cantones;
    }

    /**
     * @param cantones the cantones to set
     */
    public void setCantones(Set<Canton> cantones) {
        this.cantones = cantones;
    }
    
    public int getNumeroCantones() {
        return  this.cantones.size();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Provincia){
            Provincia ot = (Provincia)obj;        
            if(this.getId().longValue() == ot.getId().longValue()){
                return true;
            }
        }                
        
        return false;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    
    
    
    
}
