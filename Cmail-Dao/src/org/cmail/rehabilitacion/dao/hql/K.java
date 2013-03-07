/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.hql;

/**
 *
 * @author Usuario
 */
public class K {
    
    public static KProperty group(KProperty... propiedades){
        return new KPropertyGroup(propiedades);
    }    
    
    public static KProperty eq(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.eq);        
        return k;
    } 
    
    public static KProperty like(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.like);        
        return k;
    }
    
    public static KProperty rlike(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.rLike);        
        return k;
    }
    
    public static KProperty llike(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.lLike);        
        return k;
    }
    
    public static KProperty notEq(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.notEq);
        return k;
    }
    
    public static KProperty isNull(String propiedad){
        KProperty k = new KProperty(propiedad, KOperador.isNul);        
        return k;
    }
    
    public static KProperty isNotNull(String propiedad){
        KProperty k = new KProperty(propiedad, KOperador.isNotNull);        
        return k;
    }
    
    public static KProperty may(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.may);
        return k;
    }
    
    public static KProperty mayEq(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.mayEq);
        return k;
    }
    
    public static KProperty men(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.men);
        return k;
    }
    
    public static KProperty menEq(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.menEq);
        return k;
    }
    
    public static KOrderProperty asc(String propiedad){
        return new KOrderProperty(propiedad, true);
    }
    
    public static KOrderProperty desc(String propiedad){
        return new KOrderProperty(propiedad, false);
    }
    
}
