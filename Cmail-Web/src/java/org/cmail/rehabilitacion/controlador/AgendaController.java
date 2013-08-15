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
import org.cmail.rehabilitacion.modelo.core.CedulaUtil;
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

    public void eventoNuevaEvento(ActionEvent evt) {
        Evento act = new Evento();
        initAudit(act);
        getAgendaEdicion().addEvento(act);
    }

    public void eventoRemoverEvento(ActionEvent evt) {
        HtmlDataTable tableActividades = getComponent(HtmlDataTable.class, "frmEditActividad:tablaActividades");
        Evento ac = (Evento)tableActividades.getRowData();
        getAgendaEdicion().removeEvento(ac);        
    }

    public void eventoRemoverEventos(ActionEvent evt) {
        getAgendaEdicion().getEventos().clear();
    }


    public void eventoNuevo(ActionEvent evt) {
        Agenda p = new Agenda();
        p.setResponsable(new Persona());
        initAudit(p);
        FacesUtils.getSessionBean().setAgendaEdicion(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_AGENDA);
    }

    public void eventoEditar(ActionEvent evt) {
        FacesUtils.getSessionBean().setAgendaEdicion(getModelAgendas().getRowData());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_AGENDA);
    }
    
    public void eventoVer(ActionEvent evt) {
        FacesUtils.getSessionBean().setAgendaEdicion(getModelAgendas().getRowData());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_VER_AGENDA);
    }

    public void eventoGuardar(ActionEvent evt) {
        boolean b = new AgendaServicio().guardar(getAgendaEdicion());
        showMessageSaved(b);
        
        if (b) {
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_AGENDAS);
        }
    }

    public void eventoCancelar(ActionEvent evt) {
        if (getAgendaEdicion() != null && getAgendaEdicion().getId().longValue() != -1L) {
            new AgendaServicio().refrescar(getAgendaEdicion());
        }
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_AGENDAS);
    }

    public void eventoBuscar(ActionEvent evt) {
        if (fechaDesde != null && fechaHasta != null) {
            List<Agenda> lst = new AgendaServicio().listarAgendasByFecha(fechaDesde, fechaHasta);
            setModelAgendas(new CmailListDataModel<Agenda>(lst));
            showMessageResultList(lst);
        }
    }

    public void eventoFinalizar(ActionEvent evt) {
        FacesUtils.getSessionBean().setAgendaEdicion(getModelAgendas().getRowData());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_FLZ_AGENDA);
    }

    public void eventoGuardarFinalizado(ActionEvent evt) {
        getAgendaEdicion().setEstado(Agenda.ESTADO_FINALIZADO);
        eventoGuardar(evt);
    }
    
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

    public void accionLimpiarResponsable(ActionEvent evt) {
        getAgendaEdicion().setResponsable(new Persona());
    }

    public void validarResponsable(FacesContext cont, UIComponent cmp, Object value) {        
        if(value == null || value.toString().equals("-1")){
            validationMessage("Seleccione el responsable");
        }        
    }
    
    public void validarCedula(FacesContext cont, UIComponent cmp, Object value) {
        boolean b = CedulaUtil.validar(value.toString());
        if (!b) {
            validationMessage(mensajeBundle("val_cedula_incorrecta"));
        }
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

    public boolean isDisabledTodas() {
        if (getAgendaEdicion().getEventos().isEmpty()) {
            return true;
        }
        return false;
    }
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
    

}
