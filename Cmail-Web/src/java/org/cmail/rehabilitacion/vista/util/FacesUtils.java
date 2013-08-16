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
     * El servlet context.
     *
     * @return el contexto
     */
    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    /**
     * El contexto externo
     * @return 
     */
    public static ExternalContext getExternalContext() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext();
    }

    /**
     * La sesión http
     * @param create si se debe crear cuando no existe
     * @return la sesión
     */
    public static HttpSession getHttpSession(boolean create) {
        return (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(create);
    }

    /**
     * Obtiene un managed bean por su nombre
     *
     * @param beanName el nombre
     * @return el bean
     */
    public static Object getManagedBean(String beanName) {

        return getValueBinding(getJsfEl(beanName)).getValue(FacesContext.getCurrentInstance());
    }

    /**
     * Remueve un managed bean por su nombre
     *
     * @param beanName el nombre
     * @return el bean
     */
    public static void resetManagedBean(String beanName) {
        try{
            getValueBinding(getJsfEl(beanName)).setValue(FacesContext.getCurrentInstance(), getManagedBean(beanName).getClass().newInstance());
        }catch(Exception e){
            getValueBinding(getJsfEl(beanName)).setValue(FacesContext.getCurrentInstance(), null);
        }        
    }

    /**
     * Agrega un managed bean a la sesión
     *
     * @param beanName el nombre del managed bean
     * @param managedBean el bean
     */
    public static void setManagedBeanInSession(String beanName, Object managedBean) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(beanName, managedBean);
    }

    /**
     * Obtiene el valor de parámetro del ámbito de petición.
     *
     * @param name el nombre
     * @return el valor del parámetro
     */
    public static String getRequestParameter(String name) {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    /**
     * Agrega un mensaje de información
     *
     * @param msg el mensaje
     */
    public static void addInfoMessage(String msg) {
        addInfoMessage(null, msg);
    }

    /**
     * Agerag un mensaje de información a un componente en específico.
     *
     * @param clientId el id del componente
     * @param msg el mensaje
     */
    public static void addInfoMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }

    /**
     * Agrega un mensaje de error
     *
     * @param msg el mesanje
     */
    public static void addErrorMessage(String msg) {
        addErrorMessage(null, msg);
    }

    /**
     * Agrega un mensaje de error a un cliente en específico.
     *
     * @param clientId el id del cliente
     * @param msg el mensaje
     */
    public static void addErrorMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
    }

    /**
     * Obtiene el contexto de la aplicación
     * @return 
     */
    private static Application getApplication() {
        ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
        return appFactory.getApplication();
    }

    /**
     * Obtiene un binding para valores
     * @param el la expresión
     * @return el objeto
     */
    private static ValueBinding getValueBinding(String el) {
        return getApplication().createValueBinding(el);
    }

    /**
     * Obtiene el servlet de petición
     * @return el request
     */
    private static HttpServletRequest getServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    /**
     * Obtiene un value binding para la expresión
     * @param el la expresión
     * @return el objeto
     */
    private static Object getElValue(String el) {
        return getValueBinding(el).getValue(FacesContext.getCurrentInstance());
    }

    /**
     * Obtiene una expresión
     * @param value el valor
     * @return la expresion
     */
    private static String getJsfEl(String value) {
        return "#{" + value + "}";
    }
    
    /**
     * Obtiene el controlador de login
     * @return el objeto
     */ 
    public static LoginController getLoginController(){
        return (LoginController)getManagedBean(Constantes.MB_LOGIN);
    }
    
    /**
     * Obtiene el bean de sesión
     * @return el bean
     */ 
    public static SessionBean getSessionBean(){
        return (SessionBean)getManagedBean(Constantes.MB_SESSION);
    }
    
    /**
     * Obtiene el controlador de menú
     * @return el bean
     */ 
    public static MenuController getMenuController(){
        return (MenuController)getManagedBean(Constantes.MB_MENU);
    }
        
    /**
     * Obtiene el controlador de opciones
     * @return el bean
     */
    public static OpcionController getOpcionController(){
        return (OpcionController)getManagedBean(Constantes.MB_OPCION);
    }
    
    /**
     * Obtiene el bean de estilos
     * @return el bean
     */
    public static StyleBean getStyleBean(){
        return (StyleBean)getManagedBean("styleBean");
    }
    
    /**
     * Obtiene el controlador de usuarios
     * @return el controlador
     */
    public static UsuarioController getUsuarioController(){
        return (UsuarioController)getManagedBean(Constantes.MB_USUARIO);
    }   
    
    /**
     * Obtiene el controlador de personas
     * @return el controlador
     */
    public static WucBuscarPersonaController getPersonaController(){
        return (WucBuscarPersonaController)getManagedBean(Constantes.MB_WUC_BUSCAR_PERSONA);
    }
    
    /**
     * Obtiene el controlador de perfiles
     * @return el controlador
     */
    public static PerfilController getPerfilController(){
        return (PerfilController)getManagedBean(Constantes.MB_PERFIL);
    }
    
    /**
     * Obtiene el controlador de fichas de ingreso
     * @return el controlador
     */
    public static FichaIngresoController getFichaIngresoController(){
        return (FichaIngresoController)getManagedBean(Constantes.MB_FICHAINGRESO);
    }    
    
    /**
     * Obtiene el controlador de fichas de egreso
     * @return el controlador
     */
    public static FichaEgresoController getFichaEgresoController(){
        return (FichaEgresoController)getManagedBean(Constantes.MB_FICHAEGRESO);
    }    
    
    /**
     * Obtiene el controlador de agendas
     * @return el controlador
     */
    public static AgendaController getPlanificacionController(){
        return (AgendaController)getManagedBean(Constantes.MB_AGENDA);
    }    
    
    /**
     * Obtiene el managed bean por su nombre y clase
     * @param <T>
     * @param string el nombre
     * @param type la clase
     * @return el bean
     */
    public static <T extends Object> T getBean(String string, Class<? extends T> type) {
        return (T)getManagedBean(string);
    }
            
    
}
