/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import com.icesoft.faces.component.ext.HtmlDataTable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.controlador.event.ActionListenerWucBuscarPersona;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.PersonaRol;
import org.cmail.rehabilitacion.modelo.sira.Agenda;
import org.cmail.rehabilitacion.modelo.sira.Evento;
import org.cmail.rehabilitacion.servicio.AgendaServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador de agendas.
 * Permite hacer las búsquedas, crear, editar, finalizar y ver las agendas.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_AGENDA)
@ViewScoped
public class AgendaController extends Controller {   
    
    private Date fechaDesde = new Date();
    private Date fechaHasta = new Date();

    /**Constructor por defecto*/
    public AgendaController() {
    }

    /**
     * Evento invocado por el botón nuevo evento.
     * @param evt el evento
     */
    public void eventoNuevaEvento(ActionEvent evt) {
        Evento act = new Evento();
        initAudit(act);
        getAgendaEdicion().addEvento(act);
    }

    /**
     * Evento invocado por el botón remover los eventos seleccionados.
     * @param evt el evento
     */
    public void eventoRemoverEvento(ActionEvent evt) {
        HtmlDataTable tableActividades = getComponent(HtmlDataTable.class, "frmEditActividad:tablaActividades");
        Evento ac = (Evento)tableActividades.getRowData();
        getAgendaEdicion().removeEvento(ac);        
    }

    /**
     * Evento invocado por el botón remover todos los eventos
     * @param evt el evento
     */
    public void eventoRemoverEventos(ActionEvent evt) {
        getAgendaEdicion().getEventos().clear();
    }


    /**
     * Evento invocado al presionar el botón nuevo.     
     * @param evt el evento
     */
    public void eventoNuevo(ActionEvent evt) {
        Agenda p = new Agenda();
        p.setResponsable(new Persona());
        initAudit(p);
        FacesUtils.getSessionBean().setAgendaEdicion(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_AGENDA);
    }

    /**
     * Evento invocado al presionar el vínculo editar.
     * @param evt el evento
     */
    public void eventoEditar(ActionEvent evt) {
        FacesUtils.getSessionBean().setAgendaEdicion(getModelAgendas().getRowData());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_AGENDA);
    }
    
    /**
     * Evento invocado al presionar el vínculo ver.
     * @param evt el evento
     */
    public void eventoVer(ActionEvent evt) {
        FacesUtils.getSessionBean().setAgendaEdicion(getModelAgendas().getRowData());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_VER_AGENDA);
    }

    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición.
     * @param evt el evento
     */
    public void eventoGuardar(ActionEvent evt) {
        boolean b = new AgendaServicio().guardar(getAgendaEdicion());
        showMessageSaved(b);
        
        if (b) {
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_AGENDAS);
        }
    }

    /**
     * Evento invocado al presionar el botón cancelar en la edición de una agenda.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {
        if (getAgendaEdicion() != null && getAgendaEdicion().getId().longValue() != -1L) {
            new AgendaServicio().refrescar(getAgendaEdicion());
        }
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_AGENDAS);
    }

    /**
     * Evento invocado al presionar el botón buscar.
     * @param evt el evento
     */
    public void eventoBuscar(ActionEvent evt) {
        if (fechaDesde != null && fechaHasta != null) {
            List<Agenda> lst = new AgendaServicio().listarAgendasByFecha(fechaDesde, fechaHasta);
            setModelAgendas(new CmailListDataModel<Agenda>(lst));
            showMessageResultList(lst);
        }
    }

    /**
     * Evento invocado al presiona el vínculo finalizar agenda
     * @param evt el evento
     */
    public void eventoFinalizar(ActionEvent evt) {
        FacesUtils.getSessionBean().setAgendaEdicion(getModelAgendas().getRowData());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_FLZ_AGENDA);
    }

    /**
     * Evento invocado al presional el botón finalizar en la ventana de finalización de una agenda.
     * @param evt el evento
     */
    public void eventoGuardarFinalizado(ActionEvent evt) {
        getAgendaEdicion().setEstado(Agenda.ESTADO_FINALIZADO);
        eventoGuardar(evt);
    }
    
    /**
     * Evento para buscar el empleado responsable de la ejecución de la agenda.
     * @param evt el evento
     */
    public void accionBuscarResponsable(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {

            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                Agenda p = AgendaController.this.getAgendaEdicion();
                if (this.getPersona() != null) {
                    p.setResponsable(this.getPersona());
                    this.getWuc().accionCerrar(ae);
                }
            }
        }, PersonaRol.GENERAL, null);
    }

    /**
     * Evento invocado en el control del responsable para editar la información
     * @param evt el evento
     */
    public void accionEditarResponsable(ActionEvent evt) {
        Persona p = getAgendaEdicion().getResponsable();
        if (p != null) {
            getWucBuscarPersona().mostrarEditor(p, PersonaRol.EMPLEADO, new ActionListenerWucBuscarPersona() {

                @Override
                public void processAction(ActionEvent ae) throws AbortProcessingException {
                    this.getWuc().accionCerrar(ae);
                }
            });
        }
    }

    /**
     * Evento invocado al presionar el botón limpiar del control del resposnable.
     * @param evt el evento
     */
    public void accionLimpiarResponsable(ActionEvent evt) {
        getAgendaEdicion().setResponsable(new Persona());
    }

    /**
     * Valida que se haya seleccionado un responsable
     * @param cont el contexto
     * @param cmp el componente
     * @param value el valor actual
     */
    public void validarResponsable(FacesContext cont, UIComponent cmp, Object value) {        
        if(value == null || value.toString().equals("-1")){
            validationMessage("Seleccione el responsable");
        }        
    }       
    
    /**
     * Verifica si debe desabilitarse el botón remover todos los eventos
     * @return true si existe por lo menos un evento
     */
    public boolean isDisabledTodas() {
        if (getAgendaEdicion().getEventos().isEmpty()) {
            return true;
        }
        return false;
    }
    
    /**
     * Verifica si debe desabilitarse el botón remover los eventos seleccionados
     * @return true si existe por lo menos un evento seleccionado
     */
    public boolean isDisabledSeleccionadas() {
        boolean selected=true;
        if (getAgendaEdicion().getEventos().isEmpty()) {
            return true;
        }
        for (Iterator<Evento> it = getAgendaEdicion().getEventos().iterator(); it.hasNext();) {
            Evento ac = it.next();
            if(ac.isSelected()){
                selected =false;
                break;
            }
        }        
        return selected;
    }

    public Agenda getAgendaEdicion() {
        return FacesUtils.getSessionBean().getAgendaEdicion();
    }

    public ListDataModel<Agenda> getModelAgendas() {
        CmailListDataModel<Agenda> lm = (CmailListDataModel<Agenda>) FacesUtils.getSessionBean().getSessionMap("modelAgendas");
        if (lm == null) {
            lm = new CmailListDataModel<Agenda>();
        }
        return lm;
    }

    public void setModelAgendas(CmailListDataModel<Agenda> model) {
        FacesUtils.getSessionBean().addSessionMap("modelAgendas", model);
    }   

    public WucBuscarPersonaController getWucBuscarPersona() {
        return FacesUtils.getBean(Constantes.MB_WUC_BUSCAR_PERSONA, WucBuscarPersonaController.class);
    }

    /**
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
     * @param fechaDesde the fechaDesde to set
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
     * @param fechaHasta the fechaHasta to set
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }    

}
