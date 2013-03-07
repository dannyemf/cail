/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.List;
import org.cmail.rehabilitacion.dao.FichaIngresoDao;
import org.cmail.rehabilitacion.dao.hql.KProperty;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.PersonaRol;
import org.cmail.rehabilitacion.modelo.sira.FichaIngreso;

/**
 *
 * @author Usuario
 */
public class FichaIngresoServicio extends GenericServicio<FichaIngreso> {

    private FichaIngresoDao dao;
    
    public FichaIngresoServicio() {
        super(FichaIngreso.class);
        dao = new FichaIngresoDao();
    }

    //comprueba si ya existe una persona con la cedula enviada y
    //y determina si es otra persona que se esta editando
    public boolean existePersonaByCedula(String cedula, Persona persona) {
        boolean existe = false;
        if (persona != null) {
            Persona persona_aux = (Persona) obtenerUnicoPor(Persona.class, "cedula", cedula);
            if (persona_aux != null && (!persona.getId().equals(persona_aux.getId()))) {
                existe = true;
            }
        }
        return existe;
    }

    public boolean guardarFicha(FichaIngreso ficha) {
        
        //if(ficha.getId().longValue() <= 0){
            ficha.setEdad(ficha.getAdolescente().calcularEdad());
            ficha.setEstadoCivil(ficha.getAdolescente().getEstadoCivil());
            ficha.setCedulado(ficha.getAdolescente().getCedula().trim().length() > 0);
            ficha.getAdolescente().addRol(PersonaRol.ADOLESCENTE);
        //}
        
        ficha.setRazonIngreso(this.generarRazonIngreso(ficha));
            
        return dao.save(ficha);        
    }
    

    //busca personas extendiendo de la super BaseServicio
    /*public List<Persona> listarPersonas(String cedula, String nombres, String apellidos) {
        return listarTodosLike2(Persona.class, "cedula", cedula, "nombres", nombres, "apellidos", apellidos);
    }*/

    /*public Persona buscarPersonaByCedula(String cedula) {
        List<Persona> listaPersonas = getByPropiedad(Persona.class, "cedula", cedula);
        if (listaPersonas != null && !listaPersonas.isEmpty()) {
            return listaPersonas.get(0);
        } else {
            //revisar
            return new Persona();
        }

    }*/

    public List<FichaIngreso> listarFichas(String cedula, String nombres, String apellidos) {        
        return super.listarPorPropiedadesValoresLike(
            new KProperty("adolescente.cedula", cedula),
            new KProperty("adolescente.nombres", nombres),
            new KProperty("adolescente.apellidos", apellidos)
        );
    }

    public FichaIngreso crearNueva(Persona adolescente) {
        FichaIngreso fichaIngreso = new FichaIngreso();
        fichaIngreso.setAdolescente(adolescente); 
        
        //Valores posibles a cambio
        fichaIngreso.fijarDatosAdolescente();
        
        return fichaIngreso;
    }
    
    public String generarRazonIngreso(FichaIngreso fi){
        String motivo = "";
        
        if (fi.isRazInMedidaCautelar()){
            motivo += "Medida cautelar (";
            if (fi.isRazInMcRetencion()){
                motivo += "Retención; ";
            }
            if (fi.isRazInMcInvestigacion()){
                motivo += "Investigación; ";
            }
            if (fi.isRazInMcComparecencia()){
                motivo += "Asegurar comparescencia; ";
            }
            if (fi.isRazInMcIntPreventivo()){
                motivo += "Internamiento preventivo; ";
            } 
            motivo += ")";
            
            motivo = motivo.replace("; )", ")");
        }
        
        if (fi.isRazInMedidaSocioeducativa()){
            motivo += "Medida socioeducativa (";
            if (fi.isRazInMsIntFinSemana()){
                motivo += "Internamiento de fin de semana; ";
            }
            if (fi.isRazInMsIntSemiLibertad()){
                motivo += "Internamiento régimen de semilibertad; ";
            }
            if (fi.isRazInMsIntInstitucional()){
                motivo += "Internamiento institucional; ";
            }
            if (fi.isRazInMsLibAsistida()){
                motivo += "Libertad asistida; ";
            }
            if (fi.isRazInMsServComunidad()){
                motivo += "Servicio a la comunidad; ";
            }
            if (fi.isRazInMsRepDanoCausado()){
                motivo += "Reparación del daño causado; ";
            }
            if (fi.isRazInMsOrientaApoyoFamiliar()){
                motivo += "Orientación y apoyo familiar; ";
            }
            if (fi.isRazInMsAmonestacionImposicion()){
                motivo += "Amonestación e imposición de reglas de conducta; ";
            }
            if (fi.isRazInMsAmonestacion()){
                motivo += "Amonestación; ";
            }
            motivo += ")";
            
            motivo = motivo.replace("; )", ")");
        }
        
        if (fi.isRazInReingreso()){
            motivo += "Reingreso (";
            if (fi.isRazInRiMedidaCautelar()){
                motivo += "Medida cautelar; ";
            }
            if (fi.isRazInRiMedidaSocioeducativa()){
                motivo += "Medida socioeducativa; ";
            }
            if (fi.isRazInRiOtroTipo()){
                motivo += fi.getRazInRiOtroTipoNombre() + "; ";
            }
            motivo += ")";
            
            motivo = motivo.replace("; )", ")");
        }
        
        return motivo;
    }
}
