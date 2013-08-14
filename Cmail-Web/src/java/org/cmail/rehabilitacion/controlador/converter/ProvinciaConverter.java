/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;
import org.cmail.rehabilitacion.modelo.localizacion.Provincia;
import org.cmail.rehabilitacion.servicio.DivisionPoliticaServicio;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */

@FacesConverter(value="provinciaConverter")
public class ProvinciaConverter implements Converter {

    public Logger log(){
        return Logger.getLogger(this.getClass());
    }
    
    public ProvinciaConverter() {
    }
    
    

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || "".equals(value.trim())) {
            return null;
        }
        //CON BUSQUEDA DESDE LA BASE DE DATOS
        try {
            //log().info("ProvinciaConverter.getAsObject(...): " + value);
            Long id = Long.parseLong(value);
            Provincia provincia = new DivisionPoliticaServicio().obtenerPorId(Provincia.class, id);            
            return provincia;                       
        } catch (Exception e) {
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try {
            //log().info("ProvinciaConverter.getAsString(...): " + o);
            return ((Provincia)o).getId().toString();
        } catch (Exception e) {
            return o != null ? o.toString() : "";
        }
    }
}
