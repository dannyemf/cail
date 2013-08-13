/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao;


import org.cmail.rehabilitacion.modelo.htp.Categoria;

/**
 * Clase de acceso a datos para la entidad categoría
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class CategoriaDao extends GanericDao<Categoria> {

    /**
     * Constructor por defecto
     */
    public CategoriaDao() {
        super(Categoria.class);
    }
    
}
