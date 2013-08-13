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
 * Entidad que representa un log de auditoría, registrando la acción, la fecha, el usuario, etc.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class AuditoriaEntidad extends DomainEntity{
        
    /**
     * Constante de la acción de creación
     */
    public static final String ACCION_CREACION = "CREACION";
    
    /**
     * Constante de la acción de actualización
     */
    public static final String ACCION_ACTUALIZACION = "ACTUALIZACION";
    
    /**
     * Constante de la acción de eliminación
     */
    public static final String ACCION_ELIMINACION = "ELIMINACION";
    
    /**
     * Id de la entidad auditada
     */
    private Long entidadId;
    
    /**
     * Nombre de la entidad auditada
     */
    private String entidadNombre;
    
    /**
     * Clase de la entidad auditada
     */
    private String entidadClase;
    
    /**
     * Valor de una de las constantes ACCION_CREACION, ACCION_ACTUALIZACION o ACCION_ELIMINACION
     */
    private String accion;
    
    /**
     * Login del usuario que ha realizó la acción
     */
    private String usuario;
    
    /**
     * Fecha en que se realizó la acción
     */
    private Date fecha;
    
    /**
     * Colección de las propiedades o atributos auditados
     */
    private Set<AuditoriaPropiedad> propiedades = new HashSet<AuditoriaPropiedad>();
    
    /**
     * La entidad auditada
     */
    private DomainEntity entidad;

    /**
     * Constructor mínimo
     */
    public AuditoriaEntidad() {
    }
    
    /**
     * Constructor completo
     * @param entidad la entidad a auditar
     * @param accion la acción realizada
     * @param usuario el usuario que está realizando la operación
     * @param propiedades las propiedades a auditar
     * @param currentState el estado actual de la entidad
     */
    public AuditoriaEntidad(DomainEntity entidad, final String accion, final String usuario, String[] propiedades, Object[] currentState) {
        this(entidad, accion, usuario, propiedades, currentState, currentState);        
    }

    /**
     * Constructor completo
     * @param entidad la entidad a auditar
     * @param accion la acción realizada
     * @param usuario el usuario que está realizando la operación
     * @param propiedades las propiedades a auditar
     * @param previousState el estado anterior de la entidad
     * @param currentState el estado actual de la entidad
     */
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
    
    /**
     * Inicializa el id de la entidad
     */
    public void initId(){
        this.entidadId = entidad.getId();
    }
    
    /**
     * Obtiene un string representativo de la entidad.
     * 
     * @param object el objeto del que se debe obtener el string
     * @return la cadena
     */
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
    
    /**
     * Agrega una propiedad al listado de propiedades
     * @param propiedad el nombre de la propiedad
     * @param object el valor de la propiedad
     * @param estado el estado de la propiedad
     */
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
