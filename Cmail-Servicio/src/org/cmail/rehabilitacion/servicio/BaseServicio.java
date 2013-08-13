/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cmail.rehabilitacion.servicio;

import java.util.Date;
import org.cmail.rehabilitacion.dao.Dao;
import org.cmail.rehabilitacion.modelo.core.CmailList;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.dao.hql.KQuery;
import org.cmail.rehabilitacion.modelo.DomainEntity;



/**
 *
 * @author Desarrollador
 */

public class BaseServicio  {
    
    private Dao dao = new Dao();    
    
    public BaseServicio() {
    }
    
    public boolean guardar(DomainEntity model){        
         return dao.save(model);
    }
    
    public boolean refrescar(DomainEntity model){
        return dao.refresh(model);
    }
    
    public boolean eliminar(DomainEntity model){        
         return dao.delete(model);
    }       
    
    public <T extends DomainEntity> T obtenerPorId( Class<? extends T> type, Long id) {
        return (T)dao.getById(type, id);
    }
    
    public <T extends DomainEntity> KQuery<T> from( Class<? extends T> type) {
        return KQuery.from(type);
    }
    
    public boolean existe(DomainEntity entidad, String propiedad, String valor){
        return  dao.exists(entidad, propiedad, valor);
    }
    
    public <T extends DomainEntity> T obtenerUnicoPor(Class<? extends T> clase, String propiedad, Object valor){
        return dao.getByUniqueProperty(clase, propiedad, valor);
    }
        
    public <T extends DomainEntity> T obtenerPrimerPor(Class<? extends T> clase, String propiedad, Object valor){
        return dao.getFirstByProperty(clase, propiedad, valor);
    }
    
    public <T extends DomainEntity> CmailList<T> listarTodos(Class<? extends T> clase){
        return dao.getAll(clase);
    }
    
    public <T extends DomainEntity> CmailList<T> listarPorHql(Class<? extends T> clase, String hql){
        return dao.getAllByHql(clase, hql);
    }
    
    public <T extends DomainEntity> CmailList<T> listarPorPropiedad(Class<? extends T> clase, String propiedad, Object valor){
        return dao.getAllByProperty(clase, propiedad, valor);
    }        
    
    public <T extends DomainEntity> CmailList<T> listarPorPropiedadLike(Class<? extends T> clase, String propiedad, String valor){
        return dao.getAllLikeProperty(clase, propiedad, valor);
    }
    
    public <T extends DomainEntity> CmailList<T> listarPorPropiedadIn(Class<? extends T> clase, String propiedad, Object... valores){
        return dao.getAllByInProperty(clase, propiedad, valores);
    }    
    
    public <T extends DomainEntity> CmailList<T> listarPorPropiedadesOr(Class<? extends T> clase, Object valor, String... propiedades){
        return dao.getAllByOrLikePropertys(clase, valor, propiedades);
    }
    
    public <T extends DomainEntity> CmailList<T> listarPorPropiedadesValores(Class<? extends T> clase, KProperty... map){
        return dao.getAllByAndPropertys(clase, map);
    }
    
    public <T extends DomainEntity> CmailList<T> listarPorPropiedadesValoresLike(Class<? extends T> clase, KProperty... map){
        return dao.getAllByAndLikePropertys(clase, map);
    }
    
    public <T extends DomainEntity> CmailList<T> listarPorRangoFechas(Class<? extends T> clase, String propiedad, Date fechaInicial, Date fechaFinal){
        return dao.getAllByDates(clase, propiedad, fechaInicial, fechaFinal);
    }    
    

    
    
}