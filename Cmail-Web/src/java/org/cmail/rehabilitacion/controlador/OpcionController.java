package org.cmail.rehabilitacion.controlador;

import com.icesoft.faces.context.effects.Effect;
import com.icesoft.faces.context.effects.Highlight;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.cmail.rehabilitacion.modelo.core.Constantes;
import org.cmail.rehabilitacion.modelo.seguridad.Opcion;
import org.cmail.rehabilitacion.modelo.seguridad.Perfil;
import org.cmail.rehabilitacion.servicio.OpcionServicio;
import org.cmail.rehabilitacion.servicio.PerfilServicio;
import org.cmail.rehabilitacion.vista.model.OpcionUserObject;
import org.cmail.rehabilitacion.vista.util.FacesUtils;

/**
 * Controlador de las opciones de menú.
 * Permite crear, editar, eliminar y asignar los perfiles a las opciones de menú.
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_OPCION)
@ViewScoped
public class OpcionController  extends Controller {
                         
    
    private DefaultTreeModel treeModelOpciones = null;  
    private OpcionUserObject selectedOpcionUserObject = null;
    private OpcionUserObject rootOpcionUserObject;
    
    protected Effect valueChangeEffect;    
    private boolean disableEdit = true;
    private boolean disableElim = true;    
    private boolean dialogoConfirmPerfiles;
    private Set<Perfil> perfilesHijos = new HashSet<Perfil>();
    
    /**Constructor por defecto*/
    public OpcionController() {          
        log().info("Iniciar opcion controller..");                
        valueChangeEffect = new Highlight("#fda505");
        valueChangeEffect.setFired(true);
    }
    
    public void editar(Opcion opcion){        
        FacesUtils.getSessionBean().setOpcionEdicion(opcion);       
        listarPerfilesOpcion(opcion);
        initAudit(opcion);
        
        dialogoConfirmPerfiles = false;
    }

    public Effect getValueChangeEffect() {
        return valueChangeEffect;
    }
        
    public void crearTree(){
         // create root node with its children expanded
        DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode();
        rootOpcionUserObject = new OpcionUserObject(rootTreeNode, true);
        
        Opcion op = new Opcion();
        op.setEtiqueta("--Ninguno--");        
        rootOpcionUserObject.setOpcion(op);
        rootTreeNode.setUserObject(rootOpcionUserObject);
        selectedOpcionUserObject = rootOpcionUserObject;
        
        treeModelOpciones = new DefaultTreeModel(rootTreeNode);
        
        List<Opcion> lst = new OpcionServicio().obtenerOpciones(null);
        for (Iterator<Opcion> it = lst.iterator(); it.hasNext();) {
            Opcion o = it.next();            
            DefaultMutableTreeNode branchNode = crearNodo(o, rootTreeNode);
            crearSubTree(o, branchNode);
        }
    }
    
    public void crearSubTree(Opcion opcion, DefaultMutableTreeNode node){        
        List<Opcion> lst = new OpcionServicio().obtenerOpciones(opcion);                        
        for (Iterator<Opcion> it = lst.iterator(); it.hasNext();) {
            Opcion o = it.next();            
            DefaultMutableTreeNode branchNode = crearNodo(o, node);
            crearSubTree(o, branchNode);
        }        
    }     
    
    public void crearNodo(Opcion opcion){
        try {
            DefaultMutableTreeNode nodo = selectedOpcionUserObject.getWrapper();
            crearNodo(opcion, nodo);
        } catch (Exception e) {
        }
    }
    
    public void actualizarNodo(Opcion opcion){
        try {
            //selectedOpcionUserObject.setText(opcion.getEtiqueta());
        } catch (Exception e) {
        }
    }
    
    private DefaultMutableTreeNode crearNodo(Opcion opcion, DefaultMutableTreeNode node){
        DefaultMutableTreeNode branchNode = new DefaultMutableTreeNode();        
        OpcionUserObject branchObject = new OpcionUserObject(branchNode, false);                        
        //branchObject.setText(opcion.getEtiqueta());
        branchObject.setOpcion(opcion);
        branchNode.setUserObject(branchObject);
        node.add(branchNode);        
        return branchNode;
    }

    public DefaultTreeModel getModel() {
        if(treeModelOpciones == null){
            crearTree();
        }
        return treeModelOpciones;
    }
    
    public OpcionUserObject getSelectedNodeObject() {
        return selectedOpcionUserObject;
    }

    public boolean isDisableEdit() {
        return disableEdit;
    }

    public boolean isDisableElim() {
        return disableElim;
    }        

    public void setSelectedNodeObject(OpcionUserObject selectedNodeObject) {
        this.selectedOpcionUserObject = selectedNodeObject;
        valueChangeEffect.setFired(false);
    }   

    /**
     * Evento invocado al presionar el vínculo editar.
     * @param evt el evento
     */
    public void eventoEditar(ActionEvent evt) {
       if(selectedOpcionUserObject != null && selectedOpcionUserObject.isRoot() == false){
           Opcion opcion = selectedOpcionUserObject.getOpcion();    
           new OpcionServicio().refrescar(opcion);                      
           editar(opcion);           
           FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_OPCION);
       }
    }

    /**
     * Evento invocado al presionar el botón nuevo.
     * @param evt el evento
     */
    public void eventoNuevo(ActionEvent evt) {        
        Opcion opcion = new Opcion();        
        if(selectedOpcionUserObject == null || (selectedOpcionUserObject != null && selectedOpcionUserObject.isRoot())){            
            opcion.setPadre(null);
        }else{
            opcion.setPadre(selectedOpcionUserObject.getOpcion());
        }
        editar(opcion);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_OPCION);
    }   

    /**
     * Evento invocado al presionar el vínculo eliminar.
     * @param evt el evento
     */
    public void eventoEliminar(ActionEvent evt) {
        if(selectedOpcionUserObject != null && selectedOpcionUserObject.isRoot() == false){
            OpcionServicio opServicio = new OpcionServicio();
            initAudit(selectedOpcionUserObject.getOpcion());
            boolean b = opServicio.eliminar(selectedOpcionUserObject.getOpcion());
            showMessageDeleted(b, selectedOpcionUserObject.getOpcion().getEtiqueta());
            
            if (b) {
                ((DefaultMutableTreeNode)selectedOpcionUserObject.getWrapper().getParent()).remove(selectedOpcionUserObject.getWrapper());
                //selectedOpcionUserObject = (OpcionUserObject)((DefaultMutableTreeNode)selectedOpcionUserObject.getWrapper().getParent()).getUserObject();
                selectedOpcionUserObject = null;
                valueChangeEffect.setFired(false);
            }
        }        
    }

    
    
    public void eventoSelect(ActionEvent evt) {
        String id = FacesUtils.getRequestParameter("opcionId");
        log().info("Load id: "+id);
        DefaultMutableTreeNode node = findTreeNode(id);
        
        disableEdit = true;
        disableElim = true;
            
        if(node != null){
            selectedOpcionUserObject = (OpcionUserObject)node.getUserObject();
            if(selectedOpcionUserObject.isRoot() == false){
                disableEdit = false;
                if (selectedOpcionUserObject.getWrapper().children().hasMoreElements() == false) {
                    disableElim = false;
                }
            }
        }else{
            selectedOpcionUserObject = rootOpcionUserObject;            
        }
        valueChangeEffect.setFired(false);
    }

    
    
    private DefaultMutableTreeNode findTreeNode(String nodeId) {
        DefaultMutableTreeNode rootNode = rootOpcionUserObject.getWrapper();
        
        DefaultMutableTreeNode node; OpcionUserObject tmp;
        Enumeration nodes = rootNode.depthFirstEnumeration();
        while (nodes.hasMoreElements()) {
            node = (DefaultMutableTreeNode) nodes.nextElement();
            tmp = (OpcionUserObject) node.getUserObject();            
            if (((Long.parseLong(nodeId)) == tmp.getOpcion().getId().longValue())) {
                return node;
            }
        }
        return null;
    }         
    
    
     public void listarPerfilesOpcion(Opcion op) {
        List<Perfil> lst = null;
        
        if(op.getPadre() == null){
            lst = new PerfilServicio().listarTodos();
        }else{
            lst = new ArrayList<Perfil>(op.getPadre().getPerfiles());
        }        
        
        // Seleccionamos los perfiles de esta opcion
        for (Iterator<Perfil> it = op.getPerfiles().iterator(); it.hasNext();) 
            it.next().setSeleccionado(true);            
        
        // Agregamos los perfiles faltantes
        for (Iterator<Perfil> it = lst.iterator(); it.hasNext();) {
            Perfil p = it.next();
            if(op.getPerfiles().contains(p) == false){
                p.setSeleccionado(false);
                op.getPerfiles().add(p);
            }
        }        
    }   
    
    /**
     * <p>
     * Evalua el estado on/off de los perfiles de Usuario
     * </p>
     * @return 
     */
    private void guardar(){
        log().info("Guardando opcion...");
        OpcionServicio srv = new OpcionServicio();
        boolean isNew = getOpcionEdicion().getId().longValue() == -1 ? true : false;
        log().info("Opcion is new: " + isNew);
        boolean b = srv.guardar(getOpcionEdicion());
        showMessageSaved(b);
        
        if (b) {
            if(isNew){
                log().info("Crear nodo: " + getOpcionEdicion().getId());
                FacesUtils.getOpcionController().crearNodo(getOpcionEdicion());
            }else{
                log().info("Actualizar nodo: " + getOpcionEdicion().getId());
                FacesUtils.getOpcionController().actualizarNodo(getOpcionEdicion());
            }
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_OPCION);
        }
    }        
    
    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición de la opción.
     * @param evt el evento
     */
    public void eventoGuardar(ActionEvent evt) {
        OpcionServicio srv = new OpcionServicio();
        perfilesHijos = srv.verificarPerfilesHijos(getOpcionEdicion());
        
        if(perfilesHijos.isEmpty()){
            guardar();
        }else{
            dialogoConfirmPerfiles = true;
        }
    }
    
    public void eventoGuardarConfirmPerfiles(ActionEvent evt) {
        dialogoConfirmPerfiles = false;
        guardar();        
    }
    
    public void eventoGuardarConfirmPerfilesCancel(ActionEvent evt) {
        dialogoConfirmPerfiles = false;
    }

    /**
     * Evento invocado al presionar el botón cancelar en la edición de una opción.
     * @param evt el evento
     */
    public void eventoCancelar(ActionEvent evt) {
        new OpcionServicio().refrescar(getOpcionEdicion());
        FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_OPCION);
    }
    
    
    
    /**
     * @return the opcionEdicion
     */
    public Opcion getOpcionEdicion() {
        return FacesUtils.getSessionBean().getOpcionEdicion();
    }
    
     public boolean isDialogoConfirmPerfiles() {
        return dialogoConfirmPerfiles;
    }        

    public Set<Perfil> getPerfilesHijos() {
        return perfilesHijos;
    }
    
}
