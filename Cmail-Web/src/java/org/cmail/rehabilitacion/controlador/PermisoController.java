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
 * Controlador de los permisos de usuario para acceder a lás páginas y los controles.
 * Permite crear, editar, eliminar los permisos.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean (name= Constantes.MB_PERMISO) 
@SessionScoped
public class PermisoController  extends Controller{

    /** Bean de sesión */
    @ManagedProperty(value="#{"+ Constantes.MB_SESSION +"}")
    private SessionBean sessionBean;
    
    /** Nombre del permiso para realizar la búsqueda*/
    private String nombre = "";
    
    /** Lista de permisos encontrador por nombre*/
    private CmailListDataModel<Permiso> model;
    
    /** El permiso en edición*/
    private Permiso permisoEdicion;
    
    /**Constructor por defecto*/
    public PermisoController() {
    }

    /**
     * El bean de sessión a inyectar con ManagedProperty
     * @param sessionBean el bean
     */
    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    } 

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }        

    /**
     * @return the model
     */
    public CmailListDataModel<Permiso> getModel() {        
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(CmailListDataModel<Permiso> model) {
        this.model = model;
    }      
        
    /**
     * Evento invocado al presionar el botón buscar.
     * @param evt el evento
     */
    public void eventoBuscar(ActionEvent evt) {
        List<Permiso> lst = new PermisoServicio().from().where(K.like("nombre", nombre)).orderBy(K.asc("nombre")).list();
        showMessageResultList(lst);
        model = new CmailListDataModel<Permiso>(lst);
    }
    
    /**
     * Evento invocado al presionar el vínculo editar.
     * @param evt el evento
     */
    public void eventoEditar(ActionEvent evt) {        
        Permiso permiso = model.getRowData();        
        editar(permiso);
        
        //FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PERMISO);        
        runScript("dlgEditarPermiso.show();");
    }
    
    /**
     * Evento invocado al presionar el botón nuevo.     
     * @param evt el evento
     */
    public void eventoNuevo(ActionEvent evt) {        
        Permiso permiso = new Permiso();
        editar(permiso);
        
        
        //FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PERMISO);        
        runScript("dlgEditarPermiso.show();");
    }
    
    /**
     * Evento invocado al presionar el vínculo eliminar.
     * @param evt el evento
     */
    public void eventoEliminar(ActionEvent evt) {
        Permiso permiso = model.getRowData();
        boolean e = new PermisoServicio().eliminar(permiso);
        
        showMessageDeleted(e, permiso.getNombre());
    }
    
    /**
     * Método invocado por el evento nuevo y editar para inicializar el mdoelo de los perfiles.
     * @param permiso el permiso
     */
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
    
    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición del permiso.
     * @param evt el evento
     */
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
    
    /**
     * Evento invocado al presionar el botón cancelar en la edición de un permiso.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {
        new UsuarioServicio().refrescar(getPermisoEdicion());        
        //FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PERMISO);        
        runScript("dlgEditarPermiso.hide();");
    }   

    /**
     * Valida que el nombre del permiso no esté duplicado.
     * @param cont el contexto
     * @param cmp el componente
     * @param value el nombre del permiso
     */
    public void validarNombre(FacesContext cont, UIComponent cmp, Object value) {
        boolean b = new PermisoServicio().existe(getPermisoEdicion(), "nombre", value.toString());
        if (b) {
            validationMessage(mensajeBundle("val_nombre_existe"));
        }
    }    
    
    /**
     * Verifica si un determinado control (botón, vínculo, etc.) tiene el permiso indicado.
     * Es usado para habilitar o desabilitar controles. Ejemplo: disabled="#{permisoController.checkPermiso('usuario.editar')}".
     * @param permiso el nombre del permiso.
     * @return true si no tiene el permiso y false cuando tiene el permiso.
     */
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
