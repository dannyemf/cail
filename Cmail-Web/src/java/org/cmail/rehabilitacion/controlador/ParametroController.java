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
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.seguridad.Parametro;
import org.cmail.rehabilitacion.servicio.BaseServicio;
import org.cmail.rehabilitacion.servicio.GenericServicio;
import org.cmail.rehabilitacion.servicio.PerfilServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = Constantes.MB_PARAMETRO)
@ViewScoped
public class ParametroController  extends Controller{
    
    private CmailListDataModel<Parametro> model;

    /** Creates a new instance of PerfilController */
    public ParametroController() {
    }

    public void eventoEditar(ActionEvent evt) {
        Parametro p = model.getRowData();
        initAudit(p);
        FacesUtils.getSessionBean().setParametroEdicion(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PARAMETRO);
    }

    public void eventoNuevo(ActionEvent evt) {
        Parametro p = new Parametro();
        initAudit(p);
        FacesUtils.getSessionBean().setParametroEdicion(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PARAMETRO);
    }

    public void eventoEliminar(ActionEvent evt) {
        Parametro parametro = model.getRowData();
        BaseServicio bs = new BaseServicio();
        boolean b = bs.eliminar(parametro);
        
        showMessageDeleted(b, parametro.getNombre());
    }

    public void eventoGuardar(ActionEvent evt) {
        boolean b = new BaseServicio().guardar(getParametroEdicion());
        showMessageSaved(b);
        
        if (b) {
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PARAMETRO);
        }
    }

    public void eventoCancelar(ActionEvent evt) {
        new PerfilServicio().refrescar(getParametroEdicion());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PARAMETRO);
    }   

    public Parametro getParametroEdicion() {
        return FacesUtils.getSessionBean().getParametroEdicion();
    }
    
    public ListDataModel<Parametro> getModel() {
        try {
            List<Parametro> lst = new GenericServicio<Parametro>(Parametro.class).listarTodos();
            model = new CmailListDataModel<Parametro>(lst);
        } catch (Exception e) {
        }

        return model;
    }

    public void setModel(CmailListDataModel<Parametro> modelPerfiles) {
        this.model = modelPerfiles;
    }
    
    
    public void validarNombre(FacesContext cont, UIComponent cmp, Object value) {
        
        boolean b = new GenericServicio<Parametro>(Parametro.class).existe(getParametroEdicion(), "nombre", value.toString());
        
        if (b) {
            validationMessage(mensajeBundle("val_nombre_existe"));
        }
        
    }
}
