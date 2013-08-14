/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.sira;

import org.cmail.rehabilitacion.modelo.AuditEntity;

/**
 * Entidad que representa un artículo o noticia que debe presentarse en la pagina inicial.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class ArticuloWeb extends AuditEntity{
    
    /**
     * Título del artículo
     */
    private String titulo;
    
    /**
     * Indica si el artículo debe mostrarse o no (vigencia)
     */
    private boolean activo;
    
    /**
     * Indica si el artículo debe mostrarse en la página principal (true) mediante menús o en la sección de noticias (false)
     */
    private boolean paginaPrincipal;
    
    /**
     * Breve resumen que es mostrado en la sección de noticias y el artículo completo se muestra con el botón leer más.
     */
    private String resumen = "";
    
    /**
     * Contenido del artículo completo. Es mostrado con el botón leer más.
     */
    private String descripcion = "";

    /**
     * Constructor por defecto
     */
    public ArticuloWeb() {
    }
    
    /**
     * Verifica si debe mostrarse un botón leer más (cuando hay una descripción)
     * @return true si se debe mostrar
     */
    public boolean isLeerMas(){
        if(this.descripcion.length() > 0){
            return true;
        }
        return false;
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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the resumen
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * @param resumen the resumen to set
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**
     * @return the paginaPrincipal
     */
    public boolean isPaginaPrincipal() {
        return paginaPrincipal;
    }

    /**
     * @param paginaPrincipal the paginaPrincipal to set
     */
    public void setPaginaPrincipal(boolean paginaPrincipal) {
        this.paginaPrincipal = paginaPrincipal;
    }
    
    
    
    
}
