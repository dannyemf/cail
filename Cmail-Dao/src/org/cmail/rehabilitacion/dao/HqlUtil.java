/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao;

import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 *
 * @author Usuario
 */
public class HqlUtil {
    
    public static String exists(DomainEntity entidad, String propiedad, String value) {
        try {                       
            String tn = entidad.getClass().getSimpleName();
            System.out.println(tn);
            
            String hql = ("select count(*) from "
                    + tn
                    + " where " 
                    + " id != " + entidad.getId().longValue()
                    + " and " + propiedad + " = '" + value +"'");
            
            return hql;
            
        } catch (Exception e) {
            return null;
        }
        
    }        
    
    public static <T extends DomainEntity> String getAllByAndPropertys(Class<? extends T> clase, KProperty... map) {
        try {
            String sql = "from " + clase.getSimpleName() + (map.length > 0 ? " Where ":"");
            boolean and = false;
            
            for (KProperty it : map) {
                String prop = it.getKey();                
                sql += (and ? " and " : "") + prop + (it.getValor() == null ? " is null " : " =? ");
                and = true;
            }                        
            
            return sql;
        } catch (Exception e) {
            return null;
        }
    }    
    
    public static <T extends DomainEntity> String getAllByAndLikePropertys(Class<? extends T> clase, KProperty... map) {
        try {
            String sql = "from " + clase.getSimpleName() + (map.length > 0 ? " Where ":"");
            boolean and = false;
            
            for (KProperty it : map) {
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
