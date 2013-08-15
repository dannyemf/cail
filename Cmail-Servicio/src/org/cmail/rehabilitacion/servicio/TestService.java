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
 * @author uti
 */
public class TestService {
    
    public static void main(String... args){
        BaseServicio srv = new BaseServicio();
        
        List lst1 = srv.from(ImagenWeb.class).where(
                K.eq("tipo", TipoImagenWeb.GALERIA),
                K.eq("estado", true)
        ).orderBy(K.asc("orden")).list();
        
        System.out.println(lst1);
        
    }
    
}
