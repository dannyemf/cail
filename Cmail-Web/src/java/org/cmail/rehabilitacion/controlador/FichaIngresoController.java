/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import com.icesoft.faces.component.ext.HtmlDataTable;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.controlador.event.ActionListenerWucBuscarPersona;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.PersonaRol;
import org.cmail.rehabilitacion.modelo.core.CmailList;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.core.StringUtil;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.cmail.rehabilitacion.modelo.sira.FichaIngresoDocumento;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.model.ItemFichaIngreso;
import org.cmail.rehabilitacion.vista.model.TipoNotificacion;
import org.cmail.rehabilitacion.vista.util.FacesUtils;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;

/**
 * Controlador de fichas de ingreso, es decir los registro de cuando ingresa un adolescente al centro de rehabilitación.
 * Permite hacer las búsquedas, crear, editar, eliminar las fichas de ingreso.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_FICHAINGRESO)
@SessionScoped
public class FichaIngresoController extends Controller {

    /** Nombres del adolescente para el filtro de la búsqueda */
    private String nombres;
    /** Apellidos del adolescente para el filtro de la búsqueda */
    private String apellidos;
    /** Cédula del adolescente para el filtro de la búsqueda */
    private String cedula;        
    /**  La ficha de ingreso a exportar a pdf (después de guardar)*/
    private ItemFichaIngreso itemFicha;
    /** La lista de fichas de ingreso a mostrar (resultado de la búsqueda) */
    private CmailListDataModel<ItemFichaIngreso> modelFichasIngreso = new CmailListDataModel<ItemFichaIngreso>();        

    /**Constructor por defecto*/
    public FichaIngresoController() {
    }
    
    /**
     * Evento invocado por el botón nuevo. 
     * Invocado desde la bandeja para crear una nueva ficha de ingreso para el adolescente actual.
     * @param evt el evento
     * @param adolescente el adolescente
     * @param returnUrl página de retorno
     */
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
    
    /**
     * Genera una nueva ficha de ingreso a partir de la ficha de ingreso indicada.
     * Invocada desde la badeja para crear una ficha de ingreso idéntica a la anterior.
     * @param evt el evento
     * @param fio la ficha de ingreso
     * @param returnUrl la página de retorno
     */
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

    /**
     * Evento invocado desde la bandeja para realizar la edición de una ficha de ingreso.
     * @param evt el evento
     * @param fichaIngreso la ficha de ingreso
     * @param returnUrl la página de retorno
     */
    public void eventoEditar(ActionEvent evt, FichaIngreso fichaIngreso, String returnUrl) {
        addRoute("editar");
        
        setReturnUrl(Constantes.VW_LISTA_FICHAINGRESO, returnUrl);        
        initAudit(fichaIngreso);        
        FacesUtils.getSessionBean().setFichaIngresoEdicion(fichaIngreso);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_FICHAINGRESO);        
        FacesUtils.resetManagedBean(Constantes.MB_FICHAINGRESO);
    }

    /**
     * Método invocado antes de guardar una ficha de ingreso para validar información requerida.
     */ 
    private boolean validar() {
        FichaIngreso f = getFichaIngresoEdicion();
        boolean v = true;        
        
        //Verifica que tenga padre o madre
        if (f.getAdolescente().getPadre() == null && f.getAdolescente().getMadre() == null) {
            errorMessage("form:sopPadre:txtCedula", mensajeBundle("seleccionePadreMadre"));
            v=false;
        }
        
        //Verifica el representante
        if (f.getRepresentante() == null) {
            errorMessage("form:sopRepresentante:txtCedula", mensajeBundle("seleccioneRepresentante"));
            v=false;
        }
        
        //Verifica el responsable de ingresp
        if (f.getResponsableIngreso() == null) {
            errorMessage("form:sopResponsableIngreso:txtCedula", mensajeBundle("seleccionePersonaIngresaCentro"));
            v=false;
        }
        
        //Verifica el responsable de traslado
        if (f.getResponsableTraslado() == null) {
            errorMessage("form:sopResponsableTraslado:txtCedula", mensajeBundle("seleccioneResponsableTraslado"));
            v=false;
        }
        
        //Verifica el responsable de resguado pertenecias
        if (f.getResponsableResguardoPertenencia() == null) {
            errorMessage("form:sopPersonaPertenecias:txtCedula", mensajeBundle("seleccionePersonaPertenencias"));            
            v=false;
        }
        
        //Verifica que el padre no sea si mismo
        if(f.getAdolescente().getPadre() != null && f.getAdolescente().getPadre().equals(f.getAdolescente()) ){
            errorMessage("form:sopPadre:txtCedula", mensajeBundle("seleccionePadreNoMismoAdo"));
            v = false;
        }
        
        //Verifica que el madre no sea si mismo
        if(f.getAdolescente().getMadre() != null && f.getAdolescente().getMadre().equals(f.getAdolescente()) ){
            errorMessage("form:sopMadre:txtCedula", mensajeBundle("seleccioneMadreNoMismoAdo"));
            v = false;
        }
        
        //Verifica que el padre y la madre no sea iguales
        if(f.getAdolescente().getPadre() != null && f.getAdolescente().getMadre() != null && f.getAdolescente().getPadre().equals(f.getAdolescente().getMadre())){
            v = false;
            errorMessage("form:sopMadre:txtCedula", mensajeBundle("seleccioneMadreNoMismoPad"));
        }
        
        //Verifica que el representante no sea si mismo
        if(f.getRepresentante() != null && f.getRepresentante().equals(f.getAdolescente()) ){
            errorMessage("form:sopRepresentante:txtCedula", mensajeBundle("seleccioneRepreNoMismoAdo"));
            v = false;
        }

        return v;
    }
    
    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición de la ficha de ingreso.
     * @param evt el evento
     */
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
    
    /**
     * Listener para subir los documentos de la ficha de ingreso
     * @param event el evento
     */
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
    
    /**
     * Evento para remover un documento de la ficha de ingreso
     * @param evt el evento
     */
    public void eventoElimiarDocumento(ActionEvent evt) {
        HtmlDataTable tabla = getComponent(HtmlDataTable.class, "form:tablaDocumentos");        
        FichaIngresoDocumento fd =  (FichaIngresoDocumento)tabla.getRowData();
        getFichaIngresoEdicion().getDocumentos().remove(fd);
    }

    /**
     * Evento invocado al presionar el botón cancelar en la edición de una ficha de ingreso.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {
        if (getFichaIngresoEdicion() != null && getFichaIngresoEdicion().getId().longValue() != -1L) {
            new FichaIngresoServicio().refrescar(getFichaIngresoEdicion());
        }
        FacesUtils.getMenuController().redirectApp(getReturnUrl());
        
        //bandeja
        clearRoute(2); //bandeja<</Ingresos/Editar>>
    }
    
    /**
     * Evento invocada por el botón cerrar (cancelar) de la página de exportar a pdf presentada después de guardar la ficha.
     * @param evt el evento
     */
    public void eventoCancelarDescargar(ActionEvent evt) {        
        FacesUtils.getMenuController().redirectApp(getReturnUrl());
        //Refresca la bandeja
        if(getReturnUrl().equals(Constantes.VW_BANDEJA)){            
            clearRoute(3); //bandeja<</ingresos/editar/exportar>>
        }else{
            clearRoute();
        }
        
        
    }

    /**
     * Evento invocado al presionar el botón buscar.
     * @param evt el evento
     */
    public void eventoBuscar(ActionEvent evt) {
        if(StringUtil.isNullOrEmpty(cedula, nombres, apellidos)){
            showMensaje(TipoNotificacion.Error, mensajeBundle("val_required_any"));
        }else{
            List<FichaIngreso> listaFichas = new FichaIngresoServicio().listarFichas(cedula, nombres, apellidos);
            CmailList<ItemFichaIngreso> coleccion = new CmailList<ItemFichaIngreso>();
            for (Iterator<FichaIngreso> it = listaFichas.iterator(); it.hasNext();) {
                FichaIngreso fichaIngreso = it.next();            
                coleccion.add(new ItemFichaIngreso(fichaIngreso));            
            }                
            setModelFichasIngreso(new CmailListDataModel<ItemFichaIngreso>(coleccion));

            showMessageResultList(listaFichas);
        }
    }        

    /**
     * Evento invocado para seleccionar el padre
     * @param evt el evento
     */
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
        }, PersonaRol.GENERAL, PersonaRol.ADOLESCENTE, this.getAdolescente().getMadre());
    }

    /**
     * Evento invocado para seleccionar el padre
     * @param evt el evento
     */
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
        }, PersonaRol.GENERAL,PersonaRol.ADOLESCENTE, getAdolescente().getPadre());
    }

    /**
     * Evento invocado para seleccionar el representante
     * @param evt el evento
     */
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
        }, PersonaRol.GENERAL, PersonaRol.ADOLESCENTE, this.getAdolescente());
    }

    /**
     * Evento invocado para seleccionar el responsable de traslado al centro
     * @param evt el evento
     */
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
        }, PersonaRol.EMPLEADO, PersonaRol.ADOLESCENTE);
    }

    /**
     * Evento invocado para seleccionar el resposnable de ingreso al centro
     * @param evt el evento
     */
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
        }, PersonaRol.EMPLEADO, PersonaRol.ADOLESCENTE);
    }

    /**
     * Evento invocado para seleccionar el responsable que recibe las pertenecias del adolescente.
     * @param evt el evento
     */
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
        }, PersonaRol.EMPLEADO, PersonaRol.ADOLESCENTE);
    }

    /**
     * Evento invocado para editar la información del padre
     * @param evt el evento
     */
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

    /**
     * Evento invocado para editar la información de la madre
     * @param evt el evento
     */
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

    /**
     * Evento invocado para editar la información del representante
     * @param evt el evento
     */
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

    /**
     * Evento invocado para editar la información básica del adolescente
     * @param evt el evento
     */
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

    /**
     * Evento invocado para editar la información del resposnable de traslado al centro
     * @param evt el evento
     */
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

    /**
     * Evento invocado para editar la información del responsable de ingreso al centro
     * @param evt el evento
     */
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

    /**
     * Evento invocado para editar la información del responsable que recibe las pertenecias.
     * @param evt el evento
     */
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

    /**
     * Evento invocado para fijar el padre del adolescente a null
     * @param evt el evento
     */
    public void accionLimpiarPadre(ActionEvent evt) {
        getFichaIngresoEdicion().getAdolescente().setPadre(null);
    }

    /**
     * Evento invocado para fijar la madre del adolescente a null
     * @param evt el evento
     */
    public void accionLimpiarMadre(ActionEvent evt) {
        getFichaIngresoEdicion().getAdolescente().setMadre(null);
    }

    /**
     * Evento invocado para fijar el representante del adolescente a null
     * @param evt el evento
     */
    public void accionLimpiarRepresentante(ActionEvent evt) {
        getFichaIngresoEdicion().setRepresentante(null);
    }

    /**
     * Evento invocado para fijar el resposnable de traslado al centro a null
     * @param evt el evento
     */
    public void accionLimpiarResponsableTraslado(ActionEvent evt) {
        getFichaIngresoEdicion().setResponsableTraslado(null);
    }

    /**
     * Evento invocado para fijar el resposnable de ingreso al centro a null
     * @param evt el evento
     */
    public void accionLimpiarResponsableIngreso(ActionEvent evt) {
        getFichaIngresoEdicion().setResponsableIngreso(null);
    }

    /**
     * Evento invocado para fijar el resposnable de las pertenecias a null
     * @param evt el evento
     */
    public void accionLimpiarResponsablePertenencia(ActionEvent evt) {
        getFichaIngresoEdicion().setResponsableResguardoPertenencia(null);
    }
    
    /**
     * @return the wucBuscarPersona
     */
    public WucBuscarPersonaController getWucBuscarPersona() {
        return FacesUtils.getBean(Constantes.MB_WUC_BUSCAR_PERSONA, WucBuscarPersonaController.class);
    }

    /**
     * @return the fichaIngresoEdicion
     */
    public FichaIngreso getFichaIngresoEdicion() {
        return FacesUtils.getSessionBean().getFichaIngresoEdicion();
    }        
    
    /**
     * @param fichaIngreso the fichaIngresoEdicion to set
     */
    public void setFichaIngresoEdicion(FichaIngreso fichaIngreso) {
        FacesUtils.getSessionBean().setFichaIngresoEdicion(fichaIngreso);
    }        

    /**
     * @return the modelFichasIngreso
     */
    public CmailListDataModel<ItemFichaIngreso> getModelFichasIngreso() {
        return modelFichasIngreso;
    }

    /**
     * @param model the modelFichasIngreso to set
     */
    public void setModelFichasIngreso(CmailListDataModel<ItemFichaIngreso> model) {
        this.modelFichasIngreso = model;
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
     * @return the adolescente
     */
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
