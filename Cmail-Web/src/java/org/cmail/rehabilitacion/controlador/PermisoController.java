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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.controlador.bean.SessionBean;
import org.cmail.rehabilitacion.dao.hql.K;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.seguridad.Perfil;
import org.cmail.rehabilitacion.modelo.seguridad.Permiso;
import org.cmail.rehabilitacion.servicio.PerfilServicio;
import org.cmail.rehabilitacion.servicio.PermisoServicio;
import org.cmail.rehabilitacion.servicio.UsuarioServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean (name= Constantes.MB_PERMISO) 
@SessionScoped
public class PermisoController  extends Controller{

    @ManagedProperty(value="#{"+ Constantes.MB_SESSION +"}")
    private SessionBean sessionBean;
    private String nombre = "";
    
    private CmailListDataModel<Permiso> model;
    private Permiso permisoEdicion;
    
    /** Creates a new instance of PermisoController */
    public PermisoController() {
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    } 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }        

    public CmailListDataModel<Permiso> getModel() {        
        return model;
    }

    public void setModel(CmailListDataModel<Permiso> model) {
        this.model = model;
    }      
        
    
    public void eventoBuscar(ActionEvent evt) {
        List<Permiso> lst = new PermisoServicio().from().where(K.like("nombre", nombre)).orderBy(K.asc("nombre")).list();
        showMessageResultList(lst);
        model = new CmailListDataModel<Permiso>(lst);
    }
    
    public void eventoEditar(ActionEvent evt) {        
        Permiso permiso = model.getRowData();        
        editar(permiso);
        
        //FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PERMISO);        
        runScript("dlgEditarPermiso.show();");
    }
    
    public void eventoNuevo(ActionEvent evt) {        
        Permiso permiso = new Permiso();
        editar(permiso);
        
        
        //FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PERMISO);        
        runScript("dlgEditarPermiso.show();");
    }
    
    public void eventoEliminar(ActionEvent evt) {
        Permiso permiso = model.getRowData();
        boolean e = new PermisoServicio().eliminar(permiso);
        
        showMessageDeleted(e, permiso.getNombre());
    }
    
    public void editar(Permiso permiso) {
        initAudit(permiso);        
        
        this.setPermisoEdicion(permiso);   
        
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
            FacesUtils.addErrorMessage(mensajeBundle("val_perfil_seleccione"));
            //showMensaje(TipoNotificacion.Error, mensajeBundle("val_perfil_seleccione"));            
            getPermisoEdicion().setPerfiles(perfiles);
        }else{        
            
            boolean b = new PermisoServicio().guardar(getPermisoEdicion());
            showMessageSaved(b);

            if (b) {            
                //FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PERMISO);            
                runScript("dlgEditarPermiso.hide();");
            }else{
                getPermisoEdicion().setPerfiles(perfiles);
            }
        }
    }
    
    public void eventoCancelar(ActionEvent evt) {
        new UsuarioServicio().refrescar(getPermisoEdicion());        
        //FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PERMISO);        
        runScript("dlgEditarPermiso.hide();");
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
    
    public boolean checkPermiso(String permiso){
        Permiso objPermiso = null;
        List<Permiso> permisos = sessionBean.getPermisosUsuario();
        for (Iterator<Permiso> it = permisos.iterator(); it.hasNext();) {
            Permiso p = it.next();
            if(p.getNombre().equals(permiso)){
                objPermiso = p;
                break;
            }
        }
        
        
        if(objPermiso != null){
            return false;
        }else{        
            return true;
        }
    }

    /**
     * @param permisoEdicion the permisoEdicion to set
     */
    public void setPermisoEdicion(Permiso permisoEdicion) {
        this.permisoEdicion = permisoEdicion;
    }

    /**
     * @return the permisoEdicion
     */
    public Permiso getPermisoEdicion() {
        return permisoEdicion;
    }
    
}
