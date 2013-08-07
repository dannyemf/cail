/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.event;

import java.io.Serializable;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import org.cmail.rehabilitacion.controlador.WucBuscarPersonaController;
import org.cmail.rehabilitacion.modelo.Persona;


public class ActionListenerWucBuscarPersona implements javax.faces.event.ActionListener, Serializable{   

    private Persona persona;
    private WucBuscarPersonaController wuc;
    private Tipo tipo;

    public ActionListenerWucBuscarPersona() {
    }
    
    public void processAction(ActionEvent ae, Persona p, Tipo tipo, WucBuscarPersonaController wuc){
        this.persona = p;
        this.wuc= wuc;
        this.tipo = tipo;
        this.processAction(ae);
    }
    
    public void processAction(ActionEvent ae) throws AbortProcessingException {                        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    

    public Persona getPersona() {
        return persona;
    }

    public WucBuscarPersonaController getWuc() {
        return wuc;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }    
    
    public boolean isActionSaved(){
        if(this.tipo.equals(Tipo.EdicionGuardado)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isActionCancel(){
        if(this.tipo.equals(Tipo.EdicionCancelado)){
            return true;
        }else{
            return false;
        }
    }

    public enum Tipo{
        EdicionGuardado,
        EdicionCancelado,
        BuscarSeleccionado
    }
    
    
}
