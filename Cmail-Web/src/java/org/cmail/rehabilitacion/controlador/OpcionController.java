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
 * Controlador de las opciones de menú. Permite crear, editar, eliminar y
 * asignar los perfiles a las opciones de menú.
 *
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
@ManagedBean(name = Constantes.MB_OPCION)
@ViewScoped
public class OpcionController extends Controller {

    private DefaultTreeModel treeModelOpciones = null;
    private OpcionUserObject selectedOpcionUserObject = null;
    private OpcionUserObject rootOpcionUserObject;
    protected Effect valueChangeEffect;
    private boolean disableEdit = true;
    private boolean disableElim = true;
    private boolean dialogoConfirmPerfiles;
    private Set<Perfil> perfilesHijos = new HashSet<Perfil>();

    /**
     * Constructor por defecto
     */
    public OpcionController() {
        log().info("Iniciar opcion controller..");
        valueChangeEffect = new Highlight("#fda505");
        valueChangeEffect.setFired(true);
    }

    /**
     * Muestra la página de edición para la opción indicada e inicializa la
     * auditoría.
     *
     * @param opcion la opción
     */
    public void editar(Opcion opcion) {
        FacesUtils.getSessionBean().setOpcionEdicion(opcion);
        listarPerfilesOpcion(opcion);
        initAudit(opcion);

        dialogoConfirmPerfiles = false;
    }

    /**
     * @return the valueChangeEffect
     */
    public Effect getValueChangeEffect() {
        return valueChangeEffect;
    }

    /**
     * Método invocado por getModel() cuando el árbol es null, es decir crea el
     * árbol de las opciones.
     */
    public void crearTree() {
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

    /**
     * Método recursivo para crear las subramas del árbol de opciones.
     *
     * @param opcion la opción actual
     * @param node el nodo actual del árbol
     */
    public void crearSubTree(Opcion opcion, DefaultMutableTreeNode node) {
        List<Opcion> lst = new OpcionServicio().obtenerOpciones(opcion);
        for (Iterator<Opcion> it = lst.iterator(); it.hasNext();) {
            Opcion o = it.next();
            DefaultMutableTreeNode branchNode = crearNodo(o, node);
            crearSubTree(o, branchNode);
        }
    }

    /**
     * Método para crear un nodo para el árbol para la opción indicada.
     *
     * @param opcion la opción
     */
    public void crearNodo(Opcion opcion) {
        try {
            DefaultMutableTreeNode nodo = selectedOpcionUserObject.getWrapper();
            crearNodo(opcion, nodo);
        } catch (Exception e) {
        }
    }

    /**
     * Actualiza la información del nodo
     *
     * @param opcion la opción
     */
    public void actualizarNodo(Opcion opcion) {
        try {
            //selectedOpcionUserObject.setText(opcion.getEtiqueta());
        } catch (Exception e) {
        }
    }

    /**
     * Crea un nodo hijo para el nodo indicado y la opción actual.
     *
     * @param opcion la opción
     * @param node el nodo padre
     * @return el nodo
     */
    private DefaultMutableTreeNode crearNodo(Opcion opcion, DefaultMutableTreeNode node) {
        DefaultMutableTreeNode branchNode = new DefaultMutableTreeNode();
        OpcionUserObject branchObject = new OpcionUserObject(branchNode, false);
        //branchObject.setText(opcion.getEtiqueta());
        branchObject.setOpcion(opcion);
        branchNode.setUserObject(branchObject);
        node.add(branchNode);
        return branchNode;
    }

    /**
     * @return the model
     */
    public DefaultTreeModel getModel() {
        if (treeModelOpciones == null) {
            crearTree();
        }
        return treeModelOpciones;
    }

    /**
     * @return the selectedOpcionUserObject
     */
    public OpcionUserObject getSelectedNodeObject() {
        return selectedOpcionUserObject;
    }

    /**
     * @return the disableEdit
     */
    public boolean isDisableEdit() {
        return disableEdit;
    }

    /**
     * @return the disableElim
     */
    public boolean isDisableElim() {
        return disableElim;
    }

    /**
     * Fija el nodo actual seleccionado y lanza un el evento para resaltar la
     * información del nuevo nodo
     *
     * @param selectedNodeObject el nodo
     */
    public void setSelectedNodeObject(OpcionUserObject selectedNodeObject) {
        this.selectedOpcionUserObject = selectedNodeObject;
        valueChangeEffect.setFired(false);
    }

    /**
     * Evento invocado al presionar el vínculo editar.
     *
     * @param evt el evento
     */
    public void eventoEditar(ActionEvent evt) {
        if (selectedOpcionUserObject != null && selectedOpcionUserObject.isRoot() == false) {
            Opcion opcion = selectedOpcionUserObject.getOpcion();
            new OpcionServicio().refrescar(opcion);
            editar(opcion);
            FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_OPCION);
        }
    }

    /**
     * Evento invocado al presionar el botón nuevo.
     *
     * @param evt el evento
     */
    public void eventoNuevo(ActionEvent evt) {
        Opcion opcion = new Opcion();
        if (selectedOpcionUserObject == null || (selectedOpcionUserObject != null && selectedOpcionUserObject.isRoot())) {
            opcion.setPadre(null);
        } else {
            opcion.setPadre(selectedOpcionUserObject.getOpcion());
        }
        editar(opcion);
        FacesUtils.getMenuController().redirectApp(Constantes.VW_EDT_OPCION);
    }

    /**
     * Evento invocado al presionar el vínculo eliminar.
     *
     * @param evt el evento
     */
    public void eventoEliminar(ActionEvent evt) {
        if (selectedOpcionUserObject != null && selectedOpcionUserObject.isRoot() == false) {
            OpcionServicio opServicio = new OpcionServicio();
            initAudit(selectedOpcionUserObject.getOpcion());
            boolean b = opServicio.eliminar(selectedOpcionUserObject.getOpcion());
            showMessageDeleted(b, selectedOpcionUserObject.getOpcion().getEtiqueta());

            if (b) {
                ((DefaultMutableTreeNode) selectedOpcionUserObject.getWrapper().getParent()).remove(selectedOpcionUserObject.getWrapper());
                //selectedOpcionUserObject = (OpcionUserObject)((DefaultMutableTreeNode)selectedOpcionUserObject.getWrapper().getParent()).getUserObject();
                selectedOpcionUserObject = null;
                valueChangeEffect.setFired(false);
            }
        }
    }

    /**
     * Evento invocado por el árbol de opciones al selecionar un nodo
     *
     * @param evt el evento
     */
    public void eventoSelect(ActionEvent evt) {
        String id = FacesUtils.getRequestParameter("opcionId");
        log().info("Load id: " + id);
        DefaultMutableTreeNode node = findTreeNode(id);

        disableEdit = true;
        disableElim = true;

        if (node != null) {
            selectedOpcionUserObject = (OpcionUserObject) node.getUserObject();
            if (selectedOpcionUserObject.isRoot() == false) {
                disableEdit = false;
                if (selectedOpcionUserObject.getWrapper().children().hasMoreElements() == false) {
                    disableElim = false;
                }
            }
        } else {
            selectedOpcionUserObject = rootOpcionUserObject;
        }
        valueChangeEffect.setFired(false);
    }

    /**
     * Busca el nodo del árbol en base el id del nodo
     *
     * @param nodeId el id del nodo
     * @return el nodo
     */
    private DefaultMutableTreeNode findTreeNode(String nodeId) {
        DefaultMutableTreeNode rootNode = rootOpcionUserObject.getWrapper();

        DefaultMutableTreeNode node;
        OpcionUserObject tmp;
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

    /**
     * Lista los perfiles que no tiene la opción actual con la finalidad de
     * permitir al usuario agregarlos.
     *
     * @param opcion la opción
     */
    public void listarPerfilesOpcion(Opcion opcion) {
        List<Perfil> lst = null;

        if (opcion.getPadre() == null) {
            lst = new PerfilServicio().listarTodos();
        } else {
            lst = new ArrayList<Perfil>(opcion.getPadre().getPerfiles());
        }

        // Seleccionamos los perfiles de esta opcion
        for (Iterator<Perfil> it = opcion.getPerfiles().iterator(); it.hasNext();) {
            it.next().setSeleccionado(true);
        }

        // Agregamos los perfiles faltantes
        for (Iterator<Perfil> it = lst.iterator(); it.hasNext();) {
            Perfil p = it.next();
            if (opcion.getPerfiles().contains(p) == false) {
                p.setSeleccionado(false);
                opcion.getPerfiles().add(p);
            }
        }
    }

    /**
     * Método invocado por el evento guardar.
     */
    private void guardar() {
        log().info("Guardando opcion...");
        OpcionServicio srv = new OpcionServicio();
        boolean isNew = getOpcionEdicion().getId().longValue() == -1 ? true : false;
        log().info("Opcion is new: " + isNew);
        boolean b = srv.guardar(getOpcionEdicion());
        showMessageSaved(b);

        if (b) {
            if (isNew) {
                log().info("Crear nodo: " + getOpcionEdicion().getId());
                FacesUtils.getOpcionController().crearNodo(getOpcionEdicion());
            } else {
                log().info("Actualizar nodo: " + getOpcionEdicion().getId());
                FacesUtils.getOpcionController().actualizarNodo(getOpcionEdicion());
            }
            FacesUtils.getMenuController().redirectApp(Constantes.VW_ADM_OPCION);
        }
    }

    /**
     * Evento invocado al presionar el botón guardar en la ventana de edición de
     * la opción.
     *
     * @param evt el evento
     */
    public void eventoGuardar(ActionEvent evt) {
        OpcionServicio srv = new OpcionServicio();
        perfilesHijos = srv.verificarPerfilesHijos(getOpcionEdicion());

        if (perfilesHijos.isEmpty()) {
            guardar();
        } else {
            dialogoConfirmPerfiles = true;
        }
    }

    /**
     * Evento invocado desde el diálogo de confirmación para remover los
     * perfiles en cascada.
     *
     * @param evt el evento
     */
    public void eventoGuardarConfirmPerfiles(ActionEvent evt) {
        dialogoConfirmPerfiles = false;
        guardar();
    }

    /**
     * Evento invocado desde el diálogo de confirmación rechanzado remover los
     * perfiles en cascada.
     *
     * @param evt el evento
     */
    public void eventoGuardarConfirmPerfilesCancel(ActionEvent evt) {
        dialogoConfirmPerfiles = false;
    }

    /**
     * Evento invocado al presionar el botón cancelar en la edición de una
     * opción.
     *
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

    /**
     * @return the dialogoConfirmPerfiles
     */
    public boolean isDialogoConfirmPerfiles() {
        return dialogoConfirmPerfiles;
    }

    /**
     * @return the perfilesHijos
     */
    public Set<Perfil> getPerfilesHijos() {
        return perfilesHijos;
    }
}
