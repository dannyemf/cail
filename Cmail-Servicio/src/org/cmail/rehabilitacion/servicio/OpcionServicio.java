/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.cmail.rehabilitacion.dao.OpcionDao;
import org.cmail.rehabilitacion.modelo.seguridad.Opcion;
import org.cmail.rehabilitacion.modelo.seguridad.Perfil;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.modelo.seguridad.VwOpcion;

/**
 *
 * @author Usuario
 */
public class OpcionServicio extends GenericServicio<Opcion> {

    private OpcionDao dao = new OpcionDao();
    
    public OpcionServicio() {
        super(Opcion.class);
    }       
        
    public List<VwOpcion> obtenerOpciones(Usuario usuario, VwOpcion padre){
        return dao.obtenerOpciones(usuario, padre);
    }
    
    public List<Opcion> obtenerOpciones(Opcion padre){
        return dao.obtenerOpciones(padre);
    }
    
    public boolean guardar(Opcion model) {                          
        List<Perfil> noMarcados = obtenerNoMarcados(model);
        model.getPerfiles().removeAll(noMarcados);
        boolean b = dao.guardar(model, noMarcados);
        if(!b){
            model.getPerfiles().addAll(noMarcados);
        }
        return b;
    }
    
    public boolean eliminar(Opcion model) {                          
        return dao.eliminar(model);
    }
    
    
    public List<Perfil> obtenerNoMarcados(Opcion model){
        
        List<Perfil> lst = new ArrayList<Perfil>();
        
        for (Iterator<Perfil> it = model.getPerfiles().iterator(); it.hasNext();) {
            Perfil perfil = it.next();
            if( perfil.isSeleccionado() == false){
                lst.add(perfil);
            }
        }
        
        return lst;
        
    }
    
    public Set<Perfil> verificarPerfilesHijos(Opcion model) {        
        List<Perfil> lst = obtenerNoMarcados(model);
        Set<Perfil> pfs = new HashSet<Perfil>();
        
        verificarPerfilesHijos(model, lst, pfs);
        
        return pfs;
    }
    
    private void verificarPerfilesHijos(Opcion instancia, List<Perfil> removed, Set<Perfil> pfs) {
        List<Opcion> lst = dao.obtenerOpciones(instancia);
        for (Iterator<Opcion> it = lst.iterator(); it.hasNext();) {
            Opcion op = it.next();
            
            for (Iterator<Perfil> it1 = op.getPerfiles().iterator(); it1.hasNext();) {
                Perfil p = it1.next();
                if(removed.contains(p)){
                    pfs.add(p);
                }
            }            
            verificarPerfilesHijos(op, removed, pfs);            
        }                
    }
}
