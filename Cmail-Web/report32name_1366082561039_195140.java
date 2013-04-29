/*
 * Generated by JasperReports - 15/04/13 10:22 PM
 */
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.*;

import java.util.*;
import java.math.*;
import java.text.*;
import java.io.*;
import java.net.*;



/**
 *
 */
public class report32name_1366082561039_195140 extends JREvaluator
{


    /**
     *
     */
    private JRFillParameter parameter_JASPER_REPORT = null;
    private JRFillParameter parameter_REPORT_TIME_ZONE = null;
    private JRFillParameter parameter_REPORT_FILE_RESOLVER = null;
    private JRFillParameter parameter_REPORT_PARAMETERS_MAP = null;
    private JRFillParameter parameter_PRM_VISTO = null;
    private JRFillParameter parameter_REPORT_CONTEXT = null;
    private JRFillParameter parameter_REPORT_CLASS_LOADER = null;
    private JRFillParameter parameter_REPORT_DATA_SOURCE = null;
    private JRFillParameter parameter_REPORT_URL_HANDLER_FACTORY = null;
    private JRFillParameter parameter_IS_IGNORE_PAGINATION = null;
    private JRFillParameter parameter_REPORT_MAX_COUNT = null;
    private JRFillParameter parameter_REPORT_TEMPLATES = null;
    private JRFillParameter parameter_PRM_LOGO = null;
    private JRFillParameter parameter_OTROS_CUAL = null;
    private JRFillParameter parameter_REPORT_LOCALE = null;
    private JRFillParameter parameter_PRM_INSTITUCION = null;
    private JRFillParameter parameter_REPORT_VIRTUALIZER = null;
    private JRFillParameter parameter_SORT_FIELDS = null;
    private JRFillParameter parameter_PRM_ESCUDO = null;
    private JRFillParameter parameter_PRM_MODEL = null;
    private JRFillParameter parameter_REPORT_SCRIPTLET = null;
    private JRFillParameter parameter_REPORT_CONNECTION = null;
    private JRFillParameter parameter_FILTER = null;
    private JRFillParameter parameter_PRM_LUGAR_DIA_FECHA = null;
    private JRFillParameter parameter_PRM_JUEZ = null;
    private JRFillParameter parameter_REPORT_FORMAT_FACTORY = null;
    private JRFillParameter parameter_REPORT_RESOURCE_BUNDLE = null;
    private JRFillVariable variable_PAGE_NUMBER = null;
    private JRFillVariable variable_COLUMN_NUMBER = null;
    private JRFillVariable variable_REPORT_COUNT = null;
    private JRFillVariable variable_PAGE_COUNT = null;
    private JRFillVariable variable_COLUMN_COUNT = null;


    /**
     *
     */
    public void customizedInit(
        Map pm,
        Map fm,
        Map vm
        )
    {
        initParams(pm);
        initFields(fm);
        initVars(vm);
    }


    /**
     *
     */
    private void initParams(Map pm)
    {
        parameter_JASPER_REPORT = (JRFillParameter)pm.get("JASPER_REPORT");
        parameter_REPORT_TIME_ZONE = (JRFillParameter)pm.get("REPORT_TIME_ZONE");
        parameter_REPORT_FILE_RESOLVER = (JRFillParameter)pm.get("REPORT_FILE_RESOLVER");
        parameter_REPORT_PARAMETERS_MAP = (JRFillParameter)pm.get("REPORT_PARAMETERS_MAP");
        parameter_PRM_VISTO = (JRFillParameter)pm.get("PRM_VISTO");
        parameter_REPORT_CONTEXT = (JRFillParameter)pm.get("REPORT_CONTEXT");
        parameter_REPORT_CLASS_LOADER = (JRFillParameter)pm.get("REPORT_CLASS_LOADER");
        parameter_REPORT_DATA_SOURCE = (JRFillParameter)pm.get("REPORT_DATA_SOURCE");
        parameter_REPORT_URL_HANDLER_FACTORY = (JRFillParameter)pm.get("REPORT_URL_HANDLER_FACTORY");
        parameter_IS_IGNORE_PAGINATION = (JRFillParameter)pm.get("IS_IGNORE_PAGINATION");
        parameter_REPORT_MAX_COUNT = (JRFillParameter)pm.get("REPORT_MAX_COUNT");
        parameter_REPORT_TEMPLATES = (JRFillParameter)pm.get("REPORT_TEMPLATES");
        parameter_PRM_LOGO = (JRFillParameter)pm.get("PRM_LOGO");
        parameter_OTROS_CUAL = (JRFillParameter)pm.get("OTROS_CUAL");
        parameter_REPORT_LOCALE = (JRFillParameter)pm.get("REPORT_LOCALE");
        parameter_PRM_INSTITUCION = (JRFillParameter)pm.get("PRM_INSTITUCION");
        parameter_REPORT_VIRTUALIZER = (JRFillParameter)pm.get("REPORT_VIRTUALIZER");
        parameter_SORT_FIELDS = (JRFillParameter)pm.get("SORT_FIELDS");
        parameter_PRM_ESCUDO = (JRFillParameter)pm.get("PRM_ESCUDO");
        parameter_PRM_MODEL = (JRFillParameter)pm.get("PRM_MODEL");
        parameter_REPORT_SCRIPTLET = (JRFillParameter)pm.get("REPORT_SCRIPTLET");
        parameter_REPORT_CONNECTION = (JRFillParameter)pm.get("REPORT_CONNECTION");
        parameter_FILTER = (JRFillParameter)pm.get("FILTER");
        parameter_PRM_LUGAR_DIA_FECHA = (JRFillParameter)pm.get("PRM_LUGAR_DIA_FECHA");
        parameter_PRM_JUEZ = (JRFillParameter)pm.get("PRM_JUEZ");
        parameter_REPORT_FORMAT_FACTORY = (JRFillParameter)pm.get("REPORT_FORMAT_FACTORY");
        parameter_REPORT_RESOURCE_BUNDLE = (JRFillParameter)pm.get("REPORT_RESOURCE_BUNDLE");
    }


    /**
     *
     */
    private void initFields(Map fm)
    {
    }


    /**
     *
     */
    private void initVars(Map vm)
    {
        variable_PAGE_NUMBER = (JRFillVariable)vm.get("PAGE_NUMBER");
        variable_COLUMN_NUMBER = (JRFillVariable)vm.get("COLUMN_NUMBER");
        variable_REPORT_COUNT = (JRFillVariable)vm.get("REPORT_COUNT");
        variable_PAGE_COUNT = (JRFillVariable)vm.get("PAGE_COUNT");
        variable_COLUMN_COUNT = (JRFillVariable)vm.get("COLUMN_COUNT");
    }


    /**
     *
     */
    public Object evaluate(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = "Otros - ¿Cuál? "; //$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = ((java.lang.String)parameter_PRM_LOGO.getValue()); //$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = ((java.lang.String)parameter_PRM_INSTITUCION.getValue()); //$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getNombres() + " " + ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getApellidos(); //$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getEdad(); //$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getEstadoCivil(); //$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getCedula(); //$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getNivelEducativoTipo(); //$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getDireccion() + " - " + ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getTelefono(); //$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getNumeroHijosEdades(); //$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getRazonIngreso(); //$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getSituacionPresente(); //$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAnamnesisAntecedentes(); //$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getHistoriaLaboral(); //$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getHistoriaSalud(); //$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesIdentidadFortalezas(); //$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesIdentidadDebilidades(); //$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesSaludFortalezas(); //$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesSaludDebilidades(); //$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesEducacionFortalezas(); //$JR_EXPR_ID=27$
                break;
            }
            case 28 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesEducacionDebilidades(); //$JR_EXPR_ID=28$
                break;
            }
            case 29 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesVinvulosFamiliaresFortalezas(); //$JR_EXPR_ID=29$
                break;
            }
            case 30 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesVinculosFamiliaresDebilidades(); //$JR_EXPR_ID=30$
                break;
            }
            case 31 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesFormacionTrabajoFortalezas(); //$JR_EXPR_ID=31$
                break;
            }
            case 32 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesFormacionTrabajoDebilidades(); //$JR_EXPR_ID=32$
                break;
            }
            case 33 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getExamenFunciones(); //$JR_EXPR_ID=33$
                break;
            }
            case 34 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getInformacionPuebasPsicologicas(); //$JR_EXPR_ID=34$
                break;
            }
            case 35 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getInformacionReactivosTest(); //$JR_EXPR_ID=35$
                break;
            }
            case 36 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getImpresioesDiagnosticasPsicologo(); //$JR_EXPR_ID=36$
                break;
            }
            case 37 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getPlanAcompaniamiento(); //$JR_EXPR_ID=37$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateOld(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = "Otros - ¿Cuál? "; //$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = ((java.lang.String)parameter_PRM_LOGO.getValue()); //$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = ((java.lang.String)parameter_PRM_INSTITUCION.getValue()); //$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getNombres() + " " + ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getApellidos(); //$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getEdad(); //$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getEstadoCivil(); //$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getCedula(); //$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getNivelEducativoTipo(); //$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getDireccion() + " - " + ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getTelefono(); //$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getNumeroHijosEdades(); //$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getRazonIngreso(); //$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getSituacionPresente(); //$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAnamnesisAntecedentes(); //$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getHistoriaLaboral(); //$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getHistoriaSalud(); //$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesIdentidadFortalezas(); //$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesIdentidadDebilidades(); //$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesSaludFortalezas(); //$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesSaludDebilidades(); //$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesEducacionFortalezas(); //$JR_EXPR_ID=27$
                break;
            }
            case 28 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesEducacionDebilidades(); //$JR_EXPR_ID=28$
                break;
            }
            case 29 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesVinvulosFamiliaresFortalezas(); //$JR_EXPR_ID=29$
                break;
            }
            case 30 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesVinculosFamiliaresDebilidades(); //$JR_EXPR_ID=30$
                break;
            }
            case 31 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesFormacionTrabajoFortalezas(); //$JR_EXPR_ID=31$
                break;
            }
            case 32 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesFormacionTrabajoDebilidades(); //$JR_EXPR_ID=32$
                break;
            }
            case 33 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getExamenFunciones(); //$JR_EXPR_ID=33$
                break;
            }
            case 34 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getInformacionPuebasPsicologicas(); //$JR_EXPR_ID=34$
                break;
            }
            case 35 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getInformacionReactivosTest(); //$JR_EXPR_ID=35$
                break;
            }
            case 36 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getImpresioesDiagnosticasPsicologo(); //$JR_EXPR_ID=36$
                break;
            }
            case 37 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getPlanAcompaniamiento(); //$JR_EXPR_ID=37$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateEstimated(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = "Otros - ¿Cuál? "; //$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = ((java.lang.String)parameter_PRM_LOGO.getValue()); //$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = ((java.lang.String)parameter_PRM_INSTITUCION.getValue()); //$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getNombres() + " " + ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getApellidos(); //$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getEdad(); //$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getEstadoCivil(); //$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getCedula(); //$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getNivelEducativoTipo(); //$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getDireccion() + " - " + ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAdolescente().getTelefono(); //$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getNumeroHijosEdades(); //$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getFichaIngreso().getRazonIngreso(); //$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getSituacionPresente(); //$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getAnamnesisAntecedentes(); //$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getHistoriaLaboral(); //$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getHistoriaSalud(); //$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesIdentidadFortalezas(); //$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesIdentidadDebilidades(); //$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesSaludFortalezas(); //$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesSaludDebilidades(); //$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesEducacionFortalezas(); //$JR_EXPR_ID=27$
                break;
            }
            case 28 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesEducacionDebilidades(); //$JR_EXPR_ID=28$
                break;
            }
            case 29 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesVinvulosFamiliaresFortalezas(); //$JR_EXPR_ID=29$
                break;
            }
            case 30 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesVinculosFamiliaresDebilidades(); //$JR_EXPR_ID=30$
                break;
            }
            case 31 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesFormacionTrabajoFortalezas(); //$JR_EXPR_ID=31$
                break;
            }
            case 32 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getCondicionesFormacionTrabajoDebilidades(); //$JR_EXPR_ID=32$
                break;
            }
            case 33 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getExamenFunciones(); //$JR_EXPR_ID=33$
                break;
            }
            case 34 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getInformacionPuebasPsicologicas(); //$JR_EXPR_ID=34$
                break;
            }
            case 35 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getInformacionReactivosTest(); //$JR_EXPR_ID=35$
                break;
            }
            case 36 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getImpresioesDiagnosticasPsicologo(); //$JR_EXPR_ID=36$
                break;
            }
            case 37 : 
            {
                value = ((org.cmail.rehabilitacion.modelo.htp.InformePsicologico)parameter_PRM_MODEL.getValue()).getPlanAcompaniamiento(); //$JR_EXPR_ID=37$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


}
