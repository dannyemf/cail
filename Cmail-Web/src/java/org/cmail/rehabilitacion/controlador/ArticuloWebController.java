/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import org.cmail.rehabilitacion.modelo.sira.ImagenWeb;
import org.cmail.rehabilitacion.servicio.BaseServicio;
import org.cmail.rehabilitacion.servicio.GenericServicio;
import org.cmail.rehabilitacion.servicio.ImagenWebServicio;
import org.cmail.rehabilitacion.servicio.PerfilServicio;
import org.cmail.rehabilitacion.vista.model.ImageFile;
import org.cmail.rehabilitacion.vista.util.FacesUtils;
import org.icefaces.ace.component.richtextentry.RichTextEntry;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = Constantes.MB_ARTICULO_WEB)
@ViewScoped
public class ArticuloWebController extends Controller {        
    
    private CmailListDataModel<ArticuloWeb> model;        
    
    private String criterio = "NOMBRE";
    private String texto = "";
    private Date fechaInicial = new Date();
    private Date fechaFinal = new Date();    
    

    /** Creates a new instance of PerfilController */
    public ArticuloWebController() {        
    }
    
    private void initImages(){
        List<ImagenWeb> lst = new ImagenWebServicio().listarImagenesWeb();
        for (Iterator<ImagenWeb> it = lst.iterator(); it.hasNext();) {
            ImagenWeb im = it.next();
            new ImageFile(im);
        }
    }   
    
    public void eventoSelTab(ValueChangeEvent e){
        getWucMultimedia().accionCerrar(new ActionEvent(e.getComponent()));
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ARTICULO);
    }        

    public void eventoEditar(ActionEvent evt) {
        ArticuloWeb e = model.getRowData();
        initAudit(e);        
        FacesUtils.getSessionBean().setParrafoEdicion(e);
        setSelectedTab(0);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ARTICULO);
    }

    public void eventoNuevo(ActionEvent evt) {
        ArticuloWeb e = new ArticuloWeb();
        super.initAudit(e);
        
        FacesUtils.getSessionBean().setParrafoEdicion(e);
        setSelectedTab(0);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ARTICULO);
        FacesUtils.getMenuController().addRoute("Nuevo");
    }
    
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

    public void eventoEliminar(ActionEvent evt) {
        ArticuloWeb p = model.getRowData();
        initAudit(p);
        
        BaseServicio bs = new BaseServicio();
        showMessageDeleted(bs.eliminar(p));
    }
    
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

    public void eventoCancelar(ActionEvent evt) {
        new PerfilServicio().refrescar(getArticuloEdicion());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_ARTICULOS);
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
    
    public void seleccionarImagenResumen(ActionEvent e){       
        getWucMultimedia().mostar(new ActionListenerWucMultimedia(){
            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                log().info(this.getImgTag());
                //getArticuloEdicion().setResumen(getArticuloEdicion().getResumen() + this.getImgTag());                                
            }                   
        },"form:txtResumen", "Insertar al resumen");
    }
    
    public void seleccionarImagenContenido(ActionEvent e){ 
        getWucMultimedia().mostar(new ActionListenerWucMultimedia(){
            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {                                
                //getArticuloEdicion().setDescripcion(getArticuloEdicion().getDescripcion() + this.getImgTag());
            }                   
        },"form:txtDescripcion","Insertar a la página");
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
