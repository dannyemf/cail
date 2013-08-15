
package org.cmail.rehabilitacion.vista.model;

import com.icesoft.faces.component.tree.IceUserObject;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *  Clase de modelo para la vista para presentar las opciones de menú que contengan sub opciones.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class NodeUserObject extends IceUserObject {

    public NodeUserObject(DefaultMutableTreeNode defaultMutableTreeNode) {
        super(defaultMutableTreeNode);

        setLeafIcon("tree_document.gif");
        setBranchContractedIcon("tree_folder_closed.gif");
        setBranchExpandedIcon("tree_folder_open.gif");
        setExpanded(true);
    }

}
