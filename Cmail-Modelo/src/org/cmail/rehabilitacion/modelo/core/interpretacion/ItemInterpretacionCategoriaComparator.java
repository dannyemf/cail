/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core.interpretacion;

import java.util.Comparator;

/**
 * Comparador de ItemInterpretacionCategoria por tipo de categoría (Clase de vista).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class ItemInterpretacionCategoriaComparator implements Comparator<ItemInterpretacionCategoria> {

    @Override
    public int compare(ItemInterpretacionCategoria o1, ItemInterpretacionCategoria o2) {
        
        String sord1 =getOrder(o1);
        String sord2 =getOrder(o2);        
        
        String s1 = sord1 + "-" + o1.getCategoria().getNombre();
        String s2 = sord2 + "-" + o2.getCategoria().getNombre();
        
        return s1.compareTo(s2);
    }
    
    public String getOrder(ItemInterpretacionCategoria o){
        String sord = "";
        
        switch(o.getCategoria().getTipo()){
            case Proporcion: sord = "0"; break;
            case Perspectiva: sord = "1"; break;
            case Detalles: sord = "2"; break;
        }
        
        return sord;
    }
    
}
