/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import org.cmail.rehabilitacion.controlador.Controller;
import org.cmail.rehabilitacion.modelo.localizacion.Canton;
import org.cmail.rehabilitacion.modelo.localizacion.Parroquia;
import org.cmail.rehabilitacion.modelo.localizacion.Provincia;
import org.cmail.rehabilitacion.servicio.DivisionPoliticaServicio;

/**
 * Bean manejado usado para el control de selección de pronvincias, cantón y parroquia.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */

@ManagedBean
@SessionScoped
public class ProvinciaBean extends Controller implements Serializable{

    /**
     * Lista de provincias
     */
    private List<Provincia> lista = new ArrayList<Provincia>();
    
    /**Constructor por defecto*/
    public ProvinciaBean() {
        lista = new DivisionPoliticaServicio().listarProvincias();        
    }                
    
    /**
     * Obtiene el nombre del método al que debe invocar sobre el objeto para fijar la provincia el cantón o la parroquia.
     * @param base el prefijo del método (nacimiento, detencion, etc.)
     * @param tipo la clase sobre la que debe invocar
     * @return el nombre
     */
    public String getMethodName(String base, Class tipo){
        
        String metodo = null;
        
        if(base != null){
            metodo = "set"+tipo.getSimpleName()+base;
        }
        
        return metodo;
    }
    
    /**
     * Obtiene el método al que debe invocar para fijar los valores.
     * 
     * @param bean el bean sobre el que se debe fijar
     * @param base el prefijo del método (nacimiento, detención, etc.)
     * @param tipo la clase
     * @return
     * @throws Exception 
     */
    public Method getMethod(Object bean,String base, Class tipo)  throws Exception{                
        try {
            String methodName = getMethodName(base, tipo);
            Method method = bean.getClass().getDeclaredMethod(methodName, tipo);
            return method;
        } catch (Exception e) {
            throw e;
        }                
    }
    
    /**
     * Invoca al método para fijar la provincia, cantón o parroquia.
     * @param bean el bean donde se va a fijar
     * @param valor el objecto provincia, cantón o parroquia
     * @param base el prefijo del método
     * @param tipo la clase
     * @throws Exception 
     */
    public void setValue(Object bean, Object valor, String base, Class tipo) throws Exception{
        try {
            Method m = getMethod(bean, base, tipo);
            m.invoke(bean, valor);            
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Evento invocado al seleccionar una provincia
     * @param event el evento
     */
    public void changeProvincia(ValueChangeEvent event){    
        Object bean = event.getComponent().getAttributes().get("bean");
        String base = (String)event.getComponent().getAttributes().get("tipo");
        
        try {
            setValue(bean, null, base, Canton.class);
            setValue(bean, null, base, Parroquia.class);
            setValue(bean, event.getNewValue(), base, Provincia.class);            
        } catch (Exception e) {
            try {
                setValue(bean, null, base, Provincia.class);
            } catch (Exception e1) {}
            log().error("ProvinciaBean.changeProvincia(...)",e);
        }                
    }
    
    /**
     * Evento invocado al seleccionar un cantón.
     * @param event el evento
     */
    public void changeCanton(ValueChangeEvent event){    
        Object bean = event.getComponent().getAttributes().get("bean");
        String base = (String)event.getComponent().getAttributes().get("tipo");
        
        try {            
            setValue(bean, null, base, Parroquia.class);
            setValue(bean, event.getNewValue(), base, Canton.class);
        } catch (Exception e) {            
            try {
                setValue(bean, null, base, Canton.class);
            } catch (Exception e1) {}
            log().error(e.getMessage());
        }                
    }
    
    /**
     * Evento invocado al seleccionar una parroquia.
     * @param event el evento
     */
    public void changeParroquia(ValueChangeEvent event){    
        Object bean = event.getComponent().getAttributes().get("bean");
        String base = (String)event.getComponent().getAttributes().get("tipo");
        
        try {                        
            setValue(bean, event.getNewValue(), base, Parroquia.class);
        } catch (Exception e) {            
            try {
                setValue(bean, null, base, Parroquia.class);
            } catch (Exception e1) {}
            log().error(e.getMessage());
        }                
    }

    /**
     * @return the lista
     */
    public List<Provincia> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<Provincia> lista) {
        this.lista = lista;
    } 
    
}