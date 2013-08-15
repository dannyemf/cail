/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.ListDataModel;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.controlador.event.ActionListenerWucMultimedia;
import org.cmail.rehabilitacion.modelo.sira.ArticuloWeb;
import org.cmail.rehabilitacion.servicio.BaseServicio;
import org.cmail.rehabilitacion.servicio.GenericServicio;
import org.cmail.rehabilitacion.servicio.PerfilServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;
import org.icefaces.ace.component.datetimeentry.DateTimeEntry;

/**
 * Controlador de artículos o noticias.
 * Permite hacer las búsquedas, crear, editar, eliminar los artículos.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_ARTICULO_WEB)
@ViewScoped
public class ArticuloWebController extends Controller {        
    
    private CmailListDataModel<ArticuloWeb> model;        
    
    private String criterio = "NOMBRE";
    private String texto = "";
    private Date fechaInicial = new Date();
    private Date fechaFinal = new Date();    
    

    /**Constructor por defecto*/
    public ArticuloWebController() {        
    }    
    
    /**
     * Evento invocado al seleccioanr el tab de resumen y contenido del artículo
     * @param evt el evento 
     */
    public void eventoSelTab(ValueChangeEvent evt){
        getWucMultimedia().accionCerrar(new ActionEvent(evt.getComponent()));
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ARTICULO);
    } 
    
    /**
     * Evento invocado al cambiar el criterio de búsqueda con la finalidad de pasar los validadores y presentar los nuevos controles.
     * @param evt el evento 
     */
    public void eventoChangeCriterio(ValueChangeEvent evt){
        //Setea la ultima fecha fijada para evitar que coja las validaciones y se renderize las fechas (ocultar cuando estén en error)
        DateTimeEntry cf1 =  (DateTimeEntry)evt.getComponent().getParent().findComponent("form:sidFechaDesde");
        DateTimeEntry cf2 =  (DateTimeEntry)evt.getComponent().getParent().findComponent("form:sidFechaHasta");
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        cf1.setSubmittedValue(df.format(fechaInicial));
        cf2.setSubmittedValue(df.format(fechaFinal));
    }

    /**
     * Evento invocado al presionar el vínculo editar.
     * @param evt el evento
     */
    public void eventoEditar(ActionEvent evt) {
        ArticuloWeb e = model.getRowData();
        initAudit(e);        
        FacesUtils.getSessionBean().setParrafoEdicion(e);
        setSelectedTab(0);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ARTICULO);
    }

    /**
     * Evento invocado al presionar el botón nuevo.     
     * @param evt el evento
     */
    public void eventoNuevo(ActionEvent evt) {
        ArticuloWeb e = new ArticuloWeb();
        super.initAudit(e);
        
        FacesUtils.getSessionBean().setParrafoEdicion(e);
        setSelectedTab(0);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ARTICULO);
        FacesUtils.getMenuController().addRoute("Nuevo");
    }
    
    /**
     * Evento invocado al presionar el botón buscar.
     * @param evt el evento
     */
    public void eventoBuscar(ActionEvent evt) {
        
        List<ArticuloWeb> lst = new ArrayList<ArticuloWeb>();
        GenericServicio<ArticuloWeb> _srv = new GenericServicio<ArticuloWeb>(ArticuloWeb.class);        
        
        if(this.criterio.equals("NOMBRE")){
            lst = _srv.listarPorPropiedadLike("titulo", texto);
        }else{
            lst = _srv.listarPorRangoFechas("auditCreateDate", fechaInicial, fechaFinal);
        }
        
        
        this.model = new CmailListDataModel<ArticuloWeb>(lst);
        showMessageResultList(lst);
    }

    /**
     * Evento invocado al presionar el vínculo eliminar.
     * @param evt el evento
     */
    public void eventoEliminar(ActionEvent evt) {
        ArticuloWeb p = model.getRowData();
        initAudit(p);
        
        BaseServicio bs = new BaseServicio();
        showMessageDeleted(bs.eliminar(p));
    }
    
    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición.
     * @param evt el evento
     */
    public void eventoGuardar(ActionEvent evt) {
        
        if(getArticuloEdicion().isPaginaPrincipal() == false){
            if(getArticuloEdicion().getResumen() == null || (getArticuloEdicion().getResumen() != null && getArticuloEdicion().getResumen().trim().length() == 0)){
                errorMessage("form:txtResumen", mensajeBundle("resumenRequerido"));
                return;
            }
        }
        
        if(getArticuloEdicion().isPaginaPrincipal()){
            if((getArticuloEdicion().getDescripcion() == null || (getArticuloEdicion().getDescripcion() != null && getArticuloEdicion().getDescripcion().trim().length() == 0))){
                errorMessage("form:txtDescripcion", mensajeBundle("descripRequerido"));
                return;
            }
        }
        
        boolean b = new BaseServicio().guardar(getArticuloEdicion());
        showMessageSaved(b);
        
        if (b) {
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_ARTICULOS);            
        }
    }

    /**
     * Evento invocado al presionar el botón cancelar en la edición de un artículo.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {
        new PerfilServicio().refrescar(getArticuloEdicion());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_ARTICULOS);
    }
    
    /**
     * Evento invocado por el botón para seleccionar agregar las imágenes del resumen.
     * Abre el diálogo del wuc multimedia.
     * @param evt el evento 
     */
    public void seleccionarImagenResumen(ActionEvent evt){       
        getWucMultimedia().mostar(new ActionListenerWucMultimedia(){
            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                log().info(this.getImgTag());
                //getArticuloEdicion().setResumen(getArticuloEdicion().getResumen() + this.getImgTag());                                
            }                   
        },"form:txtResumen", "Insertar al resumen");
    }
    
    /**
     * Evento invocado por el botón para seleccionar agregar las imágenes del contenido.
     * Abre el diálogo del wuc multimedia.
     * @param evt el evento 
     */
    public void seleccionarImagenContenido(ActionEvent e){ 
        getWucMultimedia().mostar(new ActionListenerWucMultimedia(){
            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {                                
                //getArticuloEdicion().setDescripcion(getArticuloEdicion().getDescripcion() + this.getImgTag());
            }                   
        },"form:txtDescripcion","Insertar a la página");
    }
        
    public ArticuloWeb getArticuloEdicion() {
        return FacesUtils.getSessionBean().getParrafoEdicion();
    }
    
    public ListDataModel<ArticuloWeb> getModel() {
        return model;
    }
    
    public WucMultimediaController getWucMultimedia(){
        return FacesUtils.getBean(Constantes.MB_WUC_MULTIMEDIA, WucMultimediaController.class);
    }        

    public void setModel(CmailListDataModel<ArticuloWeb> model) {
        this.model = model;
    }   

    /**
     * @return the criterio
     */
    public String getCriterio() {
        return criterio;
    }

    /**
     * @param criterio the criterio to set
     */
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    
    public boolean isRenderFecha(){
        if(criterio.equals("FECHA")){
            return true;
        }
        return false;
    }
    
    public boolean isRenderTexto(){
        if(criterio.equals("NOMBRE")){
            return true;
        }
        return false;
    }
    
    public int getSelectedTab(){
        Integer s = (Integer)getSessionBean().getSessionMap("currentTabArticulo");
        if (s == null){
            s = 0;
            getSessionBean().addSessionMap("currentTabArticulo", s);
        }
        
        return s;
    }
    
    public void setSelectedTab(int s){
        getSessionBean().addSessionMap("currentTabArticulo", s);
    }
          
}
