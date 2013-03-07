/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.Date;
import java.util.List;
import org.cmail.rehabilitacion.modelo.sira.Agenda;

/**
 *
 * @author Usuario
 */
public class AgendaServicio extends GenericServicio<Agenda> {

    public AgendaServicio() {
        super(Agenda.class);
    }
    
    public List<Agenda> listarAgendasByFecha(Date fechaDesde, Date fechaHasta){
        List<Agenda> listaPlanificaciones = super.listarPorRangoFechas("fechaInicio", fechaDesde, fechaHasta);
        return listaPlanificaciones;               
    }
    
}
