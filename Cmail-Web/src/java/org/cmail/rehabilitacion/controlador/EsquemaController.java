/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.sira.Esquema;
import org.cmail.rehabilitacion.modelo.sira.EsquemaPregunta;
import org.cmail.rehabilitacion.servicio.EsquemaServicio;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador de esquemas de pregiuntas para el test htp.
 * Permite hacer las búsquedas, crear, editar, eliminar los esquemas.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_ESQUEMA)
@ViewScoped
public class EsquemaController extends Controller {

    private EsquemaPregunta preguntaSeleccionada;
    
    /**Constructor por defecto*/
    public EsquemaController() {
    }

    /**
     * Evento invocado al presionar el botón nuevo.     
     * @param evt el evento
     */
    public void eventoNuevo(ActionEvent evt) {
        //crear aqui todo lo nuevo o inicializar en el SessionBean? o en FichaServicioS
        Esquema fi = new Esquema();
        initAudit(fi);

        FacesUtils.getSessionBean().setEsquemaEdicion(fi);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ESQUEMA);
        FacesUtils.getMenuController().addRoute("Nuevo");
    }

    /**
     * Evento invocado por el botón nueva pregunta.
     * @param evt el evento
     */
    public void eventoNuevaPregunta(ActionEvent evt) {        
        Esquema esq = getEsquemaEdicion();
        esq.addPregunta(new EsquemaPregunta());
    }
    
    /**
     * Evento invocado el vínculo remover pregunta.
     * Muestra un mensaje de confirmación.
     * 
     * @param esquemaPregunta la pregunta
     */
    public void eventoRemoverPreguntaSelect(EsquemaPregunta esquemaPregunta) {        
        preguntaSeleccionada = esquemaPregunta;
        runScript("pnlConfEliminar.show();");
    }
    
    /**
     * Evento invocada para confirmar que se debe remover la pregunta.
     * Cierra el mensaje de confirmación.
     * @param evt el evento
     */
    public void eventoRemoverPreguntaOk(ActionEvent evt) {        
        Esquema esq = getEsquemaEdicion();        
        if(preguntaSeleccionada != null){
            esq.getPreguntas().remove(preguntaSeleccionada);
        }
        runScript("pnlConfEliminar.hide();");
    }
   
    /**
     * Evento invocado al presionar el vínculo editar.
     * @param evt el evento
     */
    public void eventoEditar(ActionEvent evt) {
        Esquema fichaIngreso = getModelEsquema().getRowData();
        initAudit(fichaIngreso);

        FacesUtils.getSessionBean().setEsquemaEdicion(fichaIngreso);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ESQUEMA);   
        FacesUtils.getMenuController().addRoute("Editar");
    }
    
    /**
     * Obtiene las preguntas del esquema actual ordenadas por número.
     * @return lista de preguntas
     */
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

    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición.
     * @param evt el evento
     */
    public void eventoGuardar(ActionEvent evt) {
        boolean b = new EsquemaServicio().guardarEsquema(getEsquemaEdicion());
        showMessageSaved(b);
        
        if (b) {
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_ESQUEMA);
            FacesUtils.getMenuController().clearLastRoute();
        }
    }   

    /**
     * Evento invocado al presionar el botón cancelar en la edición de un esquema.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {
        if (getEsquemaEdicion() != null && getEsquemaEdicion().getId().longValue() != -1L) {
            new FichaIngresoServicio().refrescar(getEsquemaEdicion());
        }
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_ESQUEMA);
        FacesUtils.getMenuController().clearLastRoute();
    }

    /**
     * Evento invocado al presionar el botón buscar.
     * @param evt el evento
     */
    public void eventoBuscar(ActionEvent evt) {
        List<Esquema> listaFichas = new EsquemaServicio().listarTodos();        
        setModelEsquema(new CmailListDataModel<Esquema>(listaFichas));
    }
    
    public WucBuscarPersonaController getWucBuscarPersona() {
        return FacesUtils.getBean(Constantes.MB_WUC_BUSCAR_PERSONA, WucBuscarPersonaController.class);
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