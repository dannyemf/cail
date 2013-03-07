
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.cmail.rehabilitacion.dao.HibernateSessionFactory;
import org.hibernate.Query;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class CQuery<T> {
    
    private Class<T> clase;
    private Propertie[] where;
    
    private List<Object> valores = new ArrayList<Object>();

    public CQuery(Class<T> clase) {
        this.clase = clase;
    }
    
    public CQuery<T> where(Propertie... properties){
        where = properties;
        return this;
    }
    
    public Query query(){
        Query q = HibernateSessionFactory.getSession().createQuery(hql());
        
        int i = 0;
        for (Iterator<Object> it = valores.iterator(); it.hasNext();) {
            Object object = it.next();
            q.setParameter(i, object);
            i++;
        }        
        
        return q;
    }
    
    public String hql(){
        String f = "from " + clase.getSimpleName() + "";
        
        String w = " where ";
        for (Propertie p : where) {
            w += "" + p.hql(valores) + "";
        }
        
        w = " where ".equals(w) ? "" : w;
        return f + w;
    }
    
    public void groupBy(){
        
    }
    
    public List<T> list(){
        try {
            return query().list();
        } catch (Exception e) {
            return new ArrayList<T>();
        }
    }
    
    public T first(){
        try {
            List<T> l = query().list();
            if(l.isEmpty()){
                return null;
            }else{
                return l.get(0);
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public T uniqueResult(){
        try {
            return (T)query().uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    
}
