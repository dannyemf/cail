/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.hql;

import java.util.List;
import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 * Interface  que debe implementar el framework personal de consultas (Framework K).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public interface IKResult<T extends DomainEntity> {
    
    /**
     * Listar los resultados
     * @return lista de entidades
     */
    public List<T> list();
    
    /**
     * Obtener el resultado único.
     * 
     * @return una única entidad o null
     */
    public T unique();
    
    /**
     * Obtener el primer resultado
     * @return la primer entidad o null
     */
    public T first();
    
    /**
     * Contar el número de entidades
     * @return el conteo
     */
    public Long count();
}


