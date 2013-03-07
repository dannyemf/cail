/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core.interpretacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.cmail.rehabilitacion.modelo.core.IndicadorOrdenComparator;
import org.cmail.rehabilitacion.modelo.htp.Categoria;
import org.cmail.rehabilitacion.modelo.htp.Indicador;
import org.cmail.rehabilitacion.modelo.htp.TipoIndicador;

/**
 *
 * @author Usuario
 */
public class ItemInterpretacionCategoria implements Serializable{
    
    private Categoria categoria;
    private List<ItemInterpretacionCategoriaIndicador> indicadores=new ArrayList<ItemInterpretacionCategoriaIndicador>();

    public ItemInterpretacionCategoria(Categoria categoria, TipoIndicador tipo) {
        this.categoria = categoria;
        
        List<Indicador> lista = categoria.obtenerIndicadores(tipo);
        Collections.sort(lista, new IndicadorOrdenComparator());
        
        for (Iterator<Indicador> it = lista.iterator(); it.hasNext();) {
            Indicador indicador = it.next();
            ItemInterpretacionCategoriaIndicador item = new ItemInterpretacionCategoriaIndicador(indicador);
            this.indicadores.add(item);            
        }
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the indicadores
     */
    public List<ItemInterpretacionCategoriaIndicador> getIndicadores() {
        return indicadores;
    }

    /**
     * @param indicadores the indicadores to set
     */
    public void setIndicadores(List<ItemInterpretacionCategoriaIndicador> indicadores) {
        this.setIndicadores(indicadores);
    }
    
    
    
    
}
