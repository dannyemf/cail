/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.controlador.event.ActionListenerWucBuscarPersona;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.PersonaRol;
import org.cmail.rehabilitacion.modelo.core.CedulaUtil;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.core.StringUtil;
import org.cmail.rehabilitacion.modelo.seguridad.TipoParametro;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.servicio.ParametroServicio;
import org.cmail.rehabilitacion.servicio.PersonaServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.model.TipoNotificacion;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador de adolescentes.
 * Permite hacer las búsquedas, crear, editar, eliminar e ingresar a la bandeja de los adolescentes.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_ADOLESCENTES)
@SessionScoped
public class AdolescenteController extends Controller {
    
    /**Nombres del adolescente para realizar la búsqueda*/
    private String nombres;
    /**Apellidos del adolescente para realizar la búsqueda*/
    private String apellidos;
    /**Cédula del adolescente para realizar la búsqueda*/
    private String cedula;
    /**Expresa si se deben incluir en la búsqueda personas que no sean adolescentes*/
    private boolean incluirOtrosRoles;
    /**Lista de personas encontradas en la búsqueda*/
    private CmailListDataModel<Persona> modelList = new CmailListDataModel<Persona>();    
    /**Adolescente en edicón*/
    private Persona adolescente;
        
    /**Constructor por defecto*/
    public AdolescenteController() {
    }   
    
    /**
     * Evento invocado al presionar el botón buscar.
     * @param evt el evento
     */
    public void eventoBuscar(ActionEvent evt) {        
        if(StringUtil.isNullOrEmpty(cedula, nombres, apellidos)){
            showMensaje(TipoNotificacion.Error, mensajeBundle("val_required_any"));
        }else{
            List<Persona> lista = new PersonaServicio().listarAdolescentes(cedula, nombres, apellidos, incluirOtrosRoles);
            modelList = new CmailListDataModel<Persona>(lista);
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
        p.addRol(PersonaRol.ADOLESCENTE);
        
        initAudit(p);
        adolescente = p;    
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ADOLESCENTE);
    }        

    /**
     * Evento invocado al presionar el vínculo editar.
     * @param evt el evento
     */
    public void eventoEditar(ActionEvent evt) {
        Persona p = modelList.getRowData();        
        p.addRol(PersonaRol.ADOLESCENTE);
        
        initAudit(p);
        adolescente = p;        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ADOLESCENTE);        
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
     * Evento invocado al presionar el vínculo bandeja.
     * @param evt el evento
     */
    public void eventoBandeja(ActionEvent evt) {
        Persona p = modelList.getRowData();        
        setAdolescente(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_BANDEJA);        
    }
    
    /**
     * Evento invocado al presionar el botón cancelar en la edición de un adolescente.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {
        if (getAdolescente() != null && getAdolescente().getId().longValue() != -1L) {
            new FichaIngresoServicio().refrescar(getAdolescente());
        }
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_ADOLESCENTE);
    }   

    /**
     * Evento invocado al presionar el botón guardar en la edición de un adolescente.
     * @param evt el evento
     */
    public void eventoGuardar(ActionEvent evt) {
        //falta controlar que el padre y la madre no sean los mismosss
        //preguntar que datos se ingresan cuando el adolescente no es reconocido por el padre (Padre NN)                                       
        boolean v = true;
        
        //Verifica que tenga padre o madre
        if(getAdolescente().getPadre() == null && getAdolescente().getMadre() == null){
            v = false;
            errorMessage("frmEditAdolescente:sopPadre:txtCedula", mensajeBundle("seleccionePadreMadre"));
        }
        
        //Verifica que el padre no sea si mismo
        if(getAdolescente().getPadre() != null && getAdolescente().equals(getAdolescente().getPadre())){
            v = false;
            errorMessage("frmEditAdolescente:sopPadre:txtCedula", mensajeBundle("seleccionePadreNoMismoAdo"));
        }
        
        //Verifica que la madre no sea si mismo
        if(getAdolescente().getMadre() != null && getAdolescente().equals(getAdolescente().getMadre())){
            v = false;
            errorMessage("frmEditAdolescente:sopMadre:txtCedula", mensajeBundle("seleccioneMadreNoMismoAdo"));
        }
        
        //Verifica que el padre y la madre no sea iguales
        if(getAdolescente().getPadre() != null && getAdolescente().getMadre() != null && getAdolescente().getPadre().equals(getAdolescente().getMadre())){
            v = false;
            errorMessage("frmEditAdolescente:sopMadre:txtCedula", mensajeBundle("seleccioneMadreNoMismoPad"));
        }
        
        if(v){
            boolean b = new PersonaServicio().guardar(getAdolescente());
            showMessageSaved(b);
            
            if (b) {
                FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_ADOLESCENTE);                
            }
        }
    }    
                                 
    /**
     * Evento invocado al presionar el botón seleccionar padre.
     * @param evt el evento
     */
    public void accionBuscarPadre(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {
            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                Persona f = AdolescenteController.this.getAdolescente();
                if (this.getPersona() != null) {
                    f.setPadre(this.getPersona());
                    this.getWuc().accionCerrar(ae);
                }
            }
        }, PersonaRol.GENERAL, PersonaRol.ADOLESCENTE, getAdolescente().getMadre());
    }

    /**
     * Evento invocado al presionar el botón seleccionar madre.
     * @param evt el evento
     */
    public void accionBuscarMadre(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {
            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                Persona f = AdolescenteController.this.getAdolescente();
                if (this.getPersona() != null) {
                    f.setMadre(this.getPersona());
                    this.getWuc().accionCerrar(ae);
                }
            }
        }, PersonaRol.GENERAL,PersonaRol.ADOLESCENTE, this.getAdolescente().getPadre());
    }       

    /**
     * Evento invocado al presionar el botón editar padre.
     * @param evt el evento
     */
    public void accionEditarPadre(ActionEvent evt) {
        Persona p = getAdolescente().getPadre();
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
     * Evento invocado al presionar el botón editar madre.
     * @param evt el evento
     */
    public void accionEditarMadre(ActionEvent evt) {
        Persona p = getAdolescente().getMadre();
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
     * Evento invocado al presionar el botón limpiar padre.
     * @param evt el evento
     */
    public void accionLimpiarPadre(ActionEvent evt) {
        getAdolescente().setPadre(null);
    }

    /**
     * Evento invocado al presionar el botón limpiar madre.
     * @param evt el evento
     */
    public void accionLimpiarMadre(ActionEvent evt) {
        getAdolescente().setMadre(null);
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
            boolean bi = new PersonaServicio().existePersonaByCedula(value.toString(), getAdolescente());
            if (bi) {
                validationMessage("Cedula ya registrada");
            }
        } else {
            validationMessage("Cédula Incorrecta");
        }
    }
    
    /**
     * Obtiene el control de usuario para buscar personas (padre, madre, etc.).
     * @return el control
     */
    public WucBuscarPersonaController getWucBuscarPersona() {
        return FacesUtils.getBean(Constantes.MB_WUC_BUSCAR_PERSONA, WucBuscarPersonaController.class);
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
  
    public void setAdolescente(Persona p) {        
        this.adolescente = p;
    }
    
    public Persona getAdolescente() {
        return adolescente;
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

    /**
     * @return the modelList
     */
    public CmailListDataModel<Persona> getModelList() {
        return modelList;
    }

    /**
     * @param modelList the modelList to set
     */
    public void setModelList(CmailListDataModel<Persona> modelList) {
        this.modelList = modelList;
    }
    
    
       
}
