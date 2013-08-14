/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.sira;

import java.util.Date;
import org.cmail.rehabilitacion.modelo.AuditEntity;
import org.cmail.rehabilitacion.modelo.Persona;

/**
 * Entidad que representa una ficha de egreso de un adolescente, es decir el registro de cuando una adolescente sale del centro de rehabilitación al cumplir su condena.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class FichaEgreso extends AuditEntity {

    /**La ficha de ingreso de la cual se está generando el egreso*/
    private FichaIngreso fichaIngreso = null;
    
    /**El adolescente de quien se está haciendo el egreso*/
    private Persona adolescente;
    
    /**El lugar donde se está haciendo el egreso*/
    private String lugar;
    
    /**Fecha en que egresa el adolescente*/
    private Date fecha = new Date();
    
    /**Tiempo de permanencia en el centro de rehabilitación*/
    private String tiempoPermanencia;
    
    //=================================================
    //1. RAZÓN DE EGRESO
    //=================================================
    
    /**1.1.- Razón de egreso: Caducidad de la retención */
    private boolean razEgCadRetencion;
    
    /**1.2.- Razón de egreso: Terminación del tiempo de la medidad socioeducativa*/
    private boolean razEgTermMedida;
    
    /**1.3.- Caducidad del internamiento preventivo*/
    private boolean razEgCadIntPreventivo;
    
    /**1.4.- Razón de egreso: Caducidad de la retención para investigación*/
    private boolean razEgCadRetInvestigacion;
    
    /**1.5.- Razón de egreso: Caducidad de la retención para comparecencia*/
    private boolean razEgCadRetComparecencia;
    
    /**1.6.- Razón de egreso: Defunción*/
    private boolean razEgDefuncion;
    
    /**1.7.- Razón de egreso: Resolución Judicial*/
    private boolean razEgResJusdicial;
    
    /**1.8.- Razón de egreso: Evasión y fuga*/
    private boolean razEgEvasionFuga;
    
    /**1.9.- Razón de egreso: Trasferencia*/
    private boolean razEgTrasferencia;
    
    /**1.10.- Razón de egreso: Embarazo*/
    private boolean razEgEmbarazo;
    
    //=================================================
    //2. AUTORIZACIÓN DEL EGRESO
    //=================================================
    
    /**2.1 Nombre y cargo de la persona que autoriza el egreso*/
    private Persona autorizaEgreso;
    

    /**2.2 Documento(s)  que avaliza(n) la autorización para el egreso del centro (Adjuntos)*/
    private String docsEgreso;
    
    //=================================================
    //3. ACTITUD DEL O LA ADOLESCENTE
    //=================================================    
    
    /**3.1 Aceptación de salir*/
    private boolean aceptacionSalida;
    
    /**3.1 Explicación de la aceptación de salida*/
    private String expAceptacionSalida;
    
    /**3.2 Aceptación de irse con la persona autorizada para llevarle*/
    private boolean aceptacionPersonaSalida;
    
    /**3.2 Explicación de la aceptación de irse con la persona autorizada para llevarle*/    
    private String expAceptacionPersonaSalida;        
    
    /**3.3 Personas con la cual egresa (ambos progenitores)*/
    private boolean egAmbosProgenitores;
    
    /**3.3 Personas con la cual egresa (padre)*/
    private boolean egPadre;
    
    /**3.3 Personas con la cual egresa (madre)*/
    private boolean egMadre;
    
    /**3.3 Personas con la cual egresa (representante)*/
    private boolean egRepresentante;
    
    /**3.3 Personas con la cual egresa (hermano)*/
    private boolean egHermano;
    
    /**3.3 Personas con la cual egresa (hermana)*/
    private boolean egHermana;
    
    /**3.3 Personas con la cual egresa (abuelo)*/
    private boolean egAbuelo;
    
    /**3.3 Personas con la cual egresa (abuela)*/
    private boolean egAbuela;
    
    /**3.3 Personas con la cual egresa (ambos abuelos)*/
    private boolean egAmbosAbuelos;
    
    /**3.3 Personas con la cual egresa (tios)*/
    private boolean egTios;
    
    /**3.3 Personas con la cual egresa (pareja)*/
    private boolean egPareja;
    
    /**3.3 Personas con la cual egresa (solo)*/
    private boolean egSolo;
    
    /**3.3 Personas con la cual egresa (otros)*/
    private boolean egOtros;
    
    /**3.3 Relación de parentezco cuando indica que egresa con otros, que no estén en el listado*/
    private String relacionEgOtros;
    
    //=================================================
    //4. CONDICIONES DE EGRESO DEL O LA ADOLESCENTE
    //=================================================        
    
    //Físicas
    
    /**4.1 Condiciones de Egreso Físicas: Perfectas*/
    private boolean condFisEgPerfectas;
    /**4.1 Condiciones de Egreso Físicas: Lastimados*/
    private boolean condFisEgLastimados;
    /**4.1 Condiciones de Egreso Físicas: Moretones*/
    private boolean condFisEgMoretones;
    
    //Emocionales
    
    /**4.2 Condiciones de Egreso Emocionales: Tranquilo*/
    private boolean condEmoEgTranquilo;
    /**4.2 Condiciones de Egreso Emocionales: Agresivo*/
    private boolean condEmoEgAgresivo;
    /**4.2 Condiciones de Egreso Emocionales: Extrovertido*/
    private boolean condEmoEgExtrovertido;
    /**4.2 Condiciones de Egreso Emocionales: Introvertido*/
    private boolean condEmoEgIntrovertido;
    /**4.2 Condiciones de Egreso Emocionales: Confinado*/
    private boolean condEmoEgConfinado;
    /**4.2 Condiciones de Egreso Emocionales: Asustado*/
    private boolean condEmoEgAsustado;
    /**4.2 Condiciones de Egreso Emocionales: Alegre*/
    private boolean condEmoEgAlegre;
    /**4.2 Condiciones de Egreso Emocionales: Otras*/
    private boolean condEmoEgOtro;
    /**4.2 Condiciones de Egreso Emocionales: Descripción cuando elije otras*/
    private String condEmoEgOtroNombre;
    
    //Salubridad
    
    /**4.3 Condiciones de Egreso Salubridad: Tipo de enfermedad*/
    private String condSaludEgTEnfermedad;
    /**4.3 Condiciones de Egreso Salubridad: Medicinas que toma*/
    private String condSaludEgMedicinas;
    
    //=================================================
    //5. PERTENENCIAS CON LAS QUE EGRESA (Describa)
    //=================================================
        
    /**5.1 Descripción de las pertenencias con las que egresa*/
    private String descPertenencias;
    
    //=================================================
    //6. DATOS DE REFERENCIA DEL O LA ADOLESCENTE
    //=================================================
    
    /**6.1 Datos de referencia: Con quién vivirá (nombre-parentesco)*/
    private String personaQueVivira;
    
    /**6.2 Datos de referencia: Dirección y teléfono de referencia del lugar donde vivirá*/
    private String direccionTelefonoRef;
    
    //6.3 Mapa de referencia
    //private String mapaRef;
    
    //=================================================
    //7. RESPONSABLE DEL EGRESO
    //=================================================
    
    /** 7.1 Empleado responsable del egreso*/
    private Persona responsableEgreso;

    //=================================================
    //8. PERSONA CON LA QUE EGRESA EL O LA ADOLESCENTE
    //=================================================
        
    /**8.1 Persona con la que egresa el adolescente*/
    private Persona companeroEgreso;
    
    /**8.2 Parentezco con la persona que egresa*/
    private String parentescoCompaneroEgreso;
    
    /**
     * Constructor por defecto
     */
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
