    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cmail.rehabilitacion.modelo.localizacion;

import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Parroquia extends DomainEntity {
    
    private Canton canton;    
    private String nombre;
    private String zona;    
    private boolean activa;    

    public Parroquia() {
        activa = true;
    }   

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Parroquia){
            Parroquia ot = (Parroquia)obj;        
            if(this.getId().longValue() == ot.getId().longValue()){
                return true;
            }
        }                
        
        return false;
    }
    
    

    /**
     * @return the canton
     */
    public Canton getCanton() {
        return canton;
    }

    /**
     * @param canton the canton to set
     */
    public void setCanton(Canton canton) {
        this.canton = canton;
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
     * @return the zona
     */
    public String getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(String zona) {
        this.zona = zona;
    }

   /**
     * @return the activa
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * @param activa the activa to set
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return this.nombre;
    }


}
