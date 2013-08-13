/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.hql;

/**
 * Enumeración que lista los operadores usados por el framework personal de consultas (Framework K).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public enum KOperador {
    /**
     * Operador igual a (=)
     */
    eq,
    
    /**
     * Operador no igual a (!=)
     */
    notEq,
    
    /**
     * Operador mayor a (&gt;)
     */
    may,
    
    /**
     * Operador mayor o igual a (&gt;=)
     */
    mayEq,
    
    /**
     * Operador menor a (&lt;)
     */
    men,
    
    /**
     * Operador menor o igual a (&lt;=)
     */
    menEq,
    
    /**
     * Operador es nulo (is null)
     */
    isNul,
    
    /**
     * Operador no es nulo (is not null)
     */
    isNotNull,    
    
    /**
     * Operador like (like '%valor%')
     */
    like,
    
    /**
     * Operador like (like 'valor%')
     */
    rLike,
    
    /**
     * Operador like (like '%valor')
     */
    lLike,
    
    /**
     * Operador no like (not like '%valor%')
     */
    notLike
}
