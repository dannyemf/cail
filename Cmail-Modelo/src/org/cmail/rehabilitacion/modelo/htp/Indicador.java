/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.htp;

import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 * Entidad que representa un indicador.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Indicador extends DomainEntity implements Cloneable{
    
    /**
     * Nomre del indicador
     */
    private String nombre;
    
    /**
     * Descripción del indicador
     */
    private String definicion;
    
    /**
     * Las posibles interpretaciones separadas por punto y coma (;)
     */
    private String significaciones;
    
    /**
     * Tipo de indicador
     */
    private TipoIndicador tipo = TipoIndicador.Arbol;
    
    /**
     * Valor o peso del indicador
     */
    private int valor;
    
    /**
     * Orden de presentación u ordenación
     */
    private int orden;
    
    /**
     * Imagen que representa al indicador
     */
    private byte[] imagen;
    
    private Categoria categoria;

    /**
     * Constructor por defecto
     */
    public Indicador() {
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
     * @return the definicion
     */
    public String getDefinicion() {
        return definicion;
    }

    /**
     * @param definicion the definicion to set
     */
    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    /**
     * @return the significaciones
     */
    public String getSignificaciones() {
        return significaciones;
    }

    /**
     * @param significaciones the significaciones to set
     */
    public void setSignificaciones(String significaciones) {
        this.significaciones = significaciones;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the imagen
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the tipo
     */
    public TipoIndicador getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoIndicador tipo) {
        this.tipo = tipo;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }
    
    
    
}
