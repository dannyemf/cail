/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.sira;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.cmail.rehabilitacion.modelo.AuditEntity;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Agenda extends AuditEntity {

    //analisis
    //datos informativos ==> fecha + lugar + (descripcion)
    //objetivos ==> fin de la planificacion    
    //recursos ==> responsable + partcipantes
    //actividades ==> actividades
    public static final String ESTADO_REGISTRADO = "REGISTRADA";
    public static final String ESTADO_FINALIZADO = "FINALIZADA";
    private Date fechaInicio = new Date();
    private Date fechaFin = new Date();
    private String objetivo;
    private String descripcion;
    private String participantes;
    // analizar si es una lista de actividades.
    private Set<Evento> eventos = new HashSet<Evento>();
    private String observaciones;
    private Persona responsable;
    private String estado = ESTADO_REGISTRADO;
    private String reporteFinal;
    //para fines de auditoria
    private Usuario usuario;

    public Agenda() {
    }

    public void addEvento(Evento a) {
        this.getEventos().add(a);
        a.setAgenda(this);
    }

    public boolean removeEvento(Evento a) {
        return this.getEventos().remove(a);
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the participantes
     */
    public String getParticipantes() {
        return participantes;
    }

    /**
     * @param participantes the participantes to set
     */
    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }

    /**
     * @return the objetivo
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * @param objetivo the objetivo to set
     */
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    /**
     * @return the reponsable
     */
    public Persona getResponsable() {
        return responsable;
    }

    /**
     * @param reponsable the reponsable to set
     */
    public void setResponsable(Persona responsable) {
        this.responsable = responsable;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || (obj != null && obj instanceof Agenda == false)) {
            return false;
        } else {
            Agenda p = (Agenda) obj;
            if (p.getId().longValue() == this.getId().longValue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    /**
     * @return the actividades
     */
    public Set<Evento> getEventos() {
        return eventos;
    }

    /**
     * @param eventos the eventos to set
     */
    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the reporteFinal
     */
    public String getReporteFinal() {
        return reporteFinal;
    }

    /**
     * @param reporteFinal the reporteFinal to set
     */
    public void setReporteFinal(String reporteFinal) {
        this.reporteFinal = reporteFinal;
    }
    
    public boolean isEstadoFinalizado(){
        if(estado.equals(ESTADO_FINALIZADO)){
            return true;
        }
        return false;
    }
}
