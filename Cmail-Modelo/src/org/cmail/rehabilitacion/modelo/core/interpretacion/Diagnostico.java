/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core.interpretacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.cmail.rehabilitacion.modelo.htp.Categoria;
import org.cmail.rehabilitacion.modelo.htp.TipoCategoria;

/**
 *
 * @author Usuario
 */
public class Diagnostico implements Serializable{
    
    private List<Categoria> categorias = new ArrayList<Categoria>();
    
    private List<ItemInterpretacionCategoriaIndicador> indicadoresProporcion = new ArrayList<ItemInterpretacionCategoriaIndicador>();
    private List<ItemInterpretacionCategoriaIndicador> indicadoresPerspectiva = new ArrayList<ItemInterpretacionCategoriaIndicador>();
    private List<ItemInterpretacionCategoriaIndicador> indicadoresDetalles = new ArrayList<ItemInterpretacionCategoriaIndicador>();
    
    private String diagnosticoProporcion;
    private String diagnosticoPerspectiva;
    private String diagnosticoDetalles;
    
    private double porcentajeRehabilitacionSistema;
    
    public Diagnostico() {
    }

    public Diagnostico(List<Categoria> categorias) {
        this.categorias = categorias;        
    }
    
    public void addItemIndicador(ItemInterpretacionCategoriaIndicador indicador, TipoCategoria tipo){
        switch(tipo){
            case Proporcion: indicadoresProporcion.add(indicador); break;
            case Perspectiva: indicadoresPerspectiva.add(indicador); break;
            case Detalles: indicadoresDetalles.add(indicador); break;
        }
    }
    
    public List<ItemInterpretacionCategoriaIndicador> obtenerIndicadores(){
        
        List<ItemInterpretacionCategoriaIndicador> lista = new ArrayList<ItemInterpretacionCategoriaIndicador>();
        lista.addAll(indicadoresProporcion);
        lista.addAll(indicadoresPerspectiva);
        lista.addAll(indicadoresDetalles);
        
        return lista;
    }
    
    public int sumarIndicadores(){
        int suma = 0;
        
        List<ItemInterpretacionCategoriaIndicador> lista = obtenerIndicadores();
        for (Iterator<ItemInterpretacionCategoriaIndicador> it = lista.iterator(); it.hasNext();) {
            ItemInterpretacionCategoriaIndicador item = it.next();
            suma += item.getIndicador().getValor();
        }
        
        return suma;
    }

    /**
     * @return the categorias
     */
    public List<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * @param categorias the categorias to set
     */
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    /**
     * @return the indicadoresProporcion
     */
    public List<ItemInterpretacionCategoriaIndicador> getIndicadoresProporcion() {
        return indicadoresProporcion;
    }

    /**
     * @param indicadoresProporcion the indicadoresProporcion to set
     */
    public void setIndicadoresProporcion(List<ItemInterpretacionCategoriaIndicador> indicadoresProporcion) {
        this.indicadoresProporcion = indicadoresProporcion;
    }

    /**
     * @return the indicadoresPerspectiva
     */
    public List<ItemInterpretacionCategoriaIndicador> getIndicadoresPerspectiva() {
        return indicadoresPerspectiva;
    }

    /**
     * @param indicadoresPerspectiva the indicadoresPerspectiva to set
     */
    public void setIndicadoresPerspectiva(List<ItemInterpretacionCategoriaIndicador> indicadoresPerspectiva) {
        this.indicadoresPerspectiva = indicadoresPerspectiva;
    }

    /**
     * @return the indicadoresDetalles
     */
    public List<ItemInterpretacionCategoriaIndicador> getIndicadoresDetalles() {
        return indicadoresDetalles;
    }

    /**
     * @param indicadoresDetalles the indicadoresDetalles to set
     */
    public void setIndicadoresDetalles(List<ItemInterpretacionCategoriaIndicador> indicadoresDetalles) {
        this.indicadoresDetalles = indicadoresDetalles;
    }

    /**
     * @return the diagnosticoProporcion
     */
    public String getDiagnosticoProporcion() {
        return diagnosticoProporcion;
    }

    /**
     * @param diagnosticoProporcion the diagnosticoProporcion to set
     */
    public void setDiagnosticoProporcion(String diagnosticoProporcion) {
        this.diagnosticoProporcion = diagnosticoProporcion;
    }

    /**
     * @return the diagnosticoPerspectiva
     */
    public String getDiagnosticoPerspectiva() {
        return diagnosticoPerspectiva;
    }

    /**
     * @param diagnosticoPerspectiva the diagnosticoPerspectiva to set
     */
    public void setDiagnosticoPerspectiva(String diagnosticoPerspectiva) {
        this.diagnosticoPerspectiva = diagnosticoPerspectiva;
    }

    /**
     * @return the diagnosticoDetalles
     */
    public String getDiagnosticoDetalles() {
        return diagnosticoDetalles;
    }

    /**
     * @param diagnosticoDetalles the diagnosticoDetalles to set
     */
    public void setDiagnosticoDetalles(String diagnosticoDetalles) {
        this.diagnosticoDetalles = diagnosticoDetalles;
    }

    /**
     * @return the porcentajeRehabilitacionSistema
     */
    public double getPorcentajeRehabilitacionSistema() {
        return porcentajeRehabilitacionSistema;
    }

    /**
     * @param porcentajeRehabilitacionSistema the porcentajeRehabilitacionSistema to set
     */
    public void setPorcentajeRehabilitacionSistema(double porcentajeRehabilitacionSistema) {
        this.porcentajeRehabilitacionSistema = porcentajeRehabilitacionSistema;
    }
    
    
    
    
    
}
