/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.vista.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.cmail.rehabilitacion.vista.model.ReporteResource;
import org.cmail.rehabilitacion.vista.util.ReporUtil;

/**
 *
 * @author Carpio Guarnizo
 */
public class ItemFichaIngreso implements Serializable{
    
    private FichaIngreso fichaIngreso;
    private ReporteResource recurso;
    private boolean selected;

    public ItemFichaIngreso(FichaIngreso fichaIngreso) {
        this.fichaIngreso = fichaIngreso;
    }
    
    public boolean  isRenderedFichaEgreso(){
        if(this.fichaIngreso.getFichaEgreso()==null){
            return true;
        }else{
            return false;
        }            
    }
    
    public boolean  isRenderedTest(){
        if(this.fichaIngreso.getFichaEgreso()==null){
            return true;
        }else{
            return false;
        }            
    }
    
    public boolean  isRenderInformePsicologico(){
        if(this.fichaIngreso.getFichaEgreso()==null){
            return true;
        }else{
            return false;
        }            
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    
    
    public FichaIngreso getFichaIngreso() {
        return fichaIngreso;
    }

    public void setFichaIngreso(FichaIngreso fichaIngreso) {
        this.fichaIngreso = fichaIngreso;
    }

    /**
     * @return the recurso
     */
    public ReporteResource getRecurso() {
        //String realPath = FacesUtils.getExternalContext().getRealPath("/");
        SimpleDateFormat sd = new SimpleDateFormat("EEEEE, dd/MMMMM/yyyy");
        String fecha = sd.format(this.fichaIngreso.getFecha());
        fecha = fecha.replace("/", " de ").toUpperCase();        
        
        
        
        
        recurso = new ReporteResource("fichaIngreso");
        recurso.addParam("PRM_FICHA", this.fichaIngreso);
        recurso.addParam("PRM_LUGAR_DIA_FECHA", this.fichaIngreso.getLugar() + " - " + fecha);
        recurso.addImage("PRM_VISTO", "visto.png");
        
        //recurso.addParam("PRM_HUELLA", ReporUtil.getBufferedImage(this.getFichaIngreso().getHuellaDigital(), 64, 64));
        //recurso.addParam("PRM_FIRMA_RESPONSABLE", ReporUtil.getBufferedImage(this.getFichaIngreso().getFirmaResponsableTraslado(), 64, 64));
        //recurso.addParam("PRM_FIRMA_PERSONA", ReporUtil.getBufferedImage(this.getFichaIngreso().getFirmaPersonaIngresa(), 64, 64));
        
        return recurso;
    }

    /**
     * @param recurso the recurso to set
     */
    public void setRecurso(ReporteResource recurso) {
        this.recurso = recurso;
    }
    
    
    
}
