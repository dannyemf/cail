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
import org.cmail.rehabilitacion.modelo.htp.Categoria;
import org.cmail.rehabilitacion.modelo.htp.Indicador;
import org.cmail.rehabilitacion.modelo.htp.TipoIndicador;

/**
 *
 * @author Usuario
 */
public class ItemInterpretacion implements Serializable{
    
    
    private List<ItemInterpretacionCategoria> categroias = new ArrayList<ItemInterpretacionCategoria>();

    public ItemInterpretacion(List<Categoria> categorias, TipoIndicador tipo) {                
        
        for (Iterator<Categoria> it = categorias.iterator(); it.hasNext();) {            
            Categoria cat = it.next();
            
            List<Indicador> lista = cat.obtenerIndicadores(tipo);
            if(!lista.isEmpty()){
                ItemInterpretacionCategoria item =new ItemInterpretacionCategoria(cat, tipo);
                this.categroias.add(item);            
            }
        }
        
        Collections.sort(this.categroias, new ItemInterpretacionCategoriaComparator());
    }        

    /**
     * @return the categroias
     */
    public List<ItemInterpretacionCategoria> getCategroias() {
        return categroias;
    }

    /**
     * @param categroias the categroias to set
     */
    public void setCategroias(List<ItemInterpretacionCategoria> categroias) {
        this.categroias = categroias;
    }
    
    
    
}
