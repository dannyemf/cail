/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.vista.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.log4j.Logger;
import org.cmail.rehabilitacion.modelo.core.FileUtil;
import org.cmail.rehabilitacion.modelo.sira.ImagenWeb;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Clase que representa un archivo de imagen.
 * Permite crear imágenes en miniatura, copiar los archivos desde la base de datos a una carpeta más cómoda para presentarlos en las vistas y en los artículos.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class ImageFile implements Serializable{    
    
    protected final Logger log = Logger.getLogger(this.getClass());
    
    public static final String CARPETA_IMAGENES = "/" + "documentos" + "/" + "imagenes" + "/";
    public static final String CARPETA_THUMBAILS = "/" + "documentos" +"/" + "imagenes" + "/" + "thumbail" + "/";
    public static final String PREFIJO_THUMBAILS = "thumbail_";
    
    private String nombre;
    private String rutaAbsoluta;
    private File file;
    private String rutaThumbail;
    private String contextPath;
    
    private ImagenWeb imagenWeb;
    private String mimeType;
    
    /**
     * Constructor mínimo
     * @param image 
     */
    public ImageFile(ImagenWeb image) {        
        this.imagenWeb = image;                
        String file = FacesUtils.getExternalContext().getRealPath("/") + ImageFile.CARPETA_IMAGENES + image.getNombre();        
        File fil = new File(file);
        this.init(fil);
        this.crearArchivo(false);
        this.crearThumbail(false);
    }        
        
    /**
     * Constructor mínimo
     * @param f
     * @param contentType 
     */
    public ImageFile(File f, String contentType) {
        this.mimeType = contentType;
        this.init(f);
        this.crearThumbail(false);
    }
    
    /**
     * Inicaliza la imagen
     * @param f el archivo
     */
    public void init(File f){
        this.file = f;
        this.nombre = f.getName();
        this.contextPath = FacesUtils.getExternalContext().getRequestContextPath();
        
        this.rutaAbsoluta = this.imagenWeb != null ? this.imagenWeb.getRutaAbsoluta():
                this.contextPath + CARPETA_IMAGENES + f.getName();
        
        this.rutaThumbail =   this.contextPath + CARPETA_THUMBAILS + PREFIJO_THUMBAILS + f.getName();                
    }        
    
    /**
     * Crear un thumbail o imagen en miniatura
     * @param force indica si se debe sobreescribir
     */
    public void crearThumbail(boolean force){
        try {
            String realPathThum=  FacesUtils.getExternalContext().getRealPath("/") + CARPETA_THUMBAILS + PREFIJO_THUMBAILS + nombre;
            File fileThum = new File(realPathThum);
            
            if(file.exists()){
                if(fileThum.exists() == false || force){
                    log.info("Creando thumbail: " + realPathThum);

                    BufferedImage im = new BufferedImage(52, 52, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = (Graphics2D)im.getGraphics();

                    ImageIcon imc = new ImageIcon(file.getAbsolutePath());
                    log.info("Image: " + imc.getImage());
                    int w = imc.getIconWidth();
                    int h = imc.getIconHeight();
                    int iw = 52, ih = 52;
                    int y = 0;

                    if(w >= h){
                        ih = (h * iw) / w;
                        y = (52 - ih) / 2;
                    }else{
                        iw = (h * ih)/ w;
                    }
                    g.setBackground(Color.white);
                    g.clearRect(0, 0, 52, 52);
                    g.drawImage(imc.getImage(), 0,y,iw,ih,imc.getImageObserver());


                    //byte[] by = ImageUtil.getPackedBinaryData(im.getData(), new Rectangle(52,52));
                    FileOutputStream fo = new FileOutputStream(fileThum);            
                    ImageIO.write(im, "png", fo);
                    fo.close();
                }
            }
        } catch (Exception e) { 
            log.error("Error: ", e);
        }
    }
    
    /**
     * Crear el fichero con la data de la base de datos
     * @param force Indica si se debe sobreescribir
     */
    public void crearArchivo(boolean force){
        try {
            if(file.exists() == false || force){
                FileOutputStream fo = new FileOutputStream(file);
                fo.write(imagenWeb.getData());
                fo.close();
            }
        } catch (Exception e) {
        }
    }
    
    /**
     * Renombra el archivo
     * @param nombre el nuevo nombre
     * @return true si se renombró
     */
    public boolean renameTo(String nombre){                
        
        nombre = nombre.trim();
        String ext = FileUtil.getExtension(file);
        String oldName = file.getName();        
        String newName = nombre + ext;
        ImagenWeb iw = imagenWeb;
        
        if(nombre.length() > 0){
            File remFile = new File(file.getAbsolutePath().replace(oldName, newName));
            if(remFile.exists() == false){
                boolean r = file.renameTo(remFile);
                
                iw.setNombre(newName);
                iw.setRutaAbsoluta(iw.getRutaAbsoluta().replace(oldName, newName));
                
                this.setNombre(newName);
                this.setFile(remFile);
                this.rutaAbsoluta = rutaAbsoluta.replace(oldName, newName);
                this.rutaThumbail = rutaThumbail.replace(oldName, newName);
                
                return r;
            }
        }
        
        return false;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the rutaAbsoluta
     */
    public String getRutaAbsoluta() {
        return rutaAbsoluta;
    }

    /**
     * @param rutaAbsoluta the rutaAbsoluta to set
     */
    public void setRutaAbsoluta(String rutaAbsoluta) {
        this.rutaAbsoluta = rutaAbsoluta;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the rutaThumbail
     */
    public String getRutaThumbail() {
        return rutaThumbail;
    }

    /**
     * @param rutaThumbail the rutaThumbail to set
     */
    public void setRutaThumbail(String rutaThumbail) {
        this.rutaThumbail = rutaThumbail;
    }

    /**
     * @return the imagenWeb
     */
    public ImagenWeb getImagenWeb() {
        if(imagenWeb == null){
            imagenWeb = new ImagenWeb();
            imagenWeb.setNombre(nombre);
            imagenWeb.setRutaAbsoluta(rutaAbsoluta);
            //imagenWeb.setRutaAbsoluta(rutaAbsoluta.replace(contextPath, ""));             
            imagenWeb.setMimeType(mimeType);
            
            try {
                byte[] data = FileUtil.loadData(file);
                imagenWeb.setData(data);
            } catch (Exception e) {                
            }
        }
        return imagenWeb;
    }
    
    /**
     * Obtiene un atg html para la imagen
     * <p>Ejemplo: &lt;img src="..."/&gt;</p>
     * @return 
     */
    public String getImageTag(){
        return "<img src=\""+ this.rutaAbsoluta +"\"/>";
    }
    
}
