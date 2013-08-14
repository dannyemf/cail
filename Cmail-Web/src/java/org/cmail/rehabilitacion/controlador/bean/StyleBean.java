
package org.cmail.rehabilitacion.controlador.bean;

import org.cmail.rehabilitacion.modelo.core.Constantes;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.cmail.rehabilitacion.modelo.seguridad.TipoParametro;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.servicio.ParametroServicio;
import org.cmail.rehabilitacion.servicio.UsuarioServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name="styleBean")
@SessionScoped
public class StyleBean implements Serializable {

    private String aceTheme = null;
    
    /**
     * Creates a new instance of the StyleBean.
     */
    public StyleBean() {
        
    }
    
    public void changeTheme(ValueChangeEvent e) throws java.io.IOException{
        String th = (String)e.getNewValue();
        if (!aceTheme.equalsIgnoreCase(th)) {
            aceTheme = th;
            Usuario usContexto = FacesUtils.getUsuarioController().getUsuarioLogeado();
            if(usContexto != null){
                UsuarioServicio usSrv = new UsuarioServicio();
                usSrv.refrescar(usContexto);
                usContexto.setTemaUi(th);
                usContexto.setAuditUpdateUser(usContexto.getLogin());
                usSrv.guardar(usContexto);
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("cmail.jsf");
        }
    }

    /**
     * @return the aceTheme
     */
    public String getAceTheme() {
        if(aceTheme == null){
            aceTheme = new ParametroServicio().obtenerValor(Constantes.PRM_TEMA_HOME, TipoParametro.Cadena);
        }
        return aceTheme;
    }
    
    public String getAceMenuIcons() {
        return "background-image: url(\"#{resource['ace-"+getAceTheme()+":images/ui-icons_2694e8_256x240.png']}\");";
    }
    
    public boolean inAceTheme(String themes){
        if(themes != null){
            for (String s : themes.split(";")) {
                if(s.equals(getAceTheme())){
                    return true;
                }
            }
        }
        
        return false;
    }

    /**
     * @param aceTheme the aceTheme to set
     */
    public void setAceTheme(String aceTheme) {
        this.aceTheme = aceTheme;
    }   

}
