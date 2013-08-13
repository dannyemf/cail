package org.cmail.rehabilitacion.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * Clase para manejar la sesión de hibernate contra la base de datos, es decir maneja la conexión de la base de datos.
 * 
 * @author Noralma Vera
 * @author Doris Viñamagua
 * @version 1.0
 */
public class HibernateSessionFactory {

    /**
     * Ubicación del archivo de configuración de hibernate
     */
    private static final String CONFIG_FILE_LOCATION = "org/cmail/rehabilitacion/dao/mapeo/hibernate.cfg.xml";
    
    /**
     * Hilo local en ejecución, para manejar una única instancia de conexión con la base de datos.
     */
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    
    /**
     * Objeto de configuración actual de hibernate.
     */
    private static final Configuration configuration = new Configuration();
    
    /**
     * Fábrica de sesiones de hibernate.
     */
    private static org.hibernate.SessionFactory sessionFactory;    

    /**
     * Bloque de inicialización automática de la sesión de hibernate cuando se invoque por primera vez a esta clase.
     */
    static {
        try {
            configuration.setInterceptor(new AuditInterceptor());
            configuration.configure(CONFIG_FILE_LOCATION);            
            sessionFactory = configuration.buildSessionFactory();                 
        } catch (Exception e) {
            System.err.println("%%%% Error al crear la SessionFactory %%%%");            
            System.err.println(e);
        }
    }

    /**
     * Constructor privado por defecto, es decir no se debe crear instancias de esta clase, sino usar sus métodos estáticos.
     */
    private HibernateSessionFactory() {        
    }
        
     
    /**
     * Retorna la sesión de hibernate albergada en el hilo local (hilo en ejecución, mediante inicialización perezosa).     
     * 
     * @return la sesión de hibernate
     * @throws HibernateException 
     */
    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();

        if (session == null || !session.isOpen()) {
            if (sessionFactory == null) {
                rebuildSessionFactory();
            }
            session = (sessionFactory != null) ? sessionFactory.openSession() : null;
            threadLocal.set(session);
        }

        return session;
    }    

    /**
     * Reconstruye la fábrica de sesiones
     */
    public static void rebuildSessionFactory() {
        try {
            configuration.configure(CONFIG_FILE_LOCATION);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("%%%% Error al crear la SessionFactory %%%%");
            System.err.println(e);
        }
    }

    /**
     *  Cierra la instancia de la sesión hibernate.
     *
     *  @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

    /**
     * Retorna la fábrica de sesiones de hibernate.
     * 
     * @return la fábrica de sesiones
     */
    public static org.hibernate.SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}