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

    private List<Provincia> lista = new ArrayList<Provincia>();
    
    /**Constructor por defecto*/
    public ProvinciaBean() {
        lista = new DivisionPoliticaServicio().listarProvincias();        
    }                
    
    public String getMethodName(String base, Class tipo){
        
        String metodo = null;
        
        if(base != null){
            metodo = "set"+tipo.getSimpleName()+base;
        }
        
        return metodo;
    }
    
    public Method getMethod(Object bean,String base, Class tipo)  throws Exception{                
        try {
            String methodName = getMethodName(base, tipo);
            Method method = bean.getClass().getDeclaredMethod(methodName, tipo);
            return method;
        } catch (Exception e) {
            throw e;
        }                
    }
    
    public void setValue(Object bean, Object valor, String base, Class tipo) throws Exception{
        try {
            Method m = getMethod(bean, base, tipo);
            m.invoke(bean, valor);            
        } catch (Exception e) {
            throw e;
        }
    }
    
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