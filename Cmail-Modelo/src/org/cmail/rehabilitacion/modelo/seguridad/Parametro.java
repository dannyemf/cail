/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.seguridad;

import java.awt.Dimension;
import org.cmail.rehabilitacion.modelo.AuditEntity;

/**
 *
 * @author Usuario
 */
public class Parametro extends AuditEntity{
    
    private String nombre;
    private String descripcion;
    private String valor;
    private TipoParametro tipo = TipoParametro.Cadena;

    public Parametro() {
    }
    
    public int toInt(){
        try {
            return Integer.parseInt(valor);
        } catch (Exception e) {
            return -1;
        }
    }
    
    public double toDouble(){
        try {
            return Double.parseDouble(valor);
        } catch (Exception e) {
            return -1;
        }
    }
    
    public Dimension toDimension(){
        try {
            String[] sps = valor.split("x");
            return new Dimension(Integer.parseInt(sps[0]), Integer.parseInt(sps[1]));
        } catch (Exception e) {
            return new Dimension(0,0);
        }
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

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the tipo
     */
    public TipoParametro getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoParametro tipo) {
        this.tipo = tipo;
    }

    
}
