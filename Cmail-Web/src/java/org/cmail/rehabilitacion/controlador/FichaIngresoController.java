/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import com.icesoft.faces.component.ext.HtmlDataTable;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
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
import org.cmail.rehabilitacion.modelo.core.CmailList;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.cmail.rehabilitacion.modelo.sira.FichaIngresoDocumento;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.model.ItemFichaIngreso;
import org.cmail.rehabilitacion.vista.util.FacesUtils;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;

/**
 *
 * @author Desarrollador
 */
@ManagedBean(name = Constantes.MB_FICHAINGRESO)
@SessionScoped
public class FichaIngresoController extends Controller {

    /** Creates a new instance of PerfilController */
    //para la busqueda
    private String nombres;
    private String apellidos;
    private String cedula;        
    
    private ItemFichaIngreso itemFicha;
    private CmailListDataModel<ItemFichaIngreso> modelFichasIngreso = new CmailListDataModel<ItemFichaIngreso>();        

    public FichaIngresoController() {
    }
    
    public void eventoNuevo(ActionEvent evt, Persona adolescente, String returnUrl) {
        addRoute("nueva");
        setReturnUrl(Constantes.VW_LISTA_FICHAINGRESO, returnUrl);
        
        //crear aqui todo lo nuevo o inicializar en el SessionBean? o en FichaServicioS
        FichaIngreso fi = new FichaIngresoServicio().crearNueva(adolescente);
        fi.setLugar(FacesUtils.getSessionBean().getUsuarioLogeado().getLocalidad());
        initAudit(fi);

        FacesUtils.getSessionBean().setFichaIngresoEdicion(fi);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_FICHAINGRESO);        
    }
    
    public void eventoGenerarNueva(ActionEvent evt, FichaIngreso fio, String returnUrl) {
        addRoute("nueva");
        setReturnUrl(Constantes.VW_LISTA_FICHAINGRESO, returnUrl);
        
        FichaIngreso fi = new FichaIngresoServicio().crearNueva(fio.getAdolescente());
        fi.setLugar(fio.getLugar());
        fi.setAdolescente(fio.getAdolescente());
        fi.setRepresentante(fio.getRepresentante());
        fi.setAutoidentificacion(fio.getAutoidentificacion());
        fi.setCedulado(fio.getAdolescente().getCedula().trim().length() > 0);        
        fi.setNacionalidad(fio.getNacionalidad());
        fi.setTipoOcupacion(fio.getTipoOcupacion());
        
        initAudit(fi);

        FacesUtils.getSessionBean().setFichaIngresoEdicion(fi);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_FICHAINGRESO);        
    }    

    public void eventoEditar(ActionEvent evt, FichaIngreso fichaIngreso, String returnUrl) {
        addRoute("editar");
        
        setReturnUrl(Constantes.VW_LISTA_FICHAINGRESO, returnUrl);        
        initAudit(fichaIngreso);        
        FacesUtils.getSessionBean().setFichaIngresoEdicion(fichaIngreso);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_FICHAINGRESO);        
        FacesUtils.resetManagedBean(Constantes.MB_FICHAINGRESO);
    }

    private boolean validar() {
        FichaIngreso f = getFichaIngresoEdicion();
        boolean v = true;        
        
        if (getFichaIngresoEdicion().getAdolescente() == null || (getFichaIngresoEdicion().getAdolescente() != null && getFichaIngresoEdicion().getAdolescente().getId() <= 0)) {
            errorMessage("form:txtCedulaAdolescente", "Ingrese los datos del Adolescente");
            v=false;
        }
        
        if (getFichaIngresoEdicion().getAdolescente().getPadre() == null && getFichaIngresoEdicion().getAdolescente().getMadre() == null) {
            errorMessage("form:txtCedulaPadre", "Ingrese los datos del Padre");
            v=false;
        }
        
        if (getFichaIngresoEdicion().getRepresentante() == null) {
            errorMessage("form:txtCedulaRepresentante", "Ingrese los datos del Representante");
            v=false;
        }
        
        if (getFichaIngresoEdicion().getResponsableIngreso() == null) {
            errorMessage("form:txtCedulaResponsableIngreso", "Ingrese los datos del Responsable de Ingreso");
            v=false;
        }
        if (getFichaIngresoEdicion().getResponsableTraslado() == null) {
            errorMessage("form:txtCedulaResponsableTraslado", "Ingrese los datos del Responsable del Traslado al centro");
            v=false;
        }
        if (getFichaIngresoEdicion().getResponsableResguardoPertenencia() == null) {
            errorMessage("form:txtCedulaResponsablePertenencia", "Ingrese los datos del Responsable del Resguardo de las Pertenencias");
            v=false;
        }

        return v;
    }

    public void eventoGuardar(ActionEvent evt) {
        //falta controlar que el padre y la madre no sean los mismosss
        //preguntar que datos se ingresan cuando el adolescente no es reconocido por el padre (Padre NN)
        FichaIngresoServicio fis = new FichaIngresoServicio();
                
        if (this.validar()) {
            FichaIngreso fi = getFichaIngresoEdicion();           
            
            boolean isNew = fi.getId().longValue() == -1;
            boolean b = fis.guardarFicha(fi);
            showMessageSaved(b);
            
            if (b) {
                itemFicha = new ItemFichaIngreso(fi);
                setFichaIngresoEdicion(null);                
                FacesUtils.getMenuController().redirectApp(Constantes.VW_EXP_FICHAINGRESO);
                addRoute("exportar");
            }
        }       
    }

    public WucBuscarPersonaController getWucBuscarPersona() {
        return FacesUtils.getBean(Constantes.MB_WUC_BUSCAR_PERSONA, WucBuscarPersonaController.class);
    }
    
//    public void listenerUploadHuella(FileEntryEvent event) {
//        byte[] bt = getFileBytes(event, new String[]{"image/jpg", "image/jpeg", "image/png"});
//        if(bt != null){            
//            getFichaIngresoEdicion().setHuellaDigital(bt);
//        }
//    }  
    
    public void listenerUploadDocumento(FileEntryEvent event) {
        byte[] bt = getFileBytes(event, new String[]{});
        if(bt != null){
            
            FileEntry fileEntry = (FileEntry) event.getSource();
            FileEntryResults results = fileEntry.getResults();         
            FileEntryResults.FileInfo file = results.getFiles().get(0);                        
            
            FichaIngresoDocumento doc = new FichaIngresoDocumento();
            doc.setFicha(getFichaIngresoEdicion());
            doc.setData(bt);
            doc.setNombre(file.getFileName());
            doc.setMimeType(file.getContentType());
            
            getFichaIngresoEdicion().getDocumentos().add(doc);
        }
    }
    
    public void eventoElimiarDocumento(ActionEvent evt) {
        HtmlDataTable tabla = getComponent(HtmlDataTable.class, "form:tablaDocumentos");        
        FichaIngresoDocumento fd =  (FichaIngresoDocumento)tabla.getRowData();
        getFichaIngresoEdicion().getDocumentos().remove(fd);
    }

    public void eventoCancelar(ActionEvent evt) {
        if (getFichaIngresoEdicion() != null && getFichaIngresoEdicion().getId().longValue() != -1L) {
            new FichaIngresoServicio().refrescar(getFichaIngresoEdicion());
        }
        FacesUtils.getMenuController().redirectApp(getReturnUrl());
        
        //bandeja
        clearRoute(2); //bandeja<</Ingresos/Editar>>
    }
    
    public void eventoCancelarDescargar(ActionEvent evt) {        
        FacesUtils.getMenuController().redirectApp(getReturnUrl());
        //Refresca la bandeja
        if(getReturnUrl().equals(Constantes.VW_BANDEJA)){            
            clearRoute(3); //bandeja<</ingresos/editar/exportar>>
        }else{
            clearRoute();
        }
        
        
    }

    public void eventoBuscar(ActionEvent evt) {
        List<FichaIngreso> listaFichas = new FichaIngresoServicio().listarFichas(cedula, nombres, apellidos);
        CmailList<ItemFichaIngreso> coleccion = new CmailList<ItemFichaIngreso>();
        for (Iterator<FichaIngreso> it = listaFichas.iterator(); it.hasNext();) {
            FichaIngreso fichaIngreso = it.next();            
            coleccion.add(new ItemFichaIngreso(fichaIngreso));            
        }                
        setModelFichasIngreso(new CmailListDataModel<ItemFichaIngreso>(coleccion));
        
        showMessageResultList(listaFichas);
    }
    
    public void eventoGenerarEgreso(ActionEvent evt, FichaIngreso item){        
        FacesUtils.getFichaEgresoController().eventoGenerarEgreso(evt, item, Constantes.VW_LISTA_FICHAINGRESO);
    }

    //revisar si van declaradas las instancias
    public void accionBuscarPadre(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {
            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                FichaIngreso f = FichaIngresoController.this.getFichaIngresoEdicion();
                if (this.getPersona() != null) {
                    f.getAdolescente().setPadre(this.getPersona());
                    this.getWuc().accionCerrar(ae);
                }
            }
        }, PersonaRol.GENERAL);
    }

    public void accionBuscarMadre(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {
            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                FichaIngreso f = FichaIngresoController.this.getFichaIngresoEdicion();
                if (this.getPersona() != null) {
                    f.getAdolescente().setMadre(this.getPersona());
                    this.getWuc().accionCerrar(ae);
                }
            }
        }, PersonaRol.GENERAL);
    }

    public void accionBuscarRepresentante(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {
            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                FichaIngreso f = FichaIngresoController.this.getFichaIngresoEdicion();
                if (this.getPersona() != null) {
                    f.setRepresentante(this.getPersona());
                    this.getWuc().accionCerrar(ae);
                }
            }
        }, PersonaRol.GENERAL);
    }

    public void accionBuscarResponsableTraslado(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {

            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                FichaIngreso f = FichaIngresoController.this.getFichaIngresoEdicion();
                if (this.getPersona() != null) {
                    f.setResponsableTraslado(this.getPersona());
                    this.getWuc().accionCerrar(ae);
                }
            }
        }, PersonaRol.EMPLEADO);
    }

    public void accionBuscarResponsableIngreso(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {
            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                FichaIngreso f = FichaIngresoController.this.getFichaIngresoEdicion();
                if (this.getPersona() != null) {
                    f.setResponsableIngreso(this.getPersona());
                    this.getWuc().accionCerrar(ae);
                }
            }
        }, PersonaRol.EMPLEADO);
    }

    public void accionBuscarResponsablePertenencia(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {

            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                FichaIngreso f = FichaIngresoController.this.getFichaIngresoEdicion();
                if (this.getPersona() != null) {
                    f.setResponsableResguardoPertenencia(this.getPersona());
                    this.getWuc().accionCerrar(ae);
                }
            }
        }, PersonaRol.EMPLEADO);
    }

    public void accionEditarPadre(ActionEvent evt) {
        Persona p = getFichaIngresoEdicion().getAdolescente().getPadre();
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
        Persona p = getFichaIngresoEdicion().getAdolescente().getMadre();
        if (p != null) {
            getWucBuscarPersona().mostrarEditor(p, PersonaRol.GENERAL, new ActionListenerWucBuscarPersona() {

                @Override
                public void processAction(ActionEvent ae) throws AbortProcessingException {
                    this.getWuc().accionCerrar(ae);
                }
            });
        }
    }

    public void accionEditarRepresentante(ActionEvent evt) {
        Persona p = getFichaIngresoEdicion().getRepresentante();
        if (p != null) {
            getWucBuscarPersona().mostrarEditor(p, PersonaRol.GENERAL, new ActionListenerWucBuscarPersona() {

                @Override
                public void processAction(ActionEvent ae) throws AbortProcessingException {
                    this.getWuc().accionCerrar(ae);
                }
            });
        }
    }

    public void accionEditarAdolescente(ActionEvent evt) {
        Persona p = getFichaIngresoEdicion().getAdolescente();
        if (p != null) {
            getWucBuscarPersona().mostrarEditor(p, PersonaRol.ADOLESCENTE, new ActionListenerWucBuscarPersona() {

                @Override
                public void processAction(ActionEvent ae) throws AbortProcessingException {                    
                    if(this.isActionSaved()){
                        //Actualiza datos en la ficha
                        getFichaIngresoEdicion().fijarDatosAdolescente();                       
                    }
                    this.getWuc().accionCerrar(ae);
                }
            });
        }
    }

    public void accionEditarResponsableTraslado(ActionEvent evt) {
        Persona p = getFichaIngresoEdicion().getResponsableTraslado();
        if (p != null) {
            getWucBuscarPersona().mostrarEditor(p, PersonaRol.EMPLEADO, new ActionListenerWucBuscarPersona() {

                @Override
                public void processAction(ActionEvent ae) throws AbortProcessingException {
                    this.getWuc().accionCerrar(ae);
                }
            });
        }
    }

    public void accionEditarResponsableIngreso(ActionEvent evt) {
        Persona p = getFichaIngresoEdicion().getResponsableIngreso();
        if (p != null) {
            getWucBuscarPersona().mostrarEditor(p, PersonaRol.EMPLEADO, new ActionListenerWucBuscarPersona() {

                @Override
                public void processAction(ActionEvent ae) throws AbortProcessingException {
                    this.getWuc().accionCerrar(ae);
                }
            });
        }
    }

    public void accionEditarResponsablePertenencia(ActionEvent evt) {
        Persona p = getFichaIngresoEdicion().getResponsableResguardoPertenencia();
        if (p != null) {
            getWucBuscarPersona().mostrarEditor(p, PersonaRol.EMPLEADO, new ActionListenerWucBuscarPersona() {

                @Override
                public void processAction(ActionEvent ae) throws AbortProcessingException {
                    this.getWuc().accionCerrar(ae);
                }
            });
        }
    }

    public void accionLimpiarPadre(ActionEvent evt) {
        getFichaIngresoEdicion().getAdolescente().setPadre(null);
    }

    public void accionLimpiarMadre(ActionEvent evt) {
        getFichaIngresoEdicion().getAdolescente().setMadre(null);
    }

    public void accionLimpiarRepresentante(ActionEvent evt) {
        getFichaIngresoEdicion().setRepresentante(null);
    }

    public void accionLimpiarResponsableTraslado(ActionEvent evt) {
        getFichaIngresoEdicion().setResponsableTraslado(null);
    }

    public void accionLimpiarResponsableIngreso(ActionEvent evt) {
        getFichaIngresoEdicion().setResponsableIngreso(null);
    }

    public void accionLimpiarResponsablePertenencia(ActionEvent evt) {
        getFichaIngresoEdicion().setResponsableResguardoPertenencia(null);
    }

    public FichaIngreso getFichaIngresoEdicion() {
        return FacesUtils.getSessionBean().getFichaIngresoEdicion();
    }        
    
    public void setFichaIngresoEdicion(FichaIngreso fichaIngreso) {
        FacesUtils.getSessionBean().setFichaIngresoEdicion(fichaIngreso);
    }        

    public CmailListDataModel<ItemFichaIngreso> getModelFichasIngreso() {
        return modelFichasIngreso;
    }

    public void setModelFichasIngreso(CmailListDataModel<ItemFichaIngreso> model) {
        this.modelFichasIngreso = model;
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
    
    public void validarAdolescente(FacesContext cont, UIComponent cmp, Object value) {
        Persona a = getAdolescente();
        boolean b = a == null || (a != null && a.getId().longValue() <= 0);
        if (b) {                        
            setFacesMessage("Seleccione el adolescente");
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
            boolean bi = new FichaIngresoServicio().existePersonaByCedula(value.toString(), getFichaIngresoEdicion().getAdolescente());
            if (bi) {
                setFacesMessage("Cedula ya registrada");
            }
        } else {
            setFacesMessage("Cédula Incorrecta");
        }
    }

    public void validarCedulaPadre(FacesContext cont, UIComponent cmp, Object value) {
        validarCedulaPersona(cont, cmp, value, getFichaIngresoEdicion().getAdolescente().getPadre());
    }

    public void validarCedulaMadre(FacesContext cont, UIComponent cmp, Object value) {
        validarCedulaPersona(cont, cmp, value, getFichaIngresoEdicion().getAdolescente().getMadre());
    }

    public void validarCedulaRepresentante(FacesContext cont, UIComponent cmp, Object value) {
        validarCedulaPersona(cont, cmp, value, getFichaIngresoEdicion().getRepresentante());
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

    public Persona getAdolescente() {
        return getFichaIngresoEdicion().getAdolescente();
    }

    /**
     * @param itemFicha the itemFicha to set
     */
    public void setItemFicha(ItemFichaIngreso itemFicha) {
        this.itemFicha = itemFicha;
    }

    /**
     * @return the itemFicha
     */
    public ItemFichaIngreso getItemFicha() {        
        return itemFicha;
    }

    
   
}
