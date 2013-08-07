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
 *
 * @author Usuario
 */
@ManagedBean(name = Constantes.MB_PERFIL)
@ViewScoped
public class PerfilController  extends Controller{
    
    private CmailListDataModel<Perfil> modelPerfiles;

    /** Creates a new instance of PerfilController */
    public PerfilController() {
    }

    public void eventoEditar(ActionEvent evt) {
        Perfil p = modelPerfiles.getRowData();
        initAudit(p);
        FacesUtils.getSessionBean().setPerfilEdicion(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PERFIL);
    }

    public void eventoNuevo(ActionEvent evt) {
        Perfil p = new Perfil();
        initAudit(p);
        FacesUtils.getSessionBean().setPerfilEdicion(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PERFIL);
    }

    public void eventoEliminar(ActionEvent evt) {
        Perfil perfil = modelPerfiles.getRowData();
        BaseServicio bs = new BaseServicio();
        boolean b = bs.eliminar(perfil);
        
        showMessageDeleted(b, perfil.getNombre());
    }

    public void eventoGuardar(ActionEvent evt) {
        boolean b = new BaseServicio().guardar(getPerfilEdicion());
        showMessageSaved(b);
        
        if (b) {
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PERFIL);
        }
    }

    public void eventoCancelar(ActionEvent evt) {
        new PerfilServicio().refrescar(getPerfilEdicion());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PERFIL);
    }
    
    public void validarNombre(FacesContext cont, UIComponent cmp, Object value) {
        boolean b = new UsuarioServicio().existe(getPerfilEdicion(), "nombre", value.toString());
        if (b) {
            validationMessage(mensajeBundle("val_nombre_existe"));
        }   
    }
    

    public Perfil getPerfilEdicion() {
        return FacesUtils.getSessionBean().getPerfilEdicion();
    }
    
    public ListDataModel<Perfil> getModelPerfiles() {
        try {
            List<Perfil> lst = new PerfilServicio().listarTodos();
            modelPerfiles = new CmailListDataModel<Perfil>(lst);
        } catch (Exception e) {
        }

        return modelPerfiles;
    }

    public void setModelPerfiles(CmailListDataModel<Perfil> modelPerfiles) {
        this.modelPerfiles = modelPerfiles;
    }
}
