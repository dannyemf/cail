package org.cmail.rehabilitacion.controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.excepcion.ActivoException;
import org.cmail.rehabilitacion.excepcion.ClaveException;
import org.cmail.rehabilitacion.excepcion.LoginException;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.servicio.UsuarioServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador de inicio y cierre de sesión de un usuario.
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_LOGIN)
@SessionScoped
public class LoginController extends Controller{

    private String usuario;
    private String clave;
    private ThreadLocal<Usuario> threadLocal;

    /**Constructor por defecto*/
    public LoginController() {
    }

    public void login(ActionEvent eve) {
        Usuario us = null;
        try {
            us = new UsuarioServicio().logear(usuario, clave);
        } catch (LoginException e) {
            FacesUtils.addErrorMessage(null, e.getMessage());
        } catch (ActivoException e) {
            FacesUtils.addErrorMessage(null, e.getMessage());
        } catch (ClaveException e) {
            FacesUtils.addErrorMessage(null, e.getMessage());
        } catch (Exception e) {
            //FacesMessage m = new FacesMessage("Error al loogear: " + e.getMessage());
            //m.setSeverity(FacesMessage.SEVERITY_FATAL);
            //throw new ValidatorException(m);
            FacesUtils.addErrorMessage(null, e.getMessage());
        }

        MenuController menu = FacesUtils.getMenuController();
        if (us != null) {            
            FacesUtils.getSessionBean().setUsuarioLogeado(us);            
            menu.crearMenu(us);
            menu.redirectMainApp();                        
        }
        
    }

    //cerrar sesion
    public String logOff() {
        MenuController menu = FacesUtils.getMenuController();
        HttpSession ss = FacesUtils.getHttpSession(true);
        ss.invalidate();
        //menu.redirectMainApp();
        //return "logoff";
        return "home";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}