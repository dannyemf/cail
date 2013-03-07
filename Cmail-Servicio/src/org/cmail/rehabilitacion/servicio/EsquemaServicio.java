/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.List;
import org.cmail.rehabilitacion.dao.GanericDao;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.modelo.sira.Esquema;

/**
 *
 * @author Usuario
 */
public class EsquemaServicio extends GenericServicio<Esquema> {

    private GanericDao<Esquema> dao;
    
    public EsquemaServicio() {
        super(Esquema.class);
        dao = new GanericDao<Esquema>(Esquema.class);
    }
    
    public boolean guardarEsquema(Esquema esquema) {        
        return dao.save(esquema);        
    }

    public List<Esquema> listarEsquemas(String cedula, String nombres, String apellidos) {        
        return super.listarPorPropiedadesValoresLike(
            new KProperty("adolescente.cedula", cedula),
            new KProperty("adolescente.nombres", nombres),
            new KProperty("adolescente.apellidos", apellidos)
        );
    }

    public Esquema crearNueva() {
        Esquema esquema = new Esquema();        
        return esquema;
    }
}