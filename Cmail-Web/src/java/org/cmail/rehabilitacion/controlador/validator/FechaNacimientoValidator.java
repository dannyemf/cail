/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.validator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.seguridad.TipoParametro;
import org.cmail.rehabilitacion.servicio.ParametroServicio;
import org.cmail.rehabilitacion.vista.util.MensajeBundleUtil;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */

@FacesValidator(value="fechaNacimientoValidator")
public class FechaNacimientoValidator implements Validator, Serializable{

    private int añosAtras = 0;
    
    
    public FechaNacimientoValidator() {
        añosAtras = new ParametroServicio().obtenerParametro(Constantes.PRM_MIN_ANIOS_FECHA_NAC, TipoParametro.Entero).toInt();
    }
    
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.YEAR, añosAtras * -1);
        
        Date v = (Date)value;
        Date max = c.getTime();
        
        if(v.after(max)){
            
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            String m = MensajeBundleUtil.getMensaje("val_fecha_nacimiento") + ": " + sd.format(max);            
            
            FacesMessage msg = new FacesMessage();
            msg.setDetail(m);
            msg.setSummary(m);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            
            throw new ValidatorException(msg);
        }
    }

    public int getAñosAtras() {
        return añosAtras;
    }

    public void setAñosAtras(int añosAtras) {
        this.añosAtras = añosAtras;
    }
    
    
}
