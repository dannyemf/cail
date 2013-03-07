/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import org.cmail.rehabilitacion.dao.CategoriaDao;
import org.cmail.rehabilitacion.modelo.htp.Categoria;

/**
 *
 * @author Usuario
 */
public class CategoriaServicio extends GenericServicio<Categoria>{

    private CategoriaDao dao = new CategoriaDao();
    
    public CategoriaServicio() {
        super(Categoria.class);
    }        
    
    
    
}
