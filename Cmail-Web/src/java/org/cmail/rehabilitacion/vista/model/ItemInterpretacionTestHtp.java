/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.vista.model;

import java.io.Serializable;
import org.cmail.rehabilitacion.modelo.htp.InterpretacionTestHtp;

/**
 * Clase de modelo para la vista para presentar las interpretaciones de los test htp.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class ItemInterpretacionTestHtp implements Serializable{
    
    private InterpretacionTestHtp interpretacion;
    private ReporteResource informe;
    private ReporteResource evaluacion;

    public ItemInterpretacionTestHtp(InterpretacionTestHtp interpretacion, ReporteResource informe, ReporteResource evaluacion) {
        this.interpretacion = interpretacion;
        this.informe = informe;
        this.evaluacion = evaluacion;
    }

    /**
     * @return the interpretacion
     */
    public InterpretacionTestHtp getInterpretacion() {
        return interpretacion;
    }

    /**
     * @param interpretacion the interpretacion to set
     */
    public void setInterpretacion(InterpretacionTestHtp interpretacion) {
        this.interpretacion = interpretacion;
    }

    /**
     * @return the informe
     */
    public ReporteResource getInforme() {
        return informe;
    }

    /**
     * @param informe the informe to set
     */
    public void setInforme(ReporteResource informe) {
        this.informe = informe;
    }

    /**
     * @return the evaluacion
     */
    public ReporteResource getEvaluacion() {
        return evaluacion;
    }

    /**
     * @param evaluacion the evaluacion to set
     */
    public void setEvaluacion(ReporteResource evaluacion) {
        this.evaluacion = evaluacion;
    }
    
}
