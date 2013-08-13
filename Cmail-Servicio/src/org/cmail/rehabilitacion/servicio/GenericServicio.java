/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cmail.rehabilitacion.servicio;

import java.util.Date;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.dao.hql.KQuery;
import org.cmail.rehabilitacion.modelo.DomainEntity;
import org.cmail.rehabilitacion.modelo.core.CmailList;



/**
 *
 * @author Desarrollador
 */

public class GenericServicio<T extends DomainEntity> extends  BaseServicio {
    
    //private GanericDao<T> dao;
    
    private Class<T> claseG = null;
    
    public GenericServicio(Class<T> entidadBase) {        
        this.claseG = entidadBase;
    }
    
    public T obtenerPorId( Long id) {
        return super.obtenerPorId(claseG, id);
    }
    
    public KQuery<T> from() {
        return KQuery.from(claseG);
    }

    public boolean existe(DomainEntity entidad, String propiedad, String valor) {
        return super.existe(entidad, propiedad, valor);
    }        
    
    public T obtenerUnicoPor(String propiedad, Object valor){
        return super.obtenerUnicoPor(claseG, propiedad, valor);
    }
        
    public T obtenerPrimerPor(String propiedad, Object valor){
        return super.obtenerPrimerPor(claseG, propiedad, valor);
    }
    
    public CmailList<T> listarPorHql(String hql){
        return super.listarPorHql(claseG, hql);
    }
    
    public CmailList<T> listarTodos(){
        return super.listarTodos(claseG);
    }    
    
    public CmailList<T> listarPorPropiedad(String propiedad, Object valor){
        return super.listarPorPropiedad(claseG, propiedad, valor);
    }        
    
    public CmailList<T> listarPorPropiedadLike(String propiedad, String valor){
        return super.listarPorPropiedadLike(claseG, propiedad, valor);
    }
    
    public CmailList<T> listarPorPropiedadIn(String propiedad, Object... valores){
        return super.listarPorPropiedadIn(claseG, propiedad, valores);
    }   
    
    public CmailList<T> listarPorPropiedadesOr(Object valor, String... propiedades){
        return super.listarPorPropiedadesOr(claseG, valor, propiedades);
    }
    
    public CmailList<T> listarPorPropiedadesValores(KProperty... map){
        return super.listarPorPropiedadesValores(claseG, map);
    }
    
    public CmailList<T> listarPorPropiedadesValoresLike(KProperty... map){
        return super.listarPorPropiedadesValoresLike(claseG, map);
    }
    
    public CmailList<T> listarPorRangoFechas(String propiedad, Date fechaInicial, Date fechaFinal){
        return super.listarPorRangoFechas(claseG, propiedad, fechaInicial, fechaFinal);
    }       
    
    
}