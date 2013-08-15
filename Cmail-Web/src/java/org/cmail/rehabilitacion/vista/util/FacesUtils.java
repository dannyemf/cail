/*
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is ICEfaces 1.5 open source software code, released
 * November 5, 2006. The Initial Developer of the Original Code is ICEsoft
 * Technologies Canada, Corp. Portions created by ICEsoft are Copyright (C)
 * 2004-2011 ICEsoft Technologies Canada, Corp. All Rights Reserved.
 *
 * Contributor(s): _____________________.
 */

package org.cmail.rehabilitacion.vista.util;

import org.cmail.rehabilitacion.controlador.bean.StyleBean;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.cmail.rehabilitacion.controlador.AgendaController;
import org.cmail.rehabilitacion.controlador.FichaEgresoController;
import org.cmail.rehabilitacion.controlador.FichaIngresoController;
import org.cmail.rehabilitacion.controlador.LoginController;
import org.cmail.rehabilitacion.controlador.MenuController;
import org.cmail.rehabilitacion.controlador.OpcionController;
import org.cmail.rehabilitacion.controlador.PerfilController;
import org.cmail.rehabilitacion.controlador.UsuarioController;
import org.cmail.rehabilitacion.controlador.WucBuscarPersonaController;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.controlador.bean.SessionBean;

/**
 * Clase de utilidades para Java Server Faces de tal manera que se puede usarla de una manera más directa.
 * Permite obtener los contextos Java Server Faces, agregar mensajes de error en las vistas, obtener beans manejados, etc.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class FacesUtils {
    /**
     * Get servlet context.
     *
     * @return the servlet context
     */
    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    public static ExternalContext getExternalContext() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext();
    }

    public static HttpSession getHttpSession(boolean create) {
        return (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(create);
    }

    /**
     * Get managed bean based on the bean name.
     *
     * @param beanName the bean name
     * @return the managed bean associated with the bean name
     */
    public static Object getManagedBean(String beanName) {

        return getValueBinding(getJsfEl(beanName)).getValue(FacesContext.getCurrentInstance());
    }

    /**
     * Remove the managed bean based on the bean name.
     *
     * @param beanName the bean name of the managed bean to be removed
     */
    public static void resetManagedBean(String beanName) {
        try{
            getValueBinding(getJsfEl(beanName)).setValue(FacesContext.getCurrentInstance(), getManagedBean(beanName).getClass().newInstance());
        }catch(Exception e){
            getValueBinding(getJsfEl(beanName)).setValue(FacesContext.getCurrentInstance(), null);
        }        
    }

    /**
     * Store the managed bean inside the session scope.
     *
     * @param beanName    the name of the managed bean to be stored
     * @param managedBean the managed bean to be stored
     */
    public static void setManagedBeanInSession(String beanName, Object managedBean) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(beanName, managedBean);
    }

    /**
     * Get parameter value from request scope.
     *
     * @param name the name of the parameter
     * @return the parameter value
     */
    public static String getRequestParameter(String name) {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    /**
     * Add information message.
     *
     * @param msg the information message
     */
    public static void addInfoMessage(String msg) {
        addInfoMessage(null, msg);
    }

    /**
     * Add information message to a specific client.
     *
     * @param clientId the client id
     * @param msg      the information message
     */
    public static void addInfoMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }

    /**
     * Add error message.
     *
     * @param msg the error message
     */
    public static void addErrorMessage(String msg) {
        addErrorMessage(null, msg);
    }

    /**
     * Add error message to a specific client.
     *
     * @param clientId the client id
     * @param msg      the error message
     */
    public static void addErrorMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
    }

    private static Application getApplication() {
        ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        return appFactory.getApplication();
    }

    private static ValueBinding getValueBinding(String el) {
        return getApplication().createValueBinding(el);
    }

    private static HttpServletRequest getServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    private static Object getElValue(String el) {
        return getValueBinding(el).getValue(FacesContext.getCurrentInstance());
    }

    private static String getJsfEl(String value) {
        return "#{" + value + "}";
    }
    
    public static LoginController getLoginController(){
        return (LoginController)getManagedBean(Constantes.MB_LOGIN);
    }
    
    public static SessionBean getSessionBean(){
        return (SessionBean)getManagedBean(Constantes.MB_SESSION);
    }
    
    public static MenuController getMenuController(){
        return (MenuController)getManagedBean(Constantes.MB_MENU);
    }
        
    
    public static OpcionController getOpcionController(){
        return (OpcionController)getManagedBean(Constantes.MB_OPCION);
    }
    
    public static StyleBean getStyleBean(){
        return (StyleBean)getManagedBean("styleBean");
    }
    
    public static UsuarioController getUsuarioController(){
        return (UsuarioController)getManagedBean(Constantes.MB_USUARIO);
    }   
    
    public static WucBuscarPersonaController getPersonaController(){
        return (WucBuscarPersonaController)getManagedBean(Constantes.MB_WUC_BUSCAR_PERSONA);
    }
    
    public static PerfilController getPerfilController(){
        return (PerfilController)getManagedBean(Constantes.MB_PERFIL);
    }
    
    public static FichaIngresoController getFichaIngresoController(){
        return (FichaIngresoController)getManagedBean(Constantes.MB_FICHAINGRESO);
    }    
    public static FichaEgresoController getFichaEgresoController(){
        return (FichaEgresoController)getManagedBean(Constantes.MB_FICHAEGRESO);
    }    
    public static AgendaController getPlanificacionController(){
        return (AgendaController)getManagedBean(Constantes.MB_AGENDA);
    }    
    
    public static <T extends Object> T getBean(String string, Class<? extends T> type) {
        return (T)getManagedBean(string);
    }
            
    
}
