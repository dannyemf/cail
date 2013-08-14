/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.vista.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import org.cmail.rehabilitacion.modelo.sira.FichaEgreso;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class ItemFichaEgreso implements Serializable{
    
    private FichaEgreso ficha;
    private ReporteResource recurso;

    public ItemFichaEgreso(FichaEgreso ficha) {
        this.ficha = ficha;
    }
    
    /**
     * @return the recurso
     */
    public ReporteResource getRecurso() {
        //String realPath = FacesUtils.getExternalContext().getRealPath("/");
        SimpleDateFormat sd = new SimpleDateFormat("EEEEE, dd/MMMMM/yyyy");
        String fecha = sd.format(this.getFicha().getFecha());
        fecha = fecha.replace("/", " de ").toUpperCase();
        
        recurso = new ReporteResource("fichaEgreso");
        recurso.addParam("PRM_FICHA", this.getFicha());
        recurso.addParam("PRM_LUGAR_DIA_FECHA", this.getFicha().getLugar() + " - " + fecha);
        recurso.addImage("PRM_VISTO", "visto.png");
        
        return recurso;
    }

    /**
     * @param recurso the recurso to set
     */
    public void setRecurso(ReporteResource recurso) {
        this.recurso = recurso;
    }

    /**
     * @return the ficha
     */
    public FichaEgreso getFicha() {
        return ficha;
    }

    /**
     * @param ficha the ficha to set
     */
    public void setFicha(FichaEgreso ficha) {
        this.ficha = ficha;
    }
    
    
    
}
