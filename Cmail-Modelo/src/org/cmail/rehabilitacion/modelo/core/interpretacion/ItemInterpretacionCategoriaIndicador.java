/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core.interpretacion;

import java.io.Serializable;
import org.cmail.rehabilitacion.modelo.htp.Indicador;

/**
 * Clase para la interpretación de un indicador (Clase de vista).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class ItemInterpretacionCategoriaIndicador implements Serializable{
    
    /**
     * Si el indicador ha sido marcado o seleccionado en la vista
     */
    private boolean seleccionado;
    
    /**
     * El indicador a tratar
     */
    private Indicador indicador;

    /**
     * Constructor por defecto
     */
    public ItemInterpretacionCategoriaIndicador() {
    }

    /**
     * Constructor completo.
     * 
     * @param indicador el indicador
     */
    public ItemInterpretacionCategoriaIndicador(Indicador indicador) {        
        this.indicador = indicador;
    }

    /**
     * @return the seleccionado
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    /**
     * @return the indicador
     */
    public Indicador getIndicador() {
        return indicador;
    }

    /**
     * @param indicador the indicador to set
     */
    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }
    
    
    
}
