/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

@FacesValidator(value="cellPhoneValidator")
public class CellPhoneValidator implements Validator{
    
    /** phone number in form of xxx-xxxx*/
    private static final String PHONE_NUM = "[0-9]{10}";
    
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{

        /* create a mask*/
        Pattern mask = Pattern.compile(PHONE_NUM);

        /* retrieve the string value of the field*/
        String phoneNumber = (String)value;

        /*check to ensure that the value is a phone number*/
        Matcher matcher = mask.matcher(phoneNumber);

        if(!matcher.matches()){
            FacesMessage msg = new FacesMessage();
            msg.setDetail("Número de celular no válido");
            msg.setSummary("Número de celular no válido");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
