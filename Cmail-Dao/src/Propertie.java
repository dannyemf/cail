
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class Propertie{
    
    private String nombre;
    private String operador;
    private Object valor;     
    private boolean group;
    
    private List<Propertie> pLista = new ArrayList<Propertie>();
    private List<String> sLista = new ArrayList<String>();

    public Propertie() {        
    }

    public Propertie(String nombre, Object valor) {        
        this(nombre, "=", valor);
    }

    public Propertie(String nombre, String operador, Object valor) {
        this.nombre = nombre;
        this.operador = operador;
        this.valor = valor;
    }
    
    public Propertie and(Propertie p){
        pLista.add(p);
        sLista.add(" and ");
        
        return this;
    }
    
    public Propertie or(Propertie p){
        
        pLista.add(p);
        sLista.add(" or ");
        
        return this;
    }
    
    public Propertie group(){
        group = true;
        return this;
    }
    
    public String hql(List<Object> valores){
        String s = (group ? "(" : "") + nombre + operador + "?";        
        valores.add(valor);        
        
        if(!pLista.isEmpty()){
            
            for (int i = 0; i < pLista.size(); i++){
                
                Propertie p = pLista.get(i);
                String op = sLista.get(i);
                
                s += op + p.hql(valores);
            }
            
        }                
        
        return s + (group ? ")" : "");
    }
    
    
}
