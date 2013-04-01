/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.modelo.core;

/**
 *
 * @author Usuario
 */
public class Constantes {
    
    public static final String PRM_INSTITUCION = "PRM_INSTITUCION";
    public static final String PRM_SLOGAN = "PRM_SLOGAN";
    public static final String PRM_SIGLAS = "PRM_SIGLAS";
    public static final String PRM_TEMA_HOME = "PRM_TEMA_HOME";
    public static final String PRM_SIZE_IMGANE_GALERIA = "PRM_SIZE_IMGANE_GALERIA";
    public static final String PRM_PORCENTAJE_EVALUADOR = "PRM_PORCENTAJE_EVALUADOR";
    public static final String PRM_NOMNIV_REHABILITACION_1 = "PRM_NOMNIV_REHABILITACION_1";
    public static final String PRM_NOMNIV_REHABILITACION_2 = "PRM_NOMNIV_REHABILITACION_2";
    public static final String PRM_NOMNIV_REHABILITACION_3 = "PRM_NOMNIV_REHABILITACION_3";
    public static final String PRM_MIN_ANIOS_FECHA_NAC = "PRM_MIN_ANIOS_FECHA_NAC";
    
    //Bean de session
    public static final String MB_SESSION = "sessionBean";
    
    //BEANS
    public static final String MB_MAIN                  = "mainController";
    public static final String MB_MENU                  = "menuController";
    public static final String MB_LOGIN                 = "loginController";    
    public static final String MB_WUC_BUSCAR_PERSONA    = "wucBuscarPersona";    
    public static final String MB_WUC_MULTIMEDIA        = "wucMultimedia";    
    public static final String MB_WUC_ACTIVIDAD         = "wucActividad";    
    public static final String MB_OPCION                = "opcionController";    
    public static final String MB_USUARIO               = "usuarioController";
    public static final String MB_PERFIL                = "perfilController";
    public static final String MB_PARAMETRO             = "parametroController";
    public static final String MB_FICHAINGRESO          = "fichaIngresoController";   
    public static final String MB_ADOLESCENTES          = "adolescenteController";
    public static final String MB_EMPLEADOS              = "empleadoController";
    public static final String MB_BANDEJA               = "bandejaController";
    public static final String MB_FICHAEGRESO           = "fichaEgresoController";
    public static final String MB_ESQUEMA               = "esquemaController";
    public static final String MB_EV_HTP                = "formHtpController";
    public static final String MB_AGENDA                = "agendaController";
    public static final String MB_DIVISION              = "divisionController";
    public static final String MB_ARTICULO_WEB          = "articuloController";
    public static final String MB_GALERIA               = "galeriaController";
    public static final String MB_REPORTE               = "reporteController";    
    public static final String MB_CATEGORIA             = "categoriaController";
    public static final String MB_INFORME           = "informeController";
                
    //SEGURIDAD
    public static final String VW_ADM_USUARIO   = "seguridad/adminUsuarios.xhtml";
    public static final String VW_EDT_USUARIO   = "seguridad/editarUsuario.xhtml";    
    public static final String VW_ADM_OPCION    = "seguridad/adminOpciones.xhtml";
    public static final String VW_EDT_OPCION    = "seguridad/editarOpcion.xhtml";    
    public static final String VW_ADM_PERFIL    = "seguridad/adminPerfiles.xhtml";
    public static final String VW_EDT_PERFIL    = "seguridad/editarPerfil.xhtml";
    public static final String VW_ADM_PARAMETRO = "seguridad/adminParametros.xhtml";
    public static final String VW_EDT_PARAMETRO = "seguridad/editarParametro.xhtml";
    
    //FICHAS DE INGRESO    
    public static final String VW_EDT_FICHAINGRESO    = "sira/editarFichaIngreso.xhtml";
    public static final String VW_EXP_FICHAINGRESO    = "sira/exportarFichaIngreso.xhtml";
    public static final String VW_BANDEJA             = "sira/adminBandeja.xhtml";
    
    public static final String VW_ADM_ADOLESCENTE    = "sira/adminAdolescentes.xhtml";
    public static final String VW_EDT_ADOLESCENTE    = "sira/editarAdolescente.xhtml";
    
    //EMPLEADOS
    public static final String VW_ADM_EMPLEADOS    = "sira/adminEmpleados.xhtml";
    public static final String VW_EDT_EMPLEADO    = "sira/editarEmpleado.xhtml";
    
    //FICHAS DE EGRESO    
    public static final String VW_EDT_FICHAEGRESO    = "sira/editarFichaEgreso.xhtml";
    public static final String VW_EXP_FICHAEGRESO    = "sira/exportarFichaEgreso.xhtml";
    
    //ESQUEMA
    public static final String VW_ADM_ESQUEMA = "htp/adminEsquema.xhtml";
    public static final String VW_EDT_ESQUEMA = "htp/editarEsquema.xhtml";
    
    //TEST HTP
    public static final String VW_EDT_TEST_HTP          = "htp/editarTestHtp.xhtml";
    public static final String VW_INTERPRETAR_TESTHTP   = "htp/interpretarTestHtp.xhtml";
    public static final String VW_EDT_INFORME_PSC       = "htp/informePsicologicoTestHtp.xhtml";        
    public static final String VW_EDT_INTERPRETACION    = "htp/editarInterpretacionTestHtp.xhtml";        
    
    
    //AGENDAS
    public static final String VW_ADM_AGENDAS    = "sira/adminAgendas.xhtml";
    public static final String VW_EDT_AGENDA    = "sira/editarAgenda.xhtml";
    public static final String VW_FLZ_AGENDA    = "sira/finalizarAgenda.xhtml";
    
    //DIVISION POLITICA
    public static final String VW_ADM_PROVINCIAS    = "loc/adminProvincias.xhtml";
    public static final String VW_EDT_PROVINCIA     = "loc/editarProvincia.xhtml";
    public static final String VW_EDT_CANTON        = "loc/editarCanton.xhtml";
    public static final String VW_EDT_PARROQUIA     = "loc/editarParroquia.xhtml";
    
    //ARTICULOS
    public static final String VW_LEE_ARTICULO    = "leerArticuloWeb.xhtml";
    public static final String VW_EDT_ARTICULO    = "web/editarArticuloWeb.xhtml";
    public static final String VW_ADM_ARTICULOS   = "web/adminArticulosWeb.xhtml";
    public static final String VW_ADM_GALERIA     = "web/adminGaleria.xhtml";
    public static final String VW_EDT_IMAGEN      = "web/editarImagen.xhtml";
    
    //CATEGORIAS
    public static final String VW_ADM_CATEGORIAS    = "htp/adminCategorias.xhtml";    
    public static final String VW_EDT_CATEGORIA     = "htp/editarCategoria.xhtml";
    
    public static final String VW_EDT_INFORME = "htp/informePsicologicoTestHtp.xhtml";
    
    //REPORTES
    public static final String VW_LISTA_TESTS_HTP      = "reporte/listaTestsHtp.xhtml";
    public static final String VW_LISTA_FICHAEGRESO    = "reporte/listaFichasEgreso.xhtml";
    public static final String VW_LISTA_FICHAINGRESO   = "reporte/listaFichasIngreso.xhtml";        
}
