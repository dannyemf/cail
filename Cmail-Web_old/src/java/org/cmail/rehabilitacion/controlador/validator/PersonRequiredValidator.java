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

/**
 *
 * @author Usuario
 */

@FacesValidator(value="personRequiredValidator")
public class PersonRequiredValidator implements Validator{
        
    
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{

        /* retrieve the string value of the field*/
        String valor = (String)value;               

        if(valor == null || valor.equals("") || valor.equals("null")){
            FacesMessage msg = new FacesMessage();
            msg.setDetail("Seleccione");
            msg.setSummary("Seleccione");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
