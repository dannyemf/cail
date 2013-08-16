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
 * Controlador de cuentas de usuario.
 * Permite crear, editar, eliminar y desactivar las cuentas de usuario.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean (name= Constantes.MB_USUARIO) 
@ViewScoped
public class UsuarioController  extends Controller{

    /**
     * Lista de usuarios
     */
    private CmailListDataModel<Usuario> modelUsuarios;
    private boolean testCailDisables = true;
    
    /**Constructor por defecto*/
    public UsuarioController() {
    }

    /**
     * @return the modelUsuarios
     */
    public CmailListDataModel<Usuario> getModelUsuarios() {
        List<Usuario> lst = new UsuarioServicio().listarTodos();
        modelUsuarios = new CmailListDataModel<Usuario>(lst);
        return modelUsuarios;
    }
    
    /**
     * Evento invocado al presionar el vínculo editar.
     * @param evt el evento
     */
    public void eventoEditar(ActionEvent evt) {        
        Usuario usuario = modelUsuarios.getRowData();        
        editar(usuario);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_USUARIO);        
    }
    
    /**
     * Evento invocado al presionar el botón nuevo.     
     * @param evt el evento
     */
    public void eventoNuevo(ActionEvent evt) {        
        Usuario usuario = new Usuario();
        editar(usuario);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_USUARIO);        
    }
    
    /**
     * Evento invocado al presionar el vínculo eliminar.
     * @param evt el evento
     */
    public void eventoEliminar(ActionEvent evt) {
        Usuario usuario = modelUsuarios.getRowData();
        boolean e = new UsuarioServicio().eliminar(usuario);
        
        showMessageDeleted(e, usuario.getLogin());
    }
    
    /**
     * Método invocado por el evento nuevo y editar para inicializar los perfiles y la auditoría.
     * @param usuario el usaurio
     */
    private void editar(Usuario usuario) {
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
    
    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición del usuario.
     * @param evt el evento
     */
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
    
    /**
     * Evento invocado al presionar el botón cancelar en la edición de un usuario.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {
        new UsuarioServicio().refrescar(getUsuarioEdicion());        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_USUARIO);        
    }   

    /**
     * Verifica que el login del usaurio no esté duplicado.
     * @param cont el contexto
     * @param cmp el componente
     * @param value el login ingresado
     */
    public void validarLogin(FacesContext cont, UIComponent cmp, Object value) {
        boolean b = new UsuarioServicio().existe(getUsuarioEdicion(), "login", value.toString());
        if (b) {
            validationMessage(mensajeBundle("val_login_existe"));
        }
    }
    
    /**
     * Valida que el número de cédula ingresado no esté duplicado y sea correcto.
     * @param cont el contexto
     * @param cmp el componente
     * @param value la cédula ingresada
     */
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
    
    /**
     * @return the usuarioEdicion
     */
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
