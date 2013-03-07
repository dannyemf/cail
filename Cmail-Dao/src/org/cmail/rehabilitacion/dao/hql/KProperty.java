
package org.cmail.rehabilitacion.dao.hql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa un par de datos clave y valor
 * 
 * @author Usuario
 */
public class KProperty {
    
    private String key;
    private Object valor;
    private boolean group = false;
    private List<KProperty> propiedades = new ArrayList<KProperty>();    
    private KOperador operador = KOperador.eq;
    private boolean and = true;  
    
    protected KProperty(KProperty... propiedades) {
        this.propiedades.addAll(Arrays.asList(propiedades));        
        group = true;
    }
    
    public KProperty(String key) {
        this.key = key;
        this.operador = KOperador.isNul;
    }
    
    public KProperty(String key, KOperador operador) {
        this.key = key;
        this.valor = null;
        this.operador = operador;        
    }

    public KProperty(String key, Object valor) {
        this.key = key;
        this.valor = valor;
        this.operador = valor == null ? KOperador.isNul : KOperador.eq;
    }
    
    public KProperty(String key, Object valor, KOperador operador) {
        this.key = key;
        this.valor = valor;
        this.operador = operador;        
    }
    
    public String op(List<Object> parametros){
        switch(operador){
            case eq: 
                parametros.add(valor);
                return "=?";
            case notEq: 
                parametros.add(valor);
                return "!=?";    
            case isNul: return " is null";
            case isNotNull: return " is not null";
            case may: 
                parametros.add(valor);
                return ">?";
            case mayEq: 
                parametros.add(valor);
                return ">=?";
            case men: 
                parametros.add(valor);
                return "<?";
            case menEq: 
                parametros.add(valor);
                return "<=?";
                
            case like: 
            case lLike: 
            case rLike: 
                
                String v = valor != null ? valor.toString() : "";
                
                if(operador == KOperador.lLike){
                    v = "%" + v;
                }
                
                if(operador == KOperador.rLike){
                    v = v + "%";
                }
                
                if(operador == KOperador.like){
                    v = v.length() == 0 ? "%" : "%" + v + "%";
                }
                
                parametros.add(v);
                
                return " Like ?";
                
            default: return "<<noop>>";
        }
        
    }        
    
    public String toHql(List<Object> parametros){        
        String s = "";        
        s += isGroup() ? "(" :  "";
        s += toHqlProperties(parametros);
        s += isGroup() ? ")" : "";
        return s;
    }
    
    public String toHqlProperties(List<Object> parametros){        
        String s = propiedades.isEmpty() ? "" : "(";
        
        if(isGroup()==false){
            s += getKey() + op(parametros);
        }
        
        int i = 0;
        for (Iterator<KProperty> it = propiedades.iterator(); it.hasNext();) {
            KProperty kv = it.next();            
            s += kv.isAnd() ? " and " : " or ";                        
            s += kv.toHql(parametros);
        }
        
        s+= propiedades.isEmpty() ? "" : ")";
        
        return s;
    }
    
    
    public KProperty and(KProperty p){
        p.and = true;
        propiedades.add(p);
        return this;
    }   
    
    public KProperty or(KProperty p){
        p.and = false;
        propiedades.add(p);
        return this;
    }
    
    public KProperty toOr(){
        and = false;
        return this;
    }
    

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the valor
     */
    public Object getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }

    public boolean isGroup() {
        return group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }

    public KOperador getOperador() {
        return operador;
    }

    public void setOperador(KOperador operador) {
        this.operador = operador;
    }        

    public boolean isAnd() {
        return and;
    }

    public void setAnd(boolean and) {
        this.and = and;
    }

    
    
}
