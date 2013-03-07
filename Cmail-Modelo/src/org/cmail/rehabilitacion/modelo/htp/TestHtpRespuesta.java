/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.htp;

import org.cmail.rehabilitacion.modelo.DomainEntity;
import org.cmail.rehabilitacion.modelo.sira.EsquemaPregunta;

/**
 *
 * @author Usuario
 */
public class TestHtpRespuesta extends DomainEntity{
    
    private TestHtp formulario;
    private EsquemaPregunta esquemaPregunta;
    private String respuesta;

    public TestHtpRespuesta() {
    }

    /**
     * @return the esquemaPregunta
     */
    public EsquemaPregunta getEsquemaPregunta() {
        return esquemaPregunta;
    }

    /**
     * @param esquemaPregunta the esquemaPregunta to set
     */
    public void setEsquemaPregunta(EsquemaPregunta esquemaPregunta) {
        this.esquemaPregunta = esquemaPregunta;
    }

    /**
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * @return the formulario
     */
    public TestHtp getFormulario() {
        return formulario;
    }

    /**
     * @param formulario the formulario to set
     */
    public void setFormulario(TestHtp formulario) {
        this.formulario = formulario;
    }
    
    
}
