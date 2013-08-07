/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.PersonaRol;
import org.cmail.rehabilitacion.modelo.core.CedulaUtil;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.servicio.PersonaServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.model.TipoNotificacion;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 *
 * @author Desarrollador
 */
@ManagedBean(name = Constantes.MB_EMPLEADOS)
@SessionScoped
public class EmpleadoController extends Controller {

    /** Creates a new instance of PerfilController */
    //para la busqueda
    private String nombres;
    private String apellidos;
    private String cedula;
    
    private CmailListDataModel<Persona> modelList = new CmailListDataModel<Persona>();    
    private Persona empleado;
    private boolean incluirOtrosRoles;

    public EmpleadoController() {
    }   
    
    public void eventoBuscar(ActionEvent evt) {
        List<Persona> lista = new PersonaServicio().listarEmpleados(cedula, nombres, apellidos, incluirOtrosRoles);        
        setModelList(new CmailListDataModel<Persona>(lista));
        showMessageResultList(lista);
    }

    public void eventoNuevo(ActionEvent evt) {
        //crear aqui todo lo nuevo o inicializar en el SessionBean? o en FichaServicioS
        Persona p = new PersonaServicio().crearPersona();
        p.addRol(PersonaRol.EMPLEADO);
        setReturnUrl(Constantes.VW_ADM_EMPLEADOS, Constantes.VW_ADM_EMPLEADOS);
        
        initAudit(p);
        empleado = p;    
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_EMPLEADO);
    }        

    public void eventoEditar(ActionEvent evt) {
        Persona p = modelList.getRowData();        
        p.addRol(PersonaRol.EMPLEADO);
        setReturnUrl(Constantes.VW_ADM_EMPLEADOS, Constantes.VW_ADM_EMPLEADOS);
        
        initAudit(p);
        empleado = p;        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_EMPLEADO);        
    } 
    
    public void eventoEnrrolar(ActionEvent evt) {
        Persona p = modelList.getRowData();        
        p.addRol(PersonaRol.EMPLEADO);        
        initAudit(p);
        
        boolean b = new PersonaServicio().guardar(p);
        
        showMensaje(TipoNotificacion.Aviso, b ? mensajeBundle("enrrolar_aviso") : mensajeBundle("enrrolar_error"));
    }
    
    public void eventoEliminar(ActionEvent evt) {
        Persona persona = modelList.getRowData();        
        boolean b = new PersonaServicio().eliminar(persona);
        showMessageDeleted(b);
        
        if (b) {
            modelList = modelList.remove(persona);
        }
    }       
    
    public void eventoCancelar(ActionEvent evt) {
        
        if (getEmpleado() != null && getEmpleado().getId().longValue() != -1L) {
            new FichaIngresoServicio().refrescar(getEmpleado());
        }
        
        FacesUtils.getMenuController().redirectApp(getReturnUrl());
    }   

    public void eventoGuardar(ActionEvent evt) {
        //falta controlar que el padre y la madre no sean los mismosss
        //preguntar que datos se ingresan cuando el adolescente no es reconocido por el padre (Padre NN)                                       
        
        boolean b = new PersonaServicio().guardar(getEmpleado());
        showMessageSaved(b);

        if (b) {
            FacesUtils.getMenuController().redirectApp(getReturnUrl());
        }        
    }    
       
    //validadores de cedula
    /**
     * al intentar guardar un adolescente con la misma cedula 
     * no se le permite. porque cada detenido solo tiene una sola
     * ficha de detenciones..     
     */
    public void validarCedulaEmpleado(FacesContext cont, UIComponent cmp, Object value) throws ValidatorException{
        boolean b = CedulaUtil.validar(value.toString());
        if (b) {
            boolean bi = new FichaIngresoServicio().existePersonaByCedula(value.toString(), getEmpleado());
            if (bi) {                
                errorMessage(cmp.getClientId(), mensajeBundle("val_cedula_registrada"));
            }
        } else {
            errorMessage(cmp.getClientId(), mensajeBundle("val_cedula_incorrecta"));            
        }
    }    

    public CmailListDataModel<Persona> getModelList() {
        return modelList;
    }

    public void setModelList(CmailListDataModel<Persona> model) {
        this.modelList = model;
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
  
    public void setEmpleado(Persona p) {        
        this.empleado = p;
    }
    
    public Persona getEmpleado() {
        return empleado;
    }        

    /**
     * @return the incluirOtrosRoles
     */
    public boolean isIncluirOtrosRoles() {
        return incluirOtrosRoles;
    }

    /**
     * @param incluirOtrosRoles the incluirOtrosRoles to set
     */
    public void setIncluirOtrosRoles(boolean incluirOtrosRoles) {
        this.incluirOtrosRoles = incluirOtrosRoles;
    }
    
   
}
