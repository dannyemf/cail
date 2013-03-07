/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.sira;

import java.util.Date;
import org.cmail.rehabilitacion.modelo.AuditEntity;
import org.cmail.rehabilitacion.modelo.Persona;

/**
 *
 * @author Desarrollador
 */
//FICHA DE EGRESO  DE ADOLESCENTES
public class FichaEgreso extends AuditEntity {

    private FichaIngreso fichaIngreso = null;
    private Persona adolescente;
    private String lugar;
    private Date fecha = new Date(); // habrá que obtener el día y la fecha del egreso, mas la hora
    private String tiempoPermanencia; // tiempo de permanencia en el centro
    /**1. RAZÓN DE EGRESO
     * raz ==> razon
     * Eg ==> Egreso
     * Cad ==> caducidad     
     * Int ==> internamiento
     * Ret ==> Retención
     * Res ==> Resolución
     */
    private boolean razEgCadRetencion;//1.1 Caducidad de la retención    
    private boolean razEgTermMedida;//1.2 Terminación del tiempo de la medidad socioeducativa    
    private boolean razEgCadIntPreventivo;//1.3 Caducidad del internamiento preventivo
    private boolean razEgCadRetInvestigacion;//1.4 Caducidad de la retención para investigación
    private boolean razEgCadRetComparecencia;//1.5  Caducidad de la retención para comparecencia
    private boolean razEgDefuncion;//1.6 Defunción
    private boolean razEgResJusdicial;//1.7 Resolución Judicial
    private boolean razEgEvasionFuga;//1.8 Evasión y fuga
    private boolean razEgTrasferencia;//1.9 Trasferencia
    private boolean razEgEmbarazo;//1.10 Embarazo
    //2. AUTORIZACIÓN DEL EGRESO
    // docs = documentos
    // Aut = Autoriza
    //2.1 Nombre y cargo de la persona que autoriza el egreso
    private Persona autorizaEgreso;
//    private String personaAutEgreso;
    //2.2 Documento/s  que avaliza/n la autorización para el egreso del CAI(Adjunte)
    private String docsEgreso;
    //3. ACTITUD DEL O LA ADOLESCENTE
    //3.1 Aceptación de salir
    // exp = explicación
//    private boolean aceptacionSalida;
    private boolean aceptacionSalida;
    private String expAceptacionSalida;
    //3.2 Aceptación de irse con la persona autorizada para llevarle
//    private boolean aceptacionPersonaSalida;
    private boolean aceptacionPersonaSalida;
    private String expAceptacionPersonaSalida;
    //3.3 Persona con la cual egresa
    //eg = egresa
    private boolean egAmbosProgenitores;
    private boolean egPadre;
    private boolean egMadre;
    private boolean egRepresentante;
    private boolean egHermano;
    private boolean egHermana;
    private boolean egAbuelo;
    private boolean egAbuela;
    private boolean egAmbosAbuelos;
    private boolean egTios;
    private boolean egPareja;
    private boolean egSolo;
    private boolean egOtros;
    private String relacionEgOtros;
    //4. CONDICIONES DE EGRESO DEL O LA ADOLESCENTE
    /**
     * cond = condiciones
     * Fis = fisicas
     * Emo = emocionales
     * Salud = salubridad
     * Eg = Egreso
     */
    //4.1 Físicas
    // 
    private boolean condFisEgPerfectas;
    private boolean condFisEgLastimados;
    private boolean condFisEgMoretones;
    //4.2 Emocionales
    private boolean condEmoEgTranquilo;
    private boolean condEmoEgAgresivo;
    private boolean condEmoEgExtrovertido;
    private boolean condEmoEgIntrovertido;
    private boolean condEmoEgConfinado;
    private boolean condEmoEgAsustado;
    private boolean condEmoEgAlegre;
    private boolean condEmoEgOtro;
    private String condEmoEgOtroNombre;
    //4.3 Salubridad
    private String condSaludEgTEnfermedad;  // Tipo de Enfermedad
    private String condSaludEgMedicinas; // Medicinas que toma
    //5. PERTENENCIAS CON LAS QUE EGRESA (Describa)
    //5.1 Descripción
    // desc = descripción
    private String descPertenencias;
    //6. DATOS DE REFERENCIA DEL O LA ADOLESCENTE
    //6.1 Con quién vivirá (nombre-parentesco)
    private String personaQueVivira;
    //6.2 Dirección y teléfono de referencia del lugar donde vivirá.
    //Ref = referencia
    private String direccionTelefonoRef;
    //6.3 Mapa de referencia
//    private String mapaRef;
    //7. RESPONSABLE DEL EGRESO
    //Eg = egreso
    private Persona responsableEgreso;
//    private String nombreResponsableEg;
//    private String cargoResponsableEg;
//    private String firmaResponsableEg;
    //8. PERSONA CON LA QUE EGRESA EL O LA ADOLESCENTE
    //pe = persona que egresa
    
    private Persona companeroEgreso;
    private String parentescoCompaneroEgreso;
    // por completar la ficha

    public FichaEgreso() {
    }

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
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
     * @return the tiempoPermanencia
     */
    public String getTiempoPermanencia() {
        return tiempoPermanencia;
    }

    /**
     * @param tiempoPermanencia the tiempoPermanencia to set
     */
    public void setTiempoPermanencia(String tiempoPermanencia) {
        this.tiempoPermanencia = tiempoPermanencia;
    }

    /**
     * @return the razEgCadRetencion
     */
    public boolean isRazEgCadRetencion() {
        return razEgCadRetencion;
    }

    /**
     * @param razEgCadRetencion the razEgCadRetencion to set
     */
    public void setRazEgCadRetencion(boolean razEgCadRetencion) {
        this.razEgCadRetencion = razEgCadRetencion;
    }

    /**
     * @return the razEgTermMedida
     */
    public boolean isRazEgTermMedida() {
        return razEgTermMedida;
    }

    /**
     * @param razEgTermMedida the razEgTermMedida to set
     */
    public void setRazEgTermMedida(boolean razEgTermMedida) {
        this.razEgTermMedida = razEgTermMedida;
    }

    /**
     * @return the razEgCadIntPreventivo
     */
    public boolean isRazEgCadIntPreventivo() {
        return razEgCadIntPreventivo;
    }

    /**
     * @param razEgCadIntPreventivo the razEgCadIntPreventivo to set
     */
    public void setRazEgCadIntPreventivo(boolean razEgCadIntPreventivo) {
        this.razEgCadIntPreventivo = razEgCadIntPreventivo;
    }

    /**
     * @return the razEgCadRetInvestigacion
     */
    public boolean isRazEgCadRetInvestigacion() {
        return razEgCadRetInvestigacion;
    }

    /**
     * @param razEgCadRetInvestigacion the razEgCadRetInvestigacion to set
     */
    public void setRazEgCadRetInvestigacion(boolean razEgCadRetInvestigacion) {
        this.razEgCadRetInvestigacion = razEgCadRetInvestigacion;
    }

    /**
     * @return the razEgCadRetComparecencia
     */
    public boolean isRazEgCadRetComparecencia() {
        return razEgCadRetComparecencia;
    }

    /**
     * @param razEgCadRetComparecencia the razEgCadRetComparecencia to set
     */
    public void setRazEgCadRetComparecencia(boolean razEgCadRetComparecencia) {
        this.razEgCadRetComparecencia = razEgCadRetComparecencia;
    }

    /**
     * @return the razEgDefuncion
     */
    public boolean isRazEgDefuncion() {
        return razEgDefuncion;
    }

    /**
     * @param razEgDefuncion the razEgDefuncion to set
     */
    public void setRazEgDefuncion(boolean razEgDefuncion) {
        this.razEgDefuncion = razEgDefuncion;
    }

    /**
     * @return the razEgResJusdicial
     */
    public boolean isRazEgResJusdicial() {
        return razEgResJusdicial;
    }

    /**
     * @param razEgResJusdicial the razEgResJusdicial to set
     */
    public void setRazEgResJusdicial(boolean razEgResJusdicial) {
        this.razEgResJusdicial = razEgResJusdicial;
    }

    /**
     * @return the razEgEvasionFuga
     */
    public boolean isRazEgEvasionFuga() {
        return razEgEvasionFuga;
    }

    /**
     * @param razEgEvasionFuga the razEgEvasionFuga to set
     */
    public void setRazEgEvasionFuga(boolean razEgEvasionFuga) {
        this.razEgEvasionFuga = razEgEvasionFuga;
    }

    /**
     * @return the razEgTrasferencia
     */
    public boolean isRazEgTrasferencia() {
        return razEgTrasferencia;
    }

    /**
     * @param razEgTrasferencia the razEgTrasferencia to set
     */
    public void setRazEgTrasferencia(boolean razEgTrasferencia) {
        this.razEgTrasferencia = razEgTrasferencia;
    }

    /**
     * @return the razEgEmbarazo
     */
    public boolean isRazEgEmbarazo() {
        return razEgEmbarazo;
    }

    /**
     * @param razEgEmbarazo the razEgEmbarazo to set
     */
    public void setRazEgEmbarazo(boolean razEgEmbarazo) {
        this.razEgEmbarazo = razEgEmbarazo;
    }

    /**
     * @return the docsEgreso
     */
    public String getDocsEgreso() {
        return docsEgreso;
    }

    /**
     * @param docsEgreso the docsEgreso to set
     */
    public void setDocsEgreso(String docsEgreso) {
        this.docsEgreso = docsEgreso;
    }

    /**
     * @return the aceptacionSalida
     */
    public boolean isAceptacionSalida() {
        return aceptacionSalida;
    }

    /**
     * @param aceptacionSalida the aceptacionSalida to set
     */
    public void setAceptacionSalida(boolean aceptacionSalida) {
        this.aceptacionSalida = aceptacionSalida;
    }

    /**
     * @return the expAceptacionSalida
     */
    public String getExpAceptacionSalida() {
        return expAceptacionSalida;
    }

    /**
     * @param expAceptacionSalida the expAceptacionSalida to set
     */
    public void setExpAceptacionSalida(String expAceptacionSalida) {
        this.expAceptacionSalida = expAceptacionSalida;
    }

    /**
     * @return the aceptacionPersonaSalida
     */
    public boolean isAceptacionPersonaSalida() {
        return aceptacionPersonaSalida;
    }

    /**
     * @param aceptacionPersonaSalida the aceptacionPersonaSalida to set
     */
    public void setAceptacionPersonaSalida(boolean aceptacionPersonaSalida) {
        this.aceptacionPersonaSalida = aceptacionPersonaSalida;
    }

    /**
     * @return the expAceptacionPersonaSalida
     */
    public String getExpAceptacionPersonaSalida() {
        return expAceptacionPersonaSalida;
    }

    /**
     * @param expAceptacionPersonaSalida the expAceptacionPersonaSalida to set
     */
    public void setExpAceptacionPersonaSalida(String expAceptacionPersonaSalida) {
        this.expAceptacionPersonaSalida = expAceptacionPersonaSalida;
    }

    /**
     * @return the egAmbosProgenitores
     */
    public boolean isEgAmbosProgenitores() {
        return egAmbosProgenitores;
    }

    /**
     * @param egAmbosProgenitores the egAmbosProgenitores to set
     */
    public void setEgAmbosProgenitores(boolean egAmbosProgenitores) {
        this.egAmbosProgenitores = egAmbosProgenitores;
    }

    /**
     * @return the egPadre
     */
    public boolean isEgPadre() {
        return egPadre;
    }

    /**
     * @param egPadre the egPadre to set
     */
    public void setEgPadre(boolean egPadre) {
        this.egPadre = egPadre;
    }

    /**
     * @return the egMadre
     */
    public boolean isEgMadre() {
        return egMadre;
    }

    /**
     * @param egMadre the egMadre to set
     */
    public void setEgMadre(boolean egMadre) {
        this.egMadre = egMadre;
    }

    /**
     * @return the egRepresentante
     */
    public boolean isEgRepresentante() {
        return egRepresentante;
    }

    /**
     * @param egRepresentante the egRepresentante to set
     */
    public void setEgRepresentante(boolean egRepresentante) {
        this.egRepresentante = egRepresentante;
    }

    /**
     * @return the egHermano
     */
    public boolean isEgHermano() {
        return egHermano;
    }

    /**
     * @param egHermano the egHermano to set
     */
    public void setEgHermano(boolean egHermano) {
        this.egHermano = egHermano;
    }

    /**
     * @return the egHermana
     */
    public boolean isEgHermana() {
        return egHermana;
    }

    /**
     * @param egHermana the egHermana to set
     */
    public void setEgHermana(boolean egHermana) {
        this.egHermana = egHermana;
    }

    /**
     * @return the egAbuelo
     */
    public boolean isEgAbuelo() {
        return egAbuelo;
    }

    /**
     * @param egAbuelo the egAbuelo to set
     */
    public void setEgAbuelo(boolean egAbuelo) {
        this.egAbuelo = egAbuelo;
    }

    /**
     * @return the egAbuela
     */
    public boolean isEgAbuela() {
        return egAbuela;
    }

    /**
     * @param egAbuela the egAbuela to set
     */
    public void setEgAbuela(boolean egAbuela) {
        this.egAbuela = egAbuela;
    }

    /**
     * @return the egAmbosAbuelos
     */
    public boolean isEgAmbosAbuelos() {
        return egAmbosAbuelos;
    }

    /**
     * @param egAmbosAbuelos the egAmbosAbuelos to set
     */
    public void setEgAmbosAbuelos(boolean egAmbosAbuelos) {
        this.egAmbosAbuelos = egAmbosAbuelos;
    }

    /**
     * @return the egTios
     */
    public boolean isEgTios() {
        return egTios;
    }

    /**
     * @param egTios the egTios to set
     */
    public void setEgTios(boolean egTios) {
        this.egTios = egTios;
    }

    /**
     * @return the egPareja
     */
    public boolean isEgPareja() {
        return egPareja;
    }

    /**
     * @param egPareja the egPareja to set
     */
    public void setEgPareja(boolean egPareja) {
        this.egPareja = egPareja;
    }

    /**
     * @return the egSolo
     */
    public boolean isEgSolo() {
        return egSolo;
    }

    /**
     * @param egSolo the egSolo to set
     */
    public void setEgSolo(boolean egSolo) {
        this.egSolo = egSolo;
    }

    /**
     * @return the egOtros
     */
    public boolean isEgOtros() {
        return egOtros;
    }

    /**
     * @param egOtros the egOtros to set
     */
    public void setEgOtros(boolean egOtros) {
        this.egOtros = egOtros;
    }

    /**
     * @return the relacionEgOtros
     */
    public String getRelacionEgOtros() {
        return relacionEgOtros;
    }

    /**
     * @param relacionEgOtros the relacionEgOtros to set
     */
    public void setRelacionEgOtros(String relacionEgOtros) {
        this.relacionEgOtros = relacionEgOtros;
    }

    /**
     * @return the condFisEgPerfectas
     */
    public boolean isCondFisEgPerfectas() {
        return condFisEgPerfectas;
    }

    /**
     * @param condFisEgPerfectas the condFisEgPerfectas to set
     */
    public void setCondFisEgPerfectas(boolean condFisEgPerfectas) {
        this.condFisEgPerfectas = condFisEgPerfectas;
    }

    /**
     * @return the condFisEgLastimados
     */
    public boolean isCondFisEgLastimados() {
        return condFisEgLastimados;
    }

    /**
     * @param condFisEgLastimados the condFisEgLastimados to set
     */
    public void setCondFisEgLastimados(boolean condFisEgLastimados) {
        this.condFisEgLastimados = condFisEgLastimados;
    }

    /**
     * @return the condFisEgMoretones
     */
    public boolean isCondFisEgMoretones() {
        return condFisEgMoretones;
    }

    /**
     * @param condFisEgMoretones the condFisEgMoretones to set
     */
    public void setCondFisEgMoretones(boolean condFisEgMoretones) {
        this.condFisEgMoretones = condFisEgMoretones;
    }

    /**
     * @return the condEmoEgTranquilo
     */
    public boolean isCondEmoEgTranquilo() {
        return condEmoEgTranquilo;
    }

    /**
     * @param condEmoEgTranquilo the condEmoEgTranquilo to set
     */
    public void setCondEmoEgTranquilo(boolean condEmoEgTranquilo) {
        this.condEmoEgTranquilo = condEmoEgTranquilo;
    }

    /**
     * @return the condEmoEgAgresivo
     */
    public boolean isCondEmoEgAgresivo() {
        return condEmoEgAgresivo;
    }

    /**
     * @param condEmoEgAgresivo the condEmoEgAgresivo to set
     */
    public void setCondEmoEgAgresivo(boolean condEmoEgAgresivo) {
        this.condEmoEgAgresivo = condEmoEgAgresivo;
    }

    /**
     * @return the condEmoEgExtrovertido
     */
    public boolean isCondEmoEgExtrovertido() {
        return condEmoEgExtrovertido;
    }

    /**
     * @param condEmoEgExtrovertido the condEmoEgExtrovertido to set
     */
    public void setCondEmoEgExtrovertido(boolean condEmoEgExtrovertido) {
        this.condEmoEgExtrovertido = condEmoEgExtrovertido;
    }

    /**
     * @return the condEmoEgIntrovertido
     */
    public boolean isCondEmoEgIntrovertido() {
        return condEmoEgIntrovertido;
    }

    /**
     * @param condEmoEgIntrovertido the condEmoEgIntrovertido to set
     */
    public void setCondEmoEgIntrovertido(boolean condEmoEgIntrovertido) {
        this.condEmoEgIntrovertido = condEmoEgIntrovertido;
    }

    /**
     * @return the condEmoEgConfinado
     */
    public boolean isCondEmoEgConfinado() {
        return condEmoEgConfinado;
    }

    /**
     * @param condEmoEgConfinado the condEmoEgConfinado to set
     */
    public void setCondEmoEgConfinado(boolean condEmoEgConfinado) {
        this.condEmoEgConfinado = condEmoEgConfinado;
    }

    /**
     * @return the condEmoEgAsustado
     */
    public boolean isCondEmoEgAsustado() {
        return condEmoEgAsustado;
    }

    /**
     * @param condEmoEgAsustado the condEmoEgAsustado to set
     */
    public void setCondEmoEgAsustado(boolean condEmoEgAsustado) {
        this.condEmoEgAsustado = condEmoEgAsustado;
    }

    /**
     * @return the condEmoEgAlegre
     */
    public boolean isCondEmoEgAlegre() {
        return condEmoEgAlegre;
    }

    /**
     * @param condEmoEgAlegre the condEmoEgAlegre to set
     */
    public void setCondEmoEgAlegre(boolean condEmoEgAlegre) {
        this.condEmoEgAlegre = condEmoEgAlegre;
    }

    /**
     * @return the condEmoEgOtro
     */
    public boolean isCondEmoEgOtro() {
        return condEmoEgOtro;
    }

    /**
     * @param condEmoEgOtro the condEmoEgOtro to set
     */
    public void setCondEmoEgOtro(boolean condEmoEgOtro) {
        this.condEmoEgOtro = condEmoEgOtro;
    }

    /**
     * @return the condEmoEgOtro_
     */
    public String getCondEmoEgOtroNombre() {
        return condEmoEgOtroNombre;
    }

    /**
     * @param condEmoEgOtroNombre the condEmoEgOtroNombre to set
     */
    public void setCondEmoEgOtroNombre(String condEmoEgOtroNombre) {
        this.condEmoEgOtroNombre = condEmoEgOtroNombre;
    }

    /**
     * @return the condSaludEgTEnfermedad
     */
    public String getCondSaludEgTEnfermedad() {
        return condSaludEgTEnfermedad;
    }

    /**
     * @param condSaludEgTEnfermedad the condSaludEgTEnfermedad to set
     */
    public void setCondSaludEgTEnfermedad(String condSaludEgTEnfermedad) {
        this.condSaludEgTEnfermedad = condSaludEgTEnfermedad;
    }

    /**
     * @return the condSaludEgMedicinas
     */
    public String getCondSaludEgMedicinas() {
        return condSaludEgMedicinas;
    }

    /**
     * @param condSaludEgMedicinas the condSaludEgMedicinas to set
     */
    public void setCondSaludEgMedicinas(String condSaludEgMedicinas) {
        this.condSaludEgMedicinas = condSaludEgMedicinas;
    }

    /**
     * @return the descPertenencias
     */
    public String getDescPertenencias() {
        return descPertenencias;
    }

    /**
     * @param descPertenencias the descPertenencias to set
     */
    public void setDescPertenencias(String descPertenencias) {
        this.descPertenencias = descPertenencias;
    }

    /**
     * @return the personaQueVivira
     */
    public String getPersonaQueVivira() {
        return personaQueVivira;
    }

    /**
     * @param personaQueVivira the personaQueVivira to set
     */
    public void setPersonaQueVivira(String personaQueVivira) {
        this.personaQueVivira = personaQueVivira;
    }

    /**
     * @return the direccionTelefonoRef
     */
    public String getDireccionTelefonoRef() {
        return direccionTelefonoRef;
    }

    /**
     * @param direccionTelefonoRef the direccionTelefonoRef to set
     */
    public void setDireccionTelefonoRef(String direccionTelefonoRef) {
        this.direccionTelefonoRef = direccionTelefonoRef;
    }

//    /**
//     * @return the mapaRef
//     */
//    public String getMapaRef() {
//        return mapaRef;
//    }
//
//    /**
//     * @param mapaRef the mapaRef to set
//     */
//    public void setMapaRef(String mapaRef) {
//        this.mapaRef = mapaRef;
//    }
    /**
     * @return the nombre_parentesco_cargo_pe
     */
    

    /**
     * @return the adolescente
     */
    public Persona getAdolescente() {
        return adolescente;
    }

    /**
     * @param adolescente the adolescente to set
     */
    public void setAdolescente(Persona adolescente) {
        this.adolescente = adolescente;
    }

    /**
     * @return the responsableEgreso
     */
    public Persona getResponsableEgreso() {
        return responsableEgreso;
    }

    /**
     * @param responsableEgreso the responsableEgreso to set
     */
    public void setResponsableEgreso(Persona responsableEgreso) {
        this.responsableEgreso = responsableEgreso;
    }

    /**
     * @return the idFichaEgreso
     */
    public FichaIngreso getFichaIngreso() {
        return fichaIngreso;
    }

    /**
     * @param idFichaEgreso the idFichaEgreso to set
     */
    public void setFichaIngreso(FichaIngreso fichaIngreso) {
        this.fichaIngreso = fichaIngreso;
    }

    /**
     * @return the autorizaEgreso
     */
    public Persona getAutorizaEgreso() {
        return autorizaEgreso;
    }

    /**
     * @param autorizaEgreso the autorizaEgreso to set
     */
    public void setAutorizaEgreso(Persona autorizaEgreso) {
        this.autorizaEgreso = autorizaEgreso;
    }

    /**
     * @return the companeroEgreso
     */
    public Persona getCompaneroEgreso() {
        return companeroEgreso;
    }

    /**
     * @param companeroEgreso the companeroEgreso to set
     */
    public void setCompaneroEgreso(Persona companeroEgreso) {
        this.companeroEgreso = companeroEgreso;
    }

    /**
     * @return the parentescoCompaneroEgreso
     */
    public String getParentescoCompaneroEgreso() {
        return parentescoCompaneroEgreso;
    }

    /**
     * @param parentescoCompaneroEgreso the parentescoCompaneroEgreso to set
     */
    public void setParentescoCompaneroEgreso(String parentescoCompaneroEgreso) {
        this.parentescoCompaneroEgreso = parentescoCompaneroEgreso;
    }
}
