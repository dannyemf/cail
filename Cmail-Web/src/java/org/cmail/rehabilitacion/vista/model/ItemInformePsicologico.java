/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.vista.model;

import java.io.Serializable;
import org.cmail.rehabilitacion.modelo.htp.InformePsicologico;

/**
 *
 * @author Usuario
 */
public class ItemInformePsicologico implements Serializable{
    
    private InformePsicologico informe;
    private ReporteResource recurso;

    public ItemInformePsicologico(InformePsicologico informe) {
        this.informe = informe;
    }
    
    /**
     * @return the recurso
     */
    public ReporteResource getRecurso() {
        
        
        recurso = new ReporteResource("informePsicologico");
        recurso.addParam("PRM_MODEL", this.getInforme());                
        
        return recurso;
    }

    /**
     * @param recurso the recurso to set
     */
    public void setRecurso(ReporteResource recurso) {
        this.recurso = recurso;
    }

    public InformePsicologico getInforme() {
        return informe;
    }

    public void setInforme(InformePsicologico informe) {
        this.informe = informe;
    }
    
    
    
}
