/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 *
 * @author Usuario
 */
public class AuditoriaEntidad extends DomainEntity{
        
    
    public static final String ACCION_CREACION = "CREACION";
    public static final String ACCION_ACTUALIZACION = "ACTUALIZACION";
    public static final String ACCION_ELIMINACION = "ELIMINACION";
    
    private Long entidadId;
    private String entidadNombre;
    private String entidadClase;
    private String accion;
    private String usuario;
    private Date fecha;
    private Set<AuditoriaPropiedad> propiedades = new HashSet<AuditoriaPropiedad>();
    
    private DomainEntity entidad;

    public AuditoriaEntidad() {
    }
    
    public AuditoriaEntidad(DomainEntity entidad, final String accion, final String usuario, String[] propiedades, Object[] currentState) {
        this(entidad, accion, usuario, propiedades, currentState, currentState);        
    }

    public AuditoriaEntidad(DomainEntity entidad, final String accion, final String usuario, String[] propiedades,  Object[] previousState, Object[] currentState) {
        this.setId(-1L);
        this.usuario = usuario;
        this.fecha = new Date();
        this.accion = accion;
        this.entidadNombre = entidad.getClass().getSimpleName();
        this.entidadClase = entidad.getClass().getCanonicalName();
        this.entidad = entidad;
        
        for(int i = 0; i < propiedades.length; i++){
            if(accion.equals(ACCION_ACTUALIZACION)){
                if(previousState != null && previousState[i] != null){
                    if(previousState[i].equals(currentState[i]) == false){
                        this.add(propiedades[i], currentState[i],AuditoriaPropiedad.ESTADO_ACTUALIZADO);
                    }else{
                        //this.add(propiedades[i], currentState[i],AuditoriaPropiedad.ESTADO_INVARIANTE);
                    }
                }else{
                    if(currentState[i] != null){
                        if(previousState != null && currentState[i].equals(previousState[i]) == false){
                            this.add(propiedades[i], currentState[i], AuditoriaPropiedad.ESTADO_ACTUALIZADO);
                        }else{
                            //this.add(propiedades[i], currentState[i],AuditoriaPropiedad.ESTADO_INVARIANTE);
                        }
                    }
                }                
            }else{
                if(accion.equals(ACCION_CREACION)){
                    this.add(propiedades[i], currentState[i], AuditoriaPropiedad.ESTADO_CREADO);
                }else{
                    if(accion.equals(ACCION_ELIMINACION)){
                        this.add(propiedades[i], currentState[i], AuditoriaPropiedad.ESTADO_ELIMINADO);
                    }
                }
            }            
        }
        
    }
    
    public void initId(){
        this.entidadId = entidad.getId();
    }
    
    public String getString(Object object){
        String valor = null;
        
        if(object != null){
            if(object instanceof String || object instanceof Number || object instanceof Boolean){
                valor = object.toString();
            }else{
                if(object instanceof Date){
                    valor = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format((Date)object);
                }else{
                    if(object instanceof DomainEntity){
                        valor = ((DomainEntity)object).getId().toString();
                    }else{
                        if(object instanceof Set){                    
                            Set<DomainEntity> coll = (Set<DomainEntity>)object;
                            for (Iterator<DomainEntity> it = coll.iterator(); it.hasNext();) {                        
                                DomainEntity d = it.next();
                                if(valor == null){
                                    valor = "[";
                                }

                                valor += d.getId().toString();
                                if(it.hasNext()) valor += ";";
                            }
                            valor += "]";
                        }else{
                            valor = object.toString();
                        }
                    }
                }
            }
        }
        
        return valor;
    }
    
    public void add(String propiedad, Object object, String estado){
        String valor = getString(object);        
        AuditoriaPropiedad p = new AuditoriaPropiedad(propiedad, valor, estado, this);        
        this.propiedades.add(p);
    }

    /**
     * @return the entidadId
     */
    public Long getEntidadId() {
        return entidadId;
    }

    /**
     * @param entidadId the entidadId to set
     */
    public void setEntidadId(Long entidadId) {
        this.entidadId = entidadId;
    }

    /**
     * @return the entidadNombre
     */
    public String getEntidadNombre() {
        return entidadNombre;
    }

    /**
     * @param entidadNombre the entidadNombre to set
     */
    public void setEntidadNombre(String entidadNombre) {
        this.entidadNombre = entidadNombre;
    }

    /**
     * @return the entidadClase
     */
    public String getEntidadClase() {
        return entidadClase;
    }

    /**
     * @param entidadClase the entidadClase to set
     */
    public void setEntidadClase(String entidadClase) {
        this.entidadClase = entidadClase;
    }

    /**
     * @return the accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the propiedades
     */
    public Set<AuditoriaPropiedad> getPropiedades() {
        return propiedades;
    }

    /**
     * @param propiedades the propiedades to set
     */
    public void setPropiedades(Set<AuditoriaPropiedad> propiedades) {
        this.propiedades = propiedades;
    }
    
    
}
