/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.localizacion.Canton;
import org.cmail.rehabilitacion.modelo.localizacion.Parroquia;
import org.cmail.rehabilitacion.modelo.localizacion.Provincia;
import org.cmail.rehabilitacion.servicio.BaseServicio;
import org.cmail.rehabilitacion.servicio.DivisionPoliticaServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador de provincias, cantones y parroquias.
 * Permite hacer las búsquedas, crear, editar, eliminar las provincias, cantones y parroquias.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_DIVISION)
@ViewScoped
public class DivisionPoliticalController  extends Controller{
    
    private CmailListDataModel<Provincia> modelProvincias = new CmailListDataModel<Provincia>();    
    private CmailListDataModel<Canton> modelCantones = new CmailListDataModel<Canton>();    
    private CmailListDataModel<Parroquia> modelParroquias = new CmailListDataModel<Parroquia>();    

    /**Constructor por defecto*/
    public DivisionPoliticalController() {
    }
        
    public void eventoNuevaProvincia(ActionEvent evt) {
        Provincia p = new Provincia();
        initAudit(p); 
        
        FacesUtils.getSessionBean().setProvinciaEdicion(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PROVINCIA);
        this.addRoute("nueva_provincia");
    }
    
    public void eventoEditarProvincia(ActionEvent evt) {
        Provincia p = modelProvincias.getRowData();           
        FacesUtils.getSessionBean().setProvinciaEdicion(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PROVINCIA);
        this.addRoute("editar_provincia");
    }
    
    public void eventoEliminarProvincia(ActionEvent evt) {
        Provincia p = modelProvincias.getRowData();
        BaseServicio bs = new BaseServicio();
        showMessageDeleted(bs.eliminar(p));
    }    
    
    public void eventoCancelarProvincia(ActionEvent evt) {        
        new DivisionPoliticaServicio().refrescar(getProvinciaEdicion());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PROVINCIAS);
        FacesUtils.getMenuController().clearRoute();
    }
    
    public void eventoGuardarProvincia(ActionEvent evt) {
        
        boolean b = new BaseServicio().guardar(getProvinciaEdicion());
        showMessageSaved(b);
        
        if (b) {
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PROVINCIAS);
            FacesUtils.getMenuController().clearRoute();
        }
    }
    
    
    
    

    public void eventoNuevoCanton(ActionEvent evt) {
        Canton c = new Canton();
        c.setProvincia(getProvinciaEdicion());
        initAudit(c);
        
        FacesUtils.getSessionBean().setCantonEdicion(c);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_CANTON);
        this.addRoute("nuevo_canton");
    }
    
    public void eventoEditarCanton(ActionEvent evt) {
        FacesUtils.getSessionBean().setCantonEdicion(modelCantones.getRowData());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_CANTON);
        this.addRoute("editar_canton");
    }

    public void eventoEliminarCanton(ActionEvent evt) {
        Canton c = modelCantones.getRowData();
        if(c.getId().longValue() == -1){
            getProvinciaEdicion().getCantones().remove(c);
        }else{
            BaseServicio bs = new BaseServicio();
            if (bs.eliminar(c)) {
                getProvinciaEdicion().getCantones().remove(c);
            }
        }
        
        getModelCantones();
    }
    
    public void eventoGuardarCanton(ActionEvent evt) {
        Canton p = getCantonEdicion();
        BaseServicio bs = new BaseServicio();
        if(p.getProvincia().getId().longValue() > -1){
            boolean b = bs.guardar(p);
            if(b){
                p.getProvincia().getCantones().add(p);
            }
        }else{
            p.getProvincia().getCantones().add(p);
        }
        
        getModelCantones();
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PROVINCIA);
        FacesUtils.getMenuController().clearLastRoute();
    }
    
    public void eventoCancelarCanton(ActionEvent evt) {
        new DivisionPoliticaServicio().refrescar(getCantonEdicion());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PROVINCIA);
        FacesUtils.getMenuController().clearLastRoute();
    }
    
    
    
    public void eventoNuevaParroquia(ActionEvent evt) {
        Parroquia c = new Parroquia();
        c.setCanton(getCantonEdicion());
        initAudit(c);
        
        FacesUtils.getSessionBean().setParroquiaEdicion(c);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PARROQUIA);
        this.addRoute("nueva_parroquia");
    }
    
    public void eventoEditarParroquia(ActionEvent evt) {
        FacesUtils.getSessionBean().setParroquiaEdicion(modelParroquias.getRowData());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PARROQUIA);
        this.addRoute("editar_parroquia");
    }

    public void eventoEliminarParroquia(ActionEvent evt) {
        Parroquia c = modelParroquias.getRowData();
        if(c.getId().longValue() == -1){            
            getCantonEdicion().getParroquias().remove(c);
        }else{
            BaseServicio bs = new BaseServicio();
            if (bs.eliminar(c)) {
                getCantonEdicion().getParroquias().remove(c);
            }
        }
        
        getModelParroquias();
    }
    
    public void eventoGuardarParroquia(ActionEvent evt) {
        Parroquia p = getParroquiaEdicion();
        BaseServicio bs = new BaseServicio();
        if(p.getCanton().getId().longValue() > -1){
            boolean b = bs.guardar(p);
            if(b){
                p.getCanton().getParroquias().add(p);
            }
        }else{
            p.getCanton().getParroquias().add(p);
        }
        
        getModelParroquias();
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_CANTON);
        FacesUtils.getMenuController().clearLastRoute();
    }
    
    public void eventoCancelarParroquia(ActionEvent evt) {
        new DivisionPoliticaServicio().refrescar(getParroquiaEdicion());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_CANTON);
        FacesUtils.getMenuController().clearLastRoute();
    }

    

    

    public Provincia getProvinciaEdicion() {
        return FacesUtils.getSessionBean().getProvinciaEdicion();
    }
    
    public Canton getCantonEdicion() {
        return FacesUtils.getSessionBean().getCantonEdicion();
    }
    
    public Parroquia getParroquiaEdicion() {
        return FacesUtils.getSessionBean().getParroquiaEdicion();
    }

    /**
     * @return the modelProvincias
     */
    public CmailListDataModel<Provincia> getModelProvincias() {
        modelProvincias = new CmailListDataModel<Provincia>(new DivisionPoliticaServicio().listarProvincias());
        return modelProvincias;
    }

    /**
     * @param modelProvincias the modelProvincias to set
     */
    public void setModelProvincias(CmailListDataModel<Provincia> modelProvincias) {
        this.modelProvincias = modelProvincias;
    }

    /**
     * @return the modelCantones
     */
    public CmailListDataModel<Canton> getModelCantones() {
        modelCantones = new CmailListDataModel<Canton>(new ArrayList<Canton>(getProvinciaEdicion().getCantones()));
        return modelCantones;
    }

    /**
     * @param modelCantones the modelCantones to set
     */
    public void setModelCantones(CmailListDataModel<Canton> modelCantones) {
        this.modelCantones = modelCantones;
    }

    /**
     * @return the modelParroquias
     */
    public CmailListDataModel<Parroquia> getModelParroquias() {
        modelParroquias = new CmailListDataModel<Parroquia>(new ArrayList<Parroquia>(getCantonEdicion().getParroquias()));
        return modelParroquias;
    }

    /**
     * @param modelParroquias the modelParroquias to set
     */
    public void setModelParroquias(CmailListDataModel<Parroquia> modelParroquias) {
        this.modelParroquias = modelParroquias;
    }
    
    
}
