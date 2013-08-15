/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.dao.hql.K;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.htp.InformePsicologico;
import org.cmail.rehabilitacion.modelo.htp.InterpretacionTestHtp;
import org.cmail.rehabilitacion.modelo.htp.TestHtp;
import org.cmail.rehabilitacion.modelo.sira.FichaEgreso;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.cmail.rehabilitacion.servicio.FichaEgresoServicio;
import org.cmail.rehabilitacion.servicio.FichaIngresoServicio;
import org.cmail.rehabilitacion.servicio.TestHtpServicio;
import org.cmail.rehabilitacion.servicio.InformePsicologicoServicio;
import org.cmail.rehabilitacion.servicio.InterpretacionHtpServicio;
import org.cmail.rehabilitacion.vista.model.ItemFichaEgreso;
import org.cmail.rehabilitacion.vista.model.ItemFichaIngreso;
import org.cmail.rehabilitacion.vista.model.ItemInformePsicologico;
import org.cmail.rehabilitacion.vista.model.ItemTestHtp;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador de la bandeja de un adolescente.
 * Muestra las fichas de ingreso, egreso, informes psícológicos, test htp, etc.
 * Tambíen puede crear o editar tests, fichas de ingreso, informes, etc.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_BANDEJA)
@SessionScoped
public class BandejaAdolescenteController extends Controller{
       
    private Persona adolescente;
    private List<ItemFichaIngreso> fichasIngreso = new ArrayList<ItemFichaIngreso>();
    private List<ItemFichaEgreso> fichasEgreso = new ArrayList<ItemFichaEgreso>();
    private List<ItemTestHtp> testsHtp = new ArrayList<ItemTestHtp>();   
    private List<ItemInformePsicologico> informesPsicologicos =  new ArrayList<ItemInformePsicologico>();   
    
    private ItemFichaIngreso fichaIngreso;
    private boolean listarEgresosPorFicha;
    private boolean listarTestPorFicha;
    private boolean listarInformesPorFicha;
    
    @ManagedProperty(value="#{"+ Constantes.MB_FICHAINGRESO +"}")
    private FichaIngresoController fichaIngresoController;
    
    @ManagedProperty(value="#{"+ Constantes.MB_EV_HTP +"}")
    private TestHtpController formularioHtpController;
        
    @ManagedProperty(value="#{"+ Constantes.MB_FICHAEGRESO +"}")
    private FichaEgresoController fichaEgresoController; 
    
    @ManagedProperty(value="#{"+ Constantes.MB_INFORME +"}")
    private InformePsicologicoController informeController;

    /**Constructor por defecto*/
    public BandejaAdolescenteController() {
    }    
    
    public void preRenderBandeja(ComponentSystemEvent evt){
        init();
    }
    
    public void eventoBandeja(ActionEvent evt, Persona p) {            
        adolescente = p;
        fichaIngreso = null;
        setListarEgresosPorFicha(false);
        setListarTestPorFicha(false);        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_BANDEJA);        
        //init();
        addRoute("bandeja");
    }
    
    public void init(){
        //Lista las fichas de ingreso
        FichaIngresoServicio fis = new FichaIngresoServicio();
        FichaEgresoServicio fes = new FichaEgresoServicio();
        TestHtpServicio fhs = new TestHtpServicio();
        InformePsicologicoServicio ips = new InformePsicologicoServicio();
        
        if(fichasIngreso.size() != fis.from().where(K.eq("adolescente", adolescente)).count()){
            List<FichaIngreso> listaFichas = fis.listarPorPropiedad("adolescente", adolescente);        
            fichasIngreso = new ArrayList<ItemFichaIngreso>();
            for (Iterator<FichaIngreso> it = listaFichas.iterator(); it.hasNext();) {
                FichaIngreso ficha = it.next();            
                fichasIngreso.add(new ItemFichaIngreso(ficha));            
            }
        }
        
        //Lista fichas de egreso
        if(fichasEgreso.size() != fes.from().where(K.eq("adolescente", adolescente)).count()){
            List<FichaEgreso> lst = fes.listarPorPropiedad("adolescente", adolescente);
            fichasEgreso = new ArrayList<ItemFichaEgreso>();        
            for (Iterator<FichaEgreso> it = lst.iterator(); it.hasNext();) {
                FichaEgreso f = it.next();
                fichasEgreso.add(new ItemFichaEgreso(f));
            }
        }
        
        //Lista test htp
        if(testsHtp.size() != fhs.from().where(K.eq("adolescente", adolescente)).count()){
            List<TestHtp> listaTest = fhs.listarPorPropiedad("adolescente", adolescente);        
            testsHtp = new ArrayList<ItemTestHtp>();        
            InterpretacionHtpServicio infServ = new InterpretacionHtpServicio();        
            for (Iterator<TestHtp> it = listaTest.iterator(); it.hasNext();) {
                TestHtp test = it.next();
                InterpretacionTestHtp inf =  infServ.obtenerUnicoPor("testHtp", test);            
                testsHtp.add(new ItemTestHtp(test, inf));            
            }
        }
        
        //Lista informes psicologicos
        if(informesPsicologicos.size() != ips.from().where(K.eq("adolescente", adolescente)).count()){
            List<InformePsicologico> lista = ips.listarPorPropiedad("adolescente", adolescente);
            informesPsicologicos = new ArrayList<ItemInformePsicologico>();
            for (Iterator<InformePsicologico> it = lista.iterator(); it.hasNext();) {
                InformePsicologico informe = it.next();
                informesPsicologicos.add(new ItemInformePsicologico(informe, getUsuarioLogeado()));
            }            
        }
    }
    
    /**
     * Genera una nueva ficha de ingreso
     * @param evt 
     */
    public void eventoNuevaFichaIngreso(ActionEvent evt) {
        addRoute("ingresos");
        fichaIngresoController.eventoNuevo(evt, adolescente, Constantes.VW_BANDEJA );        
    }
    
    /**
     * Genera nueva ficha de ingreso a partir de una ya existente
     * @param evt 
     */
    public void eventoGenerarNuevaFichaIngreso(ActionEvent evt) {
        addRoute("ingresos");
        fichaIngresoController.eventoGenerarNueva(evt, fichaIngreso.getFichaIngreso(), Constantes.VW_BANDEJA);        
    }
    
    /**
     * Edita una ficha de ingreso
     * @param evt 
     */
    public void eventoEditarFichaIngreso(ActionEvent evt) {
        addRoute("ingresos");
        fichaIngresoController.eventoEditar(evt, fichaIngreso.getFichaIngreso(), Constantes.VW_BANDEJA);        
    }
    
    /**
     * Editar una ficha de egreso
     * @param evt Evento
     * @param item  Ficha de Egreso a editar
     */
    public void eventoEditarFichaEgreso(ActionEvent evt, FichaEgreso item) {
        addRoute("egresos");
        fichaEgresoController.eventoEditar(evt, item, Constantes.VW_BANDEJA);        
    }        
    
    /**
     * Editar test
     * @param evt Evento
     * @param form Test a editar
     */
    public void eventoEditarTest(ActionEvent evt, TestHtp form) {
        addRoute("tests");
        formularioHtpController.eventoEditar(evt, form, Constantes.VW_BANDEJA);        
    }
    
    public void eventoGenerarInterpretacion(ActionEvent evt, TestHtp form){
        addRoute("tests","informePsicologico");
        formularioHtpController.eventoVerInterpretacion(evt, form, Constantes.VW_BANDEJA);
    }        
    
    /**
     * Muestra el informe psicológico
     * @param evt
     * @param form 
     */
    public void eventoVerInterpretacion(ActionEvent evt, TestHtp form){
        addRoute("tests","informePsicologico");
        formularioHtpController.eventoVerInterpretacion(evt, form, Constantes.VW_BANDEJA);
    }        
    
    public void eventoNuevoTest(ActionEvent evt) {
        addRoute("tests");
        formularioHtpController.eventoNuevo(evt, adolescente, fichaIngreso.getFichaIngreso(), Constantes.VW_BANDEJA);        
    }
    
    public void eventoNuevoInforme(ActionEvent evt) {
        addRoute("informes");
        informeController.eventoGenerarInforme(evt, fichaIngreso.getFichaIngreso(), Constantes.VW_BANDEJA);                
    }
    
    public void eventoEditarInforme(ActionEvent evt, InformePsicologico item) {
        addRoute("informes");
        informeController.eventoEditar(evt, item , Constantes.VW_BANDEJA);                
    }
    
    public void eventoGenerarFichaEgreso(ActionEvent evt) {
        addRoute("egresos");
        fichaEgresoController.eventoGenerarEgreso(evt, fichaIngreso.getFichaIngreso(), Constantes.VW_BANDEJA);        
    }

    /**
     * Evento invocado al presionar el botón cancelar en la bandeja de un adolescente.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_ADOLESCENTE);
        clearLastRoute();
    }
    
    public void reload(ActionEvent evt) {        
        init();
    }
    
    public void eventoRowSelFichaIngreso(RowSelectorEvent evt) {
        int r = evt.getRow();
        ItemFichaIngreso fi = fichasIngreso.get(r);
        
        for (ItemFichaIngreso ifi : fichasIngreso) {
            ifi.setSelected(false);
        }
        
        if(evt.isSelected()){
            fi.setSelected(true);
            fichaIngreso = fi;
        }else{            
            fichaIngreso = null;
        }
    }

    public void setFichaIngresoController(FichaIngresoController fichaIngresoController) {
        this.fichaIngresoController = fichaIngresoController;
    }        

    public void setFormularioHtpController(TestHtpController formularioHtpController) {
        this.formularioHtpController = formularioHtpController;
    }

    public void setFichaEgresoController(FichaEgresoController fichaEgresoController) {
        this.fichaEgresoController = fichaEgresoController;
    }

    public void setInformeController(InformePsicologicoController informeController) {
        this.informeController = informeController;
    }
    
    
    
    
    public void setAdolescente(Persona p) {        
        adolescente = p;
    }
    
    public Persona getAdolescente() {
        return adolescente;
    }
    
    public List<ItemFichaIngreso> getFichasIngreso(){
        if(fichasIngreso == null){
            fichasIngreso = new ArrayList<ItemFichaIngreso>();
        }
        
        return fichasIngreso;
    }

    public void setFichasIngreso(List<ItemFichaIngreso> fichasIngreso) {
        this.fichasIngreso = fichasIngreso;
    }        
    
    public List<ItemFichaEgreso> getFichasEgreso(){        
        if(fichasEgreso == null){
            fichasEgreso = new ArrayList<ItemFichaEgreso>();
        }
        
        return fichasEgreso;        
    }
    
    public List<ItemFichaEgreso> getFichasEgresoFiltradas(){ 
        List<ItemFichaEgreso> all = getFichasEgreso();
        
        if(isListarEgresosPorFicha() == false){
            return all;
        }else{
            List<ItemFichaEgreso> filtro = new ArrayList<ItemFichaEgreso>();            
            if(fichaIngreso != null){
                for (Iterator<ItemFichaEgreso> it = all.iterator(); it.hasNext();) {
                    ItemFichaEgreso fe = it.next();
                    if(fe.getFicha().getFichaIngreso().getId().longValue() == fichaIngreso.getFichaIngreso().getId().longValue()){
                        filtro.add(fe);
                    }
                }            
            }
            
            return filtro;
        }
    }
    
    public List<ItemTestHtp> getTestsHtpFiltrados(){        
        List<ItemTestHtp> all = getTestsHtp();
        
        if(isListarTestPorFicha() == false){
            return all;
        }else{
            List<ItemTestHtp> filtro = new ArrayList<ItemTestHtp>();            
            if(fichaIngreso != null){
                for (Iterator<ItemTestHtp> it = all.iterator(); it.hasNext();) {
                    ItemTestHtp fe = it.next();
                    if(fe.getTestHtp().getFichaIngreso().getId().longValue() == fichaIngreso.getFichaIngreso().getId().longValue()){
                        filtro.add(fe);
                    }
                }            
            }
            
            return filtro;
        }
    }
    
    public List<ItemInformePsicologico> getInformesPsicologicosFiltrados(){        
        List<ItemInformePsicologico> all = informesPsicologicos;
        
        if(listarInformesPorFicha == false){
            return all;
        }else{
            List<ItemInformePsicologico> filtro = new ArrayList<ItemInformePsicologico>();
            
            if(fichaIngreso != null){
                for (Iterator<ItemInformePsicologico> it = all.iterator(); it.hasNext();) {
                    ItemInformePsicologico fe = it.next();
                    if(fe.getInforme().getFichaIngreso().getId().longValue() == fichaIngreso.getFichaIngreso().getId().longValue()){
                        filtro.add(fe);
                    }
                }            
            }
            
            return filtro;
        }
    }

    public void setFichasEgreso(List<ItemFichaEgreso> fichasEgreso) {
        this.fichasEgreso = fichasEgreso;
    }        
    
    public List<ItemTestHtp> getTestsHtp() {        
        if(testsHtp == null){
            testsHtp = new ArrayList<ItemTestHtp>();
        }
        
        return testsHtp;        
    }
    
    public boolean isRenderBotonNuevoIngreso(){
        Long c = new FichaIngresoServicio().from().where(K.eq("adolescente", adolescente), K.isNull("fichaEgreso")).count();
        return c == 0;
    }
    
    public boolean isRenderBotonNuevoTest(){
        //List<FichaIngreso> listaFichas = new FichaIngresoServicio().listarPorPropiedadesValores(new KProperty("adolescente", adolescente), new KProperty("idFichaEgreso", null));
        Long c = new FichaIngresoServicio().from().where(K.eq("adolescente", adolescente), K.isNull("fichaEgreso")).count();
        return c == 0;
    }

    public ItemFichaIngreso getFichaIngreso() {
        return fichaIngreso;
    }

    public void setFichaIngreso(ItemFichaIngreso fichaIngreso) {
        this.fichaIngreso = fichaIngreso;
    }

    /**
     * @return the listarEgresosPorFicha
     */
    public boolean isListarEgresosPorFicha() {
        return listarEgresosPorFicha;
    }

    /**
     * @param listarEgresosPorFicha the listarEgresosPorFicha to set
     */
    public void setListarEgresosPorFicha(boolean listarEgresosPorFicha) {
        this.listarEgresosPorFicha = listarEgresosPorFicha;
    }

    /**
     * @return the listarTestPorFicha
     */
    public boolean isListarTestPorFicha() {
        return listarTestPorFicha;
    }

    /**
     * @param listarTestPorFicha the listarTestPorFicha to set
     */
    public void setListarTestPorFicha(boolean listarTestPorFicha) {
        this.listarTestPorFicha = listarTestPorFicha;
    }

    /**
     * @return the informesPsicologicos
     */
    public List<ItemInformePsicologico> getInformesPsicologicos() {
        return informesPsicologicos;
    }

    /**
     * @param informesPsicologicos the informesPsicologicos to set
     */
    public void setInformesPsicologicos(List<ItemInformePsicologico> informesPsicologicos) {
        this.informesPsicologicos = informesPsicologicos;
    }

    /**
     * @return the listarInformesPorFicha
     */
    public boolean isListarInformesPorFicha() {
        return listarInformesPorFicha;
    }

    /**
     * @param listarInformesPorFicha the listarInformesPorFicha to set
     */
    public void setListarInformesPorFicha(boolean listarInformesPorFicha) {
        this.listarInformesPorFicha = listarInformesPorFicha;
    }


    
   
}
