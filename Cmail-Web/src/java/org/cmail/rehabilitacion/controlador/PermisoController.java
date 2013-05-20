/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.seguridad.Perfil;
import org.cmail.rehabilitacion.modelo.seguridad.Permiso;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.servicio.PerfilServicio;
import org.cmail.rehabilitacion.servicio.PermisoServicio;
import org.cmail.rehabilitacion.servicio.UsuarioServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.model.TipoNotificacion;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 *
 * @author Usuario
 */
@ManagedBean (name= Constantes.MB_PERMISO) 
@ViewScoped
public class PermisoController  extends Controller{

    private CmailListDataModel<Permiso> model;    
    
    /** Creates a new instance of AdminUsuariosController */
    public PermisoController() {
    }

    public CmailListDataModel<Permiso> getModel() {
        List<Permiso> lst = new PermisoServicio().listarTodos();
        model = new CmailListDataModel<Permiso>(lst);
        return model;
    }
    
    public void eventoEditar(ActionEvent evt) {        
        Permiso permiso = model.getRowData();        
        editar(permiso);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PERMISO);        
    }
    
    public void eventoNuevo(ActionEvent evt) {        
        Permiso permiso = new Permiso();
        editar(permiso);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PERMISO);        
    }
    
    public void eventoEliminar(ActionEvent evt) {
        Permiso permiso = model.getRowData();
        boolean e = new PermisoServicio().eliminar(permiso);
        
        showMessageDeleted(e, permiso.getNombre());
    }
    
    public void editar(Permiso permiso) {
        initAudit(permiso);        
        
        FacesUtils.getSessionBean().setPermisoEdicion(permiso);        
        
        for (Iterator<Perfil> it = permiso.getPerfiles().iterator(); it.hasNext();) {
            it.next().setSeleccionado(true);
        }
        
        List<Perfil> lst = new PerfilServicio().listarTodos();
        for (Iterator<Perfil> it = lst.iterator(); it.hasNext();) {
            Perfil p = it.next();            
            if(getPermisoEdicion().getPerfiles().contains(p) == false){
                p.setSeleccionado(false);
                getPermisoEdicion().addPerfil(p);
            }                
        }        
    }
    
    public void eventoGuardar(ActionEvent evt) {
        
        boolean tienePerfil = false;
        Set<Perfil> perfiles = new HashSet<Perfil>(getPermisoEdicion().getPerfiles());        
        for (Iterator<Perfil> it = perfiles.iterator(); it.hasNext();) {
            Perfil p = it.next();
            
            if(p.isSeleccionado()){
                tienePerfil = true;
            }else{
                getPermisoEdicion().removePerfil(p);
            }
        }
        
        if(!tienePerfil){
            showMensaje(TipoNotificacion.Error, mensajeBundle("val_perfil_seleccione"));            
        }else{        
            
            boolean b = new PermisoServicio().guardar(getPermisoEdicion());
            showMessageSaved(b);

            if (b) {            
                FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PERMISO);            
            }else{
                getPermisoEdicion().setPerfiles(perfiles);
            }
        }
    }
    
    public void eventoCancelar(ActionEvent evt) {
        new UsuarioServicio().refrescar(getPermisoEdicion());        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PERMISO);        
    }   

    /**
     * valida que no exista un usuario con el mismo login
     * @param cont
     * @param cmp
     * @param value 
     */
    public void validarNombre(FacesContext cont, UIComponent cmp, Object value) {
        boolean b = new PermisoServicio().existe(getPermisoEdicion(), "nombre", value.toString());
        if (b) {
            validationMessage(mensajeBundle("val_nombre_existe"));
        }
    }
    
    
    public Permiso getPermisoEdicion() {
        return FacesUtils.getSessionBean().getPermisoEdicion();
    }
    
    public boolean checkPermiso(String permiso){        
        Permiso p = new PermisoServicio().obtenerPrimerPor("nombre", permiso);        
        if(p != null){
            Usuario us = getUsuarioLogeado();
            for (Iterator<Perfil> it = p.getPerfiles().iterator(); it.hasNext();) {
                Perfil perfil = it.next();
                if(us.getPerfiles().contains(perfil)){
                    return false;
                }
            }
            
            return true;
        }else{        
            return false;
        }
    }
    
}
