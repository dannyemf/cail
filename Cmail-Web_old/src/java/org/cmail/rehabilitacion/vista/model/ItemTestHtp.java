/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.vista.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import org.cmail.rehabilitacion.modelo.htp.InterpretacionTestHtp;
import org.cmail.rehabilitacion.modelo.htp.TestHtp;

/**
 *
 * @author Carpio Guarnizo
 */
public class ItemTestHtp implements Serializable{
    
    private TestHtp testHtp;
    private InterpretacionTestHtp informe;
    
    //private ReporteResource recursoTest;
    private ReporteResource recursoInforme;
    private ReporteResource recursoEvolucion;
    

    public ItemTestHtp(TestHtp testHtp, InterpretacionTestHtp interpretacion) {
        this.testHtp = testHtp;
        this.informe = interpretacion;
    }
    
    public boolean  isRenderedReporte(){        
        if(informe == null){
            return false;
        }          
        return true;
    }

    public TestHtp getTestHtp() {
        return testHtp;
    }

    public void setTestHtp(TestHtp testHtp) {
        this.testHtp = testHtp;
    }
    
    /**
     * @return the recurso
     */
//    public ReporteResource getRecursoTest() {        
//        SimpleDateFormat sd = new SimpleDateFormat("EEEEE, dd/MMMMM/yyyy");
//        String fecha = sd.format(this.getInforme().getFecha());
//        fecha = fecha.replace("/", " de ").toUpperCase();        
//        
//        recursoTest = new ReporteResource("informePsicologico");
//        recursoTest.addParam("PRM_MODEL", this.getInforme());
//        recursoTest.addParam("PRM_LUGAR_DIA_FECHA", this.getInforme().getFichaIngreso().getLugar() + " - " + fecha);
//        //recursoTest.addImage("PRM_VISTO", "visto.png");        
//        return recursoTest;
//    }

    /**
     * @param recurso the recurso to set
     */
//    public void setRecursoTest(ReporteResource recurso) {
//        this.recursoTest = recurso;
//    }

    /**
     * @return the informe
     */
    public InterpretacionTestHtp getInforme() {
        return informe;
    }

    /**
     * @param informe the informe to set
     */
    public void setInforme(InterpretacionTestHtp informe) {
        this.informe = informe;
    }   

    /**
     * @return the recursoInforme
     */
    public ReporteResource getRecursoInforme() {
        SimpleDateFormat sd = new SimpleDateFormat("EEEEE, dd/MMMMM/yyyy");
        String fecha = sd.format(this.getInforme().getFecha());
        fecha = fecha.replace("/", " de ").toUpperCase();        
        
        recursoInforme = new ReporteResource("informeEvolucionHtp");
        recursoInforme.addParam("PRM_MODEL", this.getInforme());
        recursoInforme.addParam("PRM_FECHA", fecha);        
        recursoInforme.addParam("PRM_RENDER_NIVREHAB", false); 
        
        return recursoInforme;
    }

    /**
     * @param recursoInforme the recursoInforme to set
     */
    public void setRecursoInforme(ReporteResource recursoInforme) {
        this.recursoInforme = recursoInforme;
    }

    /**
     * @return the recursoEvolucion
     */
    public ReporteResource getRecursoEvolucion() {
        
        SimpleDateFormat sd = new SimpleDateFormat("EEEEE, dd/MMMMM/yyyy");
        String fecha = sd.format(this.getInforme().getFecha());
        fecha = fecha.replace("/", " de ").toUpperCase();        
        
        recursoEvolucion = new ReporteResource("informeEvolucionHtp");
        recursoEvolucion.addParam("PRM_MODEL", this.getInforme());
        recursoEvolucion.addParam("PRM_FECHA", fecha);
        recursoEvolucion.addParam("PRM_RENDER_NIVREHAB", true); 
        
        return recursoEvolucion;
    }

    /**
     * @param recursoEvolucion the recursoEvolucion to set
     */
    public void setRecursoEvolucion(ReporteResource recursoEvolucion) {
        this.recursoEvolucion = recursoEvolucion;
    }
    
    
    
}
