
package org.cmail.rehabilitacion.dao.hql;

import java.util.List;

/**
 * Clase que representa un par de datos clave y valor
 * 
 * @author Usuario
 */
public class KProperty {
    
    private String key;
    private Object valor;    
    private KOperador operador = KOperador.eq;    
    
    protected KProperty() {                        
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
            case notLike:
                
                String v = valor != null ? valor.toString() : "";
                
                if(operador == KOperador.lLike){
                    v = "%" + v;
                }
                
                if(operador == KOperador.rLike){
                    v = v + "%";
                }
                
                if(operador == KOperador.like || operador == KOperador.notLike){
                    v = v.length() == 0 ? "%" : "%" + v + "%";
                }
                
                parametros.add(v);
                
                if(operador == KOperador.notLike){
                    return " Not Like ?";
                }else{
                    return " Like ?";
                }
                
                
            default: return "<<noop>>";
        }
        
    }        
    
    public String toHql(List<Object> parametros){        
        String s = "";        
        s += key + op(parametros);        
        return s;
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

    public KOperador getOperador() {
        return operador;
    }

    public void setOperador(KOperador operador) {
        this.operador = operador;
    }
    
}
