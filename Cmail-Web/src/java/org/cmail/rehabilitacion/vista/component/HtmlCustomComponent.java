/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.vista.component;

import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author Usuario
 */
@FacesComponent(value = "HtmlCustomComponent")
public class HtmlCustomComponent extends UIComponentBase {
 
    @Override
    public String getFamily() {
        return null;
    }
 
    @Override
    public void encodeAll(FacesContext context) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        writer.startElement("div", this);
        writer.writeAttribute("style", "color : red", null);
        final String message = (String) this.getAttributes().get("custommsg");
        if (null == message) {
            writer.writeText("Hola adictosaltrabajo, hoy es: "
                    + new java.util.Date(), null);
        } else {
            writer.writeText(message, null);
        }
        writer.endElement("div");
    }
 
}
