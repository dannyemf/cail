/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.cmail.rehabilitacion.modelo.localizacion.Canton;

import org.cmail.rehabilitacion.servicio.DivisionPoliticaServicio;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */

@FacesConverter(value="cantonConverter")
public class CantonConverter implements Converter {

   @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null) {
            return null;
        }
        //CON BUSQUEDA DESDE LA BASE DE DATOS
        try {
           Long id = Long.parseLong(value);
            Canton canton = new DivisionPoliticaServicio().obtenerPorId(Canton.class, id);
            if(canton!=null){
                return canton;           
            }
       } catch (Exception e) {
       }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try {
            return ((Canton)o).getId().toString();
        } catch (Exception e) {
            return "";
        }
    }
}
