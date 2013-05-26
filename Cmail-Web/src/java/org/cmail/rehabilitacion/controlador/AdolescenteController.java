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
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import org.cmail.rehabilitacion.controlador.event.ActionListenerWucBuscarPersona;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.PersonaRol;
import org.cmail.rehabilitacion.modelo.core.CedulaUtil;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.servicio.PersonaServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 *
 * @author Desarrollador
 */
@ManagedBean(name = Constantes.MB_ADOLESCENTES)
@SessionScoped
public class AdolescenteController extends Controller {

    /** Creates a new instance of PerfilController */
    //para la busqueda
    private String nombres;
    private String apellidos;
    private String cedula;
    
    private CmailListDataModel<Persona> modelList = new CmailListDataModel<Persona>();    
    private Persona adolescente;
    private boolean incluirOtrosRoles;

    public AdolescenteController() {
    }   
    
    public void eventoBuscar(ActionEvent evt) {
        List<Persona> lista = new PersonaServicio().listarAdolescentes(cedula, nombres, apellidos, incluirOtrosRoles);        
        setModelList(new CmailListDataModel<Persona>(lista));
        showMessageResultList(lista);
    }

    public void eventoNuevo(ActionEvent evt) {
        //crear aqui todo lo nuevo o inicializar en el SessionBean? o en FichaServicioS
        Persona p = new PersonaServicio().crearPersona();
        p.addRol(PersonaRol.ADOLESCENTE);
        
        initAudit(p);
        adolescente = p;    
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ADOLESCENTE);
    }        

    public void eventoEditar(ActionEvent evt) {
        Persona p = modelList.getRowData();        
        p.addRol(PersonaRol.ADOLESCENTE);
        
        initAudit(p);
        adolescente = p;        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_ADOLESCENTE);        
    }        
    
    public void eventoEliminar(ActionEvent evt) {
        Persona persona = modelList.getRowData();        
        boolean b = new PersonaServicio().eliminar(persona);
        showMessageDeleted(b);
        
        if (b) {
            modelList = modelList.remove(persona);
        }
    }
    
    public void eventoBandeja(ActionEvent evt) {
        Persona p = modelList.getRowData();        
        setAdolescente(p);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_BANDEJA);        
    }
    
    public void eventoCancelar(ActionEvent evt) {
        if (getAdolescente() != null && getAdolescente().getId().longValue() != -1L) {
            new FichaIngresoServicio().refrescar(getAdolescente());
        }
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_ADOLESCENTE);
    }   

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
                                 
    //revisar si van declaradas las instancias
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
        }, PersonaRol.GENERAL);
    }

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
        }, PersonaRol.GENERAL);
    }       

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

    public void accionLimpiarPadre(ActionEvent evt) {
        getAdolescente().setPadre(null);
    }

    public void accionLimpiarMadre(ActionEvent evt) {
        getAdolescente().setMadre(null);
    }       
    
    public WucBuscarPersonaController getWucBuscarPersona() {
        return FacesUtils.getBean(Constantes.MB_WUC_BUSCAR_PERSONA, WucBuscarPersonaController.class);
    }
       
    //validadores de cedula
    /**
     * al intentar guardar un adolescente con la misma cedula 
     * no se le permite. porque cada detenido solo tiene una sola
     * ficha de detenciones..     
     */
    public void validarCedulaAdolescente(FacesContext cont, UIComponent cmp, Object value) throws ValidatorException{
        boolean b = CedulaUtil.validar(value.toString());
        if (b) {
            boolean bi = new FichaIngresoServicio().existePersonaByCedula(value.toString(), getAdolescente());
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
    
   
}
