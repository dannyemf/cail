/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import org.cmail.rehabilitacion.modelo.seguridad.Parametro;
import org.cmail.rehabilitacion.modelo.seguridad.TipoParametro;
import org.cmail.rehabilitacion.vista.util.MensajeBundleUtil;

/**
 * Clase de lógica de negocio para manejar los parámetros del sistema.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class ParametroServicio extends GenericServicio<Parametro> {

    /**
     * Constructor por defecto
     */
    public ParametroServicio() {
        super(Parametro.class);
    }    
    
    /**
     * Obtiene el parámetro por nombre, si no existe lo crea.
     * 
     * @param nombre nombre único del parámetro
     * @param defaultTipo tipo de parámetro
     * @return el parámetro
     */
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
    
    /**
     * Obtiene el valor de un parámetro, si no existe lo crea.
     * @param nombre nombre único del parámetro
     * @param defaultTipo tipo por defecto
     * @return el valor del parámetro
     */
    public String obtenerValor(String nombre, TipoParametro defaultTipo){
        Parametro p = obtenerParametro(nombre, defaultTipo);
        return p != null ? p.getValor() : "¿¿¿"+nombre+"???";
    }
}
