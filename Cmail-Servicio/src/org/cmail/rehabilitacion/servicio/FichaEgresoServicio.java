/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.List;
import org.cmail.rehabilitacion.dao.Dao;
import org.cmail.rehabilitacion.modelo.sira.FichaEgreso;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;

/**
 *
 * @author Usuario
 */
public class FichaEgresoServicio extends GenericServicio<FichaEgreso> {

    private Dao dao = null;
    
    public FichaEgresoServicio() {
        super(FichaEgreso.class);
        dao = new Dao();
    }
    
    public boolean guardar(FichaEgreso egreso){
        boolean b = false;
        try {
            dao.beginTransaction();
                        
            dao.saveOnTx(egreso);
            
            //Fijo el idFichaIngreso
            egreso.getFichaIngreso().setFichaEgreso(egreso);
            
            dao.saveOnTx(egreso.getFichaIngreso());
            
            
            dao.commit();       
            b = true;
        } catch (Exception e) {
            dao.rollback();
        }
        
        return b;
    }

    //comprueba si ya existe una persona con la cedula enviada y
    //y determina si es otra persona que se esta editando
    public boolean existePersonaByCedula(String cedula, Persona persona) {
        boolean existe = false;
        if (persona != null) {
            Persona persona_aux = (Persona) obtenerUnicoPor(Persona.class, "cedula", cedula);
            if (persona_aux != null && (!persona.getId().equals(persona_aux.getId()))) {
                existe = true;
            }
        }
        return existe;
    }
    
    public List<FichaEgreso> listarFichas(String cedula, String nombres, String apellidos) {
        return super.listarPorPropiedadesValoresLike(
                new KProperty("adolescente.cedula", cedula),
                new KProperty("adolescente.nombres", nombres),
                new KProperty("adolescente.apellidos", apellidos));
    }

    public FichaEgreso crearNueva() {
        FichaEgreso fichaEgreso = new FichaEgreso();
        fichaEgreso.setAdolescente(new Persona());
        fichaEgreso.getAdolescente().setPadre(new Persona());
        fichaEgreso.getAdolescente().setMadre(new Persona());
        return fichaEgreso;
    }
}
