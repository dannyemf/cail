/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core;

import java.util.Comparator;
import org.cmail.rehabilitacion.modelo.htp.Indicador;



/**
 *
 * @author Usuario
 */
public class IndicadorOrdenComparator implements Comparator<Indicador>{

    public int compare(Indicador o1, Indicador o2) {
        return new Integer(o1.getOrden()).compareTo(new Integer(o2.getOrden()));
    }
    
}

