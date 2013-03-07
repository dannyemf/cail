/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import org.cmail.rehabilitacion.modelo.core.CmailList;
import org.cmail.rehabilitacion.modelo.localizacion.Canton;
import org.cmail.rehabilitacion.modelo.localizacion.Parroquia;
import org.cmail.rehabilitacion.modelo.localizacion.Provincia;

/**
 *
 * @author Usuario
 */
public class DivisionPoliticaServicio extends BaseServicio {

    public DivisionPoliticaServicio() {        
    }
    
    public CmailList<Provincia> listarProvincias(){
        return super.listarTodos(Provincia.class);
    }
    
    public CmailList<Canton> listarCantones(){
        return super.listarTodos(Canton.class);
    }
    
    public CmailList<Parroquia> listarParroquias(){
        return super.listarTodos(Parroquia.class);
    }
    
    
    
}
