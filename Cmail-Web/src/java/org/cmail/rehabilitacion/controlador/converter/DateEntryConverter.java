/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Convertidor de fecha usado para el control DateTimeEntry.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@FacesConverter(value="dateEntryConverter")
public class DateEntryConverter implements Converter{

    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {        
        try {
            return format.parse(string);
        } catch (Exception e) {
            return null;
        }        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Date d = (Date)o;
        if(d != null){
            String f = format.format(d);        
            return f;
        }
        return "";
    }
    
}
