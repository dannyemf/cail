/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.bean;

import org.cmail.rehabilitacion.modelo.core.Constantes;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.cmail.rehabilitacion.vista.util.MensajeBundleUtil;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.htp.Categoria;
import org.cmail.rehabilitacion.modelo.htp.TestHtp;
import org.cmail.rehabilitacion.modelo.localizacion.Canton;
import org.cmail.rehabilitacion.modelo.localizacion.Parroquia;
import org.cmail.rehabilitacion.modelo.localizacion.Provincia;
import org.cmail.rehabilitacion.modelo.seguridad.Opcion;
import org.cmail.rehabilitacion.modelo.seguridad.Parametro;
import org.cmail.rehabilitacion.modelo.seguridad.Perfil;
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;
import org.cmail.rehabilitacion.modelo.sira.Agenda;
import org.cmail.rehabilitacion.modelo.sira.ArticuloWeb;
import org.cmail.rehabilitacion.modelo.sira.Esquema;
import org.cmail.rehabilitacion.modelo.sira.Evento;
import org.cmail.rehabilitacion.modelo.sira.FichaEgreso;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;
import org.cmail.rehabilitacion.vista.model.TipoNotificacion;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = Constantes.MB_SESSION)
@SessionScoped
public class SessionBean implements Serializable {

    private String fecha;
    private SimpleDateFormat formatFecha = new SimpleDateFormat("dd/mm/yyyy");
    private Usuario usuarioLogeado;
    private Usuario usuarioEdicion;
    private Opcion opcionEdicion;
    private Perfil perfilEdicion;
    private FichaIngreso fichaIngresoEdicion;
    private Esquema esquemaEdicion;
    private TestHtp formHtpEdicion;
    private FichaEgreso fichaEgresoEdicion;
    private Agenda agendaEdicion;
    private Evento actividad;
    private Map<String, Object> sessionMap;
    private ArrayList itemsEstadoCivil;
    private ArrayList itemsSexo;
    private Provincia provinciaEdicion;
    private Canton cantonEdicion;
    private Parroquia parroquiaEdicion;
    private ArticuloWeb parrafoEdicion;
   
    private Categoria categoriaEdicion;
    private Parametro parametroEdicion;
    
    private Persona personaEdicion;
    
    private String mensajeNotificacion;
    private String tipoNotificacion = TipoNotificacion.Aviso.toString();

    /** Creates a new instance of SessionBean */
    public SessionBean() {
        sessionMap = new HashMap<String, Object>();
    }
    
    public void clearMsjNotificacion(){
        mensajeNotificacion = "";
    }

    public void addSessionMap(String nombre, Object instancia) {
        sessionMap.put(nombre, instancia);
    }

    public Object getSessionMap(String nombre) {
        if (sessionMap.containsKey(nombre)) {
            return sessionMap.get(nombre);
        }
        return null;
    }

    public void remSessionMap(String nombre) {
        if (sessionMap.containsKey(nombre)) {
            sessionMap.remove(nombre);
        }
    }

    /**
     * @return the usuarioLogeado
     */
    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    /**
     * @param usuarioLogeado the usuarioLogeado to set
     */
    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
        FacesUtils.getStyleBean().setAceTheme(usuarioLogeado.getTemaUi());
    }

    /**
     * @return the usuarioEdicion
     */
    public Usuario getUsuarioEdicion() {
        return usuarioEdicion;
    }

    /**
     * @param usuarioEdicion the usuarioEdicion to set
     */
    public void setUsuarioEdicion(Usuario usuarioEdicion) {
        this.usuarioEdicion = usuarioEdicion;
    }

    /**
     * @return the opcionEdicion
     */
    public Opcion getOpcionEdicion() {
        return opcionEdicion;
    }

    /**
     * @param opcionEdicion the opcionEdicion to set
     */
    public void setOpcionEdicion(Opcion opcionEdicion) {
        this.opcionEdicion = opcionEdicion;
    }

    /**
     * @return the perfilEdicion
     */
    public Perfil getPerfilEdicion() {
        return perfilEdicion;
    }

    /**
     * @param perfilEdicion the perfilEdicion to set
     */
    public void setPerfilEdicion(Perfil perfilEdicion) {
        this.perfilEdicion = perfilEdicion;
    }

    public String getFecha() {
        fecha = formatFecha.format(new Date());
        return fecha;
    }

    public Agenda getAgendaEdicion() {
        return agendaEdicion;
    }

    public void setAgendaEdicion(Agenda agendaEdicion) {
        this.agendaEdicion = agendaEdicion;
    }

    /**
     * @return the actividad
     */
    public Evento getActividad() {
        return actividad;
    }

    /**
     * @param actividad the actividad to set
     */
    public void setActividad(Evento actividad) {
        this.actividad = actividad;
    }

    /**
     * @return the fichaIngresoEdicion
     */
    public FichaIngreso getFichaIngresoEdicion() {
        return fichaIngresoEdicion;
    }

    /**
     * @param fichaIngresoEdicion the fichaIngresoEdicion to set
     */
    public void setFichaIngresoEdicion(FichaIngreso fichaIngresoEdicion) {
        this.fichaIngresoEdicion = fichaIngresoEdicion;
    }

    /**
     * @return the fichaEgresoEdicion
     */
    public FichaEgreso getFichaEgresoEdicion() {
        return fichaEgresoEdicion;
    }

    /**
     * @param fichaEgresoEdicion the fichaEgresoEdicion to set
     */
    public void setFichaEgresoEdicion(FichaEgreso fichaEgresoEdicion) {
        this.fichaEgresoEdicion = fichaEgresoEdicion;
    }

    /**
     * @return the itemsEstadoCivil
     */
    public ArrayList getItemsEstadoCivil() {
        itemsEstadoCivil = new ArrayList();
        itemsEstadoCivil.add(new SelectItem("Soltero", "Soltero"));
        itemsEstadoCivil.add(new SelectItem("Casado", "Casado"));
        itemsEstadoCivil.add(new SelectItem("Viudo", "Viudo"));
        itemsEstadoCivil.add(new SelectItem("Divorciado", "Divorciado"));
        itemsEstadoCivil.add(new SelectItem("Unión Libre", "Unión Libre"));
        return itemsEstadoCivil;
    }

    /**
     * @param itemsEstadoCivil the itemsEstadoCivil to set
     */
    public void setItemsEstadoCivil(ArrayList itemsEstadoCivil) {
        this.itemsEstadoCivil = itemsEstadoCivil;
    }

    /**
     * @return the itemsSexo
     */
    public ArrayList getItemsSexo() {
        itemsSexo = new ArrayList();
        itemsSexo.add(new SelectItem("M", "Maculino"));        
        itemsSexo.add(new SelectItem("F", "Femenino"));        
        return itemsSexo;
    }
    
    public void notificar(TipoNotificacion tipo, String mensaje) {
        this.mensajeNotificacion = mensaje;
        this.tipoNotificacion = tipo.toString();
    }

    /**
     * @param itemsSexo the itemsSexo to set
     */
    public void setItemsSexo(ArrayList itemsSexo) {
        this.itemsSexo = itemsSexo;
    }

    /**
     * @return the provinciaEdicion
     */
    public Provincia getProvinciaEdicion() {
        return provinciaEdicion;
    }

    /**
     * @param provinciaEdicion the provinciaEdicion to set
     */
    public void setProvinciaEdicion(Provincia provinciaEdicion) {
        this.provinciaEdicion = provinciaEdicion;
    }

    /**
     * @return the cantonEdicion
     */
    public Canton getCantonEdicion() {
        return cantonEdicion;
    }

    /**
     * @param cantonEdicion the cantonEdicion to set
     */
    public void setCantonEdicion(Canton cantonEdicion) {
        this.cantonEdicion = cantonEdicion;
    }

    /**
     * @return the parroquiaEdicion
     */
    public Parroquia getParroquiaEdicion() {
        return parroquiaEdicion;
    }

    /**
     * @param parroquiaEdicion the parroquiaEdicion to set
     */
    public void setParroquiaEdicion(Parroquia parroquiaEdicion) {
        this.parroquiaEdicion = parroquiaEdicion;
    }

    /**
     * @return the parrafoEdicion
     */
    public ArticuloWeb getParrafoEdicion() {
        return parrafoEdicion;
    }

    /**
     * @param parrafoEdicion the parrafoEdicion to set
     */
    public void setParrafoEdicion(ArticuloWeb parrafoEdicion) {
        this.parrafoEdicion = parrafoEdicion;
    }

    /**
     * @return the esquemaEdicion
     */
    public Esquema getEsquemaEdicion() {
        return esquemaEdicion;
    }

    /**
     * @param esquemaEdicion the esquemaEdicion to set
     */
    public void setEsquemaEdicion(Esquema esquemaEdicion) {
        this.esquemaEdicion = esquemaEdicion;
    }

    /**
     * @return the formHtpEdicion
     */
    public TestHtp getFormHtpEdicion() {
        return formHtpEdicion;
    }

    /**
     * @param formHtpEdicion the formHtpEdicion to set
     */
    public void setFormHtpEdicion(TestHtp formHtpEdicion) {
        this.formHtpEdicion = formHtpEdicion;
    }
   

    /**
     * @return the categoriaEdicion
     */
    public Categoria getCategoriaEdicion() {
        return categoriaEdicion;
    }

    /**
     * @param categoriaEdicion the categoriaEdicion to set
     */
    public void setCategoriaEdicion(Categoria categoriaEdicion) {
        this.categoriaEdicion = categoriaEdicion;
    }

    /**
     * @return the parametroEdicion
     */
    public Parametro getParametroEdicion() {
        return parametroEdicion;
    }

    /**
     * @param parametroEdicion the parametroEdicion to set
     */
    public void setParametroEdicion(Parametro parametroEdicion) {
        this.parametroEdicion = parametroEdicion;
    }

    public Persona getPersonaEdicion() {
        return personaEdicion;
    }

    public void setPersonaEdicion(Persona personaEdicion) {
        this.personaEdicion = personaEdicion;
    }

    public String getMensajeNotificacion() {
        return mensajeNotificacion;
    }

    public void setMensajeNotificacion(String mensajeNotificacion) {
        this.mensajeNotificacion = mensajeNotificacion;
    }

    public String getTipoNotificacionLocale() {
        return MensajeBundleUtil.getMensaje("tipoNotificacion"+tipoNotificacion);
    }
    
    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }
    
    
    
}
