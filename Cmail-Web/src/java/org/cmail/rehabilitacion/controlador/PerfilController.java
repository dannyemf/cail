/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.seguridad.Perfil;
import org.cmail.rehabilitacion.servicio.BaseServicio;
import org.cmail.rehabilitacion.servicio.PerfilServicio;
import org.cmail.rehabilitacion.servicio.UsuarioServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador de los perfiles de usuario.
 * Permite crear, editar, eliminar y asignar los permisos a los perfiles de usuario.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_PERFIL)
@ViewScoped
public class PerfilController  extends Controller{
    
    /**
     * la lista de perfiles
     */
    private CmailListDataModel<Perfil> modelPerfiles;

    /**Constructor por defecto*/
    public PerfilController() {
    }

    /**
     * Evento invocado al presionar el vínculo editar.
     * @param evt el evento
     */
    public void eventoEditar(ActionEvent evt) {
        Perfil p = modelPerfiles.getRowData();
        initAudit(p);
        FacesUtils.getSessionBean().setPerfilEdicion(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PERFIL);
    }

    /**
     * Evento invocado al presionar el botón nuevo.     
     * @param evt el evento
     */
    public void eventoNuevo(ActionEvent evt) {
        Perfil p = new Perfil();
        initAudit(p);
        FacesUtils.getSessionBean().setPerfilEdicion(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PERFIL);
    }

    /**
     * Evento invocado al presionar el vínculo eliminar.
     * @param evt el evento
     */
    public void eventoEliminar(ActionEvent evt) {
        Perfil perfil = modelPerfiles.getRowData();
        BaseServicio bs = new BaseServicio();
        boolean b = bs.eliminar(perfil);
        
        showMessageDeleted(b, perfil.getNombre());
    }

    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición del perfil.
     * @param evt el evento
     */
    public void eventoGuardar(ActionEvent evt) {
        boolean b = new BaseServicio().guardar(getPerfilEdicion());
        showMessageSaved(b);
        
        if (b) {
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PERFIL);
        }
    }

    /**
     * Evento invocado al presionar el botón cancelar en la edición de un perfil.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {
        new PerfilServicio().refrescar(getPerfilEdicion());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PERFIL);
    }
    
    /**
     * Valida que el nombre del perfil no esté duplicado.
     * @param cont el contexto
     * @param cmp el componente
     * @param value el nombre del perfil
     */
    public void validarNombre(FacesContext cont, UIComponent cmp, Object value) {
        boolean b = new UsuarioServicio().existe(getPerfilEdicion(), "nombre", value.toString());
        if (b) {
            validationMessage(mensajeBundle("val_nombre_existe"));
        }   
    }
    
    /**
     * @return the perfilEdicion
     */
    public Perfil getPerfilEdicion() {
        return FacesUtils.getSessionBean().getPerfilEdicion();
    }
    
    /**
     * @return the modelPerfiles
     */
    public ListDataModel<Perfil> getModelPerfiles() {
        try {
            List<Perfil> lst = new PerfilServicio().listarTodos();
            modelPerfiles = new CmailListDataModel<Perfil>(lst);
        } catch (Exception e) {
        }

        return modelPerfiles;
    }

    /**
     * @param modelPerfiles the modelPerfiles to set
     */
    public void setModelPerfiles(CmailListDataModel<Perfil> modelPerfiles) {
        this.modelPerfiles = modelPerfiles;
    }
}
