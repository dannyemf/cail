/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo;

/**
 *
 * @author Usuario
 */
public class AuditoriaPropiedad extends DomainEntity{
    
    public static final String ESTADO_INVARIANTE = "INVARIANTE";
    public static final String ESTADO_ELIMINADO = "ELIMINADO";
    public static final String ESTADO_ACTUALIZADO = "ACTUALIZADO";
    public static final String ESTADO_CREADO = "CREADO";
    
    private String nombre;    
    private String valor;
    private String estado;
    private AuditoriaEntidad auditoria;
            
    public AuditoriaPropiedad() {
    }

    public AuditoriaPropiedad(String nombre, String valor, String estado, AuditoriaEntidad auditoria) {
        this.nombre = nombre;        
        this.valor = valor;
        this.estado = estado;
        this.auditoria = auditoria;
    }
    
    

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }  

    /**
     * @return the valorActual
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valorActual the valorActual to set
     */
    public void setValor(String valorActual) {
        this.valor = valorActual;
    }

    /**
     * @return the auditoria
     */
    public AuditoriaEntidad getAuditoria() {
        return auditoria;
    }

    /**
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaEntidad auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    
}
