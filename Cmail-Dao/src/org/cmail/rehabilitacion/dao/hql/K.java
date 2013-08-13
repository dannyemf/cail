/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.hql;

/**
 * Framework K.
 * K es un framework propio para generar consultas con hibernate de una forma más dinámica.
 * La clase K se usa para generar el where y la ordenación de los resultados.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class K {
    
    /**
     * Agrupa todas las propiedades y los separa con and
     * @param propiedades lista de propiedades
     * @return la propiedad
     */
    public static KProperty and(KProperty... propiedades){
        return new KPropertyGroup(true, propiedades);
    }
    
    /**
     * Agrupa todas las propiedades y los separa con or
     * @param propiedades lista de propiedades
     * @return la propiedad
     */
    public static KProperty or(KProperty... propiedades){
        return new KPropertyGroup(false, propiedades);
    }
    
    /**
     * Crea una propiedad con el operador igual (propiedad = valor)
     * @param propiedad la propiedad
     * @param valor el valor
     * @return la propiedad
     */
    public static KProperty eq(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.eq);        
        return k;
    } 
    
    /**
     * Crea una propiedad con el operador like (propiedad like '%valor%')
     * @param propiedad la propiedad
     * @param valor el valor
     * @return la propiedad
     */
    public static KProperty like(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.like);        
        return k;
    }
    
    /**
     * Crea una propiedad con el operador like (propiedad like '%valor%')
     * @param propiedad la propiedad
     * @param valor el valor
     * @return la propiedad
     */
    public static KProperty notLike(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.notLike);        
        return k;
    }
    
    /**
     * Crea una propiedad con el operador like (propiedad like 'valor%')
     * @param propiedad la propiedad
     * @param valor el valor
     * @return la propiedad
     */
    public static KProperty rlike(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.rLike);        
        return k;
    }
    
    /**
     * Crea una propiedad con el operador like (propiedad like '%valor')
     * @param propiedad la propiedad
     * @param valor el valor
     * @return la propiedad
     */
    public static KProperty llike(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.lLike);        
        return k;
    }
    
    /**
     * Crea una propiedad con el operador not eq (propiedad != valor)
     * @param propiedad la propiedad
     * @param valor el valor
     * @return la propiedad
     */
    public static KProperty notEq(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.notEq);
        return k;
    }
    
    /**
     * Crea una propiedad con el operador is null (propiedad is null)
     * @param propiedad la propiedad
     * @param valor el valor
     * @return la propiedad
     */
    public static KProperty isNull(String propiedad){
        KProperty k = new KProperty(propiedad, KOperador.isNul);        
        return k;
    }
    
    /*
     * Crea una propiedad con el operador is not null (propiedad is not null)
     * @param propiedad la propiedad
     * @param valor el valor
     * @return la propiedad
     */
    public static KProperty isNotNull(String propiedad){
        KProperty k = new KProperty(propiedad, KOperador.isNotNull);        
        return k;
    }
    
    /*
     * Crea una propiedad con el operador mayor a (propiedad &gt; valor)
     * @param propiedad la propiedad
     * @param valor el valor
     * @return la propiedad
     */
    public static KProperty may(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.may);
        return k;
    }
    
    /*
     * Crea una propiedad con el operador mayor o igial a (propiedad &gt;> valor)
     * @param propiedad la propiedad
     * @param valor el valor
     * @return la propiedad
     */
    public static KProperty mayEq(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.mayEq);
        return k;
    }
    
    /*
     * Crea una propiedad con el operador menor a (propiedad &lt; valor)
     * @param propiedad la propiedad
     * @param valor el valor
     * @return la propiedad
     */
    public static KProperty men(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.men);
        return k;
    }
    
    /*
     * Crea una propiedad con el operador menor o igual a (propiedad &lt;= valor)
     * @param propiedad la propiedad
     * @param valor el valor
     * @return la propiedad
     */
    public static KProperty menEq(String propiedad, Object valor){
        KProperty k = new KProperty(propiedad, valor, KOperador.menEq);
        return k;
    }
    
    /*
     * Crea una propiedad de ordenación (order by propiedad asc)
     * @param propiedad la propiedad
     * @return la propiedad de ordenación
     */
    public static KOrderProperty asc(String propiedad){
        return new KOrderProperty(propiedad, true);
    }
    
    /*
     * Crea una propiedad de ordenación (order by propiedad desc)
     * @param propiedad la propiedad
     * @return la propiedad de ordenación
     */
    public static KOrderProperty desc(String propiedad){
        return new KOrderProperty(propiedad, false);
    }
    
}
