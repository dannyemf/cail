/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.sira;

import java.util.Date;
import org.cmail.rehabilitacion.modelo.AuditEntity;

/**
 * Entidad que representa un evento o actividad dentro de una planificación (agenda).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Evento extends AuditEntity {

    /**
     * Fecha de cuando debe llevarse a cabo
     */
    private Date fechaEjecucion = new Date();
    
    /**
     * Lugar donde debe realizarse
     */
    private String lugar;
    
    /**
     * Descripción de lo que debe realizarse
     */
    private String descripcion;
    
    /**
     * Porcentaje de ejecución del evento
     */
    private int porcentajeEjecucion = 0;
    
    /**
     * Agenda a la que pertenece
     */
    private Agenda agenda;
    
    private boolean selected;

    /**
     * Constructor por defecto
     */
    public Evento() {
    }

    /**
     * @return the fechEjecucion
     */
    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    /**
     * @param fechEjecucion the fechEjecucion to set
     */
    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @return the planificacion
     */
    public Agenda getAgenda() {
        return agenda;
    }

    /**
     * @param agenda the agenda to set
     */
    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
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
    public String toString() {
        return "Evento{" + "id=" + getId() + ", fechEjecucion=" + fechaEjecucion + ", lugar=" + lugar + ", descripcion=" + descripcion + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || (obj != null && obj instanceof Evento == false)) {
            return false;
        } else {
            Evento p = (Evento) obj;
            if (p.getId().longValue() == this.getId().longValue()) {
                return true;
            }
        }
        return false;
    }

   

    /**
     * @return the porcentajeEjecucion
     */
    public int getPorcentajeEjecucion() {
        return porcentajeEjecucion;
    }

    /**
     * @param porcentajeEjecucion the porcentajeEjecucion to set
     */
    public void setPorcentajeEjecucion(int porcentajeEjecucion) {
        this.porcentajeEjecucion = porcentajeEjecucion;
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
