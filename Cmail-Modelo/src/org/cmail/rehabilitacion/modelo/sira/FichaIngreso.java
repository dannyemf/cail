/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.sira;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.cmail.rehabilitacion.modelo.AuditEntity;
import org.cmail.rehabilitacion.modelo.Persona;
import org.cmail.rehabilitacion.modelo.localizacion.Canton;
import org.cmail.rehabilitacion.modelo.localizacion.Parroquia;
import org.cmail.rehabilitacion.modelo.localizacion.Provincia;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class FichaIngreso extends AuditEntity{
    
    private FichaEgreso fichaEgreso = null;
    
    private String lugar;
    private Date fecha = new Date();
    private String razonIngreso;
    
    /**
     * 1. RAZONES DE INGRESO
     * raz ==> Razon
     * Ing ==> Ingreso
     * Mc ==> Medida Cautelar
     * Ms ==> Medida Socioeducativa
     * Ri ==> Reingreso    
     */
    
    //1.1 Medida Cautelar
    private boolean razInMedidaCautelar; //1.1 Medida cautelar
    private boolean razInMcRetencion; //Retención
    private boolean razInMcInvestigacion; //Investigación
    private boolean razInMcComparecencia; //Asegurar comparecencia
    private boolean razInMcIntPreventivo; // Internamiento preventivo
    
    //1.2 Medida socioeducativa
    private boolean razInMedidaSocioeducativa; //1.2 Medida Socioeducativa
    private boolean razInMsIntFinSemana; // Interamiento de fin de semana
    private boolean razInMsIntSemiLibertad; // Internamiento regimen de semi libertad
    private boolean razInMsIntInstitucional; // Internamiento institucional
    private boolean razInMsLibAsistida; // Libertad asistida
    private boolean razInMsServComunidad; // Servicio a la comunidad 
    private boolean razInMsRepDanoCausado; // Reparacion del daño causado
    private boolean razInMsOrientaApoyoFamiliar; // Orientación y apoyo familiar
    private boolean razInMsAmonestacionImposicion; // Amonestacin e imposicion de reglas de conducta
    private boolean razInMsAmonestacion; // Amonestación
    
    //1.3 Reingreso
    private boolean razInReingreso; // 1.3 Reingreso
    private boolean razInRiMedidaCautelar; //  Medida Cautelar
    private boolean razInRiMedidaSocioeducativa; // Medida Socioeducativa
    private boolean razInRiOtroTipo; // (Identifique tipo de medida)
    private String razInRiOtroTipoNombre; // descripcion de otro tipo de reingrso    
    private String juez; ////Juez que dicta la medida (nombre y ciudad)
    
    //1.4 Tiempo para el cual fue dada la medida
    private String tiempoMedida;
    
    /**
     * causal de la medida
     * causal ==> causal
     * Varios ==> V
     * Inf ==> Infracciones (contra las personas, por drogas, contra la propiedad, de tipo sexual)
     */
    private boolean causalVManejarAlcoholizado; // Manejar con grado alcoholizado no permitido
    private boolean causalVHurto; //Hurto
    private boolean causalVPosecionArmas; // Posesión de armas
    private boolean causalVRecaptura; // Recaptura
    private boolean causalVFalsificacionDoc; //Falsificación de Documentos
    //Infracciones contra las personas
    private boolean causalInfHomicidio; // Homicidio
    private boolean causalInfIntHomicidio; //Intento de homicidio
    private boolean causalInfAsesinato; // Asesinato
    private boolean causalInfLesiones;//Lesiones que causan incapacidad física
    private boolean causalInfPlagio;// Plagio (Secuestro)
    private boolean causalInfIntPlagio;// Intento de Plagio
    private boolean causalInfAsalto;// Asalto contra la propiedad privada
    private boolean causalInfTrataPersonas;//Trata de personas
    //Infracciones por Drogas
    private boolean causalInfTrafico;// Tráfico de drogas
    private boolean causalInfConsumo;// Consumo y poseción ilícita
    private boolean causalInfVenta;//Venta de drogas
    private boolean causalInfTrata;//Trata con fines de tráfico de drogas
    //Infracciones contra la propiedad
    private boolean causalInfRobo;//Robo
    private boolean causalInfIntentoRobo;//Intento de Robo
    private boolean causalInfAbigeato;//Abigeato
    private boolean causalInfEstafa;//Estafa
    private boolean causalInfExtorsion;//Extorción
    private boolean causalInfDestruccion;//Destrucción a la propiedad
    //Infracciones de tipo sexual
    private boolean causalInfViolacion;//Violación
    private boolean causalInfIntentoViolacion;//Intento de Violación    
    private boolean causalInfEstupro;//Estupro
    private boolean causalInfAtentPudor;//Atentado al pudor
    private boolean causalInfAcosoSexual;//Acoso Sexual
    private boolean causalInfTrataExplotSexual;//Trata con fines de explotación sexual
    private String causalInfOtros;//Descripción de otro tipo de infracción
    
    //2. DATOS DE IDENTIFICACIÓN    
    private Persona adolescente;    
    private String estadoCivil;//estado civil del adolescente
    private boolean cedulado = true;// especifica si el cedulado tiene cedula o no?
    
    // lugar de procedencia donde fue retenido
    private Provincia provinciaDetencion;//
    private Canton cantonDetencion;//
    private Parroquia parroquiaDetencion;//
    private String barrioDetencion;//
    private String comunidadDetencion;//
    private int edad;// edad del adolescente    
    
    // 2.5 nivel educativo aprobado
    private String nivelEducativoTipo;
    private String nivelEducativoNombre;
    
    //2.6 Autoidentificación
    private String autoidentificacion;
    private String nacionalidad;
    
    //2.7 Idioma
    private boolean idiomaCastellano;
    private boolean idiomaIngles;
    private boolean lenguaNativa;
    private String lenguaNativaNombre;
    private boolean otroLenguaje;
    private String otroLenguajeNombre;
    
    //2.8 Ocupación
    private String tipoOcupacion;// Tipo de ocupación
    private String tiempoOcupacionActual;// Desde cuando realiza esta ocupación
    
    //2.9 Dirección del domicilio/residencia
    private String tiempoResidenciaActual;// Desde cuando?
    private String telefonoResidencia;// Teléfono de residencia
    private Provincia provinciaResidencia;// Provincia de residencia
    private Canton cantonResidencia;// Cantón de residencia
    private Parroquia parroquiaResidencia;// Parroquia de residencia
    private String barrioResidencia;// Barrio de residencia
    private String comunidadResidencia;// Comunidad de residencia
    
    //2.10 Hijos del o la Adolescente
    private Integer numeroHijosHombres =0;// Numero total de hijos varones que tiene el adolescente
    private String edadesHijosHombres;// descripción de las edades de los hijos varones
    private Integer numeroHijosMujeres =0;// Numero total de hijos mujeres que tiene el adolescente
    private String edadesHijosMujeres;// descripción de las edades de los hijos varones
    private boolean ingresaHijos;// Ingresa con su o sus hijos
    private String numeroHijosEdades;// Con cuantos hijos ingresa y de que edades
    
    //2.11 persona o persona con quien vive
    private String nombrePersona1;//
    private String parentescoPersona1;//
    private String telefonoPersona1;//
    private String nombrePersona2;//
    private String parentescoPersona2;//
    private String telefonoPersona2;//
    
    //3. DATOS FAMILIARES
    //3.1 PADRE - En el adolescente
    //3.2 MADRE - En el adolescente    
    //3.3 REPRESENTANTE
    private Persona representante;
    
    //4. CONDICIONES DE INGRESO DEL O LA ADOLESCENTE
    /**
     * prefijo
     * cond ==> condiciones
     * Fis ==> Fisicas
     * Emo==> Emocionales
     * Salud ==>
     * Ing = ingreso
     */
    
    //4.1 Físicas
    private boolean condFisIngMoretones;// Moretones
    private boolean condFisIngLastimados;//Lastimados
    private boolean condFisIngOtros;// otros
    private String condFisIngOtrosNombre;// otros cual?
    
    //4.2 Emocional
    private boolean condEmoIngTranquilo;// Tranquilo/a
    private boolean condEmoIngExtrovertido;//Extrovertido/a
    private boolean condEmoIngIntrovertido;//Introvertido/a
    private boolean condEmoIngAgresivo;//Agresivo/a
    private boolean condEmoIngAsustado;//Asustado/a
    private boolean condEmoIngOtros;//Otros 
    private String condEmoIngOtrosNombre;//otros cual?
    
    //4.3 Salubridad
    private String condSaludEnfermedad;// Alguna enfermedad (identifique)
    private String condSaludMedicina;// Medicina que toma
    private boolean condSaludSintDroga;// Presenta síntomas de haber consumido droga
    
    //5. PERTENENCIAS CON LAS QUE INGRESA
    //5.1 
    // pert = pertenencias
    private String pertDescripcion;// Descripción de la pertenencias con las que ingresa al centro
    //5.2
    private Persona responsableResguardoPertenencia;//  ¿Quién guarda o a quién se le entrega las pertenencias?
    //5.3
    // solo por completar el formulario
//    private String firma;// firma del o la adolescente, certificando que esas son sus pertenencias y a quien las entrega
    //6. DOCUMENTOS CON LOS QUE INGRESA EL O  LA ADOLESCENTE
    //6.1 Documentación necesaria para la admisión de un adolescente (Art 378) 
    private boolean delitoFlagrante;// Delito flagrante (Art. 326)
    private boolean medidasCautelares;//Medidas cautelares (Art. 328 y 329)
    private boolean medidasSocioeducativas;// Medidas socioeducativas (Art. 369)
    // 7. OBSERVACIONES ADICIONALES
    private String observaciones;//
    //8. RESPONSABLE DEL TRASLADO AL CENTRO
    private Persona responsableTraslado;
    //9. PERSONA QUE LO INGRESA AL CENTRO
    private Persona responsableIngreso;
    
    private Set<FichaIngresoDocumento> documentos = new HashSet<FichaIngresoDocumento>();
    

    public FichaIngreso() {
    }
    
    /**
     * Método que se encarga de copiar los datos del adolescente a la ficha de ingreso.
     * Se hace esta operación, ya que los datos del adolescente son cambiantes, en tanto que 
     * esos mismo datos deber ser históricos en la ficha.
     */
    public void fijarDatosAdolescente(){
        this.edad = this.adolescente.calcularEdad();
        this.estadoCivil = this.adolescente.getEstadoCivil();
        this.cedulado = this.adolescente.getCedula() != null && this.getAdolescente().getCedula().length() > 0;
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
     * @return the razInMedidaCautelar
     */
    public boolean isRazInMedidaCautelar() {
        return razInMedidaCautelar;
    }

    /**
     * @param razInMedidaCautelar the razInMedidaCautelar to set
     */
    public void setRazInMedidaCautelar(boolean razInMedidaCautelar) {
        this.razInMedidaCautelar = razInMedidaCautelar;
    }

    /**
     * @return the razInMcRetencion
     */
    public boolean isRazInMcRetencion() {
        return razInMcRetencion;
    }

    /**
     * @param razInMcRetencion the razInMcRetencion to set
     */
    public void setRazInMcRetencion(boolean razInMcRetencion) {
        this.razInMcRetencion = razInMcRetencion;
    }

    /**
     * @return the razInMcInvestigacion
     */
    public boolean isRazInMcInvestigacion() {
        return razInMcInvestigacion;
    }

    /**
     * @param razInMcInvestigacion the razInMcInvestigacion to set
     */
    public void setRazInMcInvestigacion(boolean razInMcInvestigacion) {
        this.razInMcInvestigacion = razInMcInvestigacion;
    }

    /**
     * @return the razInMcComparecencia
     */
    public boolean isRazInMcComparecencia() {
        return razInMcComparecencia;
    }

    /**
     * @param razInMcComparecencia the razInMcComparecencia to set
     */
    public void setRazInMcComparecencia(boolean razInMcComparecencia) {
        this.razInMcComparecencia = razInMcComparecencia;
    }

    /**
     * @return the razInMcIntPreventivo
     */
    public boolean isRazInMcIntPreventivo() {
        return razInMcIntPreventivo;
    }

    /**
     * @param razInMcIntPreventivo the razInMcIntPreventivo to set
     */
    public void setRazInMcIntPreventivo(boolean razInMcIntPreventivo) {
        this.razInMcIntPreventivo = razInMcIntPreventivo;
    }

    /**
     * @return the razInMedidaSocioeducativa
     */
    public boolean isRazInMedidaSocioeducativa() {
        return razInMedidaSocioeducativa;
    }

    /**
     * @param razInMedidaSocioeducativa the razInMedidaSocioeducativa to set
     */
    public void setRazInMedidaSocioeducativa(boolean razInMedidaSocioeducativa) {
        this.razInMedidaSocioeducativa = razInMedidaSocioeducativa;
    }

    /**
     * @return the razInMsIntFinSemana
     */
    public boolean isRazInMsIntFinSemana() {
        return razInMsIntFinSemana;
    }

    /**
     * @param razInMsIntFinSemana the razInMsIntFinSemana to set
     */
    public void setRazInMsIntFinSemana(boolean razInMsIntFinSemana) {
        this.razInMsIntFinSemana = razInMsIntFinSemana;
    }

    /**
     * @return the razInMsIntSemiLibertad
     */
    public boolean isRazInMsIntSemiLibertad() {
        return razInMsIntSemiLibertad;
    }

    /**
     * @param razInMsIntSemiLibertad the razInMsIntSemiLibertad to set
     */
    public void setRazInMsIntSemiLibertad(boolean razInMsIntSemiLibertad) {
        this.razInMsIntSemiLibertad = razInMsIntSemiLibertad;
    }

    /**
     * @return the razInMsIntInstitucional
     */
    public boolean isRazInMsIntInstitucional() {
        return razInMsIntInstitucional;
    }

    /**
     * @param razInMsIntInstitucional the razInMsIntInstitucional to set
     */
    public void setRazInMsIntInstitucional(boolean razInMsIntInstitucional) {
        this.razInMsIntInstitucional = razInMsIntInstitucional;
    }

    /**
     * @return the razInMsLibAsistida
     */
    public boolean isRazInMsLibAsistida() {
        return razInMsLibAsistida;
    }

    /**
     * @param razInMsLibAsistida the razInMsLibAsistida to set
     */
    public void setRazInMsLibAsistida(boolean razInMsLibAsistida) {
        this.razInMsLibAsistida = razInMsLibAsistida;
    }

    /**
     * @return the razInMsServComunidad
     */
    public boolean isRazInMsServComunidad() {
        return razInMsServComunidad;
    }

    /**
     * @param razInMsServComunidad the razInMsServComunidad to set
     */
    public void setRazInMsServComunidad(boolean razInMsServComunidad) {
        this.razInMsServComunidad = razInMsServComunidad;
    }

    /**
     * @return the razInMsRepDañoCausado
     */
    public boolean isRazInMsRepDanoCausado() {
        return razInMsRepDanoCausado;
    }

    /**
     * @param razInMsRepDañoCausado the razInMsRepDañoCausado to set
     */
    public void setRazInMsRepDanoCausado(boolean razInMsRepDanoCausado) {
        this.razInMsRepDanoCausado = razInMsRepDanoCausado;
    }

    /**
     * @return the razInMsOrientaApoyoFamiliar
     */
    public boolean isRazInMsOrientaApoyoFamiliar() {
        return razInMsOrientaApoyoFamiliar;
    }

    /**
     * @param razInMsOrientaApoyoFamiliar the razInMsOrientaApoyoFamiliar to set
     */
    public void setRazInMsOrientaApoyoFamiliar(boolean razInMsOrientaApoyoFamiliar) {
        this.razInMsOrientaApoyoFamiliar = razInMsOrientaApoyoFamiliar;
    }

    /**
     * @return the razInMsAmonestacionImposicion
     */
    public boolean isRazInMsAmonestacionImposicion() {
        return razInMsAmonestacionImposicion;
    }

    /**
     * @param razInMsAmonestacionImposicion the razInMsAmonestacionImposicion to set
     */
    public void setRazInMsAmonestacionImposicion(boolean razInMsAmonestacionImposicion) {
        this.razInMsAmonestacionImposicion = razInMsAmonestacionImposicion;
    }

    /**
     * @return the razInMsAmonestacion
     */
    public boolean isRazInMsAmonestacion() {
        return razInMsAmonestacion;
    }

    /**
     * @param razInMsAmonestacion the razInMsAmonestacion to set
     */
    public void setRazInMsAmonestacion(boolean razInMsAmonestacion) {
        this.razInMsAmonestacion = razInMsAmonestacion;
    }

    /**
     * @return the razInReingreso
     */
    public boolean isRazInReingreso() {
        return razInReingreso;
    }

    /**
     * @param razInReingreso the razInReingreso to set
     */
    public void setRazInReingreso(boolean razInReingreso) {
        this.razInReingreso = razInReingreso;
    }

    /**
     * @return the razInRiMedidaCautelar
     */
    public boolean isRazInRiMedidaCautelar() {
        return razInRiMedidaCautelar;
    }

    /**
     * @param razInRiMedidaCautelar the razInRiMedidaCautelar to set
     */
    public void setRazInRiMedidaCautelar(boolean razInRiMedidaCautelar) {
        this.razInRiMedidaCautelar = razInRiMedidaCautelar;
    }

    /**
     * @return the razInRiMedidaSocioeducativa
     */
    public boolean isRazInRiMedidaSocioeducativa() {
        return razInRiMedidaSocioeducativa;
    }

    /**
     * @param razInRiMedidaSocioeducativa the razInRiMedidaSocioeducativa to set
     */
    public void setRazInRiMedidaSocioeducativa(boolean razInRiMedidaSocioeducativa) {
        this.razInRiMedidaSocioeducativa = razInRiMedidaSocioeducativa;
    }

    /**
     * @return the razInRiOtroTipo
     */
    public boolean isRazInRiOtroTipo() {
        return razInRiOtroTipo;
    }

    /**
     * @param razInRiOtroTipo the razInRiOtroTipo to set
     */
    public void setRazInRiOtroTipo(boolean razInRiOtroTipo) {
        this.razInRiOtroTipo = razInRiOtroTipo;
    }

    /**
     * @return the razInRiOtroTipoNombre
     */
    public String getRazInRiOtroTipoNombre() {
        return razInRiOtroTipoNombre;
    }

    /**
     * @param razInRiOtroTipo the razInRiOtroTipoNombre to set
     */
    public void setRazInRiOtroTipoNombre(String razInRiOtroTipo) {
        this.razInRiOtroTipoNombre = razInRiOtroTipo;
    }

    /**
     * @return the juez
     */
    public String getJuez() {
        return juez;
    }

    /**
     * @param juez the juez to set
     */
    public void setJuez(String juez) {
        this.juez = juez;
    }

    /**
     * @return the tiempoMedida
     */
    public String getTiempoMedida() {
        return tiempoMedida;
    }

    /**
     * @param tiempoMedida the tiempoMedida to set
     */
    public void setTiempoMedida(String tiempoMedida) {
        this.tiempoMedida = tiempoMedida;
    }

    /**
     * @return the causalVManejarAlcoholizado
     */
    public boolean isCausalVManejarAlcoholizado() {
        return causalVManejarAlcoholizado;
    }

    /**
     * @param causalVManejarAlcoholizado the causalVManejarAlcoholizado to set
     */
    public void setCausalVManejarAlcoholizado(boolean causalVManejarAlcoholizado) {
        this.causalVManejarAlcoholizado = causalVManejarAlcoholizado;
    }

    /**
     * @return the causalVHurto
     */
    public boolean isCausalVHurto() {
        return causalVHurto;
    }

    /**
     * @param causalVHurto the causalVHurto to set
     */
    public void setCausalVHurto(boolean causalVHurto) {
        this.causalVHurto = causalVHurto;
    }

    /**
     * @return the causalVPosecionArmas
     */
    public boolean isCausalVPosecionArmas() {
        return causalVPosecionArmas;
    }

    /**
     * @param causalVPosecionArmas the causalVPosecionArmas to set
     */
    public void setCausalVPosecionArmas(boolean causalVPosecionArmas) {
        this.causalVPosecionArmas = causalVPosecionArmas;
    }

    /**
     * @return the causalVRecaptura
     */
    public boolean isCausalVRecaptura() {
        return causalVRecaptura;
    }

    /**
     * @param causalVRecaptura the causalVRecaptura to set
     */
    public void setCausalVRecaptura(boolean causalVRecaptura) {
        this.causalVRecaptura = causalVRecaptura;
    }

    /**
     * @return the causalVFalsificacionDoc
     */
    public boolean isCausalVFalsificacionDoc() {
        return causalVFalsificacionDoc;
    }

    /**
     * @param causalVFalsificacionDoc the causalVFalsificacionDoc to set
     */
    public void setCausalVFalsificacionDoc(boolean causalVFalsificacionDoc) {
        this.causalVFalsificacionDoc = causalVFalsificacionDoc;
    }

    /**
     * @return the causalInfHomicidio
     */
    public boolean isCausalInfHomicidio() {
        return causalInfHomicidio;
    }

    /**
     * @param causalInfHomicidio the causalInfHomicidio to set
     */
    public void setCausalInfHomicidio(boolean causalInfHomicidio) {
        this.causalInfHomicidio = causalInfHomicidio;
    }

    /**
     * @return the causalInfIntHomicidio
     */
    public boolean isCausalInfIntHomicidio() {
        return causalInfIntHomicidio;
    }

    /**
     * @param causalInfIntHomicidio the causalInfIntHomicidio to set
     */
    public void setCausalInfIntHomicidio(boolean causalInfIntHomicidio) {
        this.causalInfIntHomicidio = causalInfIntHomicidio;
    }

    /**
     * @return the causalInfAsesinato
     */
    public boolean isCausalInfAsesinato() {
        return causalInfAsesinato;
    }

    /**
     * @param causalInfAsesinato the causalInfAsesinato to set
     */
    public void setCausalInfAsesinato(boolean causalInfAsesinato) {
        this.causalInfAsesinato = causalInfAsesinato;
    }

    /**
     * @return the causalInfLesiones
     */
    public boolean isCausalInfLesiones() {
        return causalInfLesiones;
    }

    /**
     * @param causalInfLesiones the causalInfLesiones to set
     */
    public void setCausalInfLesiones(boolean causalInfLesiones) {
        this.causalInfLesiones = causalInfLesiones;
    }

    /**
     * @return the causalInfPlagio
     */
    public boolean isCausalInfPlagio() {
        return causalInfPlagio;
    }

    /**
     * @param causalInfPlagio the causalInfPlagio to set
     */
    public void setCausalInfPlagio(boolean causalInfPlagio) {
        this.causalInfPlagio = causalInfPlagio;
    }

    /**
     * @return the causalInfIntPlagio
     */
    public boolean isCausalInfIntPlagio() {
        return causalInfIntPlagio;
    }

    /**
     * @param causalInfIntPlagio the causalInfIntPlagio to set
     */
    public void setCausalInfIntPlagio(boolean causalInfIntPlagio) {
        this.causalInfIntPlagio = causalInfIntPlagio;
    }

    /**
     * @return the causalInfAsalto
     */
    public boolean isCausalInfAsalto() {
        return causalInfAsalto;
    }

    /**
     * @param causalInfAsalto the causalInfAsalto to set
     */
    public void setCausalInfAsalto(boolean causalInfAsalto) {
        this.causalInfAsalto = causalInfAsalto;
    }

    /**
     * @return the causalInfTrataPersonas
     */
    public boolean isCausalInfTrataPersonas() {
        return causalInfTrataPersonas;
    }

    /**
     * @param causalInfTrataPersonas the causalInfTrataPersonas to set
     */
    public void setCausalInfTrataPersonas(boolean causalInfTrataPersonas) {
        this.causalInfTrataPersonas = causalInfTrataPersonas;
    }

    /**
     * @return the causalInfTrafico
     */
    public boolean isCausalInfTrafico() {
        return causalInfTrafico;
    }

    /**
     * @param causalInfTrafico the causalInfTrafico to set
     */
    public void setCausalInfTrafico(boolean causalInfTrafico) {
        this.causalInfTrafico = causalInfTrafico;
    }

    /**
     * @return the causalInfConsumo
     */
    public boolean isCausalInfConsumo() {
        return causalInfConsumo;
    }

    /**
     * @param causalInfConsumo the causalInfConsumo to set
     */
    public void setCausalInfConsumo(boolean causalInfConsumo) {
        this.causalInfConsumo = causalInfConsumo;
    }

    /**
     * @return the causalInfVenta
     */
    public boolean isCausalInfVenta() {
        return causalInfVenta;
    }

    /**
     * @param causalInfVenta the causalInfVenta to set
     */
    public void setCausalInfVenta(boolean causalInfVenta) {
        this.causalInfVenta = causalInfVenta;
    }

    /**
     * @return the causalInfTrata
     */
    public boolean isCausalInfTrata() {
        return causalInfTrata;
    }

    /**
     * @param causalInfTrata the causalInfTrata to set
     */
    public void setCausalInfTrata(boolean causalInfTrata) {
        this.causalInfTrata = causalInfTrata;
    }

    /**
     * @return the causalInfRobo
     */
    public boolean isCausalInfRobo() {
        return causalInfRobo;
    }

    /**
     * @param causalInfRobo the causalInfRobo to set
     */
    public void setCausalInfRobo(boolean causalInfRobo) {
        this.causalInfRobo = causalInfRobo;
    }

    /**
     * @return the causalInfIntentoRobo
     */
    public boolean isCausalInfIntentoRobo() {
        return causalInfIntentoRobo;
    }

    /**
     * @param causalInfIntentoRobo the causalInfIntentoRobo to set
     */
    public void setCausalInfIntentoRobo(boolean causalInfIntentoRobo) {
        this.causalInfIntentoRobo = causalInfIntentoRobo;
    }

    /**
     * @return the causalInfAbigeato
     */
    public boolean isCausalInfAbigeato() {
        return causalInfAbigeato;
    }

    /**
     * @param causalInfAbigeato the causalInfAbigeato to set
     */
    public void setCausalInfAbigeato(boolean causalInfAbigeato) {
        this.causalInfAbigeato = causalInfAbigeato;
    }

    /**
     * @return the causalInfEstafa
     */
    public boolean isCausalInfEstafa() {
        return causalInfEstafa;
    }

    /**
     * @param causalInfEstafa the causalInfEstafa to set
     */
    public void setCausalInfEstafa(boolean causalInfEstafa) {
        this.causalInfEstafa = causalInfEstafa;
    }

    /**
     * @return the causalInfExtorsion
     */
    public boolean isCausalInfExtorsion() {
        return causalInfExtorsion;
    }

    /**
     * @param causalInfExtorsion the causalInfExtorsion to set
     */
    public void setCausalInfExtorsion(boolean causalInfExtorsion) {
        this.causalInfExtorsion = causalInfExtorsion;
    }

    /**
     * @return the causalInfDestruccion
     */
    public boolean isCausalInfDestruccion() {
        return causalInfDestruccion;
    }

    /**
     * @param causalInfDestruccion the causalInfDestruccion to set
     */
    public void setCausalInfDestruccion(boolean causalInfDestruccion) {
        this.causalInfDestruccion = causalInfDestruccion;
    }

    /**
     * @return the causalInfViolacion
     */
    public boolean isCausalInfViolacion() {
        return causalInfViolacion;
    }

    /**
     * @param causalInfViolacion the causalInfViolacion to set
     */
    public void setCausalInfViolacion(boolean causalInfViolacion) {
        this.causalInfViolacion = causalInfViolacion;
    }

    /**
     * @return the causalInfIntentoViolacion
     */
    public boolean isCausalInfIntentoViolacion() {
        return causalInfIntentoViolacion;
    }

    /**
     * @param causalInfIntentoViolacion the causalInfIntentoViolacion to set
     */
    public void setCausalInfIntentoViolacion(boolean causalInfIntentoViolacion) {
        this.causalInfIntentoViolacion = causalInfIntentoViolacion;
    }

    /**
     * @return the causalInfEstupro
     */
    public boolean isCausalInfEstupro() {
        return causalInfEstupro;
    }

    /**
     * @param causalInfEstupro the causalInfEstupro to set
     */
    public void setCausalInfEstupro(boolean causalInfEstupro) {
        this.causalInfEstupro = causalInfEstupro;
    }

    /**
     * @return the causalInfAtentPudor
     */
    public boolean isCausalInfAtentPudor() {
        return causalInfAtentPudor;
    }

    /**
     * @param causalInfAtentPudor the causalInfAtentPudor to set
     */
    public void setCausalInfAtentPudor(boolean causalInfAtentPudor) {
        this.causalInfAtentPudor = causalInfAtentPudor;
    }

    /**
     * @return the causalInfAcosoSexual
     */
    public boolean isCausalInfAcosoSexual() {
        return causalInfAcosoSexual;
    }

    /**
     * @param causalInfAcosoSexual the causalInfAcosoSexual to set
     */
    public void setCausalInfAcosoSexual(boolean causalInfAcosoSexual) {
        this.causalInfAcosoSexual = causalInfAcosoSexual;
    }

    /**
     * @return the causalInfTrataExplotSexual
     */
    public boolean isCausalInfTrataExplotSexual() {
        return causalInfTrataExplotSexual;
    }

    /**
     * @param causalInfTrataExplotSexual the causalInfTrataExplotSexual to set
     */
    public void setCausalInfTrataExplotSexual(boolean causalInfTrataExplotSexual) {
        this.causalInfTrataExplotSexual = causalInfTrataExplotSexual;
    }

    /**
     * @return the causalInfOtros
     */
    public String getCausalInfOtros() {
        return causalInfOtros;
    }

    /**
     * @param causalInfOtros the causalInfOtros to set
     */
    public void setCausalInfOtros(String causalInfOtros) {
        this.causalInfOtros = causalInfOtros;
    }

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
     * @return the cedulado
     */
    public boolean isCedulado() {
        return cedulado;
    }

    /**
     * @param cedulado the cedulado to set
     */
    public void setCedulado(boolean cedulado) {
        this.cedulado = cedulado;
    }

    /**
     * @return the provinciaDetencion
     */
    public Provincia getProvinciaDetencion() {
        return provinciaDetencion;
    }

    /**
     * @param provinciaDetencion the provinciaDetencion to set
     */
    public void setProvinciaDetencion(Provincia provinciaDetencion) {
        this.provinciaDetencion = provinciaDetencion;
    }

    /**
     * @return the cantonDetencion
     */
    public Canton getCantonDetencion() {
        return cantonDetencion;
    }

    /**
     * @param cantonDetencion the cantonDetencion to set
     */
    public void setCantonDetencion(Canton cantonDetencion) {
        this.cantonDetencion = cantonDetencion;
    }

    /**
     * @return the parroquiaDetencion
     */
    public Parroquia getParroquiaDetencion() {
        return parroquiaDetencion;
    }

    /**
     * @param parroquiaDetencion the parroquiaDetencion to set
     */
    public void setParroquiaDetencion(Parroquia parroquiaDetencion) {
        this.parroquiaDetencion = parroquiaDetencion;
    }

    /**
     * @return the barrioDetencion
     */
    public String getBarrioDetencion() {
        return barrioDetencion;
    }

    /**
     * @param barrioDetencion the barrioDetencion to set
     */
    public void setBarrioDetencion(String barrioDetencion) {
        this.barrioDetencion = barrioDetencion;
    }

    /**
     * @return the comunidadDetencion
     */
    public String getComunidadDetencion() {
        return comunidadDetencion;
    }

    /**
     * @param comunidadDetencion the comunidadDetencion to set
     */
    public void setComunidadDetencion(String comunidadDetencion) {
        this.comunidadDetencion = comunidadDetencion;
    }

    /**
     * @return the edad
     */
    public int getEdad() {      
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }   

    /**
     * @return the autoidentificacion
     */
    public String getAutoidentificacion() {
        return autoidentificacion;
    }

    /**
     * @param autoidentificacion the autoidentificacion to set
     */
    public void setAutoidentificacion(String autoidentificacion) {
        this.autoidentificacion = autoidentificacion;
    }

    /**
     * @return the idiomaCastellano
     */
    public boolean isIdiomaCastellano() {
        return idiomaCastellano;
    }

    /**
     * @param idiomaCastellano the idiomaCastellano to set
     */
    public void setIdiomaCastellano(boolean idiomaCastellano) {
        this.idiomaCastellano = idiomaCastellano;
    }

    /**
     * @return the idiomaIngles
     */
    public boolean isIdiomaIngles() {
        return idiomaIngles;
    }

    /**
     * @param idiomaIngles the idiomaIngles to set
     */
    public void setIdiomaIngles(boolean idiomaIngles) {
        this.idiomaIngles = idiomaIngles;
    }

    /**
     * @return the lenguaNativa
     */
    public boolean isLenguaNativa() {
        return lenguaNativa;
    }

    /**
     * @param lenguaNativa the lenguaNativa to set
     */
    public void setLenguaNativa(boolean lenguaNativa) {
        this.lenguaNativa = lenguaNativa;
    }

    /**
     * @return the lenguaNativaNombre
     */
    public String getLenguaNativaNombre() {
        return lenguaNativaNombre;
    }

    /**
     * @param lenguaNativa the lenguaNativaNombre to set
     */
    public void setLenguaNativaNombre(String lenguaNativa) {
        this.lenguaNativaNombre = lenguaNativa;
    }

    /**
     * @return the otroLenguaje
     */
    public boolean isOtroLenguaje() {
        return otroLenguaje;
    }

    /**
     * @param otroLenguaje the otroLenguaje to set
     */
    public void setOtroLenguaje(boolean otroLenguaje) {
        this.otroLenguaje = otroLenguaje;
    }

    /**
     * @return the otroLenguajeNombre
     */
    public String getOtroLenguajeNombre() {
        return otroLenguajeNombre;
    }

    /**
     * @param otroLenguaje the otroLenguajeNombre to set
     */
    public void setOtroLenguajeNombre(String otroLenguaje) {
        this.otroLenguajeNombre = otroLenguaje;
    }

    /**
     * @return the tipoOcupacion
     */
    public String getTipoOcupacion() {
        return tipoOcupacion;
    }

    /**
     * @param tipoOcupacion the tipoOcupacion to set
     */
    public void setTipoOcupacion(String tipoOcupacion) {
        this.tipoOcupacion = tipoOcupacion;
    }

    /**
     * @return the tiempoOcupacionActual
     */
    public String getTiempoOcupacionActual() {
        return tiempoOcupacionActual;
    }

    /**
     * @param tiempoOcupacionActual the tiempoOcupacionActual to set
     */
    public void setTiempoOcupacionActual(String tiempoOcupacionActual) {
        this.tiempoOcupacionActual = tiempoOcupacionActual;
    }

    /**
     * @return the tiempoResidenciaActual
     */
    public String getTiempoResidenciaActual() {
        return tiempoResidenciaActual;
    }

    /**
     * @param tiempoResidenciaActual the tiempoResidenciaActual to set
     */
    public void setTiempoResidenciaActual(String tiempoResidenciaActual) {
        this.tiempoResidenciaActual = tiempoResidenciaActual;
    }

    /**
     * @return the telefonoResidencia
     */
    public String getTelefonoResidencia() {
        return telefonoResidencia;
    }

    /**
     * @param telefonoResidencia the telefonoResidencia to set
     */
    public void setTelefonoResidencia(String telefonoResidencia) {
        this.telefonoResidencia = telefonoResidencia;
    }

    /**
     * @return the provinciaResidencia
     */
    public Provincia getProvinciaResidencia() {
        return provinciaResidencia;
    }

    /**
     * @param provinciaResidencia the provinciaResidencia to set
     */
    public void setProvinciaResidencia(Provincia provinciaResidencia) {
        this.provinciaResidencia = provinciaResidencia;
    }

    /**
     * @return the cantonResidencia
     */
    public Canton getCantonResidencia() {
        return cantonResidencia;
    }

    /**
     * @param cantonResidencia the cantonResidencia to set
     */
    public void setCantonResidencia(Canton cantonResidencia) {
        this.cantonResidencia = cantonResidencia;
    }

    /**
     * @return the parroquiaResidencia
     */
    public Parroquia getParroquiaResidencia() {
        return parroquiaResidencia;
    }

    /**
     * @param parroquiaResidencia the parroquiaResidencia to set
     */
    public void setParroquiaResidencia(Parroquia parroquiaResidencia) {
        this.parroquiaResidencia = parroquiaResidencia;
    }

    /**
     * @return the barrioResidencia
     */
    public String getBarrioResidencia() {
        return barrioResidencia;
    }

    /**
     * @param barrioResidencia the barrioResidencia to set
     */
    public void setBarrioResidencia(String barrioResidencia) {
        this.barrioResidencia = barrioResidencia;
    }

    /**
     * @return the comunidadResidencia
     */
    public String getComunidadResidencia() {
        return comunidadResidencia;
    }

    /**
     * @param comunidadResidencia the comunidadResidencia to set
     */
    public void setComunidadResidencia(String comunidadResidencia) {
        this.comunidadResidencia = comunidadResidencia;
    }

    /**
     * @return the numeroHijosHombres
     */
    public Integer getNumeroHijosHombres() {
        return numeroHijosHombres;
    }

    /**
     * @param numeroHijosHombres the numeroHijosHombres to set
     */
    public void setNumeroHijosHombres(Integer numeroHijosHombres) {
        this.numeroHijosHombres = numeroHijosHombres;
    }

    /**
     * @return the edadesHijosHombres
     */
    public String getEdadesHijosHombres() {
        return edadesHijosHombres;
    }

    /**
     * @param edadesHijosHombres the edadesHijosHombres to set
     */
    public void setEdadesHijosHombres(String edadesHijosHombres) {
        this.edadesHijosHombres = edadesHijosHombres;
    }

    /**
     * @return the numeroHijosMujeres
     */
    public Integer getNumeroHijosMujeres() {
        return numeroHijosMujeres;
    }

    /**
     * @param numeroHijosMujeres the numeroHijosMujeres to set
     */
    public void setNumeroHijosMujeres(Integer numeroHijosMujeres) {
        this.numeroHijosMujeres = numeroHijosMujeres;
    }

    /**
     * @return the edadesHijosMujeres
     */
    public String getEdadesHijosMujeres() {
        return edadesHijosMujeres;
    }

    /**
     * @param edadesHijosMujeres the edadesHijosMujeres to set
     */
    public void setEdadesHijosMujeres(String edadesHijosMujeres) {
        this.edadesHijosMujeres = edadesHijosMujeres;
    }

    /**
     * @return the ingresaHijos
     */
    public boolean isIngresaHijos() {
        return ingresaHijos;
    }

    /**
     * @param ingresaHijos the ingresaHijos to set
     */
    public void setIngresaHijos(boolean ingresaHijos) {
        this.ingresaHijos = ingresaHijos;
    }

    /**
     * @return the numeroHijosEdades
     */
    public String getNumeroHijosEdades() {
        return numeroHijosEdades;
    }

    /**
     * @param numeroHijosEdades the numeroHijosEdades to set
     */
    public void setNumeroHijosEdades(String numeroHijosEdades) {
        this.numeroHijosEdades = numeroHijosEdades;
    }

    /**
     * @return the nombrePersona1
     */
    public String getNombrePersona1() {
        return nombrePersona1;
    }

    /**
     * @param nombrePersona1 the nombrePersona1 to set
     */
    public void setNombrePersona1(String nombrePersona1) {
        this.nombrePersona1 = nombrePersona1;
    }

    /**
     * @return the parentescoPersona1
     */
    public String getParentescoPersona1() {
        return parentescoPersona1;
    }

    /**
     * @param parentescoPersona1 the parentescoPersona1 to set
     */
    public void setParentescoPersona1(String parentescoPersona1) {
        this.parentescoPersona1 = parentescoPersona1;
    }

    /**
     * @return the telefonoPersona1
     */
    public String getTelefonoPersona1() {
        return telefonoPersona1;
    }

    /**
     * @param telefonoPersona1 the telefonoPersona1 to set
     */
    public void setTelefonoPersona1(String telefonoPersona1) {
        this.telefonoPersona1 = telefonoPersona1;
    }

    /**
     * @return the nombrePersona2
     */
    public String getNombrePersona2() {
        return nombrePersona2;
    }

    /**
     * @param nombrePersona2 the nombrePersona2 to set
     */
    public void setNombrePersona2(String nombrePersona2) {
        this.nombrePersona2 = nombrePersona2;
    }

    /**
     * @return the parentescoPersona2
     */
    public String getParentescoPersona2() {
        return parentescoPersona2;
    }

    /**
     * @param parentescoPersona2 the parentescoPersona2 to set
     */
    public void setParentescoPersona2(String parentescoPersona2) {
        this.parentescoPersona2 = parentescoPersona2;
    }

    /**
     * @return the telefonoPersona2
     */
    public String getTelefonoPersona2() {
        return telefonoPersona2;
    }

    /**
     * @param telefonoPersona2 the telefonoPersona2 to set
     */
    public void setTelefonoPersona2(String telefonoPersona2) {
        this.telefonoPersona2 = telefonoPersona2;
    }

    /**
     * @return the representante
     */
    public Persona getRepresentante() {
        return representante;
    }

    /**
     * @param representante the representante to set
     */
    public void setRepresentante(Persona representante) {
        this.representante = representante;
    }

    /**
     * @return the condFisIngMoretones
     */
    public boolean isCondFisIngMoretones() {
        return condFisIngMoretones;
    }

    /**
     * @param condFisIngMoretones the condFisIngMoretones to set
     */
    public void setCondFisIngMoretones(boolean condFisIngMoretones) {
        this.condFisIngMoretones = condFisIngMoretones;
    }

    /**
     * @return the condFisIngLastimados
     */
    public boolean isCondFisIngLastimados() {
        return condFisIngLastimados;
    }

    /**
     * @param condFisIngLastimados the condFisIngLastimados to set
     */
    public void setCondFisIngLastimados(boolean condFisIngLastimados) {
        this.condFisIngLastimados = condFisIngLastimados;
    }

    /**
     * @return the condFisIngOtros
     */
    public boolean isCondFisIngOtros() {
        return condFisIngOtros;
    }

    /**
     * @param condFisIngOtros the condFisIngOtros to set
     */
    public void setCondFisIngOtros(boolean condFisIngOtros) {
        this.condFisIngOtros = condFisIngOtros;
    }

    /**
     * @return the condFisIngOtrosNombre
     */
    public String getCondFisIngOtrosNombre() {
        return condFisIngOtrosNombre;
    }

    /**
     * @param condFisIngOtrosNombre the condFisIngOtrosNombre to set
     */
    public void setCondFisIngOtrosNombre(String condFisIngOtrosNombre) {
        this.condFisIngOtrosNombre = condFisIngOtrosNombre;
    }

    /**
     * @return the condEmoIngTranquilo
     */
    public boolean isCondEmoIngTranquilo() {
        return condEmoIngTranquilo;
    }

    /**
     * @param condEmoIngTranquilo the condEmoIngTranquilo to set
     */
    public void setCondEmoIngTranquilo(boolean condEmoIngTranquilo) {
        this.condEmoIngTranquilo = condEmoIngTranquilo;
    }

    /**
     * @return the condEmoIngExtrovertido
     */
    public boolean isCondEmoIngExtrovertido() {
        return condEmoIngExtrovertido;
    }

    /**
     * @param condEmoIngExtrovertido the condEmoIngExtrovertido to set
     */
    public void setCondEmoIngExtrovertido(boolean condEmoIngExtrovertido) {
        this.condEmoIngExtrovertido = condEmoIngExtrovertido;
    }

    /**
     * @return the condEmoIngIntrovertido
     */
    public boolean isCondEmoIngIntrovertido() {
        return condEmoIngIntrovertido;
    }

    /**
     * @param condEmoIngIntrovertido the condEmoIngIntrovertido to set
     */
    public void setCondEmoIngIntrovertido(boolean condEmoIngIntrovertido) {
        this.condEmoIngIntrovertido = condEmoIngIntrovertido;
    }

    /**
     * @return the condEmoIngAgresivo
     */
    public boolean isCondEmoIngAgresivo() {
        return condEmoIngAgresivo;
    }

    /**
     * @param condEmoIngAgresivo the condEmoIngAgresivo to set
     */
    public void setCondEmoIngAgresivo(boolean condEmoIngAgresivo) {
        this.condEmoIngAgresivo = condEmoIngAgresivo;
    }

    /**
     * @return the condEmoIngAsustado
     */
    public boolean isCondEmoIngAsustado() {
        return condEmoIngAsustado;
    }

    /**
     * @param condEmoIngAsustado the condEmoIngAsustado to set
     */
    public void setCondEmoIngAsustado(boolean condEmoIngAsustado) {
        this.condEmoIngAsustado = condEmoIngAsustado;
    }

    /**
     * @return the condEmoIngOtros
     */
    public boolean isCondEmoIngOtros() {
        return condEmoIngOtros;
    }

    /**
     * @param condEmoIngOtros the condEmoIngOtros to set
     */
    public void setCondEmoIngOtros(boolean condEmoIngOtros) {
        this.condEmoIngOtros = condEmoIngOtros;
    }

    /**
     * @return the condEmoIngOtrosNombre
     */
    public String getCondEmoIngOtrosNombre() {
        return condEmoIngOtrosNombre;
    }

    /**
     * @param condEmoIngOtrosNombre the condEmoIngOtrosNombre to set
     */
    public void setCondEmoIngOtrosNombre(String condEmoIngOtrosNombre) {
        this.condEmoIngOtrosNombre = condEmoIngOtrosNombre;
    }

    /**
     * @return the condSaludEnfermedad
     */
    public String getCondSaludEnfermedad() {
        return condSaludEnfermedad;
    }

    /**
     * @param condSaludEnfermedad the condSaludEnfermedad to set
     */
    public void setCondSaludEnfermedad(String condSaludEnfermedad) {
        this.condSaludEnfermedad = condSaludEnfermedad;
    }

    /**
     * @return the condSaludMedicina
     */
    public String getCondSaludMedicina() {
        return condSaludMedicina;
    }

    /**
     * @param condSaludMedicina the condSaludMedicina to set
     */
    public void setCondSaludMedicina(String condSaludMedicina) {
        this.condSaludMedicina = condSaludMedicina;
    }

    /**
     * @return the condSaludSintDroga
     */
    public boolean isCondSaludSintDroga() {
        return condSaludSintDroga;
    }

    /**
     * @param condSaludSintDroga the condSaludSintDroga to set
     */
    public void setCondSaludSintDroga(boolean condSaludSintDroga) {
        this.condSaludSintDroga = condSaludSintDroga;
    }

    /**
     * @return the pertDescripcion
     */
    public String getPertDescripcion() {
        return pertDescripcion;
    }

    /**
     * @param pertDescripcion the pertDescripcion to set
     */
    public void setPertDescripcion(String pertDescripcion) {
        this.pertDescripcion = pertDescripcion;
    }


//    /**
//     * @return the firma
//     */
//    public String getFirma() {
//        return firma;
//    }
//
//    /**
//     * @param firma the firma to set
//     */
//    public void setFirma(String firma) {
//        this.firma = firma;
//    }

    /**
     * @return the delitoFlagrante
     */
    public boolean isDelitoFlagrante() {
        return delitoFlagrante;
    }

    /**
     * @param delitoFlagrante the delitoFlagrante to set
     */
    public void setDelitoFlagrante(boolean delitoFlagrante) {
        this.delitoFlagrante = delitoFlagrante;
    }

    /**
     * @return the medidasCautelares
     */
    public boolean isMedidasCautelares() {
        return medidasCautelares;
    }

    /**
     * @param medidasCautelares the medidasCautelares to set
     */
    public void setMedidasCautelares(boolean medidasCautelares) {
        this.medidasCautelares = medidasCautelares;
    }

    /**
     * @return the medidasSocioeducativas
     */
    public boolean isMedidasSocioeducativas() {
        return medidasSocioeducativas;
    }

    /**
     * @param medidasSocioeducativas the medidasSocioeducativas to set
     */
    public void setMedidasSocioeducativas(boolean medidasSocioeducativas) {
        this.medidasSocioeducativas = medidasSocioeducativas;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }   

    /**
     * @return the otraNacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param otraNacionalidad the otraNacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @return the responsableTraslado
     */
    public Persona getResponsableTraslado() {
        return responsableTraslado;
    }

    /**
     * @param responsableTraslado the responsableTraslado to set
     */
    public void setResponsableTraslado(Persona responsableTraslado) {
        this.responsableTraslado = responsableTraslado;
    }

    /**
     * @return the responsableIngreso
     */
    public Persona getResponsableIngreso() {
        return responsableIngreso;
    }

    /**
     * @param responsableIngreso the responsableIngreso to set
     */
    public void setResponsableIngreso(Persona responsableIngreso) {
        this.responsableIngreso = responsableIngreso;
    }

    /**
     * @return the reponsableResguardoPertenencia
     */
    public Persona getResponsableResguardoPertenencia() {
        return responsableResguardoPertenencia;
    }

    /**
     * @param reponsableResguardoPertenencia the reponsableResguardoPertenencia to set
     */
    public void setResponsableResguardoPertenencia(Persona responsableResguardoPertenencia) {
        this.responsableResguardoPertenencia = responsableResguardoPertenencia;
    }

    /**
     * @return the idFichaEgreso
     */
    public FichaEgreso getFichaEgreso() {
        return this.fichaEgreso;
    }

    /**
     * @param fichaEgreso the idFichaEgreso to set
     */
    public void setFichaEgreso(FichaEgreso fichaEgreso) {
        this.fichaEgreso = fichaEgreso;
    }

    /**
     * @return the razonIngreso
     */
    public String getRazonIngreso() {
        return razonIngreso;
    }

    /**
     * @param razonIngreso the razonIngreso to set
     */
    public void setRazonIngreso(String razonIngreso) {
        this.razonIngreso = razonIngreso;
    }    

    /**
     * @return the documentos
     */
    public Set<FichaIngresoDocumento> getDocumentos() {
        return documentos;
    }

    /**
     * @param documentos the documentos to set
     */
    public void setDocumentos(Set<FichaIngresoDocumento> documentos) {
        this.documentos = documentos;
    }    

    /**
     * @return the nivelEducativoTipo
     */
    public String getNivelEducativoTipo() {
        return nivelEducativoTipo;
    }

    /**
     * @param nivelEducativoTipo the nivelEducativoTipo to set
     */
    public void setNivelEducativoTipo(String nivelEducativoTipo) {
        this.nivelEducativoTipo = nivelEducativoTipo;
    }

    /**
     * @return the nivelEducativoNombre
     */
    public String getNivelEducativoNombre() {
        return nivelEducativoNombre;
    }

    /**
     * @param nivelEducativoNombre the nivelEducativoNombre to set
     */
    public void setNivelEducativoNombre(String nivelEducativoNombre) {
        this.nivelEducativoNombre = nivelEducativoNombre;
    }
}