/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.servicio;

import java.util.Iterator;
import java.util.Set;
import org.cmail.rehabilitacion.excepcion.LayerException;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.htp.InterpretacionTestHtp;
import org.cmail.rehabilitacion.modelo.htp.TestHtp;
import org.cmail.rehabilitacion.modelo.htp.TestHtpRespuesta;
import org.cmail.rehabilitacion.modelo.seguridad.Parametro;
import org.cmail.rehabilitacion.modelo.seguridad.TipoParametro;

/**
 *
 * @author Usuario
 */
public class InterpretacionHtpServicio extends GenericServicio<InterpretacionTestHtp> {

    public InterpretacionHtpServicio() {
        super(InterpretacionTestHtp.class);
    }
    
    public InterpretacionTestHtp crearInterpretacion (TestHtp test){
        
        InterpretacionTestHtp interpretacion = new InterpretacionTestHtp();
        interpretacion.setTestHtp(test);
        interpretacion.setAdolescente(test.getAdolescente());
        interpretacion.setFichaIngreso(test.getFichaIngreso());
        
        String resumenInterrogativo = this.generarResumenInterrogativo(test);
        interpretacion.setInterrogativoPosterior(resumenInterrogativo);
        
        return interpretacion;
    }
    
    public String generarResumenInterrogativo (TestHtp test){
        
        String r = "";
        
        Set<TestHtpRespuesta> lista =  test.getRespuestas();
        for (Iterator<TestHtpRespuesta> it = lista.iterator(); it.hasNext();) {
            TestHtpRespuesta rta = it.next();
            if(rta.getEsquemaPregunta().isRequerida()){
                String srta = rta.getRespuesta();
                if(srta != null && srta.trim().length() > 0){
                    r += (r.length() > 0 ? "; " : "") + rta.getRespuesta();
                }
            }
        }
        
        return r;
    }
    
    public void calcularNivelRehabilitacion(InterpretacionTestHtp interpretacion) throws LayerException{
        //Porcentaje de calificacion el evaluador
        Parametro prm = new ParametroServicio().obtenerParametro(Constantes.PRM_PORCENTAJE_EVALUADOR, TipoParametro.Porcentaje);
        double porEval = prm.toDouble(); // 20%

        if(porEval >= 0 && porEval <= 100){

            //Porcentaje de calificacion del sistema
            double porSis = 100 - porEval; // 100 - 20 = 80%

            //Calcula los valores correspondientes
            double valorEval = (interpretacion.getPorcenjateRehabilitacionEvaluador() * porEval) / 100.0;
            double valorSist = (interpretacion.getPorcenjateRehabilitacionSistema() * porSis) / 100.0;

            //Total de rehabilitación
            double total = valorEval + valorSist;
            interpretacion.setPorcenjateRehabilitacion(total);                        
                
            ParametroServicio ps = new ParametroServicio();            
            Parametro pNombreNivel = null;
                    
            if(total < 40){                
                interpretacion.setNivelRehabilitacion(1);                
                pNombreNivel = ps.obtenerParametro(Constantes.PRM_NOMNIV_REHABILITACION_1, TipoParametro.Cadena);
            }else{
                if(total < 80){
                    interpretacion.setNivelRehabilitacion(2);
                    pNombreNivel = ps.obtenerParametro(Constantes.PRM_NOMNIV_REHABILITACION_2, TipoParametro.Cadena);
                }else{
                    interpretacion.setNivelRehabilitacion(3);
                    pNombreNivel = ps.obtenerParametro(Constantes.PRM_NOMNIV_REHABILITACION_3, TipoParametro.Cadena);
                }
            }
            
            interpretacion.setNivelRehabilitacionNombre(pNombreNivel.getValor());
        }else{
            throw new LayerException("val_porcentaje");
        }
    }        
    
}
