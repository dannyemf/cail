/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import org.cmail.rehabilitacion.controlador.event.ActionListenerWucBuscarPersona;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.excepcion.LayerException;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.PersonaRol;
import org.cmail.rehabilitacion.modelo.core.CategoriaComparator;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.core.FileUtil;
import org.cmail.rehabilitacion.modelo.core.StringUtil;
import org.cmail.rehabilitacion.modelo.core.interpretacion.Diagnostico;
import org.cmail.rehabilitacion.modelo.core.interpretacion.ItemInterpretacion;
import org.cmail.rehabilitacion.modelo.core.interpretacion.ItemInterpretacionCategoria;
import org.cmail.rehabilitacion.modelo.core.interpretacion.ItemInterpretacionCategoriaIndicador;
import org.cmail.rehabilitacion.modelo.htp.Categoria;
import org.cmail.rehabilitacion.modelo.htp.InformePsicologico;
import org.cmail.rehabilitacion.modelo.htp.InterpretacionTestHtp;
import org.cmail.rehabilitacion.modelo.htp.TestHtp;
import org.cmail.rehabilitacion.modelo.htp.TestHtpRespuesta;
import org.cmail.rehabilitacion.modelo.htp.TipoIndicador;
import org.cmail.rehabilitacion.modelo.sira.Esquema;
import org.cmail.rehabilitacion.modelo.sira.EsquemaPregunta;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.cmail.rehabilitacion.servicio.CategoriaServicio;
import org.cmail.rehabilitacion.servicio.EsquemaServicio;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.servicio.InterpretacionHeuristicaServicio;
import org.cmail.rehabilitacion.servicio.InterpretacionHtpServicio;
import org.cmail.rehabilitacion.servicio.TestHtpServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.model.ItemTestHtp;
import org.cmail.rehabilitacion.vista.model.TipoNotificacion;
import org.cmail.rehabilitacion.vista.util.FacesUtils;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;


/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_EV_HTP)
@SessionScoped
public class TestHtpController extends Controller {

    private String cedula;
    private String nombres;
    private String apellidos;           
    
    private List<Categoria> categorias = new ArrayList<Categoria>();
    private InterpretacionTestHtp interpretacion;
    
    /** Creates a new instance of PerfilController */

    public TestHtpController() {
    }

    public BandejaAdolescenteController getBandejaController() {
        return FacesUtils.getBean(Constantes.MB_BANDEJA, BandejaAdolescenteController.class);
    }        

    public void eventoNuevo(ActionEvent evt, Persona adolescente, FichaIngreso fichaIngreso, String returnUrl) {
        addRoute("nuevo");
        setReturnUrl(Constantes.VW_LISTA_TESTS_HTP, returnUrl);        
        
        //crear aqui todo lo nuevo o inicializar en el SessionBean? o en FichaServicioS
        TestHtp fi = new TestHtp();
        fi.setHoraInicio(new Date());
        fi.setAdolescente(adolescente);
        fi.setEvaluador(FacesUtils.getUsuarioController().getUsuarioLogeado());
                
        if(adolescente != null && fichaIngreso != null){        
            fi.setFichaIngreso(fichaIngreso);        
            initAudit(fi);
            List<Esquema> esqs = new EsquemaServicio().listarPorPropiedadesValores(new KProperty("tipo", Esquema.TIPO_HTP), new KProperty("activo", true));

            if(esqs.isEmpty() == false){

                Esquema e = esqs.get(0);

                fi.setEsquema(e);

                for (Iterator<EsquemaPregunta> it = e.getPreguntas().iterator(); it.hasNext();) {
                    EsquemaPregunta p = it.next();

                    fi.addRespuesta(p);
                }

                FacesUtils.getSessionBean().setFormHtpEdicion(fi);
                FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_TEST_HTP);
            }else{
                showMensaje(TipoNotificacion.Error, "No hay esquemas");
            }
        }else{
            showMensaje(TipoNotificacion.Error, "Datos no pasados");
        }
    }
    
    
    public void validarResponsable(FacesContext cont, UIComponent cmp, Object value) {        
        if(value == null || value.toString().equals("-1")){
            setFacesMessage("Seleccione el adolescente");
        }        
    }    
    
    public byte[] getDibujoNoSubido(){
        try {           
            return FileUtil.loadData(new File(FacesUtils.getExternalContext().getRealPath("/") + "/resources/icono/nosubido.png"));
        } catch (Exception e) {
            return null;
        }        
    }
    
    public byte[] getDibujoCasa(){
        if(getFormEdicion().getDibujoCasa() == null){
            return getDibujoNoSubido();
        }
        return getFormEdicion().getDibujoCasa();
    }        
    
    public byte[] getDibujoArbol(){
        if(getFormEdicion().getDibujoArbol() == null){
            return getDibujoNoSubido();
        }
        return getFormEdicion().getDibujoArbol();
    }
    
    public byte[] getDibujoPersona(){
        if(getFormEdicion().getDibujoPersona() == null){
            return getDibujoNoSubido();
        }
        return getFormEdicion().getDibujoPersona();
    }

    public void eventoEditar(ActionEvent evt, TestHtp form, String returnUrl) {
        addRoute("editar");
        setReturnUrl(Constantes.VW_LISTA_TESTS_HTP, returnUrl);
        
        initAudit(form);

        FacesUtils.getSessionBean().setFormHtpEdicion(form);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_TEST_HTP);                 
    }
    
    public List<TestHtpRespuesta> getRespuestas(String grp){
        
        List<TestHtpRespuesta> lst = new ArrayList<TestHtpRespuesta>();
        
        Set<TestHtpRespuesta> set =  getFormEdicion().getRespuestas();
        for (Iterator<TestHtpRespuesta> it = set.iterator(); it.hasNext();) {
            TestHtpRespuesta fr = it.next();
            if(fr.getEsquemaPregunta().getGrupo().equals(grp)){
                lst.add(fr);
            }
        }
                
        Collections.sort(lst, 
        new Comparator<TestHtpRespuesta>() {
            @Override
            public int compare(TestHtpRespuesta o1, TestHtpRespuesta o2) {
                int n1 = o1.getEsquemaPregunta().getNumero();
                int n2 = o2.getEsquemaPregunta().getNumero();
                
                if(n1 > n2){
                    return 1;
                }else{
                    if(n1 < n2){
                    return -1;
                    }                    
                }                
                return 0;                
            }
            }
        );
        
        return lst;
    }
    
    public List<TestHtpRespuesta> getRespuestasCasa(){
        return getRespuestas(EsquemaPregunta.GRUPO_CASA);
    }
    
    public List<TestHtpRespuesta> getRespuestasArbol(){
        return getRespuestas(EsquemaPregunta.GRUPO_ARBOL);
    }
    
    public List<TestHtpRespuesta> getRespuestasPersona(){
        return getRespuestas(EsquemaPregunta.GRUPO_PERSONA);
    }
    
    public boolean validar(){
        boolean b = true;
        
        if(getFormEdicion().getDibujoArbol()==null){
            b=false;
            FacesUtils.addErrorMessage("form:txtSubArbol", "Suba el dibujo del árbol");
        }
        
        if(getFormEdicion().getDibujoCasa()==null){
            b=false;
            FacesUtils.addErrorMessage("form:txtSubCasa", "Suba el dibujo de la casa");
        }
        
        if(getFormEdicion().getDibujoPersona()==null){
            b=false;
            FacesUtils.addErrorMessage("form:txtSubPersona", "Suba el dibujo de la persona");
        }
        
        return b;
    }

    public void eventoGuardar(ActionEvent evt) {
        //falta controlar que el padre y la madre no sean los mismosss
        //preguntar que datos se ingresan cuando el adolescente no es reconocido por el padre (Padre NN)        
        if(validar()){        
            boolean b = new TestHtpServicio().guardarFormualario(getFormEdicion());
            showMessageSaved(b);
            
            if (b) {
                interpretarTestHtp();                
            }
        }
    }
    
    public void eventoVerInterpretacion(ActionEvent evt, TestHtp form, String returnUrl) {
                
        setReturnUrl(Constantes.VW_LISTA_TESTS_HTP, returnUrl);
        
        //
        interpretacion = new InterpretacionHtpServicio().obtenerUnicoPor("testHtp", form);
        
        if(interpretacion == null){
            //Informe no emitido del test, lo debe crear e ingresar la información            
            FacesUtils.getSessionBean().setFormHtpEdicion(form);            
            interpretarTestHtp();            
        }else{
            //Iinforme ya emitido, solo lo puede ver...
            addRoute("interpretacion","ver");
            FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_INTERPRETACION);
        }
    }
    
    /**
     * Creacion del infome psicológico
     * @param evt 
     */
    
    public boolean validarIngresoInterpretacion(String tipo, ItemInterpretacion item){
        boolean v = true;
        List<ItemInterpretacionCategoria> cats = item.getCategroias();
        for (Iterator<ItemInterpretacionCategoria> it = cats.iterator(); it.hasNext();) {
            ItemInterpretacionCategoria itc = it.next();
        
            boolean selectUno = false;
            for (Iterator<ItemInterpretacionCategoriaIndicador> it1 = itc.getIndicadores().iterator(); it1.hasNext();) {
                ItemInterpretacionCategoriaIndicador iti = it1.next();
                
                if(iti.isSeleccionado()){
                    selectUno = true;
                }
            }
            
            if(selectUno == false){
                v = false;                
                errorMessage("form:"+tipo+itc.getCategoria().getId(), etiquetaBundle(tipo) + " - " + itc.getCategoria().getNombre()+": " + mensajeBundle("seleccioneUnItem"));
            }
        }
        
        return v;
    }
    
    public void eventoCrearInterpretacion(ActionEvent evt) {
        
        boolean v1 = validarIngresoInterpretacion("casa", getItemIntCasa());
        boolean v2 = validarIngresoInterpretacion("arbol", getItemIntArbol());
        boolean v3 = validarIngresoInterpretacion("persona", getItemIntPersona());
        
        if(v1 && v2 && v3){
            Diagnostico diagnostico = new InterpretacionHeuristicaServicio().diagnosticar(this.categorias, getItemIntCasa(), getItemIntArbol(), getItemIntPersona());

            //Model
            interpretacion = new InterpretacionHtpServicio().crearInterpretacion(getFormEdicion());        
            initAudit(interpretacion);

            interpretacion.setGraficoProporcion(diagnostico.getDiagnosticoProporcion());
            interpretacion.setGraficoPerspectiva(diagnostico.getDiagnosticoPerspectiva());
            interpretacion.setGraficoDetalle(diagnostico.getDiagnosticoDetalles());
            interpretacion.setPorcenjateRehabilitacionSistema(diagnostico.getPorcentajeRehabilitacionSistema());
            interpretacion.setEvaluador(FacesUtils.getSessionBean().getUsuarioLogeado());

            //addRoute("informePsicologico");        
            FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_INTERPRETACION);
        }
    }
    
    public void eventoGuardarInterpretacion(ActionEvent evt) {
        
        InterpretacionHtpServicio is = new InterpretacionHtpServicio();
        
        try {
            is.calcularNivelRehabilitacion(interpretacion);            
            
            boolean b = is.guardar(interpretacion);
        
            if(b){
                FacesUtils.getMenuController().redirectApp(getReturnUrl());
            }

            //bandeja<</tests/nuevo/interpretacion/informe>>
            if(getReturnUrl().equals(Constantes.VW_BANDEJA)){            
            }

            clearRoute(4);
        } catch (LayerException e) {
            errorMessage(mensajeBundle(e.getErrorCode()));
        }        
    }
    
    public void interpretarTestHtp(){
        CategoriaServicio s = new CategoriaServicio();
        this.categorias = s.listarTodos();
        Collections.sort(this.categorias, new CategoriaComparator("nombre", true));
        
        setItemIntCasa(new ItemInterpretacion(this.categorias, TipoIndicador.Casa));
        setItemIntArbol(new ItemInterpretacion(this.categorias, TipoIndicador.Arbol));
        setItemIntPersona(new ItemInterpretacion(this.categorias, TipoIndicador.Persona));                
        
        addRoute("interpretacion");
        FacesUtils.getMenuController().redirectApp(Constantes.VW_INTERPRETAR_TESTHTP);
    }

    public WucBuscarPersonaController getWucBuscarPersona() {
        return FacesUtils.getBean(Constantes.MB_WUC_BUSCAR_PERSONA, WucBuscarPersonaController.class);
    }

    public void eventoCancelar(ActionEvent evt) {
        if (getFormEdicion() != null && getFormEdicion().getId().longValue() != -1L) {
            new FichaIngresoServicio().refrescar(getFormEdicion());
        }
        FacesUtils.getMenuController().redirectApp(getReturnUrl());
        
        //bandeja/test/editar
        clearRoute(2);
    }

    public void eventoBuscar(ActionEvent evt) {
        if(StringUtil.isNullOrEmpty(cedula, nombres, apellidos)){
            showMensaje(TipoNotificacion.Error, mensajeBundle("val_required_any"));
        }else{
            List<TestHtp> listaTest = new TestHtpServicio().listarTest(cedula, nombres, apellidos);
            List<ItemTestHtp> lst = new ArrayList<ItemTestHtp>();

            InterpretacionHtpServicio infServ = new InterpretacionHtpServicio();

            for (Iterator<TestHtp> it = listaTest.iterator(); it.hasNext();) {
                TestHtp test = it.next();
                InterpretacionTestHtp inf =  infServ.obtenerUnicoPor("testHtp", test);

                lst.add(new ItemTestHtp(test, inf));            
            }

            setModelEsquema(new CmailListDataModel<ItemTestHtp>(lst));
            showMessageResultList(listaTest);
        }
    }

    public TestHtp getFormEdicion() {
        return FacesUtils.getSessionBean().getFormHtpEdicion();
    }
    
    public Persona getAdolescente() {
        return FacesUtils.getSessionBean().getFormHtpEdicion().getAdolescente();
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }
    
    public CmailListDataModel<ItemTestHtp> getModelForm() {
        CmailListDataModel<ItemTestHtp> lm = (CmailListDataModel<ItemTestHtp>) FacesUtils.getSessionBean().getSessionMap("modelForm");
        if (lm == null) {
            lm = new CmailListDataModel<ItemTestHtp>();
        }
        return lm;
    }
        
    public void setModelEsquema(CmailListDataModel<ItemTestHtp> model) {
        FacesUtils.getSessionBean().addSessionMap("modelForm", model);
    }

    public void setFacesMessage(String facesMessage) {
        FacesMessage m = new FacesMessage(facesMessage);
        m.setSeverity(FacesMessage.SEVERITY_FATAL);
        throw new ValidatorException(m);
    }
    
    public void accionBuscarAdolescente(ActionEvent evt) {
        getWucBuscarPersona().mostrarBuscador(new ActionListenerWucBuscarPersona() {
            @Override
            public void processAction(ActionEvent ae) throws AbortProcessingException {
                TestHtp f = TestHtpController.this.getFormEdicion();
                if (this.getPersona() != null) {
                                        
                    List<FichaIngreso> lst = new FichaIngresoServicio().listarPorPropiedad("adolescente", this.getPersona());
                    
                    if(lst.size() > 0){
                        for (Iterator<FichaIngreso> it = lst.iterator(); it.hasNext();) {
                            FichaIngreso fi = it.next();
                            f.setFichaIngreso(fi);
                            
                            if((f.getFichaIngreso().getFecha().before(fi.getFecha()))){
                                f.setFichaIngreso(fi);
                            }
                            
                        }
                        
                        f.setAdolescente(this.getPersona());
                        this.getWuc().accionCerrar(ae);
                    }
                    
                    
                }
            }
        }, PersonaRol.ADOLESCENTE, null);
    }
    
    public byte[] getFileData(FileEntryEvent event){
        try {
            
            log().info("Listener invocado...");
            FileEntry fileEntry = (FileEntry) event.getComponent();
            FileEntryResults results = fileEntry.getResults(); 
        
            for (FileEntryResults.FileInfo fileInfo : results.getFiles()) {
                if (fileInfo.isSaved()) {                
                    
                    String ct = fileInfo.getContentType();
                    log().info("ContetType: " + ct);
                    
                    if(ct.equals("image/png") || ct.equals("image/jpeg")){
                        log().info("Saveed to: " + fileInfo.getFile().getAbsolutePath());  
                        File f = fileInfo.getFile();                    
                        return FileUtil.loadData(f);
                    }else{
                        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Archivo no soportado", "");
                        throw  new ValidatorException(fm);
                    }
                }
            }
        } catch (Exception e) {
            log().info("Listener error... " + e);
        }
        
        return null;
    }
    
    public void listenerUploadCasa(FileEntryEvent event) {
        byte[] bt = getFileData(event);
        if(bt != null){
            //getComponent(HtmlInputHidden.class,"form:txtSubCasa").setSubmittedValue("SI");
            getFormEdicion().setDibujoCasa(bt);
        }
    }
    
    public void listenerUploadArbol(FileEntryEvent event) {
        byte[] bt = getFileData(event);
        if(bt != null){            
            //getComponent(HtmlInputHidden.class,"form:txtSubArbol").setSubmittedValue("SI");
            getFormEdicion().setDibujoArbol(bt);
        }
    }
    
    public void listenerUploadPersona(FileEntryEvent event) {
        byte[] bt = getFileData(event);
        if(bt != null){
            //getComponent(HtmlInputHidden.class,"form:txtSubPersona").setSubmittedValue("SI");
            getFormEdicion().setDibujoPersona(bt);
        }
    }
    
    /*public void accionEditarAdolescente(ActionEvent evt) {
        Persona p = getFormEdicion().getAdolescente();
        if (p != null) {
            getWucBuscarPersona().mostrarEditor(p, new ActionListenerWucBuscarPersona() {
                @Override
                public void processAction(ActionEvent ae) throws AbortProcessingException {
                    getFormEdicion().setEdadAdolescente(getAdolescente().getEdad());
                    this.getWuc().accionCerrar(ae);
                }
            });
        }
    }*/
    
    public void validarAdolescente(FacesContext cont, UIComponent cmp, Object value) {        
        if(value == null || value.toString().equals("-1")){
            setFacesMessage("Seleccione el adolescente");
        }        
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
     * @return the itemIntCasa
     */
    public ItemInterpretacion getItemIntCasa() {
        return (ItemInterpretacion)FacesUtils.getSessionBean().getSessionMap("itemIntCasa");
    }

    /**
     * @param itemIntCasa the itemIntCasa to set
     */
    public void setItemIntCasa(ItemInterpretacion itemIntCasa) {
        FacesUtils.getSessionBean().addSessionMap("itemIntCasa", itemIntCasa);
    }

    /**
     * @return the itemIntArbol
     */
    public ItemInterpretacion getItemIntArbol() {
        return (ItemInterpretacion)FacesUtils.getSessionBean().getSessionMap("itemIntArbol");        
    }

    /**
     * @param itemIntArbol the itemIntArbol to set
     */
    public void setItemIntArbol(ItemInterpretacion itemIntArbol) {        
        FacesUtils.getSessionBean().addSessionMap("itemIntArbol", itemIntArbol);
    }

    /**
     * @return the itemIntPersona
     */
    public ItemInterpretacion getItemIntPersona() {
        return (ItemInterpretacion)FacesUtils.getSessionBean().getSessionMap("itemIntPersona");
    }

    /**
     * @param itemIntPersona the itemIntPersona to set
     */
    public void setItemIntPersona(ItemInterpretacion itemIntPersona) {        
        FacesUtils.getSessionBean().addSessionMap("itemIntPersona", itemIntPersona);
    }

    public void setInformeEdicion(InformePsicologico informe) {        
        FacesUtils.getSessionBean().addSessionMap("informeEdicion", informe);
    }
    
    public InformePsicologico getInformeEdicion() {        
        return (InformePsicologico)FacesUtils.getSessionBean().getSessionMap("informeEdicion");
    }

    /**
     * @return the categorias
     */
    public List<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * @param categorias the categorias to set
     */
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    /**
     * @return the interpretacion
     */
    public InterpretacionTestHtp getInterpretacion() {
        return interpretacion;
    }

    /**
     * @param interpretacion the interpretacion to set
     */
    public void setInterpretacion(InterpretacionTestHtp interpretacion) {
        this.interpretacion = interpretacion;
    }
    
}