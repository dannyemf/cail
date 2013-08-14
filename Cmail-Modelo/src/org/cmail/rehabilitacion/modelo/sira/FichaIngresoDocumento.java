/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.sira;

import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 * Entidad que representa los documentos (archivos) que deben subirse con la ficha de ingreso.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class FichaIngresoDocumento extends DomainEntity{

    /**
     * Nombre del documento
     */
    private String nombre;
    
    /**
     * Descripción del documento
     */
    private String descripcion;
    
    /**
     * Tipo de contenido del archivo. Ejemplo: application/pdf
     */
    private String mimeType;
    
    /**
     * Contenido del archivo (datos binarios)
     */
    private byte[] data;
    
    /**
     * Ficha de ingreso a la que pertenece el archvio
     */
    private FichaIngreso ficha;

    /**
     * Constructor por defecto
     */
    public FichaIngresoDocumento() {
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
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType the mimeType to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * @return the data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    /**
     * @return the ficha
     */
    public FichaIngreso getFicha() {
        return ficha;
    }

    /**
     * @param ficha the ficha to set
     */
    public void setFicha(FichaIngreso ficha) {
        this.ficha = ficha;
    }       
    
}
