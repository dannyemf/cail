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
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */

@FacesValidator(value="emailValidator")
public class EmailValidator implements Validator {

     private static final String EMAIL = "^[a-zA-Z0-9_-]{2,15}@[a-zA-Z0-9_-]{2,15}.[a-zA-Z]{2,4}(.[a-zA-Z]{2,4})?$";
     
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value != null){            
            String v = value.toString();
            
            if(v.length() > 0){            
                //asignamos la expresion
                Pattern p = Pattern.compile(EMAIL);   
                //comparamos con nuestro valor
                Matcher m = p.matcher(v);   
                //si el correo es correcto devuelve TRUE o de lo contrario FALSE  

                if(!m.matches()){
                    FacesMessage fm = new FacesMessage("Email no válido");
                    fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(fm);
                }
            }
        }
    }
    
}
