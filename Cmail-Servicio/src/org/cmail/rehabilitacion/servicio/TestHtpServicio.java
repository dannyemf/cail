/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.Date;
import java.util.List;
import org.cmail.rehabilitacion.dao.GanericDao;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.modelo.core.DateUtils;
import org.cmail.rehabilitacion.modelo.htp.TestHtp;

/**
 *
 * @author Usuario
 */
public class TestHtpServicio extends GenericServicio<TestHtp> {

    private GanericDao<TestHtp> dao;
    
    public TestHtpServicio() {
        super(TestHtp.class);
        dao = new GanericDao<TestHtp>(TestHtp.class);
    }
    
    public boolean guardarFormualario(TestHtp esquema) {
        
        if(esquema.getId().longValue() <= 0){            
            esquema.setHoraFin(new Date());
            int duracionMinutos = (int)DateUtils.diferenciaMinutos(esquema.getHoraInicio(), esquema.getHoraFin());
            esquema.setDuracionMinutos(duracionMinutos);
        }
        
        return dao.save(esquema);        
    }        
    
    public List<TestHtp> listarTest(String cedula, String nombres, String apellidos) {        
        return super.listarPorPropiedadesValoresLike(
            new KProperty("adolescente.cedula", cedula),
            new KProperty("adolescente.nombres", nombres),
            new KProperty("adolescente.apellidos", apellidos)
        );
    }   
    
}