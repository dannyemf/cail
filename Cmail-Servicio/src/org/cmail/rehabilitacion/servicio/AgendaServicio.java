/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.Date;
import java.util.List;
import org.cmail.rehabilitacion.modelo.sira.Agenda;

/**
 * Clase de lógica de negocio para manejar agendas.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class AgendaServicio extends GenericServicio<Agenda> {

    /**
     * Constructor por defecto
     */
    public AgendaServicio() {
        super(Agenda.class);
    }
    
    /**
     * Lista todas las agenda en el rango de fechas indicado.
     * @param fechaDesde la fecha inicial
     * @param fechaHasta la fecha final
     * @return lista de agendas
     */
    public List<Agenda> listarAgendasByFecha(Date fechaDesde, Date fechaHasta){
        List<Agenda> listaPlanificaciones = super.listarPorRangoFechas("fechaInicio", fechaDesde, fechaHasta);
        return listaPlanificaciones;               
    }
    
}
