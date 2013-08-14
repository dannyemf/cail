/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.htp;

import org.cmail.rehabilitacion.modelo.DomainEntity;
import org.cmail.rehabilitacion.modelo.sira.EsquemaPregunta;

/**
 * Entidad que representa a una respuesta del adolescente del test htp aplicado.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class TestHtpRespuesta extends DomainEntity{
    
    /**
     * El test htp al que pertenece la respuesta
     */
    private TestHtp formulario;
    
    /**
     * La pregunta que se responde
     */
    private EsquemaPregunta esquemaPregunta;
    
    /**
     * La respuesta contestada
     */
    private String respuesta;

    /**
     * Constructor por defecto
     */
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
