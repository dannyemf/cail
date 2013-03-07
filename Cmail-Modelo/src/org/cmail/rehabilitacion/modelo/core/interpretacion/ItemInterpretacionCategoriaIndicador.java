/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core.interpretacion;

import java.io.Serializable;
import org.cmail.rehabilitacion.modelo.htp.Indicador;

/**
 *
 * @author Usuario
 */
public class ItemInterpretacionCategoriaIndicador implements Serializable{
    
    private boolean seleccionado;
    private Indicador indicador;

    public ItemInterpretacionCategoriaIndicador() {
    }

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
