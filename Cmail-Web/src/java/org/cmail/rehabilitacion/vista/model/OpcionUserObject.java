
package org.cmail.rehabilitacion.vista.model;


import javax.swing.tree.DefaultMutableTreeNode;
import org.cmail.rehabilitacion.modelo.seguridad.Opcion;

/**
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class OpcionUserObject extends NodeUserObject {

    private Opcion opcion;
    private boolean root;

    public OpcionUserObject(DefaultMutableTreeNode defaultMutableTreeNode, boolean isRoot) {
        super(defaultMutableTreeNode);
        
        this.root = isRoot;
    }

    public Opcion getOpcion() {
        return opcion;
    }

    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    @Override
    public String getText() {
        
        if(root){
            return "Lista de opciones";
        }
        
        if(this.opcion.getPerfiles().isEmpty()){       
            return this.opcion.getEtiqueta();
        }else{
            return this.opcion.getEtiqueta() + " - " +this.opcion.getPerfiles().toString();
        }
        
    }

    @Override
    public String getTooltip() {
        return this.opcion.getPerfiles().toString();
    }

    /**
     * @return the root
     */
    public boolean isRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(boolean root) {
        this.root = root;
    }

    
}
