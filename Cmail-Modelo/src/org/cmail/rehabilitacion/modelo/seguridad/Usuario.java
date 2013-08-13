package org.cmail.rehabilitacion.modelo.seguridad;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.cmail.rehabilitacion.modelo.AuditEntity;

/**
 * Usuario entity. 
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */

public class Usuario extends AuditEntity {

	// Fields    	
	private String cedula = "0000000000";
        private String nombres = "";
	private String apellidos = "";
	private String login = "";
	private String clave = "";	
	private boolean activo = true;
        private String localidad = "";
        private String temaUi = "start";
        
        private Date fechaRegistro = new Date();        
        private Set<Perfil> perfiles = new HashSet<Perfil> ();
	// Constructors

	/** default constructor */
	public Usuario() {
	}

	/** minimal constructor */
	public Usuario(String cedula, String login, String clave,
			String nombres, String apellidos, Boolean isActivo) {
		this.cedula = cedula;
		this.login = login;
		this.clave = clave;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.activo = isActivo;
	}
        
        
        public boolean addPerfil(Perfil perfil){
        return this.perfiles.add(perfil);
    }
    
    public boolean removePerfil(Perfil perfil){
        return this.perfiles.remove(perfil);
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }    
    
    public String getCedula() {
            return this.cedula;
    }

    public void setCedula(String cedula) {
            this.cedula = cedula;
    }

    public String getLogin() {
            return this.login;
    }

    public void setLogin(String login) {
            this.login = login;
    }

    public String getClave() {
            return this.clave;
    }

    public void setClave(String clave) {
            this.clave = clave;
    }

    public String getNombres() {
            return this.nombres;
    }

    public String toFullName() {
            return this.nombres + " " + this.apellidos ; 
    }

    public void setNombres(String nombres) {
            this.nombres = nombres;
    }

    public String getApellidos() {
            return this.apellidos;
    }

    public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(Set<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", login=" + login + ", nombres=" + nombres + ", apellidos=" + apellidos + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || (obj != null && obj instanceof Usuario == false)){
            return false;
        }else{        
            Usuario p = (Usuario)obj;
            if(p.getId().longValue() == this.getId().longValue()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    /**
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * @param localidad the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * @return the temaUi
     */
    public String getTemaUi() {
        return temaUi;
    }

    /**
     * @param temaUi the temaUi to set
     */
    public void setTemaUi(String temaUi) {
        this.temaUi = temaUi;
    }
}