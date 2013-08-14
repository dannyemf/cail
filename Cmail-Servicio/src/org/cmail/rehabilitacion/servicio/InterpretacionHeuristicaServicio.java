/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.cmail.rehabilitacion.modelo.core.interpretacion.Diagnostico;
import org.cmail.rehabilitacion.modelo.core.interpretacion.ItemInterpretacion;
import org.cmail.rehabilitacion.modelo.core.interpretacion.ItemInterpretacionCategoria;
import org.cmail.rehabilitacion.modelo.core.interpretacion.ItemInterpretacionCategoriaIndicador;
import org.cmail.rehabilitacion.modelo.htp.Categoria;
import org.cmail.rehabilitacion.modelo.htp.TipoCategoria;

/**
 * Clase de lógica de negocio para realizar la interpretación heurística del test htp.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class InterpretacionHeuristicaServicio {       
    
    /**
     * Método principal para generar el diágnostico para lo cual necesita la información ingresada en la vista.
     * 
     * @param categorias lista con todas las categorías de indicadores
     * @param ic modelo de vista en donde especifica los indicadores seleccionados de la casa
     * @param ia modelo de vista en donde especifica los indicadores seleccionados del árbol
     * @param ip modelo de vista en donde especifica los indicadores seleccionados de la persona
     * @return un objeto Diagnostico
     */
    public Diagnostico diagnosticar(List<Categoria> categorias, ItemInterpretacion ic, ItemInterpretacion ia, ItemInterpretacion ip){
        
        Diagnostico diagnostico = new Diagnostico(categorias);                
        
        TipoCategoria[] tipos = TipoCategoria.values();
        
        //Por cada tipo obtengo
        for (TipoCategoria tipo : tipos) {
            
            //Por cada categoria del tipo obtego
            //Por cada categoria obtengo escogo el primer indicador seleccionado, luego el de mayor valor seleccionado y si hay dos con el mismo valor lo escojo aleatoriamente
            for (Iterator<Categoria> it = obtenerCategorias(categorias, tipo).iterator(); it.hasNext();) {
                Categoria cat = it.next();
               
                ItemInterpretacionCategoriaIndicador indicadorCasa = obtenerMejorIndicador(cat, ic);
                ItemInterpretacionCategoriaIndicador indicadorArbol = obtenerMejorIndicador(cat, ia);
                ItemInterpretacionCategoriaIndicador indicadorPersona = obtenerMejorIndicador(cat, ip);

                //Ahora de los tres escogo el de mayor valor
                ItemInterpretacionCategoriaIndicador indicadorSelectoCategoria = obtenerMejorIndicador(indicadorCasa, indicadorArbol, indicadorPersona);

                if(indicadorSelectoCategoria != null){
                    diagnostico.addItemIndicador(indicadorSelectoCategoria, tipo);
                }
            }
        
        }
        
        this.generarDiagnostico(categorias, diagnostico);
        
        return diagnostico;
    }
    
    /**
     * Método invocado por diagnosticar(...) para generar el diagnóstico por cada tipo de categoría y calcular el porcentaje de rehabilitación.
     * 
     * @param categorias las categorías de indicadores
     * @param diagnostico el diagnóstico actual
     */
    private void generarDiagnostico(List<Categoria> categorias, Diagnostico diagnostico){
        
        String grafProporcion = this.generarDiagnostico(diagnostico, TipoCategoria.Proporcion);
        String grafPerspectiva = this.generarDiagnostico(diagnostico, TipoCategoria.Perspectiva);
        String grafDetalles = this.generarDiagnostico(diagnostico, TipoCategoria.Detalles);
        
        diagnostico.setDiagnosticoProporcion(grafProporcion);
        diagnostico.setDiagnosticoPerspectiva(grafPerspectiva);
        diagnostico.setDiagnosticoDetalles(grafDetalles);                
        
        int nCats = categorias.size();
        int total = nCats * 3;
        int suma = diagnostico.sumarIndicadores();
        
        //Porcentaje de rehabilitacion calculador
        double valor = (suma * 100.0) / total;        
        diagnostico.setPorcentajeRehabilitacionSistema(valor);
    }
    
    /**
     * Genera un diagnóstico para el tipo de categoría indicado.
     * Este método es invocado por generarDiagnostico(...).
     * 
     * @param diagnostico el diagnóstico actual
     * @param tipo el tipo de categoría
     * @return 
     */
    private String generarDiagnostico(Diagnostico diagnostico, TipoCategoria tipo){
        
        List<String> listaSignificaciones = new ArrayList<String>();
        
        List<ItemInterpretacionCategoriaIndicador> lista = null;
        
        switch(tipo){
            case Proporcion: lista = diagnostico.getIndicadoresProporcion(); break;
            case Perspectiva: lista = diagnostico.getIndicadoresPerspectiva(); break;
            case Detalles: lista = diagnostico.getIndicadoresDetalles(); break;
        }
        
        for (Iterator<ItemInterpretacionCategoriaIndicador> it = lista.iterator(); it.hasNext();) {
            ItemInterpretacionCategoriaIndicador item = it.next();
            
            String[] significaciones = item.getIndicador().getSignificaciones().split(";");            
            for (String sig : significaciones) {
                sig = sig.trim();
                sig = replaceEnd(sig, '.',',',':','-','_');
                
                if(sig.length() > 0){
                    if(!listaSignificaciones.contains(sig)){
                        listaSignificaciones.add(sig);
                    }
                }
            }
        }
        
        String diag = "";
        for(Iterator<String> it= listaSignificaciones.iterator(); it.hasNext();){
            diag += it.next() + (it.hasNext() ? "; " : "");
        }
        
        return diag;
    }
    
    /**
     * Obtiene todas las categorías del tipo indicado.
     * 
     * @param lista todas las categorías de la base de datos
     * @param tipo el tipo de categoría a filtrar
     * @return lista de categoria con el tipo indicado
     */
    public List<Categoria> obtenerCategorias(List<Categoria> lista, TipoCategoria tipo){
        List<Categoria> listaF = new ArrayList<Categoria>();
                
        for (Iterator<Categoria> it = lista.iterator(); it.hasNext();) {
            Categoria c = it.next();
            if(c.getTipo().equals(tipo)){
                listaF.add(c);
            }
        }
        
        return listaF;
    }
    
    /**
     * Obtiene el indicador más adecuado de una categoría según los valores seleccionados por el usuario.
     * 
     * @param categoria la categoría
     * @param ic el modelo de la vista
     * @return el indicador
     */
    public ItemInterpretacionCategoriaIndicador obtenerMejorIndicador(Categoria categoria, ItemInterpretacion ic){
        
        ItemInterpretacionCategoria item = obtenerItemCategoria(ic, categoria);
        ItemInterpretacionCategoriaIndicador itemIndicador = null;
        
        if(item != null){
            for(int i= 0; i < item.getIndicadores().size(); i++){
                ItemInterpretacionCategoriaIndicador it = item.getIndicadores().get(i);
                
                if(it.isSeleccionado()){
                    if(itemIndicador == null){
                        itemIndicador = it;
                    }else{
                        itemIndicador = obtenerMayor(itemIndicador, it);
                    }
                }
                
            }
        }
        
        return itemIndicador;
    }
    
    /**
     * Obtiene el mejor indicador entre el conjunto de indicadores según los valores ingresados por el usuario.
     * 
     * @param indicadores el conjunto de indicadores
     * @return un indicador
     */
    public ItemInterpretacionCategoriaIndicador obtenerMejorIndicador(ItemInterpretacionCategoriaIndicador... indicadores){
        
        ItemInterpretacionCategoriaIndicador indicador = null;
        
        if(indicadores != null){
            for (ItemInterpretacionCategoriaIndicador item : indicadores) {
                if(item != null){
                    if(indicador == null){
                        indicador = item;
                    }else{
                        indicador = obtenerMayor(indicador, item);
                    }
                }
            }
        }
        
        return indicador;
    }
    
    /**
     * Obtiene el indicador con mayor peso y en caso de ser iguales obtiene un indicador al hazar.
     * 
     * @param i1 indicador uno
     * @param i2 indicador dos
     * @return el indicador con mayor perso o al hazar
     */
    public ItemInterpretacionCategoriaIndicador obtenerMayor(ItemInterpretacionCategoriaIndicador i1, ItemInterpretacionCategoriaIndicador i2){        
        
        if(i1.getIndicador().getValor() > i2.getIndicador().getValor()){
            return i1;
        }else{
            if(i2.getIndicador().getValor() > i1.getIndicador().getValor()){
                return i2;
            }else{
                //Escojo al hazar
                return obtenerAleatorio(i1, i2);
            }
        }
    }  
    
    /**
     * Si el peso de los dos indicadores es igual, se obtiene uno al hazar.
     * @param i1 indicador uno
     * @param i2 indicador dos
     * @return un indicador al hazar
     */
    public ItemInterpretacionCategoriaIndicador obtenerAleatorio(ItemInterpretacionCategoriaIndicador i1, ItemInterpretacionCategoriaIndicador i2){
        double r = Math.random() * 100;
        if(r >= 50.0){
            return i1;
        }else{
            return i2;
        }
    }
    
    /**
     * Obtiene el item de interpretación de la categoría mediante el item de interpretación y su categoría.
     * 
     * @param itemInt item de interpretación
     * @param categoria la categoría
     * @return el ItemInterpretacionCategoria
     */
    public ItemInterpretacionCategoria obtenerItemCategoria(ItemInterpretacion itemInt, Categoria categoria){
        
        ItemInterpretacionCategoria itemCat = null;
        
        for (Iterator<ItemInterpretacionCategoria> it = itemInt.getCategroias().iterator(); it.hasNext();) {
            
            ItemInterpretacionCategoria item = it.next();
            
            if(item.getCategoria().equals(categoria)){
                itemCat = item;
            }
        }
        
        return itemCat;
    }          
    
    /**
     * Eliminar los caracteres indicados del final de la cadena.
     * 
     * @param cadena la cadena
     * @param ends los caracteres a eliminar
     * @return 
     */
    public String replaceEnd(String cadena, char... ends){
        
        for (char c : ends) {
            while(cadena.endsWith(""+c)){
                cadena = cadena.substring(0, cadena.length()-1);
            }
        }
        
        return cadena;
    }
    
}
