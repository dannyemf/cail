package org.cmail.rehabilitacion.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cmail.rehabilitacion.dao.hql.KQuery;
import org.cmail.rehabilitacion.modelo.sira.ImagenWeb;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.EnumType;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Usuario
 */
public class MainTest {

    public List<Object> getByPropiedad(Class clase, String propiedad, Object valor) {
        try {
            Criteria cri = HibernateSessionFactory.getSession().createCriteria(clase);
            cri.add(Restrictions.eq(propiedad, valor));
            return cri.list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object> getByPropiedad2(Class clase, String propiedad, Object valor) {
        try {
            Criteria cri = HibernateSessionFactory.getSession().createCriteria(clase);            
            cri.add(Restrictions.le(propiedad, valor));
            return cri.list();
        } catch (Exception e) {
            return null;
        }
    }

    //metodo cambiado de load(...,...) a get(...,...)
    public Object getById(Class clase, Long id) {
        try {
            return (Object) HibernateSessionFactory.getSession().get(clase, id);
        } catch (Exception e) {
            return null;
        }
    }
    
    //Ejemplo de Validadores de Correo y CIF
 
//metodo para validar correo
public static boolean validaCorreo(String c){ 
//asignamos la expresion
   Pattern p = Pattern.compile("^[a-zA-Z0-9_-]{2,15}@[a-zA-Z0-9_-]{2,15}.[a-zA-Z]{2,4}(.[a-zA-Z]{2,4})?$");   
//comparamos con nuestro valor
   Matcher m = p.matcher(c);   
//si el correo es correcto devuelve TRUE o de lo contrario FALSE  
return m.matches();
 }
 
public static boolean validaCif(String cif){
   Pattern p2 = Pattern.compile("^[a-zA-Z]{1}[0-9]{8}$");
   Matcher m2 = p2.matcher(cif);
return m2.matches();
}

    public static void main(String[] aas) {
       List<ImagenWeb> lst = KQuery.from(ImagenWeb.class).list();
        for (Iterator<ImagenWeb> it = lst.iterator(); it.hasNext();) {
            ImagenWeb m = it.next();
            System.out.println(m.getTipo());            
        }
    }
}
