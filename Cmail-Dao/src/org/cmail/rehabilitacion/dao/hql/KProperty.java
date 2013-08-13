
package org.cmail.rehabilitacion.dao.hql;

import java.util.List;

/**
 * Clase que representa un par de datos clave y valor con su operador, en si representa una propiedad (Framework K).
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class KProperty {
    
    /**
     * El nombre de la propiedad
     */
    private String key;
    
    /**
     * El valor de la propiedad
     */
    private Object valor;   
    
    /**
     * El operador con el que se debe tratar
     */
    private KOperador operador = KOperador.eq;    
    
    /**
     * Constructor por defecto, no se usa.
     */
    protected KProperty() {                        
    }
    
    /**
     * Constructor mínimo con operador por defecto is null.
     * 
     * @param key nombre de la propiedad
     */
    public KProperty(String key) {
        this.key = key;
        this.operador = KOperador.isNul;
    }
    
    /**
     * Constructor completo con valor nulo.
     * 
     * @param key nombre de la propiedad
     * @param operador operador a tratar
     */
    public KProperty(String key, KOperador operador) {
        this.key = key;
        this.valor = null;
        this.operador = operador;        
    }

    /**
     * Constructor completo con operador nulo o eq dependiendo del valor.
     * 
     * @param key nombre de la propiedad
     * @param valor el valor
     */
    public KProperty(String key, Object valor) {
        this.key = key;
        this.valor = valor;
        this.operador = valor == null ? KOperador.isNul : KOperador.eq;
    }
    
    /**
     * Constructor completo.
     * 
     * @param key nombre de la propiedad
     * @param valor el valor
     * @param operador el operador a tratar
     */
    public KProperty(String key, Object valor, KOperador operador) {
        this.key = key;
        this.valor = valor;
        this.operador = operador;        
    }
    
    /**
     * Genera el hql del operador. Ejemplo: is null
     * @param parametros lista de parámetros
     * @return hql del operador
     */
    public String toHqlOperador(List<Object> parametros){
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
    
    /**
     * Genera el hql de la propiedad. Ejemplo: propiedad is null
     * @param parametros lista de parámetros
     * @return hql del la propiedad
     */
    public String toHql(List<Object> parametros){        
        String s = "";        
        s += key + toHqlOperador(parametros);        
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
