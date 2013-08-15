/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import org.cmail.rehabilitacion.vista.util.MensajeBundleUtil;
import org.cmail.rehabilitacion.vista.model.TipoNotificacion;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.cmail.rehabilitacion.controlador.bean.SessionBean;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.modelo.AuditEntity;
import org.cmail.rehabilitacion.modelo.DomainEntity;
import org.cmail.rehabilitacion.modelo.core.FileUtil;
import org.cmail.rehabilitacion.vista.util.FacesUtils;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
import org.icefaces.util.JavaScriptRunner;

/**
 * Clase base del que deben heredad todos los controladores ya que posee métodos
 * prácticos de tal manera que puedan usarse directamente.
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Controller implements Serializable {

    /**
     * Obtiene un mensaje del archivo de recursos
     *
     * @param key la clave
     * @return el mensaje
     */
    public String mensajeBundle(String key) {
        return MensajeBundleUtil.getMensaje(key);
    }

    /**
     * Obtiene una etiqueta (mensaje) del archivo de recursos
     *
     * @param key la clave
     * @return el mensaje
     */
    public String etiquetaBundle(String key) {
        return MensajeBundleUtil.getEtiqueta(key);
    }

    /**
     * Obtiene una acción (mensaje) del archivo de recursos
     *
     * @param key la clave
     * @return el mensaje
     */
    public String accionBundle(String key) {
        return MensajeBundleUtil.getAccion(key);
    }

    /**
     * Obtiene el bean manejado por su clase y nombre
     *
     * @param <T>
     * @param type clase genérica
     * @param name el nombre del bean
     * @return el bean
     */
    public <T extends Object> T getBean(Class<? extends T> type, String name) {
        return (T) FacesUtils.getManagedBean(name);
    }

    /**
     * Obtiene la sesión HTP sobre la que está corriendo la aplicación.
     *
     * @param create indica que si no existe la sesión debe crearse
     * @return la sesión
     */
    public static HttpSession getHttpSession(boolean create) {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(create);
    }

    /**
     * Obtiene el contexto JSF de ejecución
     *
     * @return el contexto
     */
    public static ExternalContext getExternalContext() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext();
    }

    /**
     * Obtiene el contexto JSF de ejecución de los Servlets
     *
     * @return el contexto
     */
    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    /**
     * Obtiene la aplicación sobre la que se está ejecutando
     *
     * @return la aplicación
     */
    private static Application getApplication() {
        ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        return appFactory.getApplication();
    }

    /**
     * Inicializa la auditoría de una entidad cuando esta es creada, editada.
     *
     * @param entidad la entidad a auditar
     */
    public void initAudit(DomainEntity entidad) {
        if (entidad instanceof AuditEntity) {
            AuditEntity e = (AuditEntity) entidad;
            log().info("Auditando entidad: (Nombre=" + e.getClass().getSimpleName() + ", Id=" + e.getId() + ")");
            Usuario us = getUsuarioLogeado();
            log().info("Usuario auditar..." + us.getLogin());
            e.setAuditUpdateUser(us.getLogin());
            log().info("Auditar --> OK");
        } else {
            log().info("Entidad no auditable: (Nombre=" + entidad.getClass().getSimpleName() + ")");
        }
    }

    /**
     * Obtiene el contenido en bytes de un archivo subido desde la aplicación
     * web usando el componente FileEntry.
     *
     * @param fileInfo información del archivo
     * @param contentTypes tipo de contenido soportado
     * @return la data
     */
    public byte[] getFileBytes(FileEntryResults.FileInfo fileInfo, String... contentTypes) {
        if (fileInfo.isSaved()) {

            String ct = fileInfo.getContentType();
            log().info("ContetType: " + ct);

            boolean vc = contentTypes == null || (contentTypes != null && contentTypes.length == 0) ? true : false;
            for (String c : contentTypes) {
                if (ct.equals(ct)) {
                    vc = true;
                }
            }

            if (vc) {
                log().info("Saveed to: " + fileInfo.getFile().getAbsolutePath());
                File f = fileInfo.getFile();
                try {
                    return FileUtil.loadData(f);
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    /**
     * Obtiene el contenido en bytes de un archivo subido desde la aplicación
     * web usando el componente InputFile,
     *
     * @param event el evento
     * @param contentTypes el tipo de contenidos soportados
     * @return la data
     */
    public byte[] getFileBytes(FileEntryEvent event, String... contentTypes) {
        try {

            log().info("Listener invocado...");
            FileEntry fileEntry = (FileEntry) event.getSource();
            FileEntryResults results = fileEntry.getResults();

            for (FileEntryResults.FileInfo fileInfo : results.getFiles()) {
                return getFileBytes(fileInfo, contentTypes);
            }
        } catch (Exception e) {
        }

        return null;
    }

    /**
     * Obtiene el componente (control) de la página actual mediante su id.
     *
     * @param id el id del componente
     * @return el componente o null si no lo encuentra
     */
    public UIComponent getComponent(String id) {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getViewRoot().findComponent(id);
    }

    /**
     * Obtiene el componente genérico (control) de la página actual mediante su
     * id.
     *
     * @param <T>
     * @param c la clase del componente
     * @param id el id del componente
     * @return el componente del tipo T
     */
    public <T extends UIComponent> T getComponent(Class<? extends T> c, String id) {
        FacesContext fc = FacesContext.getCurrentInstance();
        return (T) fc.getViewRoot().findComponent(id);
    }

    /**
     * Obtiene la ruta de la página a la que se debe regresar.
     *
     * @return la url
     */
    public String getReturnUrl() {
        return (String) FacesUtils.getSessionBean().getSessionMap("returnUrl");
    }

    /**
     * Fija la ruta a la que debe regresar una página después de que concluya su
     * acción.
     *
     * @param urlOptativa url a la que debe regresar en caso de que no se
     * especifique ningún returnUrl
     * @param returnUrl url a la que debe retornar
     */
    public void setReturnUrl(String urlOptativa, String returnUrl) {
        if (returnUrl == null || returnUrl.equals("")) {
            FacesUtils.getSessionBean().addSessionMap("returnUrl", urlOptativa);
        } else {
            FacesUtils.getSessionBean().addSessionMap("returnUrl", returnUrl);
        }
    }

    /**
     * Limpia la ultima ruta del mapa de navegación (Usted está aquí)
     */
    public void clearLastRoute() {
        FacesUtils.getMenuController().clearLastRoute();
    }

    /**
     * Limpia todas las rutas del mapa de navegación (Usted está aquí)
     */
    public void clearRoute() {
        FacesUtils.getMenuController().clearRoute();
    }

    /**
     * Limpia las n últimas rutas del mapa de navegación (Usted está aquí)
     *
     * @param nLastRutas el número de rutas
     */
    public void clearRoute(int nLastRutas) {
        FacesUtils.getMenuController().clearRoute(nLastRutas);
    }

    /**
     * Agrega las rutas al mapa de navegación (Usted está aquí)
     *
     * @param routeKeys las rutas
     */
    public void addRoute(String... routeKeys) {
        for (String rk : routeKeys) {
            String ruta = MensajeBundleUtil.getRuta(rk);
            FacesUtils.getMenuController().addRoute(ruta);
        }
    }

    /**
     * Obtiene el usuario que ha iniciado sesión en el sistema
     *
     * @return el usuario
     */
    public Usuario getUsuarioLogeado() {
        return FacesUtils.getSessionBean().getUsuarioLogeado();
    }

    /**
     * Obtiene el bean de sesión
     *
     * @return el bean
     */
    public SessionBean getSessionBean() {
        return FacesUtils.getSessionBean();
    }

    /**
     * Muestra un mensaje en la página actual
     *
     * @param tipo tipo de mensaje
     * @param mensaje el mensaje
     */
    public void showMensaje(TipoNotificacion tipo, String mensaje) {
        getSessionBean().notificar(tipo, mensaje);
    }

    /**
     * Muestra un mensaje genérico para los resultados encontrados en la páginas
     * de búsquedas
     *
     * @param lista la lista de resultados
     */
    public void showMessageResultList(List lista) {
        if (lista == null || lista.isEmpty()) {
            showMensaje(TipoNotificacion.Aviso, "No se encontraron resultados");
        }
    }

    /**
     * Obtiene el login (nombre de usuario) del usuario logeado.
     *
     * @return el login
     */
    public String getLogin() {
        return FacesUtils.getSessionBean().getUsuarioLogeado().getLogin();
    }

    /**
     * Obtiene un logger para reportar información, errores, etc.
     *
     * @return el logger (log4j)
     */
    public Logger log() {
        return Logger.getLogger(this.getClass());
    }

    /**
     * Presenta un mensaje de información en la etiqueta ice:messages
     *
     * @param msg el mensaje
     */
    public void infoMessage(String msg) {
        infoMessage(null, msg);
    }

    /**
     * Presenta un mensaje de información en la etiqueta ice:mesage del control
     * indicado por su id
     *
     * @param clientId el id del control
     * @param msg el mensaje
     */
    public void infoMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }

    /**
     * Presenta un mensaje de error en la etiqueta ice:messages
     *
     * @param msg el mensaje
     */
    public void errorMessage(String msg) {
        errorMessage(null, msg);
    }
    
    /**
     * Presenta un mensaje de error en la etiqueta ice:mesage para el control con el id indicado.
     * 
     * @param clientId
     * @param msg 
     */
    public void errorMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
    }

    /**
     * Muestra un mensaje indicando si una entidad se ha borrado o no.
     * @param b indica si la entidad se borró
     */
    public void showMessageDeleted(boolean b) {
        showMessageDeleted(b, "");
    }

    /**
     * Muestra un mensaje indicando si una entidad se ha borrado o no.
     * @param b indica si la entidad se borró
     * @param inf información adicional
     */
    public void showMessageDeleted(boolean b, String inf) {
        String msg = "";
        inf = inf == null || inf.trim().length() == 0 ? "" : ": " + inf;
        if (b) {
            msg = mensajeBundle("acc_borrada_correctamente");
            showMensaje(TipoNotificacion.Aviso, msg + inf);
        } else {
            msg = mensajeBundle("acc_borrada_error");
            showMensaje(TipoNotificacion.Error, msg + inf);
        }
    }

    /**
     * Muestra un mensaje indicando su una entidad se guardó o no.
     * @param b indica si la entidad se guardó
     */
    public void showMessageSaved(boolean b) {
        String msg = "";
        if (b) {
            msg = mensajeBundle("acc_guardada_correctamente");
            showMensaje(TipoNotificacion.Aviso, msg);
        } else {
            msg = mensajeBundle("acc_guardada_error");
            showMensaje(TipoNotificacion.Error, msg);
        }
    }   

    /**
     * Crea una expresión de método para una acción y fijarlo a un control mediante setActionExpresion(...).
     *
     * @param name la expresión en formato "#{beanName.actionListenerName}"
     * @return un objeto MethodExpression
     */
    public MethodExpression createMethodExpressionForAction(String name) {
        Class[] argtypes = new Class[0];
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        ELContext elContext = facesCtx.getELContext();
        return facesCtx.getApplication().getExpressionFactory().createMethodExpression(elContext, name, null, argtypes);
    }

    /**
     * Crea una expresión de método para un evento y fijarlo a un control mediante setActionListener(...).
     *
     * @param name la expresión en formato "#{beanName.actionListenerName}"
     * @return un objeto MethodExpression
     */
    public MethodExpression createMethodExpresionForActionListener(String name) {
        Class[] argtypes = new Class[1];
        argtypes[0] = ActionEvent.class;
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        ELContext elContext = facesCtx.getELContext();
        return facesCtx.getApplication().getExpressionFactory().createMethodExpression(elContext, name, null, argtypes);
    }

    /**
     * Crea un MethodExpressionActionListener para fijarlo con addActionListener
     *
     * @param name la expresión en formato "#{beanName.actionListenerName}"
     */
    public MethodExpressionActionListener createMethodExpressionActionListener(String name) {
        MethodExpression actionListenerExpression = createMethodExpresionForActionListener(name);
        MethodExpressionActionListener meActionListener = new MethodExpressionActionListener(actionListenerExpression);
        return meActionListener;
    }

    /**
     * Lanza una excepción de validación (ValidatorException) con el mensaje indicado.
     * @param message el mensaje
     */
    public void validationMessage(String message) {
        FacesMessage m = new FacesMessage(message);
        m.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(m);
    }

    /**
     * Ejecuta una línea de código java script usando JavaScriptRunner
     * @param script la línea de código
     */
    public void runScript(String script) {
        JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), script);
    }
}
