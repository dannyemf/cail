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

    private TimeZone timeZone = null;
    private Locale locale;
    
    
    private String prmInstitucion;
    private String prmSlogan;
    private String prmSiglas;

    /**Constructor por defecto*/
    public MainController() {
        String tema = FacesUtils.getStyleBean().getAceTheme();        
    }
    
    public List<Canton> cantones(Provincia p){        
        if(p != null){
            return new ArrayList(p.getCantones());        
        }
        return new ArrayList();        
    }
    
    public List<Parroquia> parroqias(Canton p){        
        if(p != null){
            return new ArrayList(p.getParroquias());        
        }
        return new ArrayList();        
    }
    
    
    
    public TimeZone getTimeZone() {
        
        if(timeZone == null){
            timeZone = TimeZone.getDefault();
        }
        
        return timeZone;
    }
    
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
