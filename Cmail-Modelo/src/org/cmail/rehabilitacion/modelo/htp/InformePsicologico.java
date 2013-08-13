/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.htp;

import java.util.Date;
import org.cmail.rehabilitacion.modelo.DomainEntity;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class InformePsicologico extends DomainEntity{
    
    private Persona adolescente;    
    private FichaIngreso fichaIngreso;
    private Date fecha = new Date();
    
    private String situacionPresente; // Sitiacion presente del adolescente
    private String anamnesisAntecedentes; //Anamnesis personal y antecedentes personales
    private String historiaLaboral; //Historia laboral del adolescente
    private String historiaSalud; //Historia de salud del adolescente
    private String examenFunciones; //Examen de funciones psíquicas
    private String informacionPuebasPsicologicas; // Información mas significativa de las pruebas psicológicas
    private String informacionReactivosTest; // Informacion mas significativa de los reactivos o test utilizados con su familia o persoa de su vinculo afectivo mas cercano
    private String impresioesDiagnosticasPsicologo; // Impresiones diagnósticas del psicolólogo    
    private String planAcompaniamiento; //Plan de acompañamiento al adolescente y su familia
    
    private String condicionesIdentidadFortalezas;
    private String condicionesIdentidadDebilidades;
    private String condicionesSaludFortalezas;
    private String condicionesSaludDebilidades;
    private String condicionesEducacionFortalezas;
    private String condicionesEducacionDebilidades;
    private String condicionesVinvulosFamiliaresFortalezas;
    private String condicionesVinculosFamiliaresDebilidades;
    private String condicionesFormacionTrabajoFortalezas;
    private String condicionesFormacionTrabajoDebilidades;

    public InformePsicologico() {
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
     * @return the situacionPresente
     */
    public String getSituacionPresente() {
        return situacionPresente;
    }

    /**
     * @param situacionPresente the situacionPresente to set
     */
    public void setSituacionPresente(String situacionPresente) {
        this.situacionPresente = situacionPresente;
    }

    /**
     * @return the anamnesisAntecedentes
     */
    public String getAnamnesisAntecedentes() {
        return anamnesisAntecedentes;
    }

    /**
     * @param anamnesisAntecedentes the anamnesisAntecedentes to set
     */
    public void setAnamnesisAntecedentes(String anamnesisAntecedentes) {
        this.anamnesisAntecedentes = anamnesisAntecedentes;
    }

    /**
     * @return the historiaLaboral
     */
    public String getHistoriaLaboral() {
        return historiaLaboral;
    }

    /**
     * @param historiaLaboral the historiaLaboral to set
     */
    public void setHistoriaLaboral(String historiaLaboral) {
        this.historiaLaboral = historiaLaboral;
    }

    /**
     * @return the historiaSalud
     */
    public String getHistoriaSalud() {
        return historiaSalud;
    }

    /**
     * @param historiaSalud the historiaSalud to set
     */
    public void setHistoriaSalud(String historiaSalud) {
        this.historiaSalud = historiaSalud;
    }  

    /**
     * @return the examenFunciones
     */
    public String getExamenFunciones() {
        return examenFunciones;
    }

    /**
     * @param examenFunciones the examenFunciones to set
     */
    public void setExamenFunciones(String examenFunciones) {
        this.examenFunciones = examenFunciones;
    }

    /**
     * @return the informacionPuebasPsicologicas
     */
    public String getInformacionPuebasPsicologicas() {
        return informacionPuebasPsicologicas;
    }

    /**
     * @param informacionPuebasPsicologicas the informacionPuebasPsicologicas to set
     */
    public void setInformacionPuebasPsicologicas(String informacionPuebasPsicologicas) {
        this.informacionPuebasPsicologicas = informacionPuebasPsicologicas;
    }

    /**
     * @return the informacionReactivosTest
     */
    public String getInformacionReactivosTest() {
        return informacionReactivosTest;
    }

    /**
     * @param informacionReactivosTest the informacionReactivosTest to set
     */
    public void setInformacionReactivosTest(String informacionReactivosTest) {
        this.informacionReactivosTest = informacionReactivosTest;
    }

    /**
     * @return the impresioesDiagnosticasPsicologo
     */
    public String getImpresioesDiagnosticasPsicologo() {
        return impresioesDiagnosticasPsicologo;
    }

    /**
     * @param impresioesDiagnosticasPsicologo the impresioesDiagnosticasPsicologo to set
     */
    public void setImpresioesDiagnosticasPsicologo(String impresioesDiagnosticasPsicologo) {
        this.impresioesDiagnosticasPsicologo = impresioesDiagnosticasPsicologo;
    }

    /**
     * @return the planAcompañamiento
     */
    public String getPlanAcompaniamiento() {
        return planAcompaniamiento;
    }

    /**
     * @param planAcompañamiento the planAcompañamiento to set
     */
    public void setPlanAcompaniamiento(String planAcompañamiento) {
        this.planAcompaniamiento = planAcompañamiento;
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
     * @return the condicionesIdentidadFortalezas
     */
    public String getCondicionesIdentidadFortalezas() {
        return condicionesIdentidadFortalezas;
    }

    /**
     * @param condicionesIdentidadFortalezas the condicionesIdentidadFortalezas to set
     */
    public void setCondicionesIdentidadFortalezas(String condicionesIdentidadFortalezas) {
        this.condicionesIdentidadFortalezas = condicionesIdentidadFortalezas;
    }

    /**
     * @return the condicionesIdentidadDebilidades
     */
    public String getCondicionesIdentidadDebilidades() {
        return condicionesIdentidadDebilidades;
    }

    /**
     * @param condicionesIdentidadDebilidades the condicionesIdentidadDebilidades to set
     */
    public void setCondicionesIdentidadDebilidades(String condicionesIdentidadDebilidades) {
        this.condicionesIdentidadDebilidades = condicionesIdentidadDebilidades;
    }

    /**
     * @return the condicionesSaludFortalezas
     */
    public String getCondicionesSaludFortalezas() {
        return condicionesSaludFortalezas;
    }

    /**
     * @param condicionesSaludFortalezas the condicionesSaludFortalezas to set
     */
    public void setCondicionesSaludFortalezas(String condicionesSaludFortalezas) {
        this.condicionesSaludFortalezas = condicionesSaludFortalezas;
    }

    /**
     * @return the condicionesSaludDebilidades
     */
    public String getCondicionesSaludDebilidades() {
        return condicionesSaludDebilidades;
    }

    /**
     * @param condicionesSaludDebilidades the condicionesSaludDebilidades to set
     */
    public void setCondicionesSaludDebilidades(String condicionesSaludDebilidades) {
        this.condicionesSaludDebilidades = condicionesSaludDebilidades;
    }

    /**
     * @return the condicionesEducacionFortalezas
     */
    public String getCondicionesEducacionFortalezas() {
        return condicionesEducacionFortalezas;
    }

    /**
     * @param condicionesEducacionFortalezas the condicionesEducacionFortalezas to set
     */
    public void setCondicionesEducacionFortalezas(String condicionesEducacionFortalezas) {
        this.condicionesEducacionFortalezas = condicionesEducacionFortalezas;
    }

    /**
     * @return the condicionesEducacionDebilidades
     */
    public String getCondicionesEducacionDebilidades() {
        return condicionesEducacionDebilidades;
    }

    /**
     * @param condicionesEducacionDebilidades the condicionesEducacionDebilidades to set
     */
    public void setCondicionesEducacionDebilidades(String condicionesEducacionDebilidades) {
        this.condicionesEducacionDebilidades = condicionesEducacionDebilidades;
    }

    /**
     * @return the condicionesVinvulosFamiliaresFortalezas
     */
    public String getCondicionesVinvulosFamiliaresFortalezas() {
        return condicionesVinvulosFamiliaresFortalezas;
    }

    /**
     * @param condicionesVinvulosFamiliaresFortalezas the condicionesVinvulosFamiliaresFortalezas to set
     */
    public void setCondicionesVinvulosFamiliaresFortalezas(String condicionesVinvulosFamiliaresFortalezas) {
        this.condicionesVinvulosFamiliaresFortalezas = condicionesVinvulosFamiliaresFortalezas;
    }

    /**
     * @return the condicionesVinculosFamiliaresDebilidades
     */
    public String getCondicionesVinculosFamiliaresDebilidades() {
        return condicionesVinculosFamiliaresDebilidades;
    }

    /**
     * @param condicionesVinculosFamiliaresDebilidades the condicionesVinculosFamiliaresDebilidades to set
     */
    public void setCondicionesVinculosFamiliaresDebilidades(String condicionesVinculosFamiliaresDebilidades) {
        this.condicionesVinculosFamiliaresDebilidades = condicionesVinculosFamiliaresDebilidades;
    }

    /**
     * @return the condicionesFormacionTrabajoFortalezas
     */
    public String getCondicionesFormacionTrabajoFortalezas() {
        return condicionesFormacionTrabajoFortalezas;
    }

    /**
     * @param condicionesFormacionTrabajoFortalezas the condicionesFormacionTrabajoFortalezas to set
     */
    public void setCondicionesFormacionTrabajoFortalezas(String condicionesFormacionTrabajoFortalezas) {
        this.condicionesFormacionTrabajoFortalezas = condicionesFormacionTrabajoFortalezas;
    }

    /**
     * @return the condicionesFormacionTrabajoDebilidades
     */
    public String getCondicionesFormacionTrabajoDebilidades() {
        return condicionesFormacionTrabajoDebilidades;
    }

    /**
     * @param condicionesFormacionTrabajoDebilidades the condicionesFormacionTrabajoDebilidades to set
     */
    public void setCondicionesFormacionTrabajoDebilidades(String condicionesFormacionTrabajoDebilidades) {
        this.condicionesFormacionTrabajoDebilidades = condicionesFormacionTrabajoDebilidades;
    }
    
    
}
