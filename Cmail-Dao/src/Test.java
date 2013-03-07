
import org.cmail.rehabilitacion.modelo.seguridad.Usuario;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class Test {
    
    public static void main(String[] args){
        //from Usuario where nombre = 'admin'
        
        System.out.println(QueryBuilder.from(Usuario.class).where(new Propertie("nombre", "=", "admin")).hql());
        
        //from Usuario where nombre != 'admin'
        System.out.println(QueryBuilder.from(Usuario.class).where(new Propertie("nombre", "!=", "admin")).hql());
        
        //from Usuario where nombre = 'admin'
        System.out.println(QueryBuilder.from(Usuario.class).where(new Propertie("nombre", "=", "admin")).hql());
        
        //from Usuario where nombre = 'admin' and estado = true and clave='xxx'
        System.out.println(QueryBuilder.from(Usuario.class).where(new Propertie("nombre", "=", "admin").and(new Propertie("estado",true)).and(new Propertie("clave","xxx"))).hql());
        
        //from Usuario where nombre = 'admin' or nombre = 'danny' and estado = true and clave='xxx'
        System.out.println(QueryBuilder.from(Usuario.class).where(new Propertie("nombre", "=", "admin").or(new Propertie("nombre", "danny")).and(new Propertie("estado",true)).and(new Propertie("clave","xxx"))).hql());
        
        System.out.println(QueryBuilder.from(Usuario.class).where(new Propertie("nombre", "=", "admin").or(new Propertie("nombre", "danny").or(new Propertie("nombre", "danny"))).and(new Propertie("estado",true)).and(new Propertie("clave","xxx"))).hql());
        
        //System.out.println(QueryBuilder.from(Usuario.class).where(new Propertie("nombre", "=", "admin").or(new Propertie("nombre", "danny").or(new Propertie("nombre", "danny"))).and(new Propertie("estado",true)).and(new Propertie("clave","xxx"))).list());
    }
            
    
}
