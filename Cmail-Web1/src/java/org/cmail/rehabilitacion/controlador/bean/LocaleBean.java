/*

 */

package org.cmail.rehabilitacion.controlador.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


/**
 * <p>The LocaleBean is responsible for keeping track of the current application
 * locale.  The locale of the application can be changed using the a
 * selectOneMenu located in the languageThemeControl.xhtml.  The application
 * currently support English, German and Spanish</p>
 *
 * @author ICEsoft Technologies Inc.
 * @since 1.8
 *
 */
@ManagedBean(name= "localeBean")
@SessionScoped
public class LocaleBean implements Serializable{

    //private static final Log log = LogFactory.getLog(LocaleBean.class);

    // current language selection
    private String currentLanguage;
    // current local
    private Locale usedLocale;

    // available locals to choose from.
    private static final ArrayList AVAILABLE_LOCALES = new ArrayList(3);
    static{
        // setup our list of supported languages.
        AVAILABLE_LOCALES.add(new SelectItem("en","English"));
        //AVAILABLE_LOCALES.add(new SelectItem("de","German"));
        AVAILABLE_LOCALES.add(new SelectItem("es","Spanish"));
    }

    /**
     * Creates an instance of the LocalBean.  The default language type is
     * specified by the initial request.  The availableLocales is
     */
    public LocaleBean() {
        // get default language type.
        currentLanguage = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
        usedLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    /**
     * Changes the view roots language type.
     * @param event jsf value change event.
     */
    public void changeLanguage(ValueChangeEvent event){
        FacesContext ctx = FacesContext.getCurrentInstance();
        // find out view roots current local
        Locale locale = ctx.getViewRoot().getLocale();
        String newLanguage = (String) event.getNewValue();
        // see if matches any of our translations.
        if ("en".equals(newLanguage))  {
            currentLanguage = "en";
        }
        else if ("es".equals(newLanguage)) {
            currentLanguage="es";
        }
        else if ("de".equals(newLanguage)) {
            currentLanguage="de";
        }
        // finally change the local for the view root.
        if (!currentLanguage.equals(locale.getLanguage())) 
        {
            usedLocale = new Locale(currentLanguage);
            ctx.getViewRoot().setLocale(usedLocale); 
        }
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public ArrayList getAvailableLocales() {
        return AVAILABLE_LOCALES;
    }

    public Locale getUsedLocale() {
        return usedLocale;
    }
}