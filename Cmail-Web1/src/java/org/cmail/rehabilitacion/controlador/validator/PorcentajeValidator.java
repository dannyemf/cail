/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.cmail.rehabilitacion.vista.util.MensajeBundleUtil;

/**
 *
 * @author Usuario
 */

@FacesValidator(value="porcentajeValidator")
public class PorcentajeValidator implements Validator{
        
    
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        
        double v = (Double)value;

        if(v < 0 || v > 100){
            String m = MensajeBundleUtil.getMensaje("val_porcentaje");
            
            String label = (String)component.getAttributes().get("label");
            if(label != null && label.length() > 0){
                m = label + ": " + m;
            }else{
                m = component.getClientId() + ": " + m;
            }
            
            FacesMessage msg = new FacesMessage();
            msg.setDetail(m);
            msg.setSummary(m);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            
            throw new ValidatorException(msg);
        }
    }
}
