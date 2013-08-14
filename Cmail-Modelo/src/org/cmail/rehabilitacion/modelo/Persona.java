/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.cmail.rehabilitacion.modelo.localizacion.Canton;
import org.cmail.rehabilitacion.modelo.localizacion.Parroquia;
import org.cmail.rehabilitacion.modelo.localizacion.Provincia;

/**
 * Entidad que representa a una persona, siendo ésta un empleado, un adolescente, un padre de un adolescente, etc.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class Persona extends AuditEntity implements Cloneable{
    
    public static final String SEXO_MASCULINO= "M";
    public static final String SEXO_FEMENINO= "F";        
    
    private String cedula;
    private String nombres;
    private String apellidos;
    
    private String direccion;    
    private String telefono;
    private String celular;
    private String email;
    private String sexo = SEXO_MASCULINO;
    private String ocupacion;
    //revisar este atributo
    private String parentesco;
    
    private Date fechaNacimiento = new Date();
    private Date fechaRegistro = new Date();
    
    private Persona padre;
    private Persona madre;

    
    private Provincia provinciaNacimiento;
    private Canton cantonNacimiento;
    private Parroquia parroquiaNacimiento;
    private String estadoCivil;    
    private String cargo;
    
    /**
     * Roles: EMPLEADO,ADOLESCENTE
     */
    private String roles;
    
    /**
     * Constructor por defecto
     */
    public Persona() {
        roles = "";
    }
    
    /**
     * Obtiene la edad de una persona en años.
     * 
     * @return la edad
     */
    public int calcularEdad(){
        
        Date fa = new Date();
        Date fn = this.fechaNacimiento;
        
        SimpleDateFormat fd = new SimpleDateFormat("dd");
        SimpleDateFormat fm = new SimpleDateFormat("MM");
        SimpleDateFormat fy = new SimpleDateFormat("yyyy");
                
        int da = Integer.parseInt(fd.format(fa));
        int dn = Integer.parseInt(fd.format(fn));
        
        int ma = Integer.parseInt(fm.format(fa));
        int mn = Integer.parseInt(fm.format(fn));
                
        int ya = Integer.parseInt(fy.format(fa));
        int yn = Integer.parseInt(fy.format(fn));
        
        
        int ed = 0,  em = 0, ey = 0;
        
        //n - f (fecha actual - fecha nacimiento)
        if(da < dn){
            da = da + 30;
            mn = mn + 1;
        }
        ed = da - dn;
        
        if(ma < mn){
            ma = ma + 12;
            yn = yn + 1;
        }
        em = ma - mn;
        
        ey = ya - yn;
        
        return ey;
    }
    
    /**
     * Agrega un rol a la persona.
     * 
     * @param rol el rol agregar
     */
    public void addRol(PersonaRol rol){
        if(roles == null){
            roles = "";
        }
        
        if(rol != null && roles.contains(rol.name()) == false){
            roles = roles.length() > 0 ? roles + ";" + rol.name() : rol.name();
        }
    }    
    
    /**
     * Verifica si tiene el rol empleado
     * @return true si tiene el rol
     */
    public boolean isEmpleado(){
        return roles.contains(PersonaRol.EMPLEADO.name());
    }
    
    /**
     * Verifica si tiene el rol adolescente
     * @return true si tiene el rol
     */
    public boolean isAdolescente(){
        return roles.contains(PersonaRol.ADOLESCENTE.name());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the padre
     */
    public Persona getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(Persona padre) {
        this.padre = padre;
    }

    /**
     * @return the madre
     */
    public Persona getMadre() {
        return madre;
    }

    /**
     * @param madre the madre to set
     */
    public void setMadre(Persona madre) {
        this.madre = madre;
    }

    /**
     * @return the ocupacion
     */
    public String getOcupacion() {
        return ocupacion;
    }

    /**
     * @param ocupacion the ocupacion to set
     */
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    /**
     * @return the parentesco
     */
    public String getParentesco() {
        return parentesco;
    }

    /**
     * @param parentesco the parentesco to set
     */
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    @Override
    public String toString() {
        return "Persona{" + "cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", celular=" + celular + ", ocupacion=" + ocupacion + '}';
    }
      
    @Override
    public boolean equals(Object obj) {
        if (obj != null || obj instanceof Persona) {
            Persona p = (Persona) obj;
            if (this.getId() != null && p.getId() != null && this.getId().longValue() == p.getId().longValue()) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }   

    /**
     * @return the estadoCivil
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * @param estadoCivil the estadoCivil to set
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * @return the provinciaNacimiento
     */
    public Provincia getProvinciaNacimiento() {
        return provinciaNacimiento;
    }

    /**
     * @param provinciaNacimiento the provinciaNacimiento to set
     */
    public void setProvinciaNacimiento(Provincia provinciaNacimiento) {
        this.provinciaNacimiento = provinciaNacimiento;
    }

    /**
     * @return the cantonNacimiento
     */
    public Canton getCantonNacimiento() {
        return cantonNacimiento;
    }

    /**
     * @param cantonNacimiento the cantonNacimiento to set
     */
    public void setCantonNacimiento(Canton cantonNacimiento) {
        this.cantonNacimiento = cantonNacimiento;
    }

    /**
     * @return the parroquiaNacimiento
     */
    public Parroquia getParroquiaNacimiento() {
        return parroquiaNacimiento;
    }

    /**
     * @param parroquiaNacimiento the parroquiaNacimiento to set
     */
    public void setParroquiaNacimiento(Parroquia parroquiaNacimiento) {
        this.parroquiaNacimiento = parroquiaNacimiento;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the roles
     */
    public String getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }    
}
