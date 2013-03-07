/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core;

import ch.lambdaj.Lambda;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.hamcrest.Matcher;

/**
 *
 * @author Usuario
 */
public class CmailList<E> extends ArrayList {

    public CmailList() {
    }

    public CmailList(Collection c) {
        super(c);
    }

    public CmailList(int initialCapacity) {
        super(initialCapacity);
    }
    
    public E first(){
        if(this.size() > 0){
            return (E)this.get(0);
        }
        return null;
    }   
    
    public E single(String propiedad, Object valor) throws Exception{
        return this.eq(propiedad, valor, true).first();        
    }
    
    public CmailList<E> eq(String propiedad, Object valor) throws Exception{
        return this.eq(propiedad, valor, false);
    }
    
    public CmailList<E> select(Matcher<?> matcher){
        return new CmailList<E>(Lambda.select(this, matcher));        
    }
    
    /*public E selectFirst(Matcher<?> matcher) throws Exception{
        E e = Lambda.selectFirst(this, matcher);
        return  e;
    }*/
    
    public E on(Class<E> clase){
        return Lambda.on(clase);
    }
    
    
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
