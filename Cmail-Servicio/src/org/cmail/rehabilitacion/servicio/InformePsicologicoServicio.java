/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.List;
import org.cmail.rehabilitacion.dao.GanericDao;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.modelo.htp.InformePsicologico;
import org.cmail.rehabilitacion.modelo.htp.TestHtp;

/**
 *
 * @author Usuario
 */
public class InformePsicologicoServicio extends GenericServicio<InformePsicologico> {

    private GanericDao<InformePsicologico> dao;
    
    public InformePsicologicoServicio() {
        super(InformePsicologico.class);
        dao = new GanericDao<InformePsicologico>(InformePsicologico.class);
    }
    
    public boolean guardarInforme(InformePsicologico informe) {        
        return dao.save(informe);        
    }    
    
    public List<TestHtp> listarInformes(String cedula, String nombres, String apellidos) {        
        return super.listarPorPropiedadesValoresLike(
            new KProperty("adolescente.cedula", cedula),
            new KProperty("adolescente.nombres", nombres),
            new KProperty("adolescente.apellidos", apellidos)
        );
    }
    
    
    
    
    
}