/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Colección personalizada con métodos adicionales.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class CmailList<E> extends ArrayList {

    /**
     * Constructor por defecto
     */
    public CmailList() {
    }

    /**
     * Constructor con una colección.
     * @param c la colección a agregar
     */
    public CmailList(Collection c) {
        super(c);
    }

    /**
     * Constructor con capacidad.
     * 
     * @param initialCapacity la capacidad inicial
     */
    public CmailList(int initialCapacity) {
        super(initialCapacity);
    }
    
    /**
     * Obtiene el primer elemento
     * @return el elemento
     */
    public E first(){
        if(this.size() > 0){
            return (E)this.get(0);
        }
        return null;
    }   
    
    /**
     * Obtiene el primer elemento cuya propiedad sea igual al valor
     * @param propiedad el nombre de la propiedad
     * @param valor el valor
     * @return le entidad o nulo
     * @throws Exception 
     */
    public E single(String propiedad, Object valor) throws Exception{
        return this.eq(propiedad, valor, true).first();        
    }
    
    /**
     * Obtiene una subcolección con todos los elementos cuya propiedad sea igual al valor.
     * 
     * @param propiedad el nombre de la propiedad
     * @param valor el valor a comparar
     * @return una lista
     * @throws Exception 
     */
    public CmailList<E> eq(String propiedad, Object valor) throws Exception{
        return this.eq(propiedad, valor, false);
    }            
    
    /**
     * Obtiene una lista con todos los elementos cuya propiedad sea igual al valor, si single es true retorna la colección con el primer elemento.
     * @param propiedad
     * @param valor
     * @param single
     * @return una lista
     * @throws Exception 
     */
    private CmailList<E> eq(String propiedad, Object valor, boolean single) throws Exception{
        
        CmailList<E> lst = new CmailList<E>();        
        Method method = null;
        Class clase = null;
        
        String pref = "get";
        if(valor != null && valor instanceof Boolean){
            pref = "is";
        }
        
        for (Iterator<E> it = this.iterator(); it.hasNext();) {
            E e = it.next();
            try {
                if(method == null){                    
                    method = e.getClass().getMethod(pref + (propiedad.charAt(0)+"").toUpperCase()+propiedad.substring(1), new Class[0]);
                    clase = method.getReturnType();
                }
                
                Object v = method.invoke(e, new Object[0]);
                
                if(valor == null && v==null){
                    lst.add(e);
                }else{
                    if(v != null){
                        
                        if(valor != null && valor.getClass().isInstance(v) == false){
                            throw new Exception("El valor enviado no es una instancia de " + clase.getCanonicalName());
                        }
                        
                        if(v.equals(valor)){
                            lst.add(e);
                        }
                    }
                }
                
            } catch (NoSuchMethodException ex) {
                throw  new Exception("Método accesor de la propiedad " + propiedad + " no encontrado." ,ex);
            }
            
            if(single && lst.size() > 0){
                return  lst;
            }
        }
        
        
        return lst;
    }
    
    
}
