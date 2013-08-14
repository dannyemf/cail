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
 * Clase de lógica de negocio para manejar los informes psicológicos.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class InformePsicologicoServicio extends GenericServicio<InformePsicologico> {

    /**Capa genérica de acceso a datos*/
    private GanericDao<InformePsicologico> dao;
    
    /**
     * Constructor por defecto
     */
    public InformePsicologicoServicio() {
        super(InformePsicologico.class);
        dao = new GanericDao<InformePsicologico>(InformePsicologico.class);
    }
    
    /**
     * Guarda el informe psicológico
     * @param informe el infome
     * @return true si se guardó
     */
    public boolean guardarInforme(InformePsicologico informe) {        
        return dao.save(informe);        
    }    
    
    /**
     * Lista los informes psicológicos donde por lo menos un atributo del adolescente contenga el valor respectivo.
     * @param cedula la cédula
     * @param nombres los nombres
     * @param apellidos los apellidos
     * @return lista de informes
     */
    public List<TestHtp> listarInformes(String cedula, String nombres, String apellidos) {        
        return super.listarPorPropiedadesValoresLike(
            new KProperty("adolescente.cedula", cedula),
            new KProperty("adolescente.nombres", nombres),
            new KProperty("adolescente.apellidos", apellidos)
        );
    }

}