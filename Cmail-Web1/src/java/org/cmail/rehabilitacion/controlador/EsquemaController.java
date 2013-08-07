/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import com.icesoft.faces.component.ext.HtmlDataTable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.sira.Esquema;
import org.cmail.rehabilitacion.modelo.sira.EsquemaPregunta;
import org.cmail.rehabilitacion.servicio.EsquemaServicio;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 *
 * @author Desarrollador
 */
@ManagedBean(name = Constantes.MB_ESQUEMA)
@ViewScoped
public class EsquemaController extends Controller {

    private EsquemaPregunta preguntaSeleccionada;
    
    /** Creates a new instance of PerfilController */

    public EsquemaController() {
    }

    public void eventoNuevo(ActionEvent evt) {
        //crear aqui todo lo nuevo o inicializar en el SessionBean? o en FichaServicioS
        Esquema fi = new Esquema();
        initAudit(fi);

        FacesUtils.getSessionBean().setEsquemaEdicion(fi);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ESQUEMA);
        FacesUtils.getMenuController().addRoute("Nuevo");
    }

    public void eventoNuevaPregunta(ActionEvent evt) {        
        Esquema esq = getEsquemaEdicion();
        esq.addPregunta(new EsquemaPregunta());
    }
    
    public void eventoRemoverPreguntaSelect(EsquemaPregunta esquemaPregunta) {        
        preguntaSeleccionada = esquemaPregunta;
        runScript("pnlConfEliminar.show();");
    }
    
    public void eventoRemoverPreguntaOk(ActionEvent evt) {        
        Esquema esq = getEsquemaEdicion();        
        if(preguntaSeleccionada != null){
            esq.getPreguntas().remove(preguntaSeleccionada);
        }
        runScript("pnlConfEliminar.hide();");
    }
   
    public void eventoEditar(ActionEvent evt) {
        Esquema fichaIngreso = getModelEsquema().getRowData();
        initAudit(fichaIngreso);

        FacesUtils.getSessionBean().setEsquemaEdicion(fichaIngreso);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ESQUEMA);   
        FacesUtils.getMenuController().addRoute("Editar");
    }
    
    public List<EsquemaPregunta> getPreguntas(){
        List<EsquemaPregunta> lst = new ArrayList<EsquemaPregunta>(getEsquemaEdicion().getPreguntas());
        
        Collections.sort(lst, 
        new Comparator<EsquemaPregunta>() {
            @Override
            public int compare(EsquemaPregunta o1, EsquemaPregunta o2) {
                int n1 = o1.getNumero();
                int n2 = o2.getNumero();
                
                if(n1 > n2){
                    return 1;
                }else{
                    if(n1 < n2){
                    return -1;
                    }                    
                }
                
                return 0;
                
            }
        }
        );
        
        return lst;
    }

    public void eventoGuardar(ActionEvent evt) {
        boolean b = new EsquemaServicio().guardarEsquema(getEsquemaEdicion());
        showMessageSaved(b);
        
        if (b) {
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_ESQUEMA);
            FacesUtils.getMenuController().clearLastRoute();
        }
    }

    public WucBuscarPersonaController getWucBuscarPersona() {
        return FacesUtils.getBean(Constantes.MB_WUC_BUSCAR_PERSONA, WucBuscarPersonaController.class);
    }

    public void eventoCancelar(ActionEvent evt) {
        if (getEsquemaEdicion() != null && getEsquemaEdicion().getId().longValue() != -1L) {
            new FichaIngresoServicio().refrescar(getEsquemaEdicion());
        }
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_ESQUEMA);
        FacesUtils.getMenuController().clearLastRoute();
    }

    public void eventoBuscar(ActionEvent evt) {
        List<Esquema> listaFichas = new EsquemaServicio().listarTodos();        
        setModelEsquema(new CmailListDataModel<Esquema>(listaFichas));
    }

    public Esquema getEsquemaEdicion() {
        return FacesUtils.getSessionBean().getEsquemaEdicion();
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }
    
    public CmailListDataModel<Esquema> getModelEsquema() {
        CmailListDataModel<Esquema> lm = (CmailListDataModel<Esquema>) FacesUtils.getSessionBean().getSessionMap("modelEsquema");
        if (lm == null) {
            lm = new CmailListDataModel<Esquema>();
        }
        return lm;
    }
        
    public void setModelEsquema(CmailListDataModel<Esquema> model) {
        FacesUtils.getSessionBean().addSessionMap("modelEsquema", model);
    }

    public void setFacesMessage(String facesMessage) {
        FacesMessage m = new FacesMessage(facesMessage);
        m.setSeverity(FacesMessage.SEVERITY_FATAL);
        throw new ValidatorException(m);
    }

    private HtmlDataTable table = new HtmlDataTable();

    /**
     * @return the table
     */
    public HtmlDataTable getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(HtmlDataTable table) {
        this.table = table;
    }

    /**
     * @return the preguntaSeleccionada
     */
    public EsquemaPregunta getPreguntaSeleccionada() {
        return preguntaSeleccionada;
    }

    /**
     * @param preguntaSeleccionada the preguntaSeleccionada to set
     */
    public void setPreguntaSeleccionada(EsquemaPregunta preguntaSeleccionada) {
        this.preguntaSeleccionada = preguntaSeleccionada;
    }
    
}