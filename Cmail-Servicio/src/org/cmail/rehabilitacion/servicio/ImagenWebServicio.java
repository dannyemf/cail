/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.List;
import org.cmail.rehabilitacion.dao.hql.K;
import org.cmail.rehabilitacion.modelo.sira.ImagenWeb;
import org.cmail.rehabilitacion.modelo.sira.TipoImagenWeb;

/**
 * Clase de lógica de negocio para manejar las imágenes subidas para la galería y para los artículos.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class ImagenWebServicio extends GenericServicio<ImagenWeb> {

    /**
     * Constructor por defecto
     */
    public ImagenWebServicio() {
        super(ImagenWeb.class);
    }
    
    /**
     * Lista todas las imagenes por tipo y el nombre o la descripción contengan el texto
     * @param texto el texto que debe contener el nombre o la descripción
     * @param tipo el tipo
     * @return lista de imagenes
     */
    public List<ImagenWeb> listarPorTexto(String texto, TipoImagenWeb tipo){
        return from().where(
                K.and(
                    K.eq("tipo", tipo),
                    K.or(K.like("nombre", texto), K.like("descripcion", texto))
                )
        ).list();        
    }
    
    /**
     * Lista las imagenes que deben mostrarse en la galería
     * @return lista de imagnes
     */
    public List<ImagenWeb> listarGaleriaHomePage(){
        return from().where(
                K.eq("tipo", TipoImagenWeb.GALERIA),
                K.like("estado", true)
        ).orderBy(K.asc("orden")).list();
    }
    
    /**
     * Lista las imagenes para los artículos.
     * 
     * @return lista de imagenes
     */
    public List<ImagenWeb> listarImagenesWeb(){
        return from().where(K.eq("tipo", TipoImagenWeb.ARTICULO)).list();
    }        
    
}
