/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import org.cmail.rehabilitacion.modelo.core.CmailList;
import org.cmail.rehabilitacion.modelo.localizacion.Canton;
import org.cmail.rehabilitacion.modelo.localizacion.Parroquia;
import org.cmail.rehabilitacion.modelo.localizacion.Provincia;

/**
 * Clase de lógica de negocio para manejar provincias, cantones y parroquias.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class DivisionPoliticaServicio extends BaseServicio {

    /**
     * Constructor por defecto
     */
    public DivisionPoliticaServicio() {        
    }
    
    /**
     * Obtiene el listado de todas las provincias.
     * 
     * @return lista de provincias
     */
    public CmailList<Provincia> listarProvincias(){
        return super.listarTodos(Provincia.class);
    }
    
    /**
     * Obtiene todos los cantones alamacenados en la base de datos.
     * 
     * @return lista de cantones
     */
    public CmailList<Canton> listarCantones(){
        return super.listarTodos(Canton.class);
    }
    
    /**
     * Obtiene todas las parroquias almacenadas en la base de datos.
     * 
     * @return lista de parroquias
     */
    public CmailList<Parroquia> listarParroquias(){
        return super.listarTodos(Parroquia.class);
    }
            
}
