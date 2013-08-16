package org.cmail.rehabilitacion.controlador;

import java.io.File;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.vista.model.CmailListDataModel;
import org.cmail.rehabilitacion.controlador.event.ActionListenerWucMultimedia;
import org.cmail.rehabilitacion.modelo.core.FileUtil;
import org.cmail.rehabilitacion.modelo.sira.ImagenWeb;
import org.cmail.rehabilitacion.servicio.GenericServicio;
import org.cmail.rehabilitacion.servicio.ImagenWebServicio;
import org.cmail.rehabilitacion.vista.model.ImageFile;
import org.cmail.rehabilitacion.vista.util.FacesUtils;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
import org.icefaces.ace.event.CloseEvent;


/**
 * Controlador de las imagenes para los artículos.
 * Permite subir imagenes al servidor y seleccionarlas para agregarlas al texto de los artículos.
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_WUC_MULTIMEDIA)
@SessionScoped
public class WucMultimediaController extends Controller{   
    
    
    private boolean renderWuc = false;
    private boolean reemplazar = false;
    private ActionListenerWucMultimedia listenerSeleccionar;        
    private CmailListDataModel<ImageFile> imagenes = new CmailListDataModel<ImageFile>();
    
    
    private ImageFile imagenReombrar;
    private boolean renderRenombrar;
    private String nuevoNombre;
    private String titulo;
    private String richText;
    
    /**Constructor por defecto*/
    public WucMultimediaController() {
    }
    
    /**======================ACCIONES=================================*/
    
    /**
     * Evento para cerrar el diálogo
     * @param e el evento
     */
    public void accionCerrar(ActionEvent e){
        renderWuc = false;
        log().info("Cerrando wuc...");
        runScript("wucMultimedia.hide();");
    }
    
    /**
     * Evento para cerrar el diálogo desde el botón close o la tecla escape
     * @param e el evento
     */
    public void accionCerrar(CloseEvent event) {
        renderWuc = false;
        log().info("Cerrando wuc...");
        runScript("wucMultimedia.hide();");
    }
    
    /**
     * Evento para seleccionar una imagen
     * @param e el evento
     */
    public void accionSeleccionar(ActionEvent e){
        
        accionRenombrarCancelar(e);
        
        String rt = FacesUtils.getRequestParameter("rutaAbsoluta");
        log().info("Ruta: "+rt);
        this.listenerSeleccionar.processAction(e, rt, this);
    }
    
    /**
     * Método que debe ser invocado para mostrar la ventana de selección de imágenes.
     * @param listener el evento que debe invocar al selecionar
     * @param richTextId el id del componente donde debe insertar la imagen
     * @param titulo el título del componente
     */
    public void mostar(ActionListenerWucMultimedia listener,String richTextId, String titulo){                         
        this.listenerSeleccionar = listener;
        renderWuc = true;
        this.titulo = titulo;
        this.richText = richTextId;
        
        accionRenombrarCancelar(null);
        runScript("wucMultimedia.show();");
    }
    
    /**
     * Evento invocado para renombrar una imagen
     * @param e el evento
     */
    public void accionRenombrar(ActionEvent e){
        ImageFile im = imagenes.getRowData();
        imagenReombrar = im; 
        renderRenombrar = true;          
        nuevoNombre = FileUtil.getSimpleName(im.getFile());
    }
    
    /**
     * Evento invocado al confirmar la acción renombrar.
     * @param e el evento
     */
    public void accionRenombrarConfirmar(ActionEvent e){
        boolean b = imagenReombrar.renameTo(nuevoNombre);        
        if(b){
            new GenericServicio<ImagenWeb>(ImagenWeb.class).guardar(imagenReombrar.getImagenWeb());
            accionRenombrarCancelar(e);
        }
    }
    
    /**
     * Evento invocado al cancelar la acción renombrar.
     * @param e el evento
     */
    public void accionRenombrarCancelar(ActionEvent e){
        renderRenombrar = false;
        nuevoNombre = null;
        imagenReombrar = null;
    }
    
    /**
     * Evento invocado para eliminar una imagen.
     * @param e el evento
     */
    public void accionEliminar(ActionEvent e){        
        ImageFile im = imagenes.getRowData();
        boolean b = new GenericServicio<ImagenWeb>(ImagenWeb.class).eliminar(im.getImagenWeb());
        if(b){
            List<ImageFile> imgs = this.imagenes.getData();
            imgs.remove(im);
            im.getFile().delete();
            imagenes = new CmailListDataModel<ImageFile>(imgs);                        
        }
        
        accionRenombrarCancelar(e);
    }
    
    /**
     * Evento para subir una nueva imagen.
     * @param event el evento
     */
    public void listenerUpload(FileEntryEvent event) {
        log().info("Listener invocado...");
        FileEntry fileEntry = (FileEntry) event.getComponent();
        FileEntryResults results = fileEntry.getResults();                
        
        for (FileEntryResults.FileInfo fileInfo : results.getFiles()) {
            if (fileInfo.isSaved()) {                
                log().info("Saveed to: " + fileInfo.getFile().getAbsolutePath());  
                
                ImagenWeb img = new GenericServicio<ImagenWeb>(ImagenWeb.class).obtenerPrimerPor("nombre", fileInfo.getFileName());
                
                String smovTo = FacesUtils.getExternalContext().getRealPath("/") + ImageFile.CARPETA_IMAGENES + fileInfo.getFileName();
                File fileMoveTo = new File(smovTo);
                log().info("Move to: " + fileMoveTo.getAbsolutePath());
                
                //Si ya existen con el mismo nombre y si reemplazar --> remover anterior
                if(img != null && reemplazar){
                    fileMoveTo.delete();
                }
                
                if(img != null && reemplazar == false){
                    fileMoveTo = null;
                }
                                
                if(fileMoveTo!=null){
                    boolean b = fileInfo.getFile().renameTo(fileMoveTo);                    
                }
                
                if(fileMoveTo != null && fileMoveTo.exists()){
                    
                    //Verificar si ya existe en basde
                    
                    ImageFile imf = new ImageFile(fileMoveTo, fileInfo.getContentType());                    
                    /*List<ImageFile> lst = this.imagenes.getData();
                    lst.add(imf);*/                    
                    //this.imagenes = new CmailListDataModel<ImageFile>(lst);
                    
                    ImagenWeb iw = imf.getImagenWeb(); 
                    
                    if(img != null && reemplazar){
                        img.setData(iw.getData());
                        iw = img;
                    }
                    
                    initAudit(iw);
                    
                    boolean bd = new GenericServicio<ImagenWeb>(ImagenWeb.class).guardar(iw);
                }
            }
        }
    }

    /**
     * @return the renderWuc
     */
    public boolean isRenderWuc() {
        return renderWuc;
    }

    /**
     * @param renderWuc the renderWuc to set
     */
    public void setRenderWuc(boolean renderWuc) {
        this.renderWuc = renderWuc;
    }

    public String getRichText() {
        return richText;
    }

    public void setRichText(String richText) {
        this.richText = richText;
    }
    
    

    /**
     * @return the imagenes
     */
    public CmailListDataModel<ImageFile> getImagenes() {
        List<ImageFile> lstImgs = new ArrayList<ImageFile>();
        
        imagenes = new CmailListDataModel<ImageFile>();
        
        List<ImagenWeb> lst = new ImagenWebServicio().listarImagenesWeb();
        for (Iterator<ImagenWeb> it = lst.iterator(); it.hasNext();) {
            ImagenWeb im = it.next();
            
            ImageFile imf = new ImageFile(im);
            lstImgs.add(imf);
        }        
        
        imagenes = new CmailListDataModel<ImageFile>(lstImgs);
        return imagenes;
    }

    /**
     * @param imagenes the imagenes to set
     */
    public void setImagenes(CmailListDataModel<ImageFile> imagenes) {
        this.imagenes = imagenes;
    }

    /**
     * @return the imagenReombrar
     */
    public ImageFile getImagenReombrar() {
        return imagenReombrar;
    }

    /**
     * @param imagenReombrar the imagenReombrar to set
     */
    public void setImagenReombrar(ImageFile imagenReombrar) {
        this.imagenReombrar = imagenReombrar;
    }

    /**
     * @return the renderRenombrar
     */
    public boolean isRenderRenombrar() {
        return renderRenombrar;
    }

    /**
     * @param renderRenombrar the renderRenombrar to set
     */
    public void setRenderRenombrar(boolean renderRenombrar) {
        this.renderRenombrar = renderRenombrar;
    }

    /**
     * @return the nuevoNombre
     */
    public String getNuevoNombre() {
        return nuevoNombre;
    }

    /**
     * @param nuevoNombre the nuevoNombre to set
     */
    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    public boolean isReemplazar() {
        return reemplazar;
    }

    public void setReemplazar(boolean reemplazar) {
        this.reemplazar = reemplazar;
    }            
    
}