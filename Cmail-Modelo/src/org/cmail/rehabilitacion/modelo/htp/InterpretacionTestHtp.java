/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.htp;

import java.util.Date;
import org.cmail.rehabilitacion.modelo.AuditEntity;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class InterpretacionTestHtp extends AuditEntity{
    
    private Persona adolescente;
    private TestHtp testHtp;
    private FichaIngreso fichaIngreso;    
    private Usuario evaluador;
    private Date fecha = new Date();
    
    private String historiaAdolescente;
    private String comentarioConducta;
    private String graficoProporcion;
    private String graficoPerspectiva;
    private String graficoDetalle;
    private String interrogativoPosterior;
    private String resumenPsicologo;
    
    private String nivelRehabilitacionNombre;
    
    private double porcenjateRehabilitacionSistema;
    private double porcenjateRehabilitacionEvaluador;    
    private double porcenjateRehabilitacion;
    
    private int    nivelRehabilitacion;    
    
    public InterpretacionTestHtp() {
    }   

    /**
     * @return the adolescente
     */
    public Persona getAdolescente() {
        return adolescente;
    }

    /**
     * @param adolescente the adolescente to set
     */
    public void setAdolescente(Persona adolescente) {
        this.adolescente = adolescente;
    }

    /**
     * @return the testHtp
     */
    public TestHtp getTestHtp() {
        return testHtp;
    }

    /**
     * @param testHtp the testHtp to set
     */
    public void setTestHtp(TestHtp testHtp) {
        this.testHtp = testHtp;
    }

    /**
     * @return the fichaIngreso
     */
    public FichaIngreso getFichaIngreso() {
        return fichaIngreso;
    }

    /**
     * @param fichaIngreso the fichaIngreso to set
     */
    public void setFichaIngreso(FichaIngreso fichaIngreso) {
        this.fichaIngreso = fichaIngreso;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the historiaAdolescente
     */
    public String getHistoriaAdolescente() {
        return historiaAdolescente;
    }

    /**
     * @param historiaAdolescente the historiaAdolescente to set
     */
    public void setHistoriaAdolescente(String historiaAdolescente) {
        this.historiaAdolescente = historiaAdolescente;
    }

    /**
     * @return the comentarioConducta
     */
    public String getComentarioConducta() {
        return comentarioConducta;
    }

    /**
     * @param comentarioConducta the comentarioConducta to set
     */
    public void setComentarioConducta(String comentarioConducta) {
        this.comentarioConducta = comentarioConducta;
    }

    /**
     * @return the graficoProporcion
     */
    public String getGraficoProporcion() {
        return graficoProporcion;
    }

    /**
     * @param graficoProporcion the graficoProporcion to set
     */
    public void setGraficoProporcion(String graficoProporcion) {
        this.graficoProporcion = graficoProporcion;
    }

    /**
     * @return the graficoPerspectiva
     */
    public String getGraficoPerspectiva() {
        return graficoPerspectiva;
    }

    /**
     * @param graficoPerspectiva the graficoPerspectiva to set
     */
    public void setGraficoPerspectiva(String graficoPerspectiva) {
        this.graficoPerspectiva = graficoPerspectiva;
    }

    /**
     * @return the graficoDetalle
     */
    public String getGraficoDetalle() {
        return graficoDetalle;
    }

    /**
     * @param graficoDetalle the graficoDetalle to set
     */
    public void setGraficoDetalle(String graficoDetalle) {
        this.graficoDetalle = graficoDetalle;
    }

    /**
     * @return the interrogativoPosterior
     */
    public String getInterrogativoPosterior() {
        return interrogativoPosterior;
    }

    /**
     * @param interrogativoPosterior the interrogativoPosterior to set
     */
    public void setInterrogativoPosterior(String interrogativoPosterior) {
        this.interrogativoPosterior = interrogativoPosterior;
    }

    /**
     * @return the resumenPsicologo
     */
    public String getResumenPsicologo() {
        return resumenPsicologo;
    }

    /**
     * @param resumenPsicologo the resumenPsicologo to set
     */
    public void setResumenPsicologo(String resumenPsicologo) {
        this.resumenPsicologo = resumenPsicologo;
    }


    /**
     * @return the porcenjateRehabilitacionSistema
     */
    public double getPorcenjateRehabilitacionSistema() {
        return porcenjateRehabilitacionSistema;
    }

    /**
     * @param porcenjateRehabilitacionSistema the porcenjateRehabilitacionSistema to set
     */
    public void setPorcenjateRehabilitacionSistema(double porcenjateRehabilitacionSistema) {
        this.porcenjateRehabilitacionSistema = porcenjateRehabilitacionSistema;
    }

    /**
     * @return the porcenjateRehabilitacionEvaluador
     */
    public double getPorcenjateRehabilitacionEvaluador() {
        return porcenjateRehabilitacionEvaluador;
    }

    /**
     * @param porcenjateRehabilitacionEvaluador the porcenjateRehabilitacionEvaluador to set
     */
    public void setPorcenjateRehabilitacionEvaluador(double porcenjateRehabilitacionEvaluador) {
        this.porcenjateRehabilitacionEvaluador = porcenjateRehabilitacionEvaluador;
    }

    /**
     * @return the nivelRehabilitacionNombre
     */
    public String getNivelRehabilitacionNombre() {
        return nivelRehabilitacionNombre;
    }

    /**
     * @param nivelRehabilitacionNombre the nivelRehabilitacionNombre to set
     */
    public void setNivelRehabilitacionNombre(String nivelRehabilitacionNombre) {
        this.nivelRehabilitacionNombre = nivelRehabilitacionNombre;
    }

    /**
     * @return the porcenjateRehabilitacion
     */
    public double getPorcenjateRehabilitacion() {
        return porcenjateRehabilitacion;
    }

    /**
     * @param porcenjateRehabilitacion the porcenjateRehabilitacion to set
     */
    public void setPorcenjateRehabilitacion(double porcenjateRehabilitacion) {
        this.porcenjateRehabilitacion = porcenjateRehabilitacion;
    }

    /**
     * @return the nivelRehabilitacion
     */
    public int getNivelRehabilitacion() {
        return nivelRehabilitacion;
    }

    /**
     * @param nivelRehabilitacion the nivelRehabilitacion to set
     */
    public void setNivelRehabilitacion(int nivelRehabilitacion) {
        this.nivelRehabilitacion = nivelRehabilitacion;
    }

    /**
     * @return the evaluador
     */
    public Usuario getEvaluador() {
        return evaluador;
    }

    /**
     * @param evaluador the evaluador to set
     */
    public void setEvaluador(Usuario evaluador) {
        this.evaluador = evaluador;
    }
    
}
