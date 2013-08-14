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
 * Clase de lógica de negocio para manejar fichas de ingreso.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0Usuario
 */
public class FichaIngresoServicio extends GenericServicio<FichaIngreso> {

    /**Capa de acceso a datos*/
    private FichaIngresoDao dao;
    
    /**
     * Constructor por defecto
     */
    public FichaIngresoServicio() {
        super(FichaIngreso.class);
        dao = new FichaIngresoDao();
    }    

    /**
     * Guarda la ficha de ingreso
     * @param ficha la ficha
     * @return true si se guardó
     */
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

    /**
     * Lista las fichas de ingreso donde por lo menos un atributo del adolescente contenga el valor respectivo.
     * 
     * @param cedula la cédula
     * @param nombres los nombres
     * @param apellidos los apellidos
     * @return lista de fichas de ingreso
     */
    public List<FichaIngreso> listarFichas(String cedula, String nombres, String apellidos) {        
        return super.listarPorPropiedadesValoresLike(
            new KProperty("adolescente.cedula", cedula),
            new KProperty("adolescente.nombres", nombres),
            new KProperty("adolescente.apellidos", apellidos)
        );
    }

    /**
     * Crea una nueva ficha de ingreso e la inicializa con valores por defecto.
     * 
     * @param adolescente el adolescente
     * @return la ficha de ingreso
     */
    public FichaIngreso crearNueva(Persona adolescente) {
        FichaIngreso fichaIngreso = new FichaIngreso();
        fichaIngreso.setAdolescente(adolescente); 
        
        //Valores posibles a cambio
        fichaIngreso.fijarDatosAdolescente();
        
        return fichaIngreso;
    }
    
    /**
     * Genera una descripción de la razón de ingreso del adolescente tomando en cuenta los atributos booleanos que dicatan tal motivo.
     * 
     * @param fichaIngreso la ficha de ingreso
     * @return una cadena
     */
    public String generarRazonIngreso(FichaIngreso fichaIngreso){
        String motivo = "";
        
        if (fichaIngreso.isRazInMedidaCautelar()){
            motivo += "Medida cautelar (";
            if (fichaIngreso.isRazInMcRetencion()){
                motivo += "Retención; ";
            }
            if (fichaIngreso.isRazInMcInvestigacion()){
                motivo += "Investigación; ";
            }
            if (fichaIngreso.isRazInMcComparecencia()){
                motivo += "Asegurar comparescencia; ";
            }
            if (fichaIngreso.isRazInMcIntPreventivo()){
                motivo += "Internamiento preventivo; ";
            } 
            motivo += ")";
            
            motivo = motivo.replace("; )", ")");
        }
        
        if (fichaIngreso.isRazInMedidaSocioeducativa()){
            motivo += "Medida socioeducativa (";
            if (fichaIngreso.isRazInMsIntFinSemana()){
                motivo += "Internamiento de fin de semana; ";
            }
            if (fichaIngreso.isRazInMsIntSemiLibertad()){
                motivo += "Internamiento régimen de semilibertad; ";
            }
            if (fichaIngreso.isRazInMsIntInstitucional()){
                motivo += "Internamiento institucional; ";
            }
            if (fichaIngreso.isRazInMsLibAsistida()){
                motivo += "Libertad asistida; ";
            }
            if (fichaIngreso.isRazInMsServComunidad()){
                motivo += "Servicio a la comunidad; ";
            }
            if (fichaIngreso.isRazInMsRepDanoCausado()){
                motivo += "Reparación del daño causado; ";
            }
            if (fichaIngreso.isRazInMsOrientaApoyoFamiliar()){
                motivo += "Orientación y apoyo familiar; ";
            }
            if (fichaIngreso.isRazInMsAmonestacionImposicion()){
                motivo += "Amonestación e imposición de reglas de conducta; ";
            }
            if (fichaIngreso.isRazInMsAmonestacion()){
                motivo += "Amonestación; ";
            }
            motivo += ")";
            
            motivo = motivo.replace("; )", ")");
        }
        
        if (fichaIngreso.isRazInReingreso()){
            motivo += "Reingreso (";
            if (fichaIngreso.isRazInRiMedidaCautelar()){
                motivo += "Medida cautelar; ";
            }
            if (fichaIngreso.isRazInRiMedidaSocioeducativa()){
                motivo += "Medida socioeducativa; ";
            }
            if (fichaIngreso.isRazInRiOtroTipo()){
                motivo += fichaIngreso.getRazInRiOtroTipoNombre() + "; ";
            }
            motivo += ")";
            
            motivo = motivo.replace("; )", ")");
        }
        
        return motivo;
    }
}
