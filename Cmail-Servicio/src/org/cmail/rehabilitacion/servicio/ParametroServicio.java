/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import org.cmail.rehabilitacion.modelo.seguridad.Parametro;
import org.cmail.rehabilitacion.modelo.seguridad.TipoParametro;
import org.cmail.rehabilitacion.vista.util.MensajeBundleUtil;

/**
 *
 * @author Usuario
 */
public class ParametroServicio extends GenericServicio<Parametro> {

    public ParametroServicio() {
        super(Parametro.class);
    }
    
//    public Parametro obtenerParametro(String nombre){
//        return obtenerParametro(nombre,"DefaultValue");
//    }
    
//    public Parametro obtenerParametro(String nombre, String defaultValues){
//        return obtenerParametro(nombre, defaultValues, "Parámetro no inicializado");
//    }
    
    public Parametro obtenerParametro(String nombre, TipoParametro defaultTipo){
        Parametro p = super.obtenerUnicoPor("nombre", nombre);
        
        if(p == null){
            try {
                p = new Parametro();
                p.setNombre(nombre);
                p.setTipo(defaultTipo);
                p.setValor(MensajeBundleUtil.getMensaje(nombre+"_VALOR"));
                p.setDescripcion(MensajeBundleUtil.getMensaje(nombre+"_DESCR"));
                p.setAuditUpdateUser("admin");
                
                if(!super.guardar(p)){
                    p = null;
                }
            } catch (Exception e) {
                p = null;
            }
        }
        
        return p;
    }
    
    /*public String obtenerValor(String nombre){
        Parametro p = obtenerParametro(nombre);        
        return p != null ? p.getValor() : "DefaultValue";
    }*/
    
    public String obtenerValor(String nombre, TipoParametro defaultTipo){
        Parametro p = obtenerParametro(nombre, defaultTipo);
        return p != null ? p.getValor() : "¿¿¿"+nombre+"???";
    }
}
