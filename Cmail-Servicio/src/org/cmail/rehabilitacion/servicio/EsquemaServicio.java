/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import org.cmail.rehabilitacion.dao.GanericDao;
import org.cmail.rehabilitacion.modelo.sira.Esquema;

/**
 * Clase de lógica de negocio para manejar los esquemas de preguntas.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class EsquemaServicio extends GenericServicio<Esquema> {

    /**Dao genérico para acceder a los datos*/
    private GanericDao<Esquema> dao;
    
    /**
     * Constructor por defecto
     */
    public EsquemaServicio() {
        super(Esquema.class);
        dao = new GanericDao<Esquema>(Esquema.class);
    }
    
    /**
     * Guarda un esquema en la base de datos
     * @param esquema el esquema
     * @return true si se guardó
     */
    public boolean guardarEsquema(Esquema esquema) {        
        return dao.save(esquema);        
    }    

    /**
     * Crea un nuevo esquema
     * @return el esquema
     */
    public Esquema crearNueva() {
        Esquema esquema = new Esquema();        
        return esquema;
    }
}