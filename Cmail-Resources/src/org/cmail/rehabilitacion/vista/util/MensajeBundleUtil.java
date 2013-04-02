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
 *
 * @author Usuario
 */
public class MensajeBundleUtil {        
    
    protected static Logger log = Logger.getLogger(MensajeBundleUtil.class);
    
    public static final String RUTA_MENSAJES    = "org.cmail.rehabilitacion.vista.recursos.mensajes";
    public static final String RUTA_ETIQUETAS   = "org.cmail.rehabilitacion.vista.recursos.etiquetas";
    public static final String RUTA_ROUTES      = "org.cmail.rehabilitacion.vista.recursos.rutas";
    public static final String RUTA_ACCIONES    = "org.cmail.rehabilitacion.vista.recursos.acciones";
    
    private static HashMap messageBundles = new HashMap();
    
    public static String getRuta(String key){
        String xx = getBundle(RUTA_ROUTES).getString(key);
        if(xx == null || xx.equals("")){
            return "<NoRouteKey>";
        }
        return xx;
    }
    
    public static String getMensaje(String key){
        String xx = getBundle(RUTA_MENSAJES).getString(key);
        if(xx == null || xx.equals("")){
            return "¿¿¿"+key+"???";
        }
        return xx;
    }
    
    public static String getEtiqueta(String key){
        String xx = getBundle(RUTA_ETIQUETAS).getString(key);
        if(xx == null || xx.equals("")){
            return "¿¿¿"+key+"???";
        }
        return xx;
    }
    
    
    public static String getAccion(String key){
        String xx = getBundle(RUTA_ACCIONES).getString(key);
        if(xx == null || xx.equals("")){
            return "¿¿¿"+key+"???";
        }
        return xx;
    }
    

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
    
    public static void main(String... args){
        System.out.println(getMensaje("eliminar_usuario"));
        System.out.println(getRuta("nuevo"));
        
    }
    
}

