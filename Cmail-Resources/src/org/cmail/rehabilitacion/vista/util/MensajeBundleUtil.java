/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.vista.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;


/**
 * Clase de utilidad para cargar mensajes de los archivos de recursos (Internacionalización).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class MensajeBundleUtil {        
    
    protected static Logger log = Logger.getLogger(MensajeBundleUtil.class);
    
    public static final String RUTA_MENSAJES    = "org.cmail.rehabilitacion.vista.recursos.mensajes";
    public static final String RUTA_ETIQUETAS   = "org.cmail.rehabilitacion.vista.recursos.etiquetas";
    public static final String RUTA_ROUTES      = "org.cmail.rehabilitacion.vista.recursos.rutas";
    public static final String RUTA_ACCIONES    = "org.cmail.rehabilitacion.vista.recursos.acciones";
    
    private static HashMap messageBundles = new HashMap();
    
    /**
     * Obtiene un mensaje de ruta para el mapa de navegación (Usted está aquí).
     * 
     * @param key la clave
     * @return el mensaje
     */
    public static String getRuta(String key){
        String xx = getBundle(RUTA_ROUTES).getString(key);
        if(xx == null || xx.equals("")){
            return "<NoRouteKey>";
        }
        return xx;
    }
    
    /**
     * Obtiene un mensaje del archivo de recursos.
     * 
     * @param key la clave
     * @return  el mensaje
     */
    public static String getMensaje(String key){
        String xx = getBundle(RUTA_MENSAJES).getString(key);
        if(xx == null || xx.equals("")){
            return "¿¿¿"+key+"???";
        }
        return xx;
    }
    
    /**
     * Obtiene la etiqueta usada para los controles (texto en botones, etc.).
     * @param key la clave
     * @return la etiqueta
     */
    public static String getEtiqueta(String key){
        String xx = getBundle(RUTA_ETIQUETAS).getString(key);
        if(xx == null || xx.equals("")){
            return "¿¿¿"+key+"???";
        }
        return xx;
    }
    
    /**
     * Obtiene el nombre de la acción usada en los controles (botones, vínculos, etc.)
     * @param key la clave
     * @return la acción
     */
    public static String getAccion(String key){
        String xx = getBundle(RUTA_ACCIONES).getString(key);
        if(xx == null || xx.equals("")){
            return "¿¿¿"+key+"???";
        }
        return xx;
    }
    

    /**
     * Obtiene un el recurso para la presentación de mensajes, etiquetas, etc.
     * 
     * @param bundle La ruta del archivo de recursos
     * @return el recurso
     */
    public static ResourceBundle getBundle(String bundle){                
        
        Locale locale = null;
        try {
            locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        } catch (Exception e) {
            locale = locale.getDefault();
        }
        
        log.info("Faces Context Locale: " + locale);
        
        if (locale == null) {
            locale = new Locale("es", "EC");
            log.info("No Locale: " + locale);
        }
        
        String key = bundle+"-"+locale.toString();
        
        ResourceBundle messages = (ResourceBundle)messageBundles.get(key);
        
        if (messages == null) {
            messages = ResourceBundle.getBundle(bundle, locale);
            messageBundles.put(key, messages);
        }
        
        return messages;
    }          
    
}

