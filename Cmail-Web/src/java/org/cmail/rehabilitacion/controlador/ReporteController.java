/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.htp.InformePsicologico;
import org.cmail.rehabilitacion.modelo.sira.VwFichaIngreso;
import org.cmail.rehabilitacion.modelo.sira.VwIngresoSalida;
import org.cmail.rehabilitacion.servicio.GenericServicio;
import org.cmail.rehabilitacion.vista.model.ItemInformePsicologico;
import org.cmail.rehabilitacion.vista.model.ReporteResource;

/**
 * Controlador para la generación de reportes.
 * Permite generar los reportes y renderizarlos en un archivo pdf para que el usuario pueda descargarlos.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean (name= Constantes.MB_REPORTE) 
@ViewScoped
public class ReporteController  extends Controller{

    //private String ruta = "";
    
    /** Fecha inicial para reportes con filtros de fechas */
    private Date desde = new Date();
    /** Fecha final para reportes con filtros de fechas */
    private Date hasta = new Date();
    
    /** Recurso para reporte de ingresos */
    private ReporteResource recursoIngreso;
    /** Recurso para reporte de ingresos y salidas*/
    private ReporteResource recursoIngresoSalida;
    
    /** Lista de fichas de ingreso encontradas */
    private List<VwFichaIngreso> listaIngresos = new ArrayList<VwFichaIngreso> ();
    /** Lista de informes psicológicos encontrados */
    private List<ItemInformePsicologico> listaInformes = new ArrayList<ItemInformePsicologico> ();
    /** Lista de ingresos y salidas */
    private List<VwIngresoSalida> listaIngresosSalidas = new ArrayList<VwIngresoSalida> ();
    
    /**Constructor por defecto*/
    public ReporteController() {
    }
    
    /**
     * Evento invocado para generar el reporte de fichas de ingreo
     * @param evt el evento 
     */
    public void eventoBuscarIngresos(ActionEvent evt){
        List<VwFichaIngreso> lst = new GenericServicio<VwFichaIngreso>(VwFichaIngreso.class).listarPorRangoFechas("fecha", desde, hasta);
        listaIngresos = lst;
        
        //SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        
        //ruta = "NewServlet?desde="+ sd.format(desde) + "&hasta="+sd.format(hasta)+"&us="+getUsuarioLogeado().getId().longValue();
        
        /*JavascriptContext.addJavascriptCall(
            FacesContext.getCurrentInstance(),
            "window.open('"+ruta+"', " +
            "'myWindow', " +
            "'width=600," +
            "height=300," +
            "directories=0," +
            "location=0, " +
            "menubar=0, " +
            "resizable=0, " +
            "scrollbars=1, " +
            "status=0, " +
            "toolbar=0');"
        );*/
        
    }
    
    /**
     * Evento invocado para generar el reporte de ingresos y salidas
     * @param evt el evento 
     */
    public void eventoBuscarIngresosSalidas(ActionEvent evt){
        List<VwIngresoSalida> lst = new GenericServicio<VwIngresoSalida>(VwIngresoSalida.class).listarPorRangoFechas("fechaIngreso", desde, hasta);
        listaIngresosSalidas = lst;
    }
    
    /**
     * Evento invocado para generar el reporte de informes psicológicos
     * @param evt el evento 
     */
    public void eventoBuscarInformes(ActionEvent evt){        
        
        List<InformePsicologico> lst = new GenericServicio<InformePsicologico>(InformePsicologico.class).listarPorRangoFechas("fecha", desde, hasta);
        List<ItemInformePsicologico> lista = new ArrayList<ItemInformePsicologico>();
        
        for (Iterator<InformePsicologico> it = lst.iterator(); it.hasNext();) {
            InformePsicologico vw = it.next();
            lista.add(new ItemInformePsicologico(vw, getUsuarioLogeado()));
        }
        
        listaInformes = lista;
    }

    /**
     * @return the desde
     */
    public Date getDesde() {
        return desde;
    }

    /**
     * @param desde the desde to set
     */
    public void setDesde(Date desde) {
        this.desde = desde;
    }

    /**
     * @return the hasta
     */
    public Date getHasta() {
        return hasta;
    }

    /**
     * @param hasta the hasta to set
     */
    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    /**
     * @return the recurso
     */
    public ReporteResource getRecursoIngreso() {
        recursoIngreso = new ReporteResource("ingresoAdolescentes");        
        recursoIngreso.addParam("PRM_DESDE", desde);
        recursoIngreso.addParam("PRM_HASTA", hasta);
        recursoIngreso.addParam("PRM_FECHA", new Date());
        recursoIngreso.addParam("PRM_USUARIO", String.format("%s %s", getUsuarioLogeado().getNombres(), getUsuarioLogeado().getApellidos()));
        recursoIngreso.setDatos(listaIngresos);
        
        return recursoIngreso;
    }

    /**
     * @param recurso the recurso to set
     */
    public void setRecursoIngreso(ReporteResource recurso) {
        this.recursoIngreso = recurso;
    }

    /**
     * @return the listaIngresos
     */
    public List<VwFichaIngreso> getListaIngresos() {
        return listaIngresos;
    }

    /**
     * @param listaIngresos the listaIngresos to set
     */
    public void setListaIngresos(List<VwFichaIngreso> listaIngresos) {
        this.listaIngresos = listaIngresos;
    }

    /**
     * @return the listaInformes
     */
    public List<ItemInformePsicologico> getListaInformes() {
        return listaInformes;
    }

    /**
     * @param listaInformes the listaInformes to set
     */
    public void setListaInformes(List<ItemInformePsicologico> listaInformes) {
        this.listaInformes = listaInformes;
    }

    /**
     * @return the listaIngresosSalidas
     */
    public List<VwIngresoSalida> getListaIngresosSalidas() {
        return listaIngresosSalidas;
    }

    /**
     * @param listaIngresosSalidas the listaIngresosSalidas to set
     */
    public void setListaIngresosSalidas(List<VwIngresoSalida> listaIngresosSalidas) {
        this.listaIngresosSalidas = listaIngresosSalidas;
    }

    /**
     * @return the recursoIngresoSalida
     */
    public ReporteResource getRecursoIngresoSalida() {
        
        recursoIngresoSalida = new ReporteResource("reporteGeneral");        
        recursoIngresoSalida.addParam("PRM_DESDE", desde);
        recursoIngresoSalida.addParam("PRM_HASTA", hasta);
        recursoIngresoSalida.addParam("PRM_FECHA", new Date());
        recursoIngresoSalida.addParam("PRM_USUARIO", String.format("%s %s",getUsuarioLogeado().getNombres(), getUsuarioLogeado().getApellidos()));
        
        recursoIngresoSalida.setDatos(listaIngresosSalidas);
        
        return recursoIngresoSalida;
    }

    /**
     * @param recursoIngresoSalida the recursoIngresoSalida to set
     */
    public void setRecursoIngresoSalida(ReporteResource recursoIngresoSalida) {
        this.recursoIngresoSalida = recursoIngresoSalida;
    }   
    
}
