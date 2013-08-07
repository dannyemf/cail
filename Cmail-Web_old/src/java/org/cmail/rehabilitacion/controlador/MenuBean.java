/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.controlador;

import java.util.Date;
import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import org.icefaces.ace.component.menuitem.MenuItem;
import org.icefaces.ace.model.DefaultMenuModel;
       
/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class MenuBean {

    private DefaultMenuModel model = null;
    private String mensaje;
    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
        
        model = new DefaultMenuModel();
        
        MenuItem mi = new MenuItem();
        mi.setId("menuitem-test-action");
        mi.setValue("Action");
        model.addMenuItem(mi);
        
        MenuItem mia = new MenuItem();
        mia.setId("menuitem-test-actionlistener");
        mia.setValue("ActionListener");
        model.addMenuItem(mia);
        
        //Action a mi
        /*MethodExpression actionExpression = getActionMethodExpression("#{menuBean.menuAction}");
        mi.setActionExpression(actionExpression);*/
        
        //ActionListener a mia
        MethodExpression actionListenerExpression = getActionListenerMethodExpression("#{menuBean.menuActionListener}");
        MethodExpressionActionListener meActionListener = new MethodExpressionActionListener(actionListenerExpression);
        mia.addActionListener(meActionListener);       
       
    }
    
    private MethodExpression getActionMethodExpression(String name)
    {
       Class[] argtypes = new Class[0];
       //argtypes[0] = ActionEvent.class;
       FacesContext facesCtx = FacesContext.getCurrentInstance();
       ELContext elContext = facesCtx.getELContext();
       return facesCtx.getApplication().getExpressionFactory().createMethodExpression(elContext, name, null, argtypes);
    }
    
    private MethodExpression getActionListenerMethodExpression(String name)
    {
       Class[] argtypes = new Class[1];
       argtypes[0] = ActionEvent.class;
       FacesContext facesCtx = FacesContext.getCurrentInstance();
       ELContext elContext = facesCtx.getELContext();
       return facesCtx.getApplication().getExpressionFactory().createMethodExpression(elContext, name, null, argtypes);
    }
    
    public String menuAction(){
        mensaje = "Action Activado: " + new Date().toString();
        return null;
    }
    
    public void menuActionListener(ActionEvent ev){
        mensaje = "ActionListener Activado: " + new Date().toString();
    }

    /**
     * @return the model
     */
    public DefaultMenuModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(DefaultMenuModel model) {
        this.model = model;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
