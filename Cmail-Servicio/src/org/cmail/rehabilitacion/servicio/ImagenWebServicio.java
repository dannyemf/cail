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
 *
 * @author Usuario
 */
public class ImagenWebServicio extends GenericServicio<ImagenWeb> {

    public ImagenWebServicio() {
        super(ImagenWeb.class);
    }
    
    public List<ImagenWeb> listarPorTexto(String texto, TipoImagenWeb tipo){
        return from().where(
                K.and(
                    K.eq("tipo", tipo),
                    K.or(K.like("nombre", texto), K.like("descripcion", texto))
                )
        ).list();
        //return from().where(K.eq("tipo", tipo).and(K.like("nombre", texto).or(K.like("descripcion", texto)))).list();
    }
    
    public List<ImagenWeb> listarGaleriaHomePage(){
        return from().where(
                K.eq("tipo", TipoImagenWeb.GALERIA),
                K.like("estado", true)
        ).orderBy(K.asc("orden")).list();
        //return from().where(K.eq("tipo", TipoImagenWeb.GALERIA).and(K.eq("estado", true))).orderBy(K.asc("orden")).list();
    }
    
    public List<ImagenWeb> listarImagenesWeb(){
        return from().where(K.eq("tipo", TipoImagenWeb.ARTICULO)).list();
    }        
    
}
