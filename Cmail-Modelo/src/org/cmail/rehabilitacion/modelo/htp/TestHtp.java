/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.htp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.cmail.rehabilitacion.modelo.AuditEntity;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.modelo.sira.Esquema;
import org.cmail.rehabilitacion.modelo.sira.EsquemaPregunta;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;

/**
 * Formulacion de evaluación H-T-P
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class TestHtp extends AuditEntity{
    
    private Persona adolescente;
    private Usuario evaluador;
    private Esquema esquema;
    private FichaIngreso fichaIngreso;
    
    private Date fecha = new Date();
    private Date horaInicio = new Date();
    private Date horaFin = new Date();
    
    private int edadAdolescente = 0;
    private int duracionMinutos = 0;
    
    private int tiempoLatenciaCasa = 0;
    private int tiempoLatenciaArbol = 0;
    private int tiempoLatenciaPersona = 0;
    
    private int tiempoDuracionCasa = 0;
    private int tiempoDuracionArbol = 0;
    private int tiempoDuracionPersona = 0;
    
    private byte[] dibujoCasa;
    private byte[] dibujoArbol;
    private byte[] dibujoPersona;
    
    private Set<TestHtpRespuesta> respuestas = new HashSet<TestHtpRespuesta>();

    public TestHtp() {
    }        
    
    public void addRespuesta(EsquemaPregunta esq){
        TestHtpRespuesta rta = new TestHtpRespuesta();
        rta.setEsquemaPregunta(esq);
        this.getRespuestas().add(rta);
        rta.setFormulario(this);
        rta.setRespuesta("");
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
     * @return the evaluador
     */
    public Usuario getEvaluador() {
        return evaluador;
    }
    
    public void calcularDuracion(){
        
    }

    /**
     * @param evaluador the evaluador to set
     */
    public void setEvaluador(Usuario evaluador) {
        this.evaluador = evaluador;
    }

    /**
     * @return the esquema
     */
    public Esquema getEsquema() {
        return esquema;
    }

    /**
     * @param esquema the esquema to set
     */
    public void setEsquema(Esquema esquema) {
        this.esquema = esquema;
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
     * @return the edadAdolescente
     */
    public int getEdadAdolescente() {
        return edadAdolescente;
    }

    /**
     * @param edadAdolescente the edadAdolescente to set
     */
    public void setEdadAdolescente(int edadAdolescente) {
        this.edadAdolescente = edadAdolescente;
    }

    /**
     * @return the tiempoLatenciaCasa
     */
    public int getTiempoLatenciaCasa() {
        return tiempoLatenciaCasa;
    }

    /**
     * @param tiempoLatenciaCasa the tiempoLatenciaCasa to set
     */
    public void setTiempoLatenciaCasa(int tiempoLatenciaCasa) {
        this.tiempoLatenciaCasa = tiempoLatenciaCasa;
    }

    /**
     * @return the tiempoLatenciaArbol
     */
    public int getTiempoLatenciaArbol() {
        return tiempoLatenciaArbol;
    }

    /**
     * @param tiempoLatenciaArbol the tiempoLatenciaArbol to set
     */
    public void setTiempoLatenciaArbol(int tiempoLatenciaArbol) {
        this.tiempoLatenciaArbol = tiempoLatenciaArbol;
    }

    /**
     * @return the tiempoLatenciaPersona
     */
    public int getTiempoLatenciaPersona() {
        return tiempoLatenciaPersona;
    }

    /**
     * @param tiempoLatenciaPersona the tiempoLatenciaPersona to set
     */
    public void setTiempoLatenciaPersona(int tiempoLatenciaPersona) {
        this.tiempoLatenciaPersona = tiempoLatenciaPersona;
    }

    /**
     * @return the tiempoDuracionCasa
     */
    public int getTiempoDuracionCasa() {
        return tiempoDuracionCasa;
    }

    /**
     * @param tiempoDuracionCasa the tiempoDuracionCasa to set
     */
    public void setTiempoDuracionCasa(int tiempoDuracionCasa) {
        this.tiempoDuracionCasa = tiempoDuracionCasa;
    }

    /**
     * @return the tiempoDuracionArbol
     */
    public int getTiempoDuracionArbol() {
        return tiempoDuracionArbol;
    }

    /**
     * @param tiempoDuracionArbol the tiempoDuracionArbol to set
     */
    public void setTiempoDuracionArbol(int tiempoDuracionArbol) {
        this.tiempoDuracionArbol = tiempoDuracionArbol;
    }

    /**
     * @return the tiempoDuracionPersona
     */
    public int getTiempoDuracionPersona() {
        return tiempoDuracionPersona;
    }

    /**
     * @param tiempoDuracionPersona the tiempoDuracionPersona to set
     */
    public void setTiempoDuracionPersona(int tiempoDuracionPersona) {
        this.tiempoDuracionPersona = tiempoDuracionPersona;
    }

    /**
     * @return the dibujoCasa
     */
    public byte[] getDibujoCasa() {
        return dibujoCasa;
    }

    /**
     * @param dibujoCasa the dibujoCasa to set
     */
    public void setDibujoCasa(byte[] dibujoCasa) {
        this.dibujoCasa = dibujoCasa;
    }

    /**
     * @return the dibujoArbol
     */
    public byte[] getDibujoArbol() {
        return dibujoArbol;
    }

    /**
     * @param dibujoArbol the dibujoArbol to set
     */
    public void setDibujoArbol(byte[] dibujoArbol) {
        this.dibujoArbol = dibujoArbol;
    }

    /**
     * @return the dibujoPersona
     */
    public byte[] getDibujoPersona() {
        return dibujoPersona;
    }

    /**
     * @param dibujoPersona the dibujoPersona to set
     */
    public void setDibujoPersona(byte[] dibujoPersona) {
        this.dibujoPersona = dibujoPersona;
    }

    /**
     * @return the respuestas
     */
    public Set<TestHtpRespuesta> getRespuestas() {
        return respuestas;
    }

    /**
     * @param respuestas the respuestas to set
     */
    public void setRespuestas(Set<TestHtpRespuesta> respuestas) {
        this.respuestas = respuestas;
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
     * @return the horaInicio
     */
    public Date getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the horaFin
     */
    public Date getHoraFin() {
        return horaFin;
    }

    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * @return the duracionMinutos
     */
    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    /**
     * @param duracionMinutos the duracionMinutos to set
     */
    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }
    
    
    
}
