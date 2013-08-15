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
import org.cmail.rehabilitacion.modelo.core.StringUtil;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.servicio.PersonaServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.model.TipoNotificacion;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador de empleados.
 * Permite hacer las búsquedas, crear, editar, eliminar los empleados.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
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

    /**Constructor por defecto*/
    public EmpleadoController() {
    }   
    
    /**
     * Evento invocado al presionar el botón buscar.
     * @param evt el evento
     */
    public void eventoBuscar(ActionEvent evt) {
        if(StringUtil.isNullOrEmpty(cedula, nombres, apellidos)){
            showMensaje(TipoNotificacion.Error, mensajeBundle("val_required_any"));
        }else{
            List<Persona> lista = new PersonaServicio().listarEmpleados(cedula, nombres, apellidos, incluirOtrosRoles);        
            setModelList(new CmailListDataModel<Persona>(lista));
            showMessageResultList(lista);
        }
    }

    /**
     * Evento invocado al presionar el botón nuevo.     
     * @param evt el evento
     */
    public void eventoNuevo(ActionEvent evt) {
        //crear aqui todo lo nuevo o inicializar en el SessionBean? o en FichaServicioS
        Persona p = new PersonaServicio().crearPersona();
        p.addRol(PersonaRol.EMPLEADO);
        setReturnUrl(Constantes.VW_ADM_EMPLEADOS, Constantes.VW_ADM_EMPLEADOS);
        
        initAudit(p);
        empleado = p;    
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_EMPLEADO);
    }        

    /**
     * Evento invocado al presionar el vínculo editar.
     * @param evt el evento
     */
    public void eventoEditar(ActionEvent evt) {
        Persona p = modelList.getRowData();        
        p.addRol(PersonaRol.EMPLEADO);
        setReturnUrl(Constantes.VW_ADM_EMPLEADOS, Constantes.VW_ADM_EMPLEADOS);
        
        initAudit(p);
        empleado = p;        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_EMPLEADO);        
    } 
    
    /**
     * Evento invocado por el vínculo enrrolar que permite poner a cualquier persona como empleado.
     * @param evt 
     */
    public void eventoEnrrolar(ActionEvent evt) {
        Persona p = modelList.getRowData();        
        p.addRol(PersonaRol.EMPLEADO);        
        initAudit(p);
        
        boolean b = new PersonaServicio().guardar(p);
        
        showMensaje(TipoNotificacion.Aviso, b ? mensajeBundle("enrrolar_aviso") : mensajeBundle("enrrolar_error"));
    }
    
    /**
     * Evento invocado al presionar el vínculo eliminar.
     * @param evt el evento
     */
    public void eventoEliminar(ActionEvent evt) {
        Persona persona = modelList.getRowData();        
        boolean b = new PersonaServicio().eliminar(persona);
        showMessageDeleted(b);
        
        if (b) {
            modelList = modelList.remove(persona);
        }
    }       
    
    /**
     * Evento invocado al presionar el botón cancelar en la edición de un empleado.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {
        
        if (getEmpleado() != null && getEmpleado().getId().longValue() != -1L) {
            new FichaIngresoServicio().refrescar(getEmpleado());
        }
        
        FacesUtils.getMenuController().redirectApp(getReturnUrl());
    }   

    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición.
     * @param evt el evento
     */
    public void eventoGuardar(ActionEvent evt) {
        //falta controlar que el padre y la madre no sean los mismosss
        //preguntar que datos se ingresan cuando el adolescente no es reconocido por el padre (Padre NN)                                       
        
        boolean b = new PersonaServicio().guardar(getEmpleado());
        showMessageSaved(b);

        if (b) {
            FacesUtils.getMenuController().redirectApp(getReturnUrl());
        }        
    }    
           
    /**
     * Valida que la cédula del empleado sea correcta y no esté repetida.
     * @param cont el contexto
     * @param cmp el componente
     * @param value el valor actual
     * @throws ValidatorException 
     */
    public void validarCedulaEmpleado(FacesContext cont, UIComponent cmp, Object value) throws ValidatorException{
        boolean b = CedulaUtil.validar(value.toString());
        if (b) {
            boolean bi = new PersonaServicio().existePersonaByCedula(value.toString(), getEmpleado());
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
