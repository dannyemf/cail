/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.sira;

import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 *
 * @author Usuario
 */
public class EsquemaPregunta extends DomainEntity{
    
    public static final String TIPO_ABIERTA = "LIBRE"; // RESPONDE TEXTO
    public static final String TIPO_SI_NO = "CERRADA"; // CASILLA SI NO
    public static final String TIPO_MULTIPLE = "MULTIPLE"; // VARIAS CASILLAS
    
    private static final String GRUPO_NINGUNO = "NINGUNO";
    public static final String GRUPO_CASA = "CASA";
    public static final String GRUPO_ARBOL = "ARBOL";
    public static final String GRUPO_PERSONA = "PERSONA";

    /**
     * @return the GRUPO_NINGUNO
     */
    public static String getGRUPO_NINGUNO() {
        return GRUPO_NINGUNO;
    }
    
    private int numero = 0;
    private String enunciado = "";
    private String tipo = TIPO_ABIERTA;
    private String grupo = getGRUPO_NINGUNO();
    private boolean requerida;
    
    private Esquema esquema;

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the enunciado
     */
    public String getEnunciado() {
        return enunciado;
    }

    /**
     * @param enunciado the enunciado to set
     */
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
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
     * @return the esquema
     */
    public Esquema getEsquema() {
        return esquema;
    }

    /**
     * @param esquema the esquema to set
     */
    public void setEsquema(Esquema esquema) {
        this.esquema = esquema;
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the requerida
     */
    public boolean isRequerida() {
        return requerida;
    }

    /**
     * @param requerida the requerida to set
     */
    public void setRequerida(boolean requerida) {
        this.requerida = requerida;
    }
    
    
}
