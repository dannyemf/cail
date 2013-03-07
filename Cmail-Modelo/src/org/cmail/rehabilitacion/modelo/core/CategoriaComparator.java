/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core;

import java.util.Comparator;
import org.cmail.rehabilitacion.modelo.htp.Categoria;



/**
 *
 * @author Usuario
 */
public class CategoriaComparator implements Comparator<Categoria>{

    private String nombreAtributo;
    private boolean ascendente;

    public CategoriaComparator(String nombreAtributo, boolean ascendente) {
        this.nombreAtributo = nombreAtributo;
        this.ascendente = ascendente;
    }    
    
    public int compare(Categoria o1, Categoria o2) {
        if(nombreAtributo.equals("nombre")){
            if(ascendente){
                return o1.getNombre().compareTo(o2.getNombre());
            }else{
                return o2.getNombre().compareTo(o1.getNombre());
            }
        }
        
        if(nombreAtributo.equals("descripcion")){
            if(ascendente){
                return o1.getDescripcion().compareTo(o2.getDescripcion());
            }else{
                return o2.getDescripcion().compareTo(o1.getDescripcion());
            }
        }                
        
        if(nombreAtributo.equals("tipo")){
            if(ascendente){
                return o1.getTipo().compareTo(o2.getTipo());
            }else{
                return o2.getTipo().compareTo(o1.getTipo());
            }
        }
        
        return 0;
    }

    /**
     * @return the nombreAtributo
     */
    public String getNombreAtributo() {
        return nombreAtributo;
    }

    /**
     * @param nombreAtributo the nombreAtributo to set
     */
    public void setNombreAtributo(String nombreAtributo) {
        this.nombreAtributo = nombreAtributo;
    }

    /**
     * @return the ascendente
     */
    public boolean isAscendente() {
        return ascendente;
    }

    /**
     * @param ascendente the ascendente to set
     */
    public void setAscendente(boolean ascendente) {
        this.ascendente = ascendente;
    }
    
}

