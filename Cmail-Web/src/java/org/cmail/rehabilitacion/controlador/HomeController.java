/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.cmail.rehabilitacion.dao.hql.K;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.core.CmailList;
import org.cmail.rehabilitacion.modelo.seguridad.Parametro;
import org.cmail.rehabilitacion.modelo.seguridad.TipoParametro;
import org.cmail.rehabilitacion.modelo.sira.ArticuloWeb;
import org.cmail.rehabilitacion.modelo.sira.ImagenWeb;
import org.cmail.rehabilitacion.servicio.GenericServicio;
import org.cmail.rehabilitacion.servicio.ImagenWebServicio;
import org.cmail.rehabilitacion.servicio.ParametroServicio;
import org.cmail.rehabilitacion.vista.model.ImageFile;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador de la página de incio.
 * Este controlador permite cargar las imagenes que se presentan en el banner y los artículos.
 * También carga los artículos en la sección de noticias y los artículos que deben salir con un menú en la página principal.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */

@ManagedBean
@ViewScoped
public class HomeController extends Controller implements Serializable{
    
    private List<ImagenWeb> imagenesGaleria = new ArrayList<ImagenWeb>();
    private CmailList<ArticuloWeb> entradasHome = new CmailList<ArticuloWeb>();
    private CmailList<ArticuloWeb> entradasMenu = new CmailList<ArticuloWeb>();
    private Parametro parametroSize;

    /**Constructor por defecto*/
    public HomeController() {
    }
    
    final String CARPETA_THUMBAILS = "/" + "documentos" + "/" + "galeria" + "/";
    
    /**
     * Inicializa los parámetros y las imaganes
     */
    @PostConstruct
    public void init(){
        parametroSize = new ParametroServicio().obtenerParametro(Constantes.PRM_SIZE_IMGANE_GALERIA, TipoParametro.Dimension);
        
        imagenesGaleria = new ImagenWebServicio().listarGaleriaHomePage();
        for (Iterator<ImagenWeb> it = imagenesGaleria.iterator(); it.hasNext();) {
            ImagenWeb imagenWeb = it.next();
            imageResized(imagenWeb);
        }
        
        entradasHome = new GenericServicio<ArticuloWeb>(ArticuloWeb.class).listarPorPropiedadesValores(K.eq("activo", true), K.eq("paginaPrincipal", false));
        entradasMenu = new GenericServicio<ArticuloWeb>(ArticuloWeb.class).listarPorPropiedadesValores(K.eq("activo", true), K.eq("paginaPrincipal", true));
        
        initImages();
    }
    
    /**
     * Inicializa las imagenes
     */
    private void initImages(){        
        List<ImagenWeb> lista = new ImagenWebServicio().listarImagenesWeb();
        for (Iterator<ImagenWeb> it = lista.iterator(); it.hasNext();) {
            ImagenWeb im = it.next();
            new ImageFile(im);
        }
    }   
    
    /**
     * Redimensiona las imagenes para la galería de fotos
     * @param img 
     */
    public void imageResized(ImagenWeb img){        
               
        String folder = FacesUtils.getExternalContext().getRealPath("/") + CARPETA_THUMBAILS;
        File f = new File(folder);
        if(!f.exists()){
            f.mkdirs();
        }
        
        String realPathThum=  folder + img.getNombre();
        
        
        File fileThum = new File(realPathThum);
        
        Dimension d = parametroSize.toDimension();
        int IMG_WIDTH = d.width;
        int IMG_HEIGHT = d.height;
        
        try {            
            boolean create = true;
            
            if(fileThum.exists()){
                BufferedImage eb = ImageIO.read(fileThum);
                if(IMG_WIDTH == eb.getWidth() || IMG_HEIGHT == eb.getHeight()){
                    create = false;
                }
            }
            
            if(create){
                fileThum.createNewFile();            
                
                
                BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(img.getData()));
                
                Thumbnails.of(originalImage).size(IMG_WIDTH, IMG_HEIGHT).crop(Positions.CENTER).toFile(fileThum);
                
                /*BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
                
                g.dispose();	
                g.setComposite(AlphaComposite.Src);

                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                ImageIO.write(resizedImage, "jpg", fileThum);
                * */
                
                //Thumbnails.of(ob).crop().size(100,100).outputFormat("jpg").toFile(fileThum);
            }
            
        } catch (Exception e) {
            log().error(img.getNombre(), e);            
        }
    }
    
    /**
     * Evento que muestra la página para leer o ver un artículo.
     * @param evt el evento
     */
    public void eventoLeerEntrada(ActionEvent evt) {        
        String pr = FacesUtils.getRequestParameter("contenidoId");
        log().info("Parametro: " + pr);
        Long id = Long.parseLong(pr);
        ArticuloWeb p = new GenericServicio<ArticuloWeb>(ArticuloWeb.class).obtenerPorId(id);
        FacesUtils.getSessionBean().setParrafoEdicion(p);
        FacesUtils.getMenuController().redirectHome(Constantes.VW_LEE_ARTICULO);
    }
    
    /**
     * Evento que retorna a la página principal (inicio) después de leer el artículo.
     * @param evt 
     */
    public void eventoCancelarLeerEntrada(ActionEvent evt) {
        FacesUtils.getMenuController().redirectHome();
    }

    /**
     * @return the imagenesGaleria
     */
    public List<ImagenWeb> getImagenesGaleria() {
        return imagenesGaleria;
    }

    /**
     * @param imagenesGaleria the imagenesGaleria to set
     */
    public void setImagenesGaleria(List<ImagenWeb> imagenesGaleria) {
        this.imagenesGaleria = imagenesGaleria;
    }
    
    /**
     * @return the entradasHome
     */
    public CmailList<ArticuloWeb> getEntradasHome() {        
        return entradasHome;
    }

    /**
     * @param entradasHome the entradasHome to set
     */
    public void setEntradasHome(CmailList<ArticuloWeb> entradasHome) {
        this.entradasHome = entradasHome;
    }

    /**
     * @return the parametroSize
     */
    public Parametro getParametroSize() {
        return parametroSize;
    }

    /**
     * @param parametroSize the parametroSize to set
     */
    public void setParametroSize(Parametro parametroSize) {
        this.parametroSize = parametroSize;
    }

    /**
     * @return the entradasMenu
     */
    public CmailList<ArticuloWeb> getEntradasMenu() {
        return entradasMenu;
    }

    /**
     * @param entradasMenu the entradasMenu to set
     */
    public void setEntradasMenu(CmailList<ArticuloWeb> entradasMenu) {
        this.entradasMenu = entradasMenu;
    }
    
}
