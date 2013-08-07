/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import org.cmail.rehabilitacion.modelo.core.Constantes;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.controlador.bean.*;
import org.cmail.rehabilitacion.modelo.htp.InformePsicologico;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.servicio.InformePsicologicoServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 *
 * @author Desarrollador
 */
@ManagedBean(name = Constantes.MB_INFORME)
@SessionScoped
public class InformePsicologicoController extends Controller {
    
    private InformePsicologico informeEdicion;

    public InformePsicologicoController() {
    }
    
    public void eventoGenerarInforme(ActionEvent evt, FichaIngreso fichaIngreso, String returnUrl) {
        addRoute("nuevo");
        setReturnUrl(returnUrl, returnUrl);
        
        setInformeEdicion(new InformePsicologico());
        getInformeEdicion().setAdolescente(fichaIngreso.getAdolescente());
        getInformeEdicion().setFichaIngreso(fichaIngreso);
        
        initAudit(getInformeEdicion());        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_INFORME);        
    }

    public void eventoEditar(ActionEvent evt, InformePsicologico item, String returnUrl) {
        addRoute("editar");
        setReturnUrl(returnUrl, returnUrl);
        
        setInformeEdicion(item);
        initAudit(getInformeEdicion());        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_INFORME);
    }
    

    public void eventoGuardar(ActionEvent evt) {                    
        boolean b = new InformePsicologicoServicio().guardar(getInformeEdicion());
        showMessageSaved(b);

        if (b) {
            //bandeja/infformes/editar
            clearRoute(2);
            FacesUtils.getMenuController().redirectApp(getReturnUrl());
        }        
    }

    public void eventoCancelar(ActionEvent evt) {
        if (getInformeEdicion() != null && getInformeEdicion().getId().longValue() != -1L) {
            new FichaIngresoServicio().refrescar(getInformeEdicion());            
        }
        
        //bandeja/infformes/editar
        clearRoute(2);
        FacesUtils.getMenuController().redirectApp(getReturnUrl());        
    }

    /**
     * @return the informeEdicion
     */
    public InformePsicologico getInformeEdicion() {
        return informeEdicion;
    }

    /**
     * @param informeEdicion the informeEdicion to set
     */
    public void setInformeEdicion(InformePsicologico informeEdicion) {
        this.informeEdicion = informeEdicion;
    }


}
