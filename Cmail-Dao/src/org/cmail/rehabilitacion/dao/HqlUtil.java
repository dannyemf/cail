/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao;

import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 * Clase de utilidad para generar hql en base a clases de entidades, propiedades y valores.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class HqlUtil {
    
    /**
     * Genera una cadena para verificar si existe una entidad
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * select count(*) from entidad where id != :id and propiedad = :valor
     * <span>
     * </p>
     * 
     * @param entidad la entidad
     * @param propiedad en nombre de la propiedad
     * @param valor el valor de la propiedad
     * @return cadena sql generada
     */
    public static String exists(DomainEntity entidad, String propiedad, String valor) {
        try {                       
            String tn = entidad.getClass().getSimpleName();
            System.out.println(tn);
            
            String hql = ("select count(*) from "
                    + tn
                    + " where " 
                    + " id != " + entidad.getId().longValue()
                    + " and " + propiedad + " = '" + valor +"'");
            
            return hql;
            
        } catch (Exception e) {
            return null;
        }
        
    }
    
    /**
     * Obtiene el hql de la clase indicada cuyas propiedades son separadas con un and y el operador =
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad1 = :valor1 and propiedad2 = :valor2 and propiedad(n) = :valor(n)
     * <span>
     * </p>
     * 
     * @param <T> tipo genérico
     * @param clase clase de entidad
     * @param propiedades lista de propiedades con su valor
     * @return cadena hql generada
     */
    public static <T extends DomainEntity> String getAllByAndPropertys(Class<? extends T> clase, KProperty... propiedades) {
        try {
            String sql = "from " + clase.getSimpleName() + (propiedades.length > 0 ? " Where ":"");
            boolean and = false;
            
            for (KProperty it : propiedades) {
                String prop = it.getKey();                
                sql += (and ? " and " : "") + prop + (it.getValor() == null ? " is null " : " =? ");
                and = true;
            }                        
            
            return sql;
        } catch (Exception e) {
            return null;
        }
    }    
    
    /**
     * Obtiene el hql para la clase indicada, mediante el listado de propiedades, el agrupador and y el operador like.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad1 like '%:valor1%' and propiedad2 like '%:valor2%' and propiedad(n) like '%:valor(n)'
     * <br/>
     * Donde op1, op2, op(n) el el operador de cada Kproperty
     * <span>
     * </p>
     * 
     * @param <T> tipo genérico
     * @param clase clase de entidad
     * @param propiedades
     * @return cadena hql generada
     */
    public static <T extends DomainEntity> String getAllByAndLikePropertys(Class<? extends T> clase, KProperty... propiedades) {
        try {
            String sql = "from " + clase.getSimpleName() + (propiedades.length > 0 ? " Where ":"");
            boolean and = false;
            
            for (KProperty it : propiedades) {
                String prop = it.getKey();
                String valor = it.getValor().toString();
                
                String like = valor.toString().length() == 0 ? "'%'" : "'%"+valor+"%'";                
                sql += (and ? " and " : "") + prop + " Like " + like;
                and = true;
            }
            
            return sql;
        } catch (Exception e) {
            return null;
        }
    }    
    
    /**
     * Obtiene el hql para la entidad indicada mediante likes y or.
     * 
     * <p>
     * <b style='color: red;'>Hql Generado: </b>
     * <span style='color: blue;'>
     * from entidad where propiedad1 like '%:valor%' or propiedad2 like '%:valor%' or ... propiedad(n) like '%valor%'
     * <span>
     * </p>
     * 
     * @param <T> tipo genérico
     * @param clase clase de entidad
     * @param valor valor a verificar
     * @param propiedades lista de propiedades
     * @return cadena hql generada
     */
    public static <T extends DomainEntity> String getAllByOrLikePropertys(Class<? extends T> clase, Object valor, String... propiedades) {
        try {
            
            String like = valor.toString().length() == 0 ? "'%'" : "'%"+valor+"%'";
            String sql = "from " + clase.getSimpleName() + (propiedades.length > 0 ? " Where ":"");
            
            boolean or = false;
            for (String prop : propiedades) {
                sql += (or ? " or " : "") + prop + " Like " + like;
                or = true;
            }
            
            return  sql;
        } catch (Exception e) {
            return null;
        }
    }       
}
