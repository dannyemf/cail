/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.sira;

import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 * Entidad que representa las imágenes subidas al servidor para usarlas en los artículos y en la galería de imágenes (banner).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class ImagenWeb extends DomainEntity{

    /**Nombre de la imagen*/
    private String nombre;
    
    /**Descripción de la imagen. En caso de la galería se muestra como pié de la imagen.*/
    private String descripcion;
    
    /**Indica es imagen para la galería o para uso en un artículo*/
    private TipoImagenWeb tipo = TipoImagenWeb.ARTICULO;
    
    /**Ruta de la imagen*/
    private String rutaAbsoluta;
    
    /**Tipo de contenido de la imagen. Ejemplo: "image/jpg"*/
    private String mimeType;
    
    /**Contenido binario de la imagen*/
    private byte[] data;
    
    /**Estado o vigencia de la imagen (indica si debe mostrarse o no)*/
    private boolean estado = true;
    
    /**Orden de presentación*/
    private int orden;
    
    /**
     * Constructor por defecto
     */
    public ImagenWeb() {
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
     * @return the rutaAbsoluta
     */
    public String getRutaAbsoluta() {
        return rutaAbsoluta;
    }

    /**
     * @param rutaAbsoluta the rutaAbsoluta to set
     */
    public void setRutaAbsoluta(String rutaAbsoluta) {
        this.rutaAbsoluta = rutaAbsoluta;
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
     * @return the tipo
     */
    public TipoImagenWeb getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoImagenWeb tipo) {
        this.tipo = tipo;
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
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
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
