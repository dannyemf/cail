/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.htp;

import java.util.Date;
import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class VwInformePsicologico extends DomainEntity{        
    
    private Long idAdolescente;
    
    private Date fechaTest;
    private Date fechaInforme;
    
    private String cedula;
    private String adolescente;
    private String evaluador;
    private String lugar;
    private int edad;
    private String estadoCivil;
    private String nivelInstruccion;
    private String resultadoTest;
    private String motivoIngreso;
    
    public VwInformePsicologico() {
    }

    /**
     * @return the fechaTest
     */
    public Date getFechaTest() {
        return fechaTest;
    }

    /**
     * @param fechaTest the fechaTest to set
     */
    public void setFechaTest(Date fechaTest) {
        this.fechaTest = fechaTest;
    }

    /**
     * @return the fechaInforme
     */
    public Date getFechaInforme() {
        return fechaInforme;
    }

    /**
     * @param fechaInforme the fechaInforme to set
     */
    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the adolescente
     */
    public String getAdolescente() {
        return adolescente;
    }

    /**
     * @param adolescente the adolescente to set
     */
    public void setAdolescente(String adolescente) {
        this.adolescente = adolescente;
    }

    /**
     * @return the evaluador
     */
    public String getEvaluador() {
        return evaluador;
    }

    /**
     * @param evaluador the evaluador to set
     */
    public void setEvaluador(String evaluador) {
        this.evaluador = evaluador;
    }

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the estadoCivil
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * @param estadoCivil the estadoCivil to set
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * @return the nivelInstruccion
     */
    public String getNivelInstruccion() {
        return nivelInstruccion;
    }

    /**
     * @param nivelInstruccion the nivelInstruccion to set
     */
    public void setNivelInstruccion(String nivelInstruccion) {
        this.nivelInstruccion = nivelInstruccion;
    }

    /**
     * @return the resultadoTest
     */
    public String getResultadoTest() {
        return resultadoTest;
    }

    /**
     * @param resultadoTest the resultadoTest to set
     */
    public void setResultadoTest(String resultadoTest) {
        this.resultadoTest = resultadoTest;
    }

    /**
     * @return the motivoIngreso
     */
    public String getMotivoIngreso() {
        return motivoIngreso;
    }

    /**
     * @param motivoIngreso the motivoIngreso to set
     */
    public void setMotivoIngreso(String motivoIngreso) {
        this.motivoIngreso = motivoIngreso;
    }

    /**
     * @return the idAdolescente
     */
    public Long getIdAdolescente() {
        return idAdolescente;
    }

    /**
     * @param idAdolescente the idAdolescente to set
     */
    public void setIdAdolescente(Long idAdolescente) {
        this.idAdolescente = idAdolescente;
    }

    

    
}
