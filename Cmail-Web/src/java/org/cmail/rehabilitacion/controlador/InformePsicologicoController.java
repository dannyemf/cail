/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import org.cmail.rehabilitacion.modelo.core.Constantes;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.modelo.htp.InformePsicologico;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.servicio.InformePsicologicoServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador de informes psicológicos.
 * Permite generar y editar un informe psicológico a partir de una ficha de ingreso.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_INFORME)
@SessionScoped
public class InformePsicologicoController extends Controller {
    
    /**
     * El informe psicológico en edición
     */
    private InformePsicologico informeEdicion;

    /**Constructor por defecto*/
    public InformePsicologicoController() {
    }
    
    /**
     * Evento para generar el informe psicológico a partir de una ficha de ingreso.
     * Evento invocado desde la badeja del adolescente.
     * 
     * @param evt el evento
     * @param fichaIngreso la ficha de ingreso
     * @param returnUrl el página de retorno
     */
    public void eventoGenerarInforme(ActionEvent evt, FichaIngreso fichaIngreso, String returnUrl) {
        addRoute("nuevo");
        setReturnUrl(returnUrl, returnUrl);
        
        setInformeEdicion(new InformePsicologico());
        getInformeEdicion().setAdolescente(fichaIngreso.getAdolescente());
        getInformeEdicion().setFichaIngreso(fichaIngreso);
        
        initAudit(getInformeEdicion());        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_INFORME);        
    }

    /**
     * Evento invocado desde la bandeja del adolescente para editar un informe psicológico.
     * 
     * @param evt el evento
     * @param item el informe psicológico
     * @param returnUrl la página de retorno
     */
    public void eventoEditar(ActionEvent evt, InformePsicologico item, String returnUrl) {
        addRoute("editar");
        setReturnUrl(returnUrl, returnUrl);
        
        setInformeEdicion(item);
        initAudit(getInformeEdicion());        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_INFORME);
    }
    
    /**
     * Evento invocado por el botón guardar en la página de edición del informe.
     * @param evt el evento
     */
    public void eventoGuardar(ActionEvent evt) {                    
        boolean b = new InformePsicologicoServicio().guardar(getInformeEdicion());
        showMessageSaved(b);

        if (b) {
            //bandeja/infformes/editar
            clearRoute(2);
            FacesUtils.getMenuController().redirectApp(getReturnUrl());
        }        
    }

    /**
     * Evento invocado al presionar el botón cancelar en la edición de un informe psicológico.
     * @param evt el evento
     */
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
