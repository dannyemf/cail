/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.vista.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.cmail.rehabilitacion.modelo.AuditEntity;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Modelo de datos personalizado para la presentación de resultado en tablas.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class CmailListDataModel<E> extends ListDataModel<E> implements Serializable{

    private List<E> lista = new ArrayList<E>();
    
    /**Constructor por defecto*/
    public CmailListDataModel() {
        super();
    }

    public CmailListDataModel(List<E> list) {
        super(list);
        lista = list;
    }
    
    public CmailListDataModel(Collection<E> list) {
        super(new ArrayList<E>(list));
        lista = new ArrayList<E>();
    }
    
    public List<E> getData(){
        return new ArrayList<E>(lista);
    }

    @Override
    public E getRowData() {       
        E e = super.getRowData();
        if(e != null){
            if(e instanceof AuditEntity){
                AuditEntity de = (AuditEntity)e;
                Usuario us = FacesUtils.getSessionBean().getUsuarioLogeado();
                if(us != null){
                    de.setAuditUpdateUser(us.getLogin());
                }                
            }
        }
        return e;
    }
    
    public CmailListDataModel<E> remove(E elemento){
        List<E> lst = getData();
        lst.remove(elemento);
        return new CmailListDataModel<E>(lst);
    }
    
}
