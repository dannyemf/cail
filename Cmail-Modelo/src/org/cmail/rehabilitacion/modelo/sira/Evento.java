/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.sira;

import java.util.Date;
import org.cmail.rehabilitacion.modelo.AuditEntity;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Evento extends AuditEntity {

    private Date fechaEjecucion = new Date();
    private String lugar;
    private String descripcion;
    private int porcentajeEjecucion = 0;
    private Agenda agenda;
    private boolean selected;

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
