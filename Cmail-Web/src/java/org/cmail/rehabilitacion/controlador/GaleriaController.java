/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.seguridad.Parametro;
import org.cmail.rehabilitacion.modelo.seguridad.TipoParametro;
import org.cmail.rehabilitacion.modelo.sira.ImagenWeb;
import org.cmail.rehabilitacion.modelo.sira.TipoImagenWeb;
import org.cmail.rehabilitacion.servicio.BaseServicio;
import org.cmail.rehabilitacion.servicio.ImagenWebServicio;
import org.cmail.rehabilitacion.servicio.ParametroServicio;
import org.cmail.rehabilitacion.servicio.PerfilServicio;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.vista.util.FacesUtils;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;

/**
 * Controlador de las imagenes que deben presentarse en el banner de la página web.
 * Permite hacer las búsquedas, crear, editar, eliminar las imagenes.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_GALERIA)
@ViewScoped
public class GaleriaController extends Controller {        
    
    private CmailListDataModel<ImagenWeb> model;    
    private String texto;
    private Parametro parametroSize;
   
    /**Constructor por defecto*/
    public GaleriaController() {        
    }
    
    @PostConstruct
    public void init(){
        parametroSize = new ParametroServicio().obtenerParametro(Constantes.PRM_SIZE_IMGANE_GALERIA, TipoParametro.Dimension);
    }

    /**
     * Evento invocado al presionar el vínculo editar.
     * @param evt el evento
     */
    public void eventoEditar(ActionEvent evt) {
        ImagenWeb e = model.getRowData();
        setImagenEdicion(e); 
        
        initAudit(e);                
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_IMAGEN);
    }

    /**
     * Evento invocado al presionar el botón nuevo.     
     * @param evt el evento
     */
    public void eventoNuevo(ActionEvent evt) {
        ImagenWeb e = new ImagenWeb();
        e.setTipo(TipoImagenWeb.GALERIA);        
        e.setRutaAbsoluta("");
        setImagenEdicion(e);
        
        super.initAudit(e);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_IMAGEN);        
    }
    
    /**
     * Evento invocado al presionar el botón buscar.
     * @param evt el evento
     */
    public void eventoBuscar(ActionEvent evt) {        
        List<ImagenWeb> lst = new ImagenWebServicio().listarPorTexto(texto, TipoImagenWeb.GALERIA);        
        this.model = new CmailListDataModel<ImagenWeb>(lst);
        showMessageResultList(lst);
    }

    /**
     * Evento invocado al presionar el vínculo eliminar.
     * @param evt el evento
     */
    public void eventoEliminar(ActionEvent evt) {
        ImagenWeb p = model.getRowData();
        initAudit(p);        
        BaseServicio bs = new BaseServicio();
        showMessageDeleted(bs.eliminar(p));
    }
    
    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición de la imagen de galería.
     * @param evt el evento
     */
    public void eventoGuardar(ActionEvent evt) {
        boolean v = true;
        if(getImagenEdicion().getData() == null){
            v = false;
            errorMessage("formImage:feImagen", mensajeBundle("seleccioneImagen"));
        }
        
        if(v){
            boolean b = new BaseServicio().guardar(getImagenEdicion());
            showMessageSaved(b);

            if (b) {
                FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_GALERIA);            
            }
        }
    }

    /**
     * Evento invocado al presionar el botón cancelar en la edición de una imagen.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {
        new PerfilServicio().refrescar(getImagenEdicion());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_GALERIA);
    }     
    
    public void listenerUpload(FileEntryEvent event) {
        log().info("Listener invocado...");
        FileEntry fileEntry = (FileEntry) event.getComponent();
        FileEntryResults results = fileEntry.getResults();
        ImagenWeb im = getImagenEdicion();
        
        for (FileEntryResults.FileInfo fileInfo : results.getFiles()) {
            if (fileInfo.isSaved()) {                
                log().info("Saveed to: " + fileInfo.getFile().getAbsolutePath());                
                
                byte[] data = getFileBytes(fileInfo, "image/jpg");
                if(data != null){                
                    im.setMimeType(fileInfo.getContentType());
                    im.setNombre(fileInfo.getFileName());
                    im.setData(data);
                }else{
                    errorMessage(event.getComponent().getClientId(), "Lo sentimos no se ha podido aceptar este archivo");
                }
                
                
            }
        }
    }

    public ImagenWeb getImagenEdicion() {
        return (ImagenWeb)FacesUtils.getSessionBean().getSessionMap("ImagenWebEdicion");
    }
    
    public void setImagenEdicion(ImagenWeb img) {
        FacesUtils.getSessionBean().addSessionMap("ImagenWebEdicion", img);
    }
    
    public ListDataModel<ImagenWeb> getModel() {
        return model;
    }      

    public void setModel(CmailListDataModel<ImagenWeb> model) {        
        this.model = model;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Parametro getParametroSize() {
        return parametroSize;
    }

    public void setParametroSize(Parametro parametroSize) {
        this.parametroSize = parametroSize;
    }
    
    
}
