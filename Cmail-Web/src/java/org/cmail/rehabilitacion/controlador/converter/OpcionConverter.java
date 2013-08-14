/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.cmail.rehabilitacion.modelo.seguridad.Opcion;
import org.cmail.rehabilitacion.servicio.OpcionServicio;

/**
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class OpcionConverter implements Converter {

//    private List<Opcion> listadoOpciones;
//
//    //NO SE ESTA USANDO
//    public OpcionConverter(List<Opcion> lista) {
//        this.listadoOpciones = lista;
//    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null) {
            return null;
        }
        //CON BUSQUEDA DESDE LA BASE DE DATOS
        Long id = Long.parseLong(value);
        Opcion opcion = new OpcionServicio().obtenerPorId(id);
        if(opcion!=null){
            return opcion;           
        }
        return null;
//
//        Opcion opp = null;
//        if (!listadoOpciones.isEmpty()) {
//            for (Iterator<Opcion> it = listadoOpciones.iterator(); it.hasNext();) {
//                Opcion opcion = it.next();
//                if (opcion != null) {
//                    Long id = new Long(value);
//                    if (opcion.getId() == id) {
//                        opp= opcion;
//                        break;
//                    }
//                }
//            }
//        }
//        return opp;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Opcion)o).getId().toString();
    }
}
