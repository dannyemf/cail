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
import java.util.TimeZone;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import org.cmail.rehabilitacion.controlador.bean.*;
import org.cmail.rehabilitacion.controlador.event.ActionListenerWucBuscarPersona;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.PersonaRol;
import org.cmail.rehabilitacion.modelo.core.CedulaUtil;
import org.cmail.rehabilitacion.modelo.sira.FichaEgreso;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.cmail.rehabilitacion.servicio.FichaEgresoServicio;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 *
 * @author Desarrollador
 */
@ManagedBean(name = Constantes.MB_FICHAEGRESO)
@SessionScoped
public class FichaEgresoController extends Controller {

    /** Creates a new instance of PerfilController */
    //para la busqueda
    private String nombres;
    private String apellidos;
    private String cedula;
    private ItemFichaEgreso itemFicha;
    private CmailListDataModel<ItemFichaEgreso> modelFichasEgreso = new CmailListDataModel<ItemFichaEgreso>();

    public FichaEgresoController() {
    }

//    public void eventoNuevo(ActionEvent evt) {        
//        
//        FichaEgreso fi = new FichaEgresoServicio().crearNueva();
//        initAudit(fi);
//        FacesUtils.getSessionBean().setFichaEgresoEdicion(fi);
//        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_FICHAEGRESO);
//    }

    public void eventoEditar(ActionEvent evt, FichaEgreso item, String returnUrl) {
        addRoute("editar");
        setReturnUrl(Constantes.VW_LISTA_FICHAEGRESO, returnUrl);
        
        FacesUtils.getSessionBean().setFichaEgresoEdicion(item);        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_FICHAEGRESO);
    }

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

    public void eventoCancelar(ActionEvent evt) {
        if (getFichaEgresoEdicion() != null && getFichaEgresoEdicion().getId().longValue() != -1L) {
            new FichaIngresoServicio().refrescar(getFichaEgresoEdicion());            
        }
        
        FacesUtils.getMenuController().redirectApp(getReturnUrl());
        clearRoute(2);
    }
    
    public void eventoCancelarDescargar(ActionEvent evt) {        
        FacesUtils.getMenuController().redirectApp(getReturnUrl());
        clearRoute(3);
    }

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

    public void eventoBuscar(ActionEvent evt) {
        List<FichaEgreso> lst = new FichaEgresoServicio().listarFichas(cedula, nombres, apellidos);
        List<ItemFichaEgreso> datos = new ArrayList<ItemFichaEgreso>();
        
        for (Iterator<FichaEgreso> it = lst.iterator(); it.hasNext();) {
            FichaEgreso f = it.next();
            datos.add(new ItemFichaEgreso(f));
        }
        
        setModelFichasEgreso(new CmailListDataModel<ItemFichaEgreso>(datos));
        showMessageResultList(lst);
    }

    public FichaEgreso getFichaEgresoEdicion() {
        return FacesUtils.getSessionBean().getFichaEgresoEdicion();
    }

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
        }, PersonaRol.EMPLEADO);
    }
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
        }, PersonaRol.EMPLEADO);
    }
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
        }, PersonaRol.GENERAL);
    }

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

    public void accionLimpiarResponsableEgreso(ActionEvent evt) {
        getFichaEgresoEdicion().setResponsableEgreso(null);
    }
    public void accionLimpiarAutorizaEgreso(ActionEvent evt) {
        getFichaEgresoEdicion().setAutorizaEgreso(null);
    }
    public void accionLimpiarCompaneroEgreso(ActionEvent evt) {
        getFichaEgresoEdicion().setCompaneroEgreso(null);
    }

    public WucBuscarPersonaController getWucBuscarPersona() {
        return FacesUtils.getBean(Constantes.MB_WUC_BUSCAR_PERSONA, WucBuscarPersonaController.class);
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public CmailListDataModel<ItemFichaEgreso> getModelFichasEgreso() {
        /*CmailListDataModel<ItemFichaEgreso> lm = (CmailListDataModel<ItemFichaEgreso>) FacesUtils.getSessionBean().getSessionMap("modelFichasEgreso");
        if (lm == null) {
            lm = new CmailListDataModel<ItemFichaEgreso>();
        }
        return lm;
        */
        return modelFichasEgreso;
    }

    public void setModelFichasEgreso(CmailListDataModel<ItemFichaEgreso> model) {
        //FacesUtils.getSessionBean().addSessionMap("modelFichasEgreso", model);
        modelFichasEgreso=model;
    }

    //revisar si va en los servicios
    /**
     * 
     * @param cont
     * @param cmp
     * @param value
     * @param persona 
     * 
     * solo validamos si la cedula es correcta o incorrectas..
     * porque la persona registrada pueder ser el padre,madre o
     * representante de varios detenidos
     */
    public void validarCedulaPersona(FacesContext cont, UIComponent cmp, Object value, Persona persona) {
        boolean b = CedulaUtil.validar(value.toString());
        if (b == false) {
            setFacesMessage("Cédula Incorrecta");
        }
    }

    //validadores de cedula
    /**
     * al intentar guardar un adolescente con la misma cedula 
     * no se le permite. porque cada detenido solo tiene una sola
     * ficha de detenciones..     
     */
    public void validarCedulaAdolescente(FacesContext cont, UIComponent cmp, Object value) {
        boolean b = CedulaUtil.validar(value.toString());
        if (b) {
            boolean bi = new FichaIngresoServicio().existePersonaByCedula(value.toString(), getFichaEgresoEdicion().getAdolescente());
            if (bi) {
                setFacesMessage("Cedula ya registrada");
            }
        } else {
            setFacesMessage("Cédula Incorrecta");
        }
    }

    public void validarCedulaPadre(FacesContext cont, UIComponent cmp, Object value) {
        validarCedulaPersona(cont, cmp, value, getFichaEgresoEdicion().getAdolescente().getPadre());
    }

    public void validarCedulaMadre(FacesContext cont, UIComponent cmp, Object value) {
        validarCedulaPersona(cont, cmp, value, getFichaEgresoEdicion().getAdolescente().getMadre());
    }

    public void setFacesMessage(String facesMessage) {
        FacesMessage m = new FacesMessage(facesMessage);
        m.setSeverity(FacesMessage.SEVERITY_FATAL);
        throw new ValidatorException(m);
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
