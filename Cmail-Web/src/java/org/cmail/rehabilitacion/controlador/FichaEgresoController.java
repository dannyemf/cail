/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.vista.model.ItemFichaEgreso;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.controlador.event.ActionListenerWucBuscarPersona;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.PersonaRol;
import org.cmail.rehabilitacion.modelo.core.StringUtil;
import org.cmail.rehabilitacion.modelo.sira.FichaEgreso;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.cmail.rehabilitacion.servicio.FichaEgresoServicio;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.vista.model.TipoNotificacion;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador de fichas de egreso, es decir la salida del adolescente de éste centro.
 * Permite hacer las búsquedas, crear, editar, eliminar las fichas de egreso.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_FICHAEGRESO)
@SessionScoped
public class FichaEgresoController extends Controller {
    
    /** Nombres del adolescente para el filtro de la búsqueda */
    private String nombres;
    /** Apellidos del adolescente para el filtro de la búsqueda */
    private String apellidos;
    /** Cédula del adolescente para el filtro de la búsqueda */
    private String cedula;
    /**  La ficha de ingreso a exportar a pdf (después de guardar)*/
    private ItemFichaEgreso itemFicha;
    /** La lista de fichas de egreso a mostrar (resultado de la búsqueda) */
    private CmailListDataModel<ItemFichaEgreso> modelFichasEgreso = new CmailListDataModel<ItemFichaEgreso>();

    /**Constructor por defecto*/
    public FichaEgresoController() {
    }
    
    /**
     * Evento invocada desde la bandeja de entrada para editar una ficha de egreso.
     * @param evt el evento
     * @param item el item de la ficha
     * @param returnUrl ruta a volver despues de editar
     */
    public void eventoEditar(ActionEvent evt, FichaEgreso item, String returnUrl) {
        addRoute("editar");
        setReturnUrl(Constantes.VW_LISTA_FICHAEGRESO, returnUrl);
        
        FacesUtils.getSessionBean().setFichaEgresoEdicion(item);        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_FICHAEGRESO);
    }
    
    /**
     * Evento invocada por la bandeja de entrada para generar una ficha de egreso a partir de una ficha de ingreso.
     * @param evt el evento
     * @param fichaIngreso la ficha de ingreso
     * @param returnUrl a donde volver después de guardar
     */
    public void eventoGenerarEgreso(ActionEvent evt, FichaIngreso fichaIngreso, String returnUrl) {
        addRoute("nueva");
        setReturnUrl(Constantes.VW_LISTA_FICHAEGRESO, returnUrl);
        
        FichaEgreso fe = new FichaEgresoServicio().crearNueva();
        fe.setAdolescente(fichaIngreso.getAdolescente());
        fe.setFichaIngreso(fichaIngreso);
        fe.setLugar(fichaIngreso.getLugar());        
        
//        fichaIngreso.setIdFichaEgreso(fichaEgreso.getIdFichaIngreso());
        initAudit(fe);
        FacesUtils.getSessionBean().setFichaEgresoEdicion(fe);        
        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_FICHAEGRESO);        
    }

    /**
     * Valida que la información esté completa
     * @return true si es correcta
     */
    private boolean validar() {
        FichaEgreso f = getFichaEgresoEdicion();
        boolean v = true;
        if (f.getResponsableEgreso() == null) {
            errorMessage("form:", "Ingrese los datos del Responsable del Egreso");
            v = false;
        }
        if (f.getAutorizaEgreso()== null) {
            errorMessage("form:", "Ingrese los datos de la Persona que autoriza el Egreso");
            v = false;
        }
        if (f.getCompaneroEgreso()== null) {
            errorMessage("form:", "Ingrese los datos de la Persona con quién Egresa el adolescente");
            v = false;
        }

        return v;
    }

    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición de la ficha de egreso.
     * @param evt el evento
     */
    public void eventoGuardar(ActionEvent evt) {
        if (this.validar()) {
            
            boolean b = new FichaEgresoServicio().guardar(getFichaEgresoEdicion());
            showMessageSaved(b);
            
            if (b) {
                addRoute("exportar");
                FacesUtils.getMenuController().redirectApp(Constantes.VW_EXP_FICHAEGRESO);                
                //FacesUtils.getMenuController().redirectApp(getReturnUrl());                        
            }
        }
    }

    /**
     * Evento invocado al presionar el botón cancelar en la edición de una ficha de egreso.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {
        if (getFichaEgresoEdicion() != null && getFichaEgresoEdicion().getId().longValue() != -1L) {
            new FichaIngresoServicio().refrescar(getFichaEgresoEdicion());            
        }
        
        FacesUtils.getMenuController().redirectApp(getReturnUrl());
        clearRoute(2);
    }
    
    /**
     * Evento invocada por el botón cancelar en la página de descargar la ficha de egreso después de haberla guardado.
     * @param evt el evento
     */
    public void eventoCancelarDescargar(ActionEvent evt) {        
        FacesUtils.getMenuController().redirectApp(getReturnUrl());
        clearRoute(3);
    }   

    /**
     * Evento invocado al presionar el botón buscar.
     * @param evt el evento
     */
    public void eventoBuscar(ActionEvent evt) {
        if(StringUtil.isNullOrEmpty(cedula, nombres, apellidos)){
            showMensaje(TipoNotificacion.Error, mensajeBundle("val_required_any"));
        }else{
            List<FichaEgreso> lst = new FichaEgresoServicio().listarFichas(cedula, nombres, apellidos);
            List<ItemFichaEgreso> datos = new ArrayList<ItemFichaEgreso>();

            for (Iterator<FichaEgreso> it = lst.iterator(); it.hasNext();) {
                FichaEgreso f = it.next();
                datos.add(new ItemFichaEgreso(f));
            }

            setModelFichasEgreso(new CmailListDataModel<ItemFichaEgreso>(datos));
            showMessageResultList(lst);
        }
    }    

    /**
     * Evento para presentar la ventana de búsqueda y selección del resposnable de egreso
     * @param evt el evento
     */
    public void accionBuscarResponsableEgreso(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {

            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                FichaEgreso f = FichaEgresoController.this.getFichaEgresoEdicion();
                if (this.getPersona() != null) {
                    f.setResponsableEgreso(this.getPersona());
                    this.getWuc().accionCerrar(ae);
                }
            }
        }, PersonaRol.EMPLEADO, PersonaRol.ADOLESCENTE);
    }
    
    /**
     * Evento para presentar la ventana de búsqueda y selección de la persona que autoriza el egreso.
     * @param evt el evento
     */
    public void accionBuscarAutorizaEgreso(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {

            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                FichaEgreso f = FichaEgresoController.this.getFichaEgresoEdicion();
                if (this.getPersona() != null) {
                    f.setAutorizaEgreso(this.getPersona());
                    this.getWuc().accionCerrar(ae);
                }
            }
        }, PersonaRol.EMPLEADO, PersonaRol.ADOLESCENTE);
    }
    
    /**
     * Evento para presentar la ventana de búsqueda y selección del compañero de egreso
     * @param evt el evento
     */
    public void accionBuscarCompaneroEgreso(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {
            
            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                FichaEgreso f = FichaEgresoController.this.getFichaEgresoEdicion();
                if (this.getPersona() != null) {
                    f.setCompaneroEgreso(this.getPersona());
                    this.getWuc().accionCerrar(ae);
                }
            }
        }, PersonaRol.GENERAL, PersonaRol.ADOLESCENTE);
    }

    /**
     * Evento para presentar la ventana de edición del responasble de egreso
     * @param evt el evento
     */
    public void accionEditarResponsableEgreso(ActionEvent evt) {
        Persona p = getFichaEgresoEdicion().getResponsableEgreso();
        if (p != null) {
            getWucBuscarPersona().mostrarEditor(p, PersonaRol.EMPLEADO, new ActionListenerWucBuscarPersona() {

                @Override
                public void processAction(ActionEvent ae) throws AbortProcessingException {
                    this.getWuc().accionCerrar(ae);
                }
            });
        }
    }
    
    /**
     * Evento para presentar la ventana de edición de la persona que autoriza el egreso
     * @param evt el evento
     */
    public void accionEditarAutorizaEgreso(ActionEvent evt) {
        Persona p = getFichaEgresoEdicion().getAutorizaEgreso();
        if (p != null) {
            getWucBuscarPersona().mostrarEditor(p, PersonaRol.EMPLEADO, new ActionListenerWucBuscarPersona() {
                @Override
                public void processAction(ActionEvent ae) throws AbortProcessingException {
                    this.getWuc().accionCerrar(ae);
                }
            });
        }
    }
    
    /**
     * Evento para presentar la ventana de edición del compañero de egreso
     * @param evt el evento
     */
    public void accionEditarCompaneroEgreso(ActionEvent evt) {
        Persona p = getFichaEgresoEdicion().getCompaneroEgreso();
        if (p != null) {
            getWucBuscarPersona().mostrarEditor(p, PersonaRol.GENERAL, new ActionListenerWucBuscarPersona() {

                @Override
                public void processAction(ActionEvent ae) throws AbortProcessingException {
                    this.getWuc().accionCerrar(ae);
                }
            });
        }
    }

    /**
     * Evento para limpiar el responsable de egreso (pone como null)
     * @param evt el evento
     */
    public void accionLimpiarResponsableEgreso(ActionEvent evt) {
        getFichaEgresoEdicion().setResponsableEgreso(null);
    }
    
    /**
     * Evento para limpiar la persona que autoriza el egreso (pone como null)
     * @param evt el evento
     */
    public void accionLimpiarAutorizaEgreso(ActionEvent evt) {
        getFichaEgresoEdicion().setAutorizaEgreso(null);
    }
    
    /**
     * Evento para limpiar el compañero de egreso (pone como null)
     * @param evt el evento
     */
    public void accionLimpiarCompaneroEgreso(ActionEvent evt) {
        getFichaEgresoEdicion().setCompaneroEgreso(null);
    }
    
    /*     
     * @return the fichaIngresoEdicion
     */
    public FichaEgreso getFichaEgresoEdicion() {
        return FacesUtils.getSessionBean().getFichaEgresoEdicion();
    }

    /**
     * @return the wucBuscarPersona
     */
    public WucBuscarPersonaController getWucBuscarPersona() {
        return FacesUtils.getBean(Constantes.MB_WUC_BUSCAR_PERSONA, WucBuscarPersonaController.class);
    }

    /**
     * @return the modelFichasEgreso
     */
    public CmailListDataModel<ItemFichaEgreso> getModelFichasEgreso() {        
        return modelFichasEgreso;
    }

    /**
     * @param model the modelFichasEgreso to set
     */ 
    public void setModelFichasEgreso(CmailListDataModel<ItemFichaEgreso> model) {
        modelFichasEgreso=model;
    }    

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the itemFicha
     */
    public ItemFichaEgreso getItemFicha() {
        itemFicha = new ItemFichaEgreso(getFichaEgresoEdicion());
        return itemFicha;
    }

    /**
     * @param itemFicha the itemFicha to set
     */
    public void setItemFicha(ItemFichaEgreso itemFicha) {
        this.itemFicha = itemFicha;
    }
}
