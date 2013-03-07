/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.ArrayList;
import java.util.Iterator;
import org.cmail.rehabilitacion.excepcion.ActivoException;
import org.cmail.rehabilitacion.excepcion.ClaveException;
import org.cmail.rehabilitacion.excepcion.LoginException;
import org.cmail.rehabilitacion.modelo.seguridad.Perfil;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;

/**
 *
 * @author Usuario
 */
public class UsuarioServicio extends GenericServicio<Usuario> {

    public UsuarioServicio() {
        super(Usuario.class);
    }
    
    /**
     * 
     * @param login
     * @param clave
     * @return
     * @throws Exception 
     */
    public Usuario logear(String login, String clave) throws Exception {
        Usuario p =  super.obtenerUnicoPor("login", login);
        if (p!=null) {                                      
                if (p.getClave().equals(clave)) {               
                    if (p.isActivo()) { 
                        return p;
                    }else{
                        throw new ActivoException();
                    }
                }else{
                    throw new ClaveException();
                }            
        }else{
            throw new LoginException();
        }        
    }
    
    public boolean guardar(Usuario model, Usuario userLogeado) {
        
        ArrayList<Perfil> prfs = new ArrayList<Perfil>(model.getPerfiles());
        
        for (Iterator<Perfil> it = prfs.iterator(); it.hasNext();) {
            Perfil perfil = it.next();
            if(! perfil.isSeleccionado()){
                model.getPerfiles().remove(perfil);                        
            }
        }
        
        return super.guardar(model);
    }
    
    
}
