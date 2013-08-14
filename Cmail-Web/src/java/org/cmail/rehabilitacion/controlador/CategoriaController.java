/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import org.cmail.rehabilitacion.vista.model.TipoNotificacion;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlInputTextarea;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.dao.hql.K;
import org.cmail.rehabilitacion.modelo.core.CategoriaComparator;
import org.cmail.rehabilitacion.modelo.core.FileUtil;
import org.cmail.rehabilitacion.modelo.htp.Categoria;
import org.cmail.rehabilitacion.modelo.htp.Indicador;
import org.cmail.rehabilitacion.modelo.htp.TipoIndicador;
import org.cmail.rehabilitacion.servicio.BaseServicio;
import org.cmail.rehabilitacion.servicio.CategoriaServicio;
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
@ManagedBean(name = Constantes.MB_CATEGORIA)
@SessionScoped
public class CategoriaController  extends Controller{
    
    private List<Categoria> listaCategorias=new ArrayList<Categoria>();
    private CmailListDataModel<Categoria> model;
    private Categoria categoriaEdicion;
    private Indicador indicadorEdicion;
    private Indicador indicadorOldValues;
    
    private String sortColumn = "nombre";
    private String texto;
    private boolean ascending = true;
    private boolean nuevoIndicador;
    
    private List<SelectItem> tiposIndicadores = new ArrayList<SelectItem>();
    private List<SelectItem> valoresIndicadores = new ArrayList<SelectItem>();
    private TipoIndicador[] tiposIndicadorSeleccionados = new TipoIndicador[0];

    /** Creates a new instance of PerfilController */
    public CategoriaController() {
        valoresIndicadores.add(new SelectItem(3, "3-"+etiquetaBundle("nivelPsicologicoAceptable")));
        valoresIndicadores.add(new SelectItem(2, "2-"+etiquetaBundle("nivelPsicologicoMedio")));
        valoresIndicadores.add(new SelectItem(1, "1-"+etiquetaBundle("nivelPsicologicoBajo")));
    }
    
    public void eventoBuscar(ActionEvent evt) {
        listaCategorias = new CategoriaServicio().from().where(
                K.or(
                    K.like("nombre", texto), 
                    K.like("descripcion", texto), 
                    K.like("tipo", texto)
                )
        ).list();
    }

    public void eventoNuevo(ActionEvent evt) {
        setCategoriaEdicion(new Categoria());
        initAudit(getCategoriaEdicion());        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_CATEGORIA);
    }
    
    public void eventoEditar(ActionEvent evt) {
        setCategoriaEdicion(model.getRowData());
        initAudit(getCategoriaEdicion());        
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_CATEGORIA);
    }
    
    public void eventoEliminar(ActionEvent evt) {
        Categoria cat = model.getRowData();
        CategoriaServicio bs = new CategoriaServicio();
        boolean b = bs.eliminar(cat);
        showMessageDeleted(b);
    }

    public void eventoGuardar(ActionEvent evt) {
        boolean b = new BaseServicio().guardar(getCategoriaEdicion());
        showMessageSaved(b);        
        if (b) {
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_CATEGORIAS);
        } 
    }

    public void eventoCancelar(ActionEvent evt) {
        new BaseServicio().refrescar(getCategoriaEdicion());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_CATEGORIAS);
    }    
    
    public void eventoNuevoIndicadorCasa(ActionEvent evt) {
        crearIndicador(TipoIndicador.Casa);
    }
    
    public void eventoNuevoIndicadorArbol(ActionEvent evt) {
        crearIndicador(TipoIndicador.Arbol);
    }
    
    public void eventoNuevoIndicadorPersona(ActionEvent evt) {
        crearIndicador(TipoIndicador.Persona);
    }
    
    public void crearIndicador(TipoIndicador tipo) {        
        indicadorEdicion = new Indicador();
        indicadorOldValues = null;        
        indicadorEdicion.setTipo(tipo);
        nuevoIndicador = true;
        
        //Obtiene el nuevo orden
        List<Indicador> lst = categoriaEdicion.obtenerIndicadores(tipo);
        int newOrden = 1;
        if(lst.size() > 0){
            newOrden = lst.get(lst.size() - 1).getOrden() + 1;            
        }        
        indicadorEdicion.setOrden(newOrden);
        
        showIndicador(indicadorEdicion);
    }
    
    public boolean verificarCopiarIndicador(Indicador indicador) {
        boolean addCasa = !indicador.getTipo().equals(TipoIndicador.Casa);
        boolean addArbol = !indicador.getTipo().equals(TipoIndicador.Arbol);
        boolean addPersona = !indicador.getTipo().equals(TipoIndicador.Persona);
        
        for (Indicador tp : categoriaEdicion.getIndicadores()) {
            if(tp.getTipo().equals(TipoIndicador.Casa) && tp.getNombre().equals(indicador.getNombre())){
                addCasa = false;
            }
            if(tp.getTipo().equals(TipoIndicador.Arbol) && tp.getNombre().equals(indicador.getNombre())){
                addArbol = false;
            }
            if(tp.getTipo().equals(TipoIndicador.Persona) && tp.getNombre().equals(indicador.getNombre())){
                addPersona = false;
            }
        }
        
        return addCasa || addArbol || addPersona;
    }
    public void eventoCopiarIndicador(ActionEvent evt, Indicador indicador) {
        indicadorEdicion = indicador;
        tiposIndicadores = new ArrayList<SelectItem>();
        
        boolean addCasa = !indicador.getTipo().equals(TipoIndicador.Casa);
        boolean addArbol = !indicador.getTipo().equals(TipoIndicador.Arbol);
        boolean addPersona = !indicador.getTipo().equals(TipoIndicador.Persona);
        
        for (Indicador tp : categoriaEdicion.getIndicadores()) {
            if(tp.getTipo().equals(TipoIndicador.Casa) && tp.getNombre().equals(indicador.getNombre())){
                addCasa = false;
            }
            if(tp.getTipo().equals(TipoIndicador.Arbol) && tp.getNombre().equals(indicador.getNombre())){
                addArbol = false;
            }
            if(tp.getTipo().equals(TipoIndicador.Persona) && tp.getNombre().equals(indicador.getNombre())){
                addPersona = false;
            }
        }
        
        if(addCasa){
            tiposIndicadores.add(new SelectItem(TipoIndicador.Casa.name(), etiquetaBundle(TipoIndicador.Casa.name().toLowerCase())));
        }
        
        if(addArbol){
            tiposIndicadores.add(new SelectItem(TipoIndicador.Arbol.name(), etiquetaBundle(TipoIndicador.Arbol.name().toLowerCase())));
        }
        
        if(addPersona){
            tiposIndicadores.add(new SelectItem(TipoIndicador.Persona.name(), etiquetaBundle(TipoIndicador.Persona.name().toLowerCase())));
        }
        
        if(tiposIndicadores.isEmpty()){
            showMensaje(TipoNotificacion.Aviso, mensajeBundle("copiar_indicador_empty"));
            indicadorEdicion = null;
        }else{
            runScript("dlgCopiarIndicador.show();");
        }
    }
    
    public void eventoCopiarIndicadorOk(ActionEvent evt) {
        
        if(tiposIndicadorSeleccionados!=null && tiposIndicadorSeleccionados.length > 0){
            for (TipoIndicador tp : tiposIndicadorSeleccionados) {
                Indicador ind = new Indicador();
                ind.setNombre(indicadorEdicion.getNombre());
                ind.setDefinicion(indicadorEdicion.getDefinicion());
                ind.setSignificaciones(indicadorEdicion.getSignificaciones());
                ind.setOrden(indicadorEdicion.getOrden());
                ind.setValor(indicadorEdicion.getValor());
                ind.setTipo(tp);                
                categoriaEdicion.addIndicador(ind);
            }
            
            showMensaje(TipoNotificacion.Aviso, mensajeBundle("copiar_indicador_ok") + getIndicadoresSeleccionadosString());
        }else{
            showMensaje(TipoNotificacion.Aviso, mensajeBundle("copiar_indicador_no"));
        }
        
        indicadorEdicion = null;
        runScript("dlgCopiarIndicador.hide();");
    }
    
    public void eventoCancelarCopiarIndicador(ActionEvent evt) {
        indicadorEdicion = null;
        runScript("dlgCopiarIndicador.hide();");
    }
    
    public void showIndicador(Indicador indicador){                                
        initAudit(indicador);
        
        getComponent(HtmlInputText.class,"frmEditarCategoria:txtIndNombre").setSubmittedValue(indicador.getNombre());
        getComponent(HtmlInputTextarea.class,"frmEditarCategoria:txtIndDefinicion").setSubmittedValue(indicador.getDefinicion());
        getComponent(HtmlInputTextarea.class,"frmEditarCategoria:txtIndSignificaciones").setSubmittedValue(indicador.getSignificaciones());        
        getComponent(HtmlInputText.class,"frmEditarCategoria:txtIndOrden").setSubmittedValue(indicador.getOrden());
        getComponent(javax.faces.component.html.HtmlSelectOneMenu.class,"frmEditarCategoria:cmbIndValor").setSubmittedValue(indicador.getValor());
        
        runScript("dlgEditarIndicador.show();");
    }
    
    public void eventoEliminarIndicador(ActionEvent evt, Indicador indicador) {                
        this.indicadorEdicion = indicador;
        runScript("dlgEliminarIndicador.show();");
    }
    
    public void eventoEliminarIndicadorOk(ActionEvent evt) {
        categoriaEdicion.getIndicadores().remove(indicadorEdicion);
        runScript("dlgEliminarIndicador.hide();");
    }
    
    
    public void eventoEditarIndicador(ActionEvent evt, Indicador indicador) {
        this.indicadorEdicion = indicador;
        try {
            this.indicadorOldValues = (Indicador)indicador.clone();
        } catch (Exception e) {
            log().error("No clonó", e);
        }
        nuevoIndicador = false;
        showIndicador(indicadorEdicion);                
    }
    
    public void eventoGuardarIndicador(ActionEvent evt) {
        if(nuevoIndicador){
            categoriaEdicion.addIndicador(indicadorEdicion);            
        }
        
        runScript("dlgEditarIndicador.hide();");
    }
    
    public void eventoCancelarEditarIndicador(ActionEvent evt) {
        if(indicadorOldValues != null){
            indicadorEdicion.setNombre(indicadorOldValues.getNombre());
            indicadorEdicion.setDefinicion(indicadorOldValues.getDefinicion());
            indicadorEdicion.setSignificaciones(indicadorOldValues.getSignificaciones());
            indicadorEdicion.setImagen(indicadorOldValues.getImagen());
        }
        
        indicadorEdicion = null;
        runScript("dlgEditarIndicador.hide();");
    }
    
    public void validateNombreIndicador(FacesContext ctx, UIComponent cmp, Object value) throws ValidatorException{
        
        TipoIndicador tipo = this.indicadorEdicion.getTipo();
        List<Indicador> lst = this.categoriaEdicion.obtenerIndicadores(tipo);
        for (Indicador ind : lst) {
            if(ind.getNombre().equals(value) && ind.equals(this.indicadorEdicion) == false){                
                FacesMessage fm = new FacesMessage(mensajeBundle("val_nombre_existe"));
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(fm);                
            }
        }
    }
    
    public void eventoLimpiarImagenIndicador(ActionEvent evt) {                
        indicadorEdicion.setImagen(null);
    }
    
    public void listenerUploadImagenIndicador(FileEntryEvent event) {                
        
        log().info("Listener invocado...");
        FileEntry fileEntry = (FileEntry) event.getSource();
        FileEntryResults results = fileEntry.getResults();                
        
        for (FileEntryResults.FileInfo fileInfo : results.getFiles()) {
            if (fileInfo.isSaved()) {                
                log().info("Saveed to: " + fileInfo.getFile().getAbsolutePath());                
                try {
                    indicadorEdicion.setImagen(FileUtil.loadData(fileInfo.getFile()));
                } catch (Exception e) {
                    indicadorEdicion.setImagen(null);
                }
            }
        }
    }       
    
    /**
     * @return the model
     */
    public CmailListDataModel<Categoria> getModel() {
        Collections.sort(listaCategorias, new CategoriaComparator(sortColumn, ascending));
        model = new CmailListDataModel<Categoria>(listaCategorias);
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(CmailListDataModel<Categoria> model) {
        this.model = model;
    }

    /**
     * @return the categoriaEdicion
     */
    public Categoria getCategoriaEdicion() {
        return categoriaEdicion;
    }

    /**
     * @param categoriaEdicion the categoriaEdicion to set
     */
    public void setCategoriaEdicion(Categoria categoriaEdicion) {
        this.categoriaEdicion = categoriaEdicion;
    }

    /**
     * @return the sortColumn
     */
    public String getSortColumn() {
        return sortColumn;
    }

    /**
     * @param sortColumn the sortColumn to set
     */
    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    /**
     * @return the ascending
     */
    public boolean isAscending() {
        return ascending;
    }

    /**
     * @param ascending the ascending to set
     */
    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    /**
     * @return the indicadorEdicion
     */
    public Indicador getIndicadorEdicion() {
        return indicadorEdicion;
    }

    /**
     * @param indicadorEdicion the indicadorEdicion to set
     */
    public void setIndicadorEdicion(Indicador indicadorEdicion) {
        this.indicadorEdicion = indicadorEdicion;
    }

    /**
     * @return the nuevoIndicador
     */
    public boolean isNuevoIndicador() {
        return nuevoIndicador;
    }

    /**
     * @param nuevoIndicador the nuevoIndicador to set
     */
    public void setNuevoIndicador(boolean nuevoIndicador) {
        this.nuevoIndicador = nuevoIndicador;
    }

    /**
     * @return the indicadorOldValues
     */
    public Indicador getIndicadorOldValues() {
        return indicadorOldValues;
    }

    /**
     * @param indicadorOldValues the indicadorOldValues to set
     */
    public void setIndicadorOldValues(Indicador indicadorOldValues) {
        this.indicadorOldValues = indicadorOldValues;
    }

    /**
     * @return the tiposIndicadores
     */
    public List<SelectItem> getTiposIndicadores() {
        return tiposIndicadores;
    }

    /**
     * @param tiposIndicadores the tiposIndicadores to set
     */
    public void setTiposIndicadores(List<SelectItem> tiposIndicadores) {
        this.tiposIndicadores = tiposIndicadores;
    }

    /**
     * @return the tiposIndicadorSeleccionados
     */
    public TipoIndicador[] getTiposIndicadorSeleccionados() {
        return tiposIndicadorSeleccionados;
    }

    /**
     * @param tiposIndicadorSeleccionados the tiposIndicadorSeleccionados to set
     */
    public void setTiposIndicadorSeleccionados(TipoIndicador[] tiposIndicadorSeleccionados) {
        this.tiposIndicadorSeleccionados = tiposIndicadorSeleccionados;
    }
    
    public String getIndicadoresSeleccionadosString(){
        String s = "";
        int i = 0;
        for (TipoIndicador tp : tiposIndicadorSeleccionados) {
            s += (i > 0 ? ", " : "") + etiquetaBundle(tp.name().toLowerCase());
            i++;
        }
        return s;
    }

    /**
     * @return the valoresIndicadores
     */
    public List<SelectItem> getValoresIndicadores() {
        return valoresIndicadores;
    }

    /**
     * @param valoresIndicadores the valoresIndicadores to set
     */
    public void setValoresIndicadores(List<SelectItem> valoresIndicadores) {
        this.valoresIndicadores = valoresIndicadores;
    }

    /**
     * @return the listaCategorias
     */
    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    /**
     * @param listaCategorias the listaCategorias to set
     */
    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }
  
}
