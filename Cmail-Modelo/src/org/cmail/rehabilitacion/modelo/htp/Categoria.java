/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.htp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.cmail.rehabilitacion.modelo.DomainEntity;
import org.cmail.rehabilitacion.modelo.core.IndicadorOrdenComparator;

/**
 * Entidad que representa una categoría de indicadores.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Categoria extends DomainEntity{
    
    /**
     * Nombre de la categoría
     */
    private String nombre;   
    
    /**
     * Descripción de la categoría
     */
    private String descripcion;
    
    /**
     * Tipo de categoría
     */
    private TipoCategoria tipo;
    
    //private Interpretacion interpretacion;
    private Set<Indicador> indicadores = new HashSet<Indicador>();   

    /**
     * Constructor por defecto
     */
    public Categoria() {
    }
    
    /**
     * Agrega un indicador a la categoría
     * @param indicador el indicador
     */
    public void addIndicador(Indicador indicador){
        indicador.setCategoria(this);
        indicadores.add(indicador);
    }
    
    /**
     * Obtiene una lista de indicadores solo del tipo indicado
     * @param tipo el tipo de indicador
     * @return la lista de indicadores
     */
    public List<Indicador> obtenerIndicadores(TipoIndicador tipo){
        List<Indicador> lst = new ArrayList<Indicador>();
        for (Iterator<Indicador> it = this.indicadores.iterator(); it.hasNext();) {
            Indicador indicador = it.next();
            if(indicador.getTipo().equals(tipo)){
                lst.add(indicador);
            }
        }        
        
        Collections.sort(lst, new IndicadorOrdenComparator());
        
        return lst;
    }
    
    /**
     * Obtiene los indicadores cuyo tipo es Casa
     * @return la lista de indicadores
     */
    public List<Indicador> getIndicadoresCasa(){
        return obtenerIndicadores(TipoIndicador.Casa);
    }
    
    /**
     * Obtiene los indicadores cuyo tipo es Arbol
     * @return la lista de indicadores
     */
    public List<Indicador> getIndicadoresArbol(){
        return obtenerIndicadores(TipoIndicador.Arbol);
    }
    
    /**
     * Obtiene los indicadores cuyo tipo es Persona
     * @return la lista de indicadores
     */
    public List<Indicador> getIndicadoresPersona(){
        return obtenerIndicadores(TipoIndicador.Persona);
    }

    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }  

    /**
     * @return the indicadores
     */
    public Set<Indicador> getIndicadores() {
        return indicadores;
    }

    /**
     * @param indicadores the indicadores to set
     */
    public void setIndicadores(Set<Indicador> indicadores) {
        this.indicadores = indicadores;
    }

    /**
     * @return the tipo
     */
    public TipoCategoria getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoCategoria tipo) {
        this.tipo = tipo;
    }  
    
}
