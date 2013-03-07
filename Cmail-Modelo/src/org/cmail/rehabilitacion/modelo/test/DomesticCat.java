/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.test;

/**
 *
 * @author Usuario
 */
public class DomesticCat extends Cat{
    
    private String nombre;

    public DomesticCat() {
    }

    
    public DomesticCat(Long id, String raza) {
        setRaza(raza);
        setId(id);
    }

    @Override
    public String toString() {
        return super.toString() + ", nombre: " + nombre;
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
    
}
