/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador.event;

import java.io.File;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import org.cmail.rehabilitacion.controlador.WucMultimediaController;

public class ActionListenerWucMultimedia implements javax.faces.event.ActionListener{   

    private File file;
    private WucMultimediaController wuc;
    private String rutaAbsoluta;

    public ActionListenerWucMultimedia() {
    }
    
    public void processAction(ActionEvent ae, String ruta, WucMultimediaController wuc){
        this.rutaAbsoluta =  ruta;
        this.setWuc(wuc);
        this.processAction(ae);
    }
    
    public void processAction(ActionEvent ae) throws AbortProcessingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }    

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the wuc
     */
    public WucMultimediaController getWuc() {
        return wuc;
    }

    /**
     * @param wuc the wuc to set
     */
    public void setWuc(WucMultimediaController wuc) {
        this.wuc = wuc;
    }

    /**
     * @return the rutaAbsoluta
     */
    public String getRutaAbsoluta() {
        return rutaAbsoluta;
    }
    
    public String getImgTag(){
        return "<img src=\""+ this.rutaAbsoluta +"\"/>";
    }
    
}
