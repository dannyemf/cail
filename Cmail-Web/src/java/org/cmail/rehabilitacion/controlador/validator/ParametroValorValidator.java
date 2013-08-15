/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.cmail.rehabilitacion.modelo.seguridad.TipoParametro;
import org.cmail.rehabilitacion.vista.util.MensajeBundleUtil;

/**
 * Validador Java Server Faces para el valor ingresado de un parámtro según sea el tipo de dato del mismo.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */

@FacesValidator(value="parametroValorValidator")
public class ParametroValorValidator implements Validator{
        
    
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{        
                
        HtmlSelectOneMenu uiTipo = (HtmlSelectOneMenu ) component.getAttributes().get("tipo");        
        
        String valor = (String)value;
        TipoParametro tipo = (TipoParametro)uiTipo.getValue();
        
        String mensaje = null;
        
        switch(tipo){
            case Cadena: break;
            case Entero:
                try {
                    Integer.parseInt(valor);
                } catch (Exception e) {
                    mensaje = MensajeBundleUtil.getMensaje("val_parametro_entero");
                }
                break;
            case Decimal:
                try {
                    Double.parseDouble(valor);
                } catch (Exception e) {
                    mensaje = MensajeBundleUtil.getMensaje("val_parametro_decimal");
                }
                break;
            case Porcentaje:
                try {
                    double p = Double.parseDouble(valor);
                    if(p < 0 || p > 100){
                        mensaje = MensajeBundleUtil.getMensaje("val_parametro_porcentaje");
                    }
                } catch (Exception e) {
                    mensaje = MensajeBundleUtil.getMensaje("val_parametro_decimal");
                }
                break;
            case Dimension:
                try {
                    String sps[] = valor.split("x");
                    Integer.parseInt(sps[0]);
                    Integer.parseInt(sps[1]);                    
                } catch (Exception e) {
                    mensaje = MensajeBundleUtil.getMensaje("val_parametro_dimension");
                }
        }
        
        if(mensaje != null){
            String label = (String)component.getAttributes().get("label");
            if(label == null){
                label = component.getClientId();
            }
            
            FacesMessage fm = new FacesMessage(label + ": " + mensaje);
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);            
            throw  new ValidatorException(fm);
        }
    }
}
