/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.vista.model;

import java.io.Serializable;
import java.util.Date;
import org.cmail.rehabilitacion.modelo.htp.InformePsicologico;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;

/**
 * Clase de modelo para la vista para presentar los informes psicológicos.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class ItemInformePsicologico implements Serializable{
    
    private InformePsicologico informe;
    private ReporteResource recurso;
    private Usuario responsable;

    public ItemInformePsicologico(InformePsicologico informe, Usuario responsable) {
        this.informe = informe;
        this.responsable = responsable;
    }
    
    /**
     * @return the recurso
     */
    public ReporteResource getRecurso() {
        
        
        recurso = new ReporteResource("informePsicologico");
        recurso.addParam("PRM_MODEL", this.getInforme());
        recurso.addParam("PRM_FECHA", new Date());
        recurso.addParam("PRM_USUARIO", String.format("%s %s", responsable.getNombres(), responsable.getApellidos()));
        
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

    /**
     * @return the responsable
     */
    public Usuario getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }
    
    
    
}
