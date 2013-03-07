/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.core.CmailList;
import org.cmail.rehabilitacion.modelo.sira.ArticuloWeb;
import org.cmail.rehabilitacion.modelo.sira.ImagenWeb;
import org.cmail.rehabilitacion.servicio.GenericServicio;
import org.cmail.rehabilitacion.servicio.ImagenWebServicio;
import org.cmail.rehabilitacion.vista.model.ImageFile;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 *
 * @author Usuario
 */

@ManagedBean
@ViewScoped
public class HomeController extends Controller implements Serializable{
    
    private List<ImagenWeb> imagenesGaleria = new ArrayList<ImagenWeb>();
    private CmailList<ArticuloWeb> entradasHome = new CmailList<ArticuloWeb>();

    public HomeController() {
    }
    
    @PostConstruct
    public void init(){
        imagenesGaleria = new ImagenWebServicio().listarGaleriaHomePage();
        entradasHome = new GenericServicio<ArticuloWeb>(ArticuloWeb.class).listarPorPropiedad("activo", true);
        initImages();
    }
    
    private void initImages(){        
        List<ImagenWeb> lista = new ImagenWebServicio().listarImagenesWeb();
        for (Iterator<ImagenWeb> it = lista.iterator(); it.hasNext();) {
            ImagenWeb im = it.next();
            new ImageFile(im);
        }
    }   
    
    public void eventoLeerEntrada(ActionEvent evt) {        
        String pr = FacesUtils.getRequestParameter("contenidoId");
        log().info("Parametro: " + pr);
        Long id = Long.parseLong(pr);
        ArticuloWeb p = new GenericServicio<ArticuloWeb>(ArticuloWeb.class).obtenerPorId(id);
        FacesUtils.getSessionBean().setParrafoEdicion(p);
        FacesUtils.getMenuController().redirectHome(Constantes.VW_LEE_ARTICULO);
    }
    
    public void eventoCancelarLeerEntrada(ActionEvent evt) {
        FacesUtils.getMenuController().redirectHome();
    }

    public List<ImagenWeb> getImagenesGaleria() {
        return imagenesGaleria;
    }

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
    
}
