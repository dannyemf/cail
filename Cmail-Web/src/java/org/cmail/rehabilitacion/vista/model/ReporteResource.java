/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cmail.rehabilitacion.vista.model;

import com.icesoft.faces.context.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.seguridad.TipoParametro;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.servicio.ParametroServicio;
import org.cmail.rehabilitacion.vista.util.FacesUtils;
import org.cmail.rehabilitacion.vista.util.ReporUtil;



/**
 * Clase de recurso usada para crear los reportes pdf y descargarlos.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class ReporteResource implements Resource, Serializable{

    //protected final Logger log = Logger.getLogger(ReporUtil.class);
    
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private String plantilla;
    private Date modified = new Date();
    private Usuario usuario;
    private List datos;

    public ReporteResource(String plantilla) {
        this.plantilla = plantilla;
        this.initParams();
    }
    
    private void initParams(){
        ParametroServicio app = new ParametroServicio();
        usuario = FacesUtils.getSessionBean().getUsuarioLogeado();
        
        addParam("PRM_USUARIO", usuario);
        addParam("PRM_INSTITUCION", app.obtenerValor(Constantes.PRM_INSTITUCION,TipoParametro.Cadena));
        addParam("PRM_REAL_PATH", FacesUtils.getExternalContext().getRealPath("/"));
        
        addImage("PRM_LOGO", "logo-reporte.png");
    }
    
    public void addParam(String name, Object valor){
        parametros.put(name, valor);
    }
    
    public void addImage(String name, String imagen){
        String rp = FacesUtils.getExternalContext().getRealPath("/");
        String img = rp + ReporUtil.PATH_IMAGEN + imagen;
        
        //log.info("AddImage: "+img);
        
        parametros.put(name, img);
    }
    
    @Override
    public String calculateDigest() {
        return "";
    }

    @Override
    public InputStream open() throws IOException {
        byte[] data = ReporUtil.getReportData(parametros, datos, plantilla);
        return new ByteArrayInputStream(data);        
    }

    @Override
    public Date lastModified() {
        return modified;
    }

    public Date getModified() {
        return modified;
    }        

    @Override
    public void withOptions(Options optns) throws IOException {}

    /**
     * @return the parametros
     */
    public Map<String, Object> getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    /**
     * @return the plantilla
     */
    public String getPlantilla() {
        return plantilla;
    }

    /**
     * @param plantilla the plantilla to set
     */
    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

    /**
     * @return the datos
     */
    public List getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(List datos) {
        this.datos = datos;
    }
    
}
