package org.cmail.rehabilitacion.controlador;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.controlador.event.ActionListenerWucBuscarPersona;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.PersonaRol;
import org.cmail.rehabilitacion.modelo.core.CedulaUtil;
import org.cmail.rehabilitacion.modelo.core.CmailList;
import org.cmail.rehabilitacion.servicio.GenericServicio;
import org.cmail.rehabilitacion.servicio.PersonaServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.icefaces.ace.component.datetimeentry.DateTimeEntry;

/**
 * <p>The NavigationBean class is responsible for storing the state of the
 * included dynamic content for display.  </p>
 *
 * @since 0.3.0
 */
@ManagedBean(name = Constantes.MB_WUC_BUSCAR_PERSONA)
@SessionScoped
public class WucBuscarPersonaController extends Controller{           
    
    private boolean renderPopupBuscar = false;
    private CmailListDataModel<Persona> listaPersonas = new CmailListDataModel<Persona>();
    private ActionListenerWucBuscarPersona listenerSeleccionar;
    private String textoPopupBuscar;        
    private Persona personaEdicion = new Persona();
    private Persona personaEdicionOldState = new Persona();
    private int selectedIndex = 0;        
    private PersonaRol rol = PersonaRol.GENERAL;            
    private boolean notificarBuscar = true;            
    private Persona personaSeleccionada = null;    
    
    public WucBuscarPersonaController() {    
        
    }    
    
    public String getPopupTitle(){
        String t = "";
        
        if(selectedIndex == 0){
            t = accionBundle("buscar") + " " + this.rol.name().toLowerCase();
        }else{
            t = accionBundle("editar") + " " + this.rol.name().toLowerCase();
        }
        
        return t;
    }
    
    /**======================ACCIONES=================================*/
    
    public void accionCerrar(ActionEvent e){
        setRenderPopupBuscar(false);
        //FacesContext.getCurrentInstance().renderResponse();  
        runScript("wucBuscarPersona.hide();");
    }
    
    public void accionSeleccionar(ActionEvent e){
        Persona p = listaPersonas.getRowData();
        accionNotificarEdicion(p,ActionListenerWucBuscarPersona.Tipo.BuscarSeleccionado, e);
    }    
    
    public void accionNotificarEdicion(Persona p, ActionListenerWucBuscarPersona.Tipo tipo, ActionEvent e){        
        if(tipo.equals(ActionListenerWucBuscarPersona.Tipo.EdicionCancelado)){
            restaurarEstado();
        }
        try {            
            listenerSeleccionar.processAction(e,p,tipo, this);
        } catch (Exception ex) {
        }                
    }
    
    public void accionBuscar(ActionEvent e){        
        try {
            List<Persona> lst = new PersonaServicio().listarPersonas(textoPopupBuscar, rol);
            this.setListaPersonas(new CmailListDataModel<Persona>(lst));
        } catch (Exception ex) {
            this.setListaPersonas(new CmailListDataModel<Persona>());
        }                
    }
    
    public void accionEditar(ActionEvent e){    
        personaEdicion = listaPersonas.getRowData();        
        iniciarlizar(personaEdicion);        
        selectedIndex = 1;
        notificarBuscar = true;        
        //FacesContext.getCurrentInstance().renderResponse();
    }        
    
    public void accionEliminar(Persona persona){
        personaSeleccionada = persona;        
        runScript("pnlConfEliminar.show();");
    }
    
    public void accionEliminarOk(ActionEvent e){        
        boolean b = new GenericServicio<Persona>(Persona.class).eliminar(personaSeleccionada);
        if(b){
            List<Persona> lst = listaPersonas.getData();
            if (lst.remove(personaSeleccionada)){
                setListaPersonas(new CmailListDataModel<Persona>(lst));
            }
        }
        
        runScript("pnlConfEliminar.hide();");
    }        
    
    public void iniciarlizar(Persona p){
        
        try {
            this.personaEdicionOldState = (Persona)p.clone();
        } catch (Exception e) {
        }
        
        FacesContext fc = FacesContext.getCurrentInstance();
        
        DateTimeEntry dtpFechaNac = (DateTimeEntry)fc.getViewRoot().findComponent("frmBuscar:dtpFechaNac");
        HtmlInputText txtCedula = (HtmlInputText)fc.getViewRoot().findComponent("frmBuscar:txtCedula");
        HtmlInputText txtNombres = (HtmlInputText)fc.getViewRoot().findComponent("frmBuscar:txtNombres");
        HtmlInputText txtApellidos = (HtmlInputText)fc.getViewRoot().findComponent("frmBuscar:txtApellidos");
        HtmlInputText txtCelular = (HtmlInputText)fc.getViewRoot().findComponent("frmBuscar:txtCelular");
        HtmlInputTextarea txtDireccion = (HtmlInputTextarea)fc.getViewRoot().findComponent("frmBuscar:txtDireccion");
        HtmlInputText txtEmail = (HtmlInputText)fc.getViewRoot().findComponent("frmBuscar:txtEmail");
        HtmlInputText txtOcupacion = (HtmlInputText)fc.getViewRoot().findComponent("frmBuscar:txtOcupacion");
        HtmlInputText txtTelefono = (HtmlInputText)fc.getViewRoot().findComponent("frmBuscar:txtTelefono");
        HtmlSelectOneMenu sompro = (HtmlSelectOneMenu)fc.getViewRoot().findComponent("frmBuscar:sopNacimiento:provincia");        
        HtmlSelectOneMenu somcan = (HtmlSelectOneMenu)fc.getViewRoot().findComponent("frmBuscar:sopNacimiento:canton");
        HtmlSelectOneMenu sompar = (HtmlSelectOneMenu)fc.getViewRoot().findComponent("frmBuscar:sopNacimiento:parroquia");
                        
        txtCedula.setSubmittedValue(p.getCedula());
        txtNombres.setSubmittedValue(p.getNombres());        
        txtApellidos.setSubmittedValue(p.getApellidos());
        txtCelular.setSubmittedValue(p.getCelular());
        txtDireccion.setSubmittedValue(p.getDireccion());
        txtEmail.setSubmittedValue(p.getEmail());
        txtOcupacion.setSubmittedValue(p.getOcupacion());
        txtTelefono.setSubmittedValue(p.getTelefono());        
        dtpFechaNac.setSubmittedValue(new SimpleDateFormat("dd/MM/yyyy").format(p.getFechaNacimiento()));                        
        sompro.setSubmittedValue(p.getProvinciaNacimiento());
        somcan.setSubmittedValue(p.getCantonNacimiento());
        sompar.setSubmittedValue(p.getParroquiaNacimiento());
    }
    
    
    
    public void accionNueva(ActionEvent e){
        personaEdicion = new PersonaServicio().crearPersona();
        
        initAudit(personaEdicion);        
        iniciarlizar(personaEdicion);
        selectedIndex = 1;    
        notificarBuscar = true;
        //FacesContext.getCurrentInstance().renderResponse();
    }
    
    public void accionCancelar(ActionEvent e){ 
        if(notificarBuscar){
            personaEdicion = null;
            selectedIndex = 0;        
        }else{            
            accionNotificarEdicion(personaEdicion, ActionListenerWucBuscarPersona.Tipo.EdicionCancelado, e);
            runScript("wucBuscarPersona.hide();");
        }
    }       
    
    public void restaurarEstado(){
        try {
            personaEdicion.setCedula(personaEdicionOldState.getCedula());
            personaEdicion.setNombres(personaEdicionOldState.getNombres());
            personaEdicion.setApellidos(personaEdicionOldState.getApellidos());
            personaEdicion.setSexo(personaEdicionOldState.getSexo());
            personaEdicion.setEstadoCivil(personaEdicionOldState.getEstadoCivil());
            personaEdicion.setFechaNacimiento(personaEdicionOldState.getFechaNacimiento());
            personaEdicion.setCargo(personaEdicionOldState.getCargo());
            personaEdicion.setOcupacion(personaEdicionOldState.getOcupacion());
            personaEdicion.setCelular(personaEdicionOldState.getCelular());
            personaEdicion.setTelefono(personaEdicionOldState.getTelefono());
            personaEdicion.setDireccion(personaEdicionOldState.getDireccion());
            personaEdicion.setParroquiaNacimiento(personaEdicionOldState.getParroquiaNacimiento());
            personaEdicion.setCantonNacimiento(personaEdicionOldState.getCantonNacimiento());
            personaEdicion.setProvinciaNacimiento(personaEdicionOldState.getProvinciaNacimiento());
        } catch (Exception e) {
            log().error("Error restaurar estado(): ", e);
        }
    }
    
    public void accionGuardar(ActionEvent e){
        
        if(personaEdicion != null){
            boolean add =  personaEdicion.getId().longValue() == -1 ? true : false;
            
            //Agrega los roles respectivos            
            personaEdicion.addRol(rol);
            
            boolean b = new PersonaServicio().guardar(personaEdicion);
            
            if(b){   
                if(notificarBuscar){
                    if(add) {
                        List<Persona> lst = listaPersonas.getData();
                        lst.add(0, personaEdicion);
                        listaPersonas = new CmailListDataModel<Persona>(lst);
                    }                
                    personaEdicion = null;
                    selectedIndex = 0;
                }else{
                    accionNotificarEdicion(personaEdicion,ActionListenerWucBuscarPersona.Tipo.EdicionGuardado, e);
                }
            }
        }
    }
    
    public void mostrarBuscador(ActionListenerWucBuscarPersona listener, PersonaRol rol){        
        this.listenerSeleccionar = listener;        
        selectedIndex = 0;        
        renderPopupBuscar = true;
        
        //Limpia la lista cuando cambia de rol
        if(!this.rol.equals(rol)){
            this.listaPersonas = new CmailListDataModel<Persona>();
        }
        
        this.rol = rol;        
        
        runScript("wucBuscarPersona.show();"); 
    }
    
    public void mostrarEditor(Persona p, PersonaRol rol, ActionListenerWucBuscarPersona listener){
        this.personaEdicion = p;        
        this.listenerSeleccionar = listener;
        this.rol = rol;        
        
        iniciarlizar(p);
        selectedIndex = 1;
        renderPopupBuscar = true;
        notificarBuscar = false;
        runScript("wucBuscarPersona.show();");
    }
    
    public Persona getPersonaSeleccionada(){
        return personaSeleccionada;
    }
    
    public void validarCedula(FacesContext contexto, UIComponent componente, Object valor){
        String cedula = valor.toString().trim();
        
        if(cedula.length() > 0){
            // Si ingresa una cedula se verifica que sea correcto y que no esté duplicado
            boolean b = CedulaUtil.validar(cedula);            
            if (b) {                
                boolean p = new GenericServicio<Persona>(Persona.class).existe(getPersonaEdicion(), "cedula", cedula);
                if ( p ) {
                    FacesMessage m = new FacesMessage(mensajeBundle("val_cedula_registrada"));
                    m.setSeverity(FacesMessage.SEVERITY_FATAL);
                    throw new ValidatorException(m);
                }
            } else {
                FacesMessage m = new FacesMessage(mensajeBundle("val_cedula_incorrecta"));
                m.setSeverity(FacesMessage.SEVERITY_FATAL);
                throw new ValidatorException(m);
            }
            
        }else{
            if(this.rol.equals(PersonaRol.ADOLESCENTE) == false){
                FacesMessage m = new FacesMessage(mensajeBundle("val_cedula_requerida"));
                m.setSeverity(FacesMessage.SEVERITY_FATAL);
                throw new ValidatorException(m);
            }
        }
    }   
    
    public boolean isCedulaRequired(){
        if(this.rol.equals(PersonaRol.ADOLESCENTE)){
            return false;
        }
        
        return true;
    }
    
            
    /**=======================================================*/
    
    
    /**===========================GETTERS ============================*/
    
    

    /**
     * @return the renderPopupBuscar
     */
    public boolean isRenderPopupBuscar() {
        return renderPopupBuscar;
    }
    
    
    /**
     * @return the listaPersonas
     */
    public CmailListDataModel<Persona> getListaPersonas() {
        return listaPersonas;
    }

    /**
     * @return the textoPopupBuscar
     */
    public String getTextoPopupBuscar() {
        return textoPopupBuscar;
    }

    /**
     * @param textoPopupBuscar the textoPopupBuscar to set
     */
    public void setTextoPopupBuscar(String textoPopupBuscar) {
        this.textoPopupBuscar = textoPopupBuscar;
    }   

    /**
     * @return the selectedIndex
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * @param selectedIndex the selectedIndex to set
     */
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }  

    /**
     * @param renderPopupBuscar the renderPopupBuscar to set
     */
    public void setRenderPopupBuscar(boolean renderPopupBuscar) {
        this.renderPopupBuscar = renderPopupBuscar;
    }

    /**
     * @param listaPersonas the listaPersonas to set
     */
    public void setListaPersonas(CmailListDataModel<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    /**
     * @return the personaEdicion
     */
    public Persona getPersonaEdicion() {
        return personaEdicion;
    }

    /**
     * @param personaEdicion the personaEdicion to set
     */
    public void setPersonaEdicion(Persona personaEdicion) {
        this.personaEdicion = personaEdicion;
    }   

    /**
     * @return the notificarBuscar
     */
    public boolean isNotificarBuscar() {
        return notificarBuscar;
    }

    /**
     * @param notificarBuscar the notificarBuscar to set
     */
    public void setNotificarBuscar(boolean notificarBuscar) {
        this.notificarBuscar = notificarBuscar;
    }

   

    /**
     * @param personaSeleccionada the personaSeleccionada to set
     */
    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    /**
     * @return the rol
     */
    public PersonaRol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(PersonaRol rol) {
        this.rol = rol;
    }

    /**
     * @return the personaEdicionOldState
     */
    public Persona getPersonaEdicionOldState() {
        return personaEdicionOldState;
    }

    /**
     * @param personaEdicionOldState the personaEdicionOldState to set
     */
    public void setPersonaEdicionOldState(Persona personaEdicionOldState) {
        this.personaEdicionOldState = personaEdicionOldState;
    }
    
    
}