/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.seguridad;

import java.util.HashSet;
import java.util.Set;
import org.cmail.rehabilitacion.modelo.AuditEntity;

/**
 * Entidad que representa un menú de usuario.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Opcion extends AuditEntity {

    /**
     * Texto que se muestra en el menú
     */
    private String etiqueta;
    
    /**
     * Página web o control que se debe mostrar al hacer click.
     */
    private String url;
    
    /**
     * Título que se debe mostrar
     */
    private String titulo;
    
    /**
     * Icono jquery que debe tener el menú
     */
    private String icono;
    
    /**
     * Opción padre para mostrar el menú con jerarquías
     */
    private Opcion padre;
    
    /**
     * Colección de perfiles a los que les debe aparecer el menú.
     */
    private Set<Perfil> perfiles = new HashSet<Perfil>();

    /**
     * Constructor por defecto
     */
    public Opcion() {
    }

    /**
     *
     * Constructor por completo.
     *
     * @param etiqueta
     * @param url
     * @param titulo
     * @param padre 
     */
    public Opcion(String etiqueta, String url, String titulo, Opcion padre) {
        this.etiqueta = etiqueta;
        this.url = url;
        this.titulo = titulo;
        this.padre = padre;
    }

    /**
     * Agrega un perfil al menú
     * @param perfil el perfil a agregar
     * @return true si se agregó
     */
    public boolean addPerfil(Perfil perfil) {
        return this.perfiles.add(perfil);
    }

    /**
     * Remueve un perfil del menú
     * @param perfil el perfil a remover
     * @return true si se removió
     */
    public boolean removePerfil(Perfil perfil) {
        return this.perfiles.remove(perfil);
    }
    
    /**
     * @return the etiqueta
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * @param etiqueta the etiqueta to set
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
     * @return the padre
     */
    public Opcion getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(Opcion padre) {
        this.padre = padre;
    }

    /**
     * @return the perfiles
     */
    public Set<Perfil> getPerfiles() {
        return perfiles;
    }

    /**
     * @param perfiles the perfiles to set
     */
    public void setPerfiles(Set<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    @Override
    public String toString() {
        return "Opcion{" + "etiqueta=" + etiqueta + ", url=" + url + ", titulo=" + titulo + ", padre=" + padre + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || (obj != null && obj instanceof Opcion == false)){
            return false;
        }else{        
            Opcion p = (Opcion)obj;
            if(p.getId().longValue() == this.getId().longValue()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    /**
     * @return the icono
     */
    public String getIcono() {
        return icono;
    }

    /**
     * @param icono the icono to set
     */
    public void setIcono(String icono) {
        this.icono = icono;
    }
}
