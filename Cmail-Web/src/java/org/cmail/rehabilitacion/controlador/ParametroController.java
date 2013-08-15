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
 * Controlador de los parámetros del sistema.
 * Permite crear, editar, eliminar los parámetros.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_PARAMETRO)
@ViewScoped
public class ParametroController  extends Controller{
    
    private CmailListDataModel<Parametro> model;

    /**Constructor por defecto*/
    public ParametroController() {
    }

    /**
     * Evento invocado al presionar el vínculo editar.
     * @param evt el evento
     */
    public void eventoEditar(ActionEvent evt) {
        Parametro p = model.getRowData();
        initAudit(p);
        FacesUtils.getSessionBean().setParametroEdicion(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PARAMETRO);
    }

    /**
     * Evento invocado al presionar el botón nuevo.     
     * @param evt el evento
     */
    public void eventoNuevo(ActionEvent evt) {
        Parametro p = new Parametro();
        initAudit(p);
        FacesUtils.getSessionBean().setParametroEdicion(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_PARAMETRO);
    }

    /**
     * Evento invocado al presionar el vínculo eliminar.
     * @param evt el evento
     */
    public void eventoEliminar(ActionEvent evt) {
        Parametro parametro = model.getRowData();
        BaseServicio bs = new BaseServicio();
        boolean b = bs.eliminar(parametro);
        
        showMessageDeleted(b, parametro.getNombre());
    }

    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición del parámetro.
     * @param evt el evento
     */
    public void eventoGuardar(ActionEvent evt) {
        boolean b = new BaseServicio().guardar(getParametroEdicion());
        showMessageSaved(b);
        
        if (b) {
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_PARAMETRO);
        }
    }

    /**
     * Evento invocado al presionar el botón cancelar en la edición de un parámetro.
     * @param evt el evento
     */
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
