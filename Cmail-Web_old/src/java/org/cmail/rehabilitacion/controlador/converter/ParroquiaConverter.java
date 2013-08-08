/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.cmail.rehabilitacion.modelo.localizacion.Parroquia;
import org.cmail.rehabilitacion.servicio.DivisionPoliticaServicio;


/**
 *
 * @author Usuario
 */

@FacesConverter(value="parroquiaConverter")
public class ParroquiaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        try {
            Long id = Long.parseLong(value);
            Parroquia parroquia = new DivisionPoliticaServicio().obtenerPorId(Parroquia.class, id);       
            return parroquia;           
        } catch (Exception e) {
            return null;
        }                
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        try {
            return ((Parroquia)o).getId().toString();
        } catch (Exception e) {
            return "";
        }
    }
}