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
 *
 * @author Usuario
 */
public class Categoria extends DomainEntity{
    
    private String nombre;
    private String descripcion;
    private TipoCategoria tipo;
    
    //private Interpretacion interpretacion;
    private Set<Indicador> indicadores = new HashSet<Indicador>();   

    public Categoria() {
    }
    
    public void addIndicador(Indicador indicador){
        indicador.setCategoria(this);
        indicadores.add(indicador);
    }
    
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
    
    public List<Indicador> getIndicadoresCasa(){
        return obtenerIndicadores(TipoIndicador.Casa);
    }
    
    public List<Indicador> getIndicadoresArbol(){
        return obtenerIndicadores(TipoIndicador.Arbol);
    }
    
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
