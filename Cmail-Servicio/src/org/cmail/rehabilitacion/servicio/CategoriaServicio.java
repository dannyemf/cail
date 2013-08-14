/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import org.cmail.rehabilitacion.dao.CategoriaDao;
import org.cmail.rehabilitacion.modelo.htp.Categoria;

/**
 * Clase de lógica de negocio para manejar categorías de indicadores.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class CategoriaServicio extends GenericServicio<Categoria>{   
    
    /**
     * Constructor por defecto
     */
    public CategoriaServicio() {
        super(Categoria.class);
    }        
    
}
