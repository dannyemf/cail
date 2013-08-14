/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.sira;

import java.util.HashSet;
import java.util.Set;
import org.cmail.rehabilitacion.modelo.AuditEntity;

/**
 * Entidad para representar un esquema de preguntas para el formulario HTP o posibles evaluaciones.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Esquema extends AuditEntity {
    
    public static final String TIPO_HTP = "H-T-P";
    public static final String TIPO_ANA = "ANAM";
    
    /**
     * Nombre del test
     */
    private String nombre = "";
    
    /**
     * Descripción del test
     */
    private String descripcion = "";
        
    /**
     * Indica si el test es vigente o no. Ya que se pueden crear múltiples versiones de un mismo test.
     */
    private boolean activo = false;
    
    /**
     * Tipo de test
     */
    private String tipo = TIPO_HTP;
    
    /**
     * Colección de preguntas del test
     */
    private Set<EsquemaPregunta> preguntas = new HashSet<EsquemaPregunta>();

    public Esquema() {
    }
    
    public void addPregunta(EsquemaPregunta pregunta){
        this.getPreguntas().add(pregunta);
        pregunta.setNumero(this.getPreguntas().size());
        pregunta.setEsquema(this);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the preguntas
     */
    public Set<EsquemaPregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * @param preguntas the preguntas to set
     */
    public void setPreguntas(Set<EsquemaPregunta> preguntas) {
        this.preguntas = preguntas;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
