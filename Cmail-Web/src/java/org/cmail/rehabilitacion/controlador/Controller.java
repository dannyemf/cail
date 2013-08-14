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
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Controller implements Serializable{        
            
    
    public String mensajeBundle(String key){
        return MensajeBundleUtil.getMensaje(key);
    }
    
    public String etiquetaBundle(String key){
        return MensajeBundleUtil.getEtiqueta(key);
    }
    
    public String accionBundle(String key){
        return MensajeBundleUtil.getAccion(key);
    }
    
    public <T extends Object> T getBean(Class<? extends T> type, String name){
        return (T)FacesUtils.getManagedBean(name);
    }
    
    public static HttpSession getHttpSession(boolean create) {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(create);
    }
    
    public static ExternalContext getExternalContext() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext();
    }
    
    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }
    
    private static Application getApplication() {
        ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        return appFactory.getApplication();
    }    
    
    public void initAudit(DomainEntity entidad){
        if(entidad instanceof AuditEntity){
            AuditEntity e = (AuditEntity)entidad;
            log().info("Auditando entidad: (Nombre="+e.getClass().getSimpleName()+", Id="+ e.getId()+")");            
            Usuario us = getUsuarioLogeado();
            log().info("Usuario auditar..." + us.getLogin());
            e.setAuditUpdateUser(us.getLogin());        
            log().info("Auditar --> OK");
        }else{
            log().info("Entidad no auditable: (Nombre=" + entidad.getClass().getSimpleName()+")");
        }        
    }
    
    public byte[] getFileBytes(FileEntryResults.FileInfo fileInfo, String... contentTypes){
        if (fileInfo.isSaved()) {                
                    
            String ct = fileInfo.getContentType();
            log().info("ContetType: " + ct);

            boolean vc = contentTypes == null || (contentTypes != null && contentTypes.length == 0) ? true : false;
            for (String c : contentTypes) {
                if(ct.equals(ct)){
                    vc = true;
                }
            }

            if(vc){
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
    
    public byte[] getFileBytes(FileEntryEvent event, String... contentTypes){
        try {
            
            log().info("Listener invocado...");
            FileEntry fileEntry = (FileEntry) event.getSource();
            FileEntryResults results = fileEntry.getResults(); 
        
            for (FileEntryResults.FileInfo fileInfo : results.getFiles()) {
                return getFileBytes(fileInfo, contentTypes);
            }
        } catch (Exception e) {}
        
        return null;
    }        
    
    public UIComponent getComponent(String id){
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getViewRoot().findComponent(id);
    }
    
    public <T extends UIComponent> T getComponent(Class<? extends T> c, String id){
        FacesContext fc = FacesContext.getCurrentInstance();
        return (T)fc.getViewRoot().findComponent(id);
    }
    
    public String getReturnUrl() {
        return (String)FacesUtils.getSessionBean().getSessionMap("returnUrl");
    }
    
    public void setReturnUrl(String urlOptativa, String returnUrl) {
        if(returnUrl == null || returnUrl.equals("")){
            FacesUtils.getSessionBean().addSessionMap("returnUrl", urlOptativa);
        }else{
            FacesUtils.getSessionBean().addSessionMap("returnUrl", returnUrl);
        }
    }
    
    /*public void setReturnUrl(String url) {
        FacesUtils.getSessionBean().addSessionMap("returnUrl", url);
    }*/
    
    public void clearLastRoute() {
         FacesUtils.getMenuController().clearLastRoute();
    }
    
    public void clearRoute() {
        FacesUtils.getMenuController().clearRoute();
    }
    
    public void clearRoute(int nLastRutas) {
        FacesUtils.getMenuController().clearRoute(nLastRutas);
    }
    
    public void addRoute(String... routeKeys) {
        for (String rk : routeKeys) {
            String ruta = MensajeBundleUtil.getRuta(rk);
            FacesUtils.getMenuController().addRoute(ruta);
        }        
    }
    
    public Usuario getUsuarioLogeado(){
        return FacesUtils.getSessionBean().getUsuarioLogeado();
    }
    
    public SessionBean getSessionBean(){
        return FacesUtils.getSessionBean();
    }
    
    public void showMensaje(TipoNotificacion tipo, String mensaje){
        getSessionBean().notificar(tipo, mensaje);
    }
    
    public void showMessageResultList(List lista){
        if(lista == null || lista.isEmpty()){
            showMensaje(TipoNotificacion.Aviso, "No se encontraron resultados");
        }
    }
    
    public String getLogin(){
        return FacesUtils.getSessionBean().getUsuarioLogeado().getLogin();
    }
    
    public Logger log(){
        return Logger.getLogger(this.getClass());
    }
            
    
    public void infoMessage(String msg) {
        infoMessage(null, msg);
    }
 
    public void infoMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }
    
    public void errorMessage(String msg) {
        errorMessage(null, msg);
    }
    
    public void showMessageDeleted(boolean b) {
        showMessageDeleted(b, "");
    }
    
    public void showMessageDeleted(boolean b, String inf) {
        String msg = "";
        inf = inf == null || inf.trim().length() == 0 ? "" : ": " + inf;
        if(b){
            msg = mensajeBundle("acc_borrada_correctamente");
            showMensaje(TipoNotificacion.Aviso, msg + inf);
        }else{
            msg = mensajeBundle("acc_borrada_error");
            showMensaje(TipoNotificacion.Error, msg + inf);
        }                
    }        
    
    public void showMessageSaved(boolean b) {
        String msg = "";
        if(b){
            msg = mensajeBundle("acc_guardada_correctamente");
            showMensaje(TipoNotificacion.Aviso, msg);
        }else{
            msg = mensajeBundle("acc_guardada_error");
            showMensaje(TipoNotificacion.Error, msg);
        }                
    }   
    
    public void errorMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
    }

    /**
     * Crea un MethodExpression para utilizar en un action para fijaro setActionExpresion
     * @param name "#{beanName.actionListenerName}"
     * @return MethodExpression
     */
    public MethodExpression createMethodExpressionForAction(String name)
    {
       Class[] argtypes = new Class[0];
       FacesContext facesCtx = FacesContext.getCurrentInstance();
       ELContext elContext = facesCtx.getELContext();
       return facesCtx.getApplication().getExpressionFactory().createMethodExpression(elContext, name, null, argtypes);
    }
    
    /**
     * Crea un MethodExpression para utilizar en un action listener
     * @param name "#{beanName.actionListenerName}"
     * @return MethodExpression
     */
    public MethodExpression createMethodExpresionForActionListener(String name)
    {
       Class[] argtypes = new Class[1];
       argtypes[0] = ActionEvent.class;
       FacesContext facesCtx = FacesContext.getCurrentInstance();
       ELContext elContext = facesCtx.getELContext();
       return facesCtx.getApplication().getExpressionFactory().createMethodExpression(elContext, name, null, argtypes);
    }
    
    /**
     * Crea un MethodExpressionActionListener para fijarlo con addActionListener
     * @param name "#{beanName.actionListenerName}"
     */
    public MethodExpressionActionListener createMethodExpressionActionListener(String name){
        MethodExpression actionListenerExpression = createMethodExpresionForActionListener(name);
        MethodExpressionActionListener meActionListener = new MethodExpressionActionListener(actionListenerExpression);
        return meActionListener;
    }
    
    public void validationMessage(String message) {
        FacesMessage m = new FacesMessage(message);
        m.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(m);
    }
    
    public void runScript(String script){
        JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), script); 
    }
}
