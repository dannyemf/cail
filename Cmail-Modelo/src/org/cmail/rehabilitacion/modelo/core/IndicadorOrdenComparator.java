/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core;

import java.util.Comparator;
import org.cmail.rehabilitacion.modelo.htp.Indicador;



/**
 * Comparador de un indicador por orden.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class IndicadorOrdenComparator implements Comparator<Indicador>{

    /**
     * Método comparador
     * 
     * @param o1 el indicador uno
     * @param o2 el indicador dos
     * @return 1, -1 ó 0
     */
    public int compare(Indicador o1, Indicador o2) {
        return new Integer(o1.getOrden()).compareTo(new Integer(o2.getOrden()));
    }
    
}

