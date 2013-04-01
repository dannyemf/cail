/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.core.CedulaUtil;
import org.cmail.rehabilitacion.modelo.seguridad.Perfil;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.servicio.PerfilServicio;
import org.cmail.rehabilitacion.servicio.UsuarioServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.model.TipoNotificacion;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 *
 * @author Usuario
 */
@ManagedBean (name= Constantes.MB_USUARIO) 
@ViewScoped
public class UsuarioController  extends Controller{

    private CmailListDataModel<Usuario> modelUsuarios;
    private boolean testCailDisables = true;
    
    /** Creates a new instance of AdminUsuariosController */
    public UsuarioController() {
    }

    public CmailListDataModel<Usuario> getModelUsuarios() {
        List<Usuario> lst = new UsuarioServicio().listarTodos();
        modelUsuarios = new CmailListDataModel<Usuario>(lst);
        return modelUsuarios;
    }
    
    public void eventoEditar(ActionEvent evt) {        
        Usuario usuario = modelUsuarios.getRowData();        
        editar(usuario);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_USUARIO);        
    }
    
    public void eventoNuevo(ActionEvent evt) {        
        Usuario usuario = new Usuario();
        editar(usuario);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_USUARIO);        
    }
    
    public void eventoEliminar(ActionEvent evt) {
        Usuario usuario = modelUsuarios.getRowData();
        boolean e = new UsuarioServicio().eliminar(usuario);
        
        showMessageDeleted(e, usuario.getLogin());
    }
    
    public void editar(Usuario usuario) {
        initAudit(usuario);        
        
        FacesUtils.getSessionBean().setUsuarioEdicion(usuario);        
        
        for (Iterator<Perfil> it = usuario.getPerfiles().iterator(); it.hasNext();) {
            it.next().setSeleccionado(true);
        }
        
        List<Perfil> lst = new PerfilServicio().listarTodos();
        for (Iterator<Perfil> it = lst.iterator(); it.hasNext();) {
            Perfil p = it.next();            
            if(getUsuarioEdicion().getPerfiles().contains(p) == false){
                p.setSeleccionado(false);
                getUsuarioEdicion().addPerfil(p);
            }                
        }        
    }
    
    public void eventoGuardar(ActionEvent evt) {
        
        boolean tienePerfil = false;
        for (Iterator<Perfil> it = getUsuarioEdicion().getPerfiles().iterator(); it.hasNext();) {
            if(it.next().isSeleccionado()){
                tienePerfil = true;
            }            
        }
        
        if(!tienePerfil){
            showMensaje(TipoNotificacion.Error, mensajeBundle("val_perfil_seleccione"));            
        }else{        
            boolean b = new UsuarioServicio().guardar(getUsuarioEdicion(), getUsuarioLogeado());
            showMessageSaved(b);

            if (b) {            
                FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_USUARIO);            
            }
        }
    }
    
    public void eventoCancelar(ActionEvent evt) {
        new UsuarioServicio().refrescar(getUsuarioEdicion());        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_USUARIO);        
    }   

    /**
     * valida que no exista un usuario con el mismo login
     * @param cont
     * @param cmp
     * @param value 
     */
    public void validarLogin(FacesContext cont, UIComponent cmp, Object value) {
        boolean b = new UsuarioServicio().existe(getUsuarioEdicion(), "login", value.toString());
        if (b) {
            validationMessage(mensajeBundle("val_login_existe"));
        }
    }
    public void validarCedula(FacesContext cont, UIComponent cmp, Object value) {
        boolean b = CedulaUtil.validar(value.toString());
        if (b) {
            boolean bi = new UsuarioServicio().existe(getUsuarioEdicion(), "cedula",value.toString());
            if (bi) {
                validationMessage(mensajeBundle("val_cedula_registrada"));
            }
        } else {
            validationMessage(mensajeBundle("val_cedula_incorrecta"));
        }        
    }
    
    public Usuario getUsuarioEdicion() {
        return FacesUtils.getSessionBean().getUsuarioEdicion();
    }
    
    public void eventoTestCailDisabled(ActionEvent evt) {
        testCailDisables = !testCailDisables;
    }        

    /**
     * @return the testCailDisables
     */
    public boolean isTestCailDisables() {
        return testCailDisables;
    }

    /**
     * @param testCailDisables the testCailDisables to set
     */
    public void setTestCailDisables(boolean testCailDisables) {
        this.testCailDisables = testCailDisables;
    }
    
}
