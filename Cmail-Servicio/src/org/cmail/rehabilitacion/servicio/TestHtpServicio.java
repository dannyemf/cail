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
 * Clase de lógica de negocio para manejar test's htp.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class TestHtpServicio extends GenericServicio<TestHtp> {

    /**Capa de acceso a datos*/
    private GanericDao<TestHtp> dao;
    
    /**
     * Constructor por defecto
     */
    public TestHtpServicio() {
        super(TestHtp.class);
        dao = new GanericDao<TestHtp>(TestHtp.class);
    }
    
    /**
     * Guarda el test htp
     * @param test el test htp
     * @return true si se guardó correctamente
     */
    public boolean guardarFormualario(TestHtp test) {
        
        if(test.getId().longValue() <= 0){            
            test.setHoraFin(new Date());
            int duracionMinutos = (int)DateUtils.diferenciaMinutos(test.getHoraInicio(), test.getHoraFin());
            test.setDuracionMinutos(duracionMinutos);
        }
        
        return dao.save(test);        
    }        
    
    /**
     * Lista todos los test's htp donde por los menos uno de los atributos (cédula, nombres, apellidos) del adolescente contenga el valor correspondiente.
     * @param cedula la cédula
     * @param nombres los nombres
     * @param apellidos los apellidos
     * @return  lista de test htp
     */
    public List<TestHtp> listarTest(String cedula, String nombres, String apellidos) {        
        return super.listarPorPropiedadesValoresLike(
            new KProperty("adolescente.cedula", cedula),
            new KProperty("adolescente.nombres", nombres),
            new KProperty("adolescente.apellidos", apellidos)
        );
    }   
    
}