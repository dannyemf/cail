/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.localizacion.Canton;
import org.cmail.rehabilitacion.modelo.localizacion.Parroquia;
import org.cmail.rehabilitacion.modelo.localizacion.Provincia;
import org.cmail.rehabilitacion.modelo.seguridad.TipoParametro;
import org.cmail.rehabilitacion.servicio.ParametroServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador que maneja la configuración del sistema.
 * Este controlador almacena la configuración regional y la zona horaria; 
 * también guarda el valor de los parámetros comunes para presentarlos en múltiples páginas.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_MAIN)
@SessionScoped
public class MainController  extends Controller{

    /** Configuración de la zona horaria */
    private TimeZone timeZone = null;
    /** Configuración de la ubicación */
    private Locale locale;
    /** Nombre de la institución cargada desde un parámetro */    
    private String prmInstitucion;
    /** Slogan de la institución cargada desde un parámetro */    
    private String prmSlogan;
    /** Siglas de la institución cargada desde un parámetro */    
    private String prmSiglas;

    /**Constructor por defecto*/
    public MainController() {
        String tema = FacesUtils.getStyleBean().getAceTheme();        
    }
    
    /**
     * Obtiene una lista de los cantones de la provincia indicada
     * @param provincia la provincia
     * @return lista de cantones
     */
    public List<Canton> cantones(Provincia provincia){        
        if(provincia != null){
            return new ArrayList(provincia.getCantones());        
        }
        return new ArrayList();        
    }
    
    /**
     * Obtiene una lista de los parroquias del cantón indicado
     * @param canton el cantón
     * @return lista de parroquias
     */
    public List<Parroquia> parroqias(Canton canton){        
        if(canton != null){
            return new ArrayList(canton.getParroquias());        
        }
        return new ArrayList();        
    }
    
    /**
     * @return the timeZone
     */
    public TimeZone getTimeZone() {        
        if(timeZone == null){
            timeZone = TimeZone.getDefault();
        }        
        return timeZone;
    }
    
    /**
     * Obtiene la ruta del contexto actual
     * @return la ruta
     */
    public String getContextPath(){        
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    /**
     * @return the prmInstitucion
     */
    public String getPrmInstitucion() {
        if(prmInstitucion == null){
            prmInstitucion = new ParametroServicio().obtenerValor(Constantes.PRM_INSTITUCION, TipoParametro.Cadena);
        }
        return prmInstitucion;
    }

    /**
     * @param prmInstitucion the prmInstitucion to set
     */
    public void setPrmInstitucion(String prmInstitucion) {
        this.prmInstitucion = prmInstitucion;
    }

    /**
     * @return the prmSlogan
     */
    public String getPrmSlogan() {
        if(prmSlogan == null){
            prmSlogan = new ParametroServicio().obtenerValor(Constantes.PRM_SLOGAN, TipoParametro.Cadena);
        }
        return prmSlogan;
    }

    /**
     * @param prmSlogan the prmSlogan to set
     */
    public void setPrmSlogan(String prmSlogan) {
        this.prmSlogan = prmSlogan;
    }

    /**
     * @return the prmSiglas
     */
    public String getPrmSiglas() {
        if(prmSiglas == null){
            prmSiglas = new ParametroServicio().obtenerValor(Constantes.PRM_SIGLAS, TipoParametro.Cadena);
        }
        return prmSiglas;
    }

    /**
     * @param prmSiglas the prmSiglas to set
     */
    public void setPrmSiglas(String prmSiglas) {
        this.prmSiglas = prmSiglas;
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        if(locale == null){
            locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            if(locale == null){
                locale = new Locale("es", "EC");
                FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
            }
        }
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    
}
