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
 * Clase de lógica de negocio para manejar los menús del usuario.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class OpcionServicio extends GenericServicio<Opcion> {

    /**
     * Capa de acceso a datos
     */
    private OpcionDao dao = new OpcionDao();
    
    /**
     * Constructor por defecto
     */
    public OpcionServicio() {
        super(Opcion.class);
    }       
    
    /**
     * Obtiene las opciones para el usuario considerando sus perfiles y el menú padre.
     * 
     * @param usuario el usuario
     * @param padre el menú padre
     * @return lista de menús
     */
    public List<VwOpcion> obtenerOpciones(Usuario usuario, VwOpcion padre){
        return dao.obtenerOpciones(usuario, padre);
    }
    
    /**
     * Obtiene todos los menús cuyo padre sea el indicado en el parámetro.
     * 
     * @param padre el menú padre
     * @return lista de menús
     */
    public List<Opcion> obtenerOpciones(Opcion padre){
        return dao.obtenerOpciones(padre);
    }
    
    /**
     * Guarda el menú en la base de datos.
     * 
     * @param model el menú
     * @return true si se guardó
     */
    public boolean guardar(Opcion model) {                          
        List<Perfil> noMarcados = obtenerNoMarcados(model);
        model.getPerfiles().removeAll(noMarcados);
        boolean b = dao.guardar(model, noMarcados);
        if(!b){
            model.getPerfiles().addAll(noMarcados);
        }
        return b;
    }
    
    /**
     * Elimina un menú de la base de datos
     * @param model el menú
     * @return  true si se eliminó
     */
    public boolean eliminar(Opcion model) {                          
        return dao.eliminar(model);
    }
    
    /**
     * Obtiene un listado de perfiles que no han sido seleccionados al editar o crear una opción.
     * 
     * @param model la opción
     * @return la lista de perfiles
     */
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
    
    /**
     * Vericia si las opciones hijas contienen los perfiles no seleccionados del padre con la finalidad de eliminar los perfiles tanto de la opción padre como de sus hijas. 
     * 
     * @param model la opción padre
     * @return lista de perfiles
     */
    public Set<Perfil> verificarPerfilesHijos(Opcion model) {        
        List<Perfil> lst = obtenerNoMarcados(model);
        Set<Perfil> pfs = new HashSet<Perfil>();
        
        verificarPerfilesHijos(model, lst, pfs);
        
        return pfs;
    }
    
    /**
     * Método recursivo para verificar si las opciones hijas contienen los perfiles no seleccionados de la opción padre.
     * 
     * @param instancia la opción actual
     * @param removed los perfiles removidos
     * @param pfs los perfiles removidos encontrados
     */
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
